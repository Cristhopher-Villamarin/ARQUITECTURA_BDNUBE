using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using CLICON_EUREKA_GR08.ec.edu.monster.model;

namespace CLICON_EUREKA_GR08.ec.edu.monster.controller
{
    public class MainController
    {
        private string usuarioActual = null;
        private readonly HttpClient httpClient;
        private readonly string apiBaseUrl;

        public MainController()
        {
            apiBaseUrl = ConfigurationManager.AppSettings["ApiBaseUrl"] ?? "https://localhost:7222/api";
            
            // Asegurar que la URL base termine con /
            if (!apiBaseUrl.EndsWith("/"))
            {
                apiBaseUrl += "/";
            }
            
            // Configurar HttpClient para desarrollo (ignorar certificados SSL)
            var handler = new System.Net.Http.HttpClientHandler();
            handler.ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true;
            
            httpClient = new HttpClient(handler);
            httpClient.BaseAddress = new Uri(apiBaseUrl);
            httpClient.DefaultRequestHeaders.Accept.Clear();
            httpClient.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
        }

        public string GetUsuarioActual()
        {
            return usuarioActual;
        }

        public bool IniciarSesion(string username, string password)
        {
            try
            {
                var loginRequest = new { Username = username, Password = password };
                var json = JsonConvert.SerializeObject(loginRequest);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                // Construir URL completa manualmente para evitar problemas con BaseAddress
                var url = new Uri(new Uri(apiBaseUrl), "login");
                var response = httpClient.PostAsync(url, content).Result;
                var responseContent = response.Content.ReadAsStringAsync().Result;

                if (response.IsSuccessStatusCode)
                {
                    var result = JsonConvert.DeserializeObject<dynamic>(responseContent);
                    
                    if (result != null && result.success == true)
                    {
                        usuarioActual = username;
                        return true;
                    }
                }
                else
                {
                    // Intentar leer el mensaje de error
                    try
                    {
                        var errorResult = JsonConvert.DeserializeObject<dynamic>(responseContent);
                        if (errorResult != null && errorResult.message != null)
                        {
                            Console.WriteLine($"Error: {errorResult.message}");
                        }
                    }
                    catch { }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error en la autenticación: " + ex.Message);
                Console.WriteLine("Detalles: " + ex.InnerException?.Message);
                Console.WriteLine("Stack Trace: " + ex.StackTrace);
            }
            return false;
        }

        public void CerrarSesion()
        {
            usuarioActual = null;
        }

        public bool RealizarDeposito(string cuenta, string monto, string tipo, string cd)
        {
            try
            {
                object request;
                string endpoint;

                if (tipo == "DEP")
                {
                    request = new { Cuenta = cuenta, Monto = double.Parse(monto) };
                    endpoint = "cuenta/deposito";
                }
                else if (tipo == "RET")
                {
                    request = new { Cuenta = cuenta, Monto = double.Parse(monto) };
                    endpoint = "cuenta/retiro";
                }
                else if (tipo == "TRA")
                {
                    request = new { Cuenta = cuenta, Monto = double.Parse(monto), CuentaDestino = cd };
                    endpoint = "cuenta/transferencia";
                }
                else
                {
                    return false;
                }

                var json = JsonConvert.SerializeObject(request);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                // Construir URL completa manualmente
                var url = new Uri(new Uri(apiBaseUrl), endpoint);
                var response = httpClient.PostAsync(url, content).Result;

                if (response.IsSuccessStatusCode)
                {
                    var responseContent = response.Content.ReadAsStringAsync().Result;
                    var result = JsonConvert.DeserializeObject<dynamic>(responseContent);
                    return result != null && result.success == true;
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al realizar operación: " + ex.Message);
                return false;
            }
            return false;
        }

        public bool RealizarRetiro(string cuenta, string monto)
        {
            return RealizarDeposito(cuenta, monto, "RET", null);
        }

        public bool RealizarTransferencia(string cuentaOrigen, string cuentaDestino, string monto)
        {
            // Usar el endpoint de transferencia del servicio RESTful
            return RealizarDeposito(cuentaOrigen, monto, "TRA", cuentaDestino);
        }

        public string VerMovimientos(string cuenta)
        {
            try
            {
                List<MovimientoModel> movimientos = null;
                CuentaModel cuentaModel = null;

                // Obtener movimientos
                var movimientoRequest = new { Numcuenta = cuenta };
                var movimientoJson = JsonConvert.SerializeObject(movimientoRequest);
                var movimientoContent = new StringContent(movimientoJson, Encoding.UTF8, "application/json");

                var movimientoUrl = new Uri(new Uri(apiBaseUrl), "movimiento");
                var movimientoResponse = httpClient.PostAsync(movimientoUrl, movimientoContent).Result;

                if (movimientoResponse.IsSuccessStatusCode)
                {
                    var movimientoResponseContent = movimientoResponse.Content.ReadAsStringAsync().Result;
                    movimientos = JsonConvert.DeserializeObject<List<MovimientoModel>>(movimientoResponseContent);
                }

                // Obtener datos de cuenta
                var cuentaUrl = new Uri(new Uri(apiBaseUrl), $"cuenta/{cuenta}");
                var cuentaResponse = httpClient.GetAsync(cuentaUrl).Result;
                if (cuentaResponse.IsSuccessStatusCode)
                {
                    var cuentaResponseContent = cuentaResponse.Content.ReadAsStringAsync().Result;
                    cuentaModel = JsonConvert.DeserializeObject<CuentaModel>(cuentaResponseContent);
                }

                StringBuilder resultado = new StringBuilder();
                if (movimientos == null || movimientos.Count == 0)
                {
                    resultado.Append("No se encontraron movimientos para la cuenta: ").Append(cuenta).AppendLine();
                    return EncloseInBox("MOVIMIENTOS DE LA CUENTA " + cuenta, resultado.ToString());
                }

                // Mostrar saldo al inicio
                if (cuentaModel != null)
                {
                    resultado.AppendFormat("Saldo Actual de la Cuenta: {0:F2}", cuentaModel.DecCuenSaldo).AppendLine();
                    resultado.AppendLine();
                }

                resultado.AppendFormat("{0,-10} {1,-20} {2,-12} {3,-20} {4,-15} {5,-15} {6,-15}",
                    "Nro", "Fecha", "Tipo Mov.", "Descripción", "Importe", "Cta Ref.", "Saldo").AppendLine();
                resultado.AppendLine("----------------------------------------------------------------------------------------------------");

                foreach (var mov in movimientos)
                {
                    string fecha = mov.FechaMovimientoDt?.ToString("dd/MM/yyyy HH:mm") ?? mov.FechaMovimiento ?? "N/A";
                    string cuentaRef = mov.CuentaReferencia ?? "N/A";
                    string tipoDesc = mov.TipoDescripcion ?? "N/A";

                    resultado.AppendFormat("{0,-10} {1,-20} {2,-12} {3,-20} {4,-15:F2} {5,-15} {6,-15:F2}",
                        mov.NumeroMovimiento,
                        fecha,
                        mov.CodigoTipoMovimiento ?? "N/A",
                        tipoDesc,
                        mov.ImporteMovimiento,
                        cuentaRef,
                        mov.Saldo).AppendLine();
                }

                // Calcular totales
                double totalIngresos = movimientos
                    .Where(m => m.TipoDescripcion != null && !m.TipoDescripcion.Equals("Retiro", StringComparison.OrdinalIgnoreCase))
                    .Sum(m => m.ImporteMovimiento);

                double totalEgresos = movimientos
                    .Where(m => m.TipoDescripcion != null && m.TipoDescripcion.Equals("Retiro", StringComparison.OrdinalIgnoreCase))
                    .Sum(m => m.ImporteMovimiento);

                resultado.AppendLine();
                resultado.AppendLine("RESUMEN:");
                resultado.AppendFormat("Total Ingresos: {0:F2}", totalIngresos).AppendLine();
                resultado.AppendFormat("Total Egresos (Retiros): {0:F2}", Math.Abs(totalEgresos)).AppendLine();
                resultado.AppendFormat("Saldo Neto: {0:F2}", totalIngresos + totalEgresos).AppendLine();

                // Mostrar saldo actual de la cuenta
                if (cuentaModel != null)
                {
                    resultado.AppendFormat("Saldo Actual de la Cuenta: {0:F2}", cuentaModel.DecCuenSaldo).AppendLine();
                }

                return EncloseInBox("MOVIMIENTOS DE LA CUENTA " + cuenta, resultado.ToString());
            }
            catch (Exception ex)
            {
                return "Error al obtener los movimientos: " + ex.Message;
            }
        }

        public string VerDatosCuenta(string cuenta)
        {
            try
            {
                CuentaModel cuentaModel = null;

                var url = new Uri(new Uri(apiBaseUrl), $"cuenta/{cuenta}");
                var response = httpClient.GetAsync(url).Result;
                if (response.IsSuccessStatusCode)
                {
                    var responseContent = response.Content.ReadAsStringAsync().Result;
                    cuentaModel = JsonConvert.DeserializeObject<CuentaModel>(responseContent);
                }

                if (cuentaModel == null)
                {
                    return "No se encontraron datos para la cuenta: " + cuenta;
                }

                StringBuilder resultado = new StringBuilder();
                resultado.AppendFormat("{0,-20} {1,-30}", "Campo", "Valor").AppendLine();
                resultado.AppendLine("--------------------------------------------------");
                resultado.AppendFormat("{0,-20} {1,-30:F2}", "Saldo", cuentaModel.DecCuenSaldo).AppendLine();
                resultado.AppendFormat("{0,-20} {1,-30}", "Contador Movimientos", cuentaModel.IntCuenContMov).AppendLine();
                resultado.AppendLine("--------------------------------------------------");

                return EncloseInBox("DATOS DE LA CUENTA " + cuenta, resultado.ToString());
            }
            catch (Exception ex)
            {
                return "Error al obtener los datos de la cuenta: " + ex.Message;
            }
        }

        // Utilidades para crear recuadros ASCII
        private string EncloseInBox(string title, string multilineContent)
        {
            string[] lines = multilineContent.Split(new[] { "\r\n", "\r", "\n" }, StringSplitOptions.None);
            int maxLineLength = title.Length;
            foreach (string line in lines)
            {
                if (line.Length > maxLineLength)
                {
                    maxLineLength = line.Length;
                }
            }

            int innerWidth = Math.Max(maxLineLength, title.Length);
            string horizontal = Repeat('-', innerWidth + 2);

            StringBuilder box = new StringBuilder();
            box.Append('+').Append(horizontal).Append("+").AppendLine();
            box.Append('|').Append(PadCenter(" " + title + " ", innerWidth + 2)).Append('|').AppendLine();
            box.Append('+').Append(horizontal).Append("+").AppendLine();
            foreach (string line in lines)
            {
                box.Append('|').Append(PadRight(" " + line, innerWidth + 2)).Append('|').AppendLine();
            }
            box.Append('+').Append(horizontal).Append('+');
            return box.ToString();
        }

        private string Repeat(char ch, int count)
        {
            return new string(ch, count);
        }

        private string PadRight(string text, int width)
        {
            if (text.Length >= width)
            {
                return text;
            }
            return text.PadRight(width);
        }

        private string PadCenter(string text, int width)
        {
            if (text.Length >= width)
            {
                return text;
            }
            int totalPadding = width - text.Length;
            int left = totalPadding / 2;
            int right = totalPadding - left;
            return new string(' ', left) + text + new string(' ', right);
        }
    }
}

