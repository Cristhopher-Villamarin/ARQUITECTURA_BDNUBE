using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using Newtonsoft.Json;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller
{
    public class MainController
    {
        private string usuarioActual = null;
        private readonly HttpClient httpClient;
        private readonly string apiBaseUrl;

        public MainController()
        {
            // URL base fija (ajusta el puerto si tu API usa otro)
            var baseUrl = "https://localhost:7222/api/";

            // Asegurar que termine con '/'
            if (!baseUrl.EndsWith("/"))
                baseUrl += "/";

            apiBaseUrl = baseUrl;

            // HttpClient ignorando certificado en desarrollo
            var handler = new HttpClientHandler
            {
                ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true
            };

            httpClient = new HttpClient(handler)
            {
                BaseAddress = new Uri(apiBaseUrl)
            };

            httpClient.DefaultRequestHeaders.Accept.Clear();
            httpClient
                .DefaultRequestHeaders
                .Accept
                .Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
        }

        // ================== SESIÓN ==================

        public string GetUsuarioActual() => usuarioActual;

        public bool IniciarSesion(string username, string password)
        {
            try
            {
                var loginRequest = new { Username = username, Password = password };
                var json = JsonConvert.SerializeObject(loginRequest);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                // Construir URL completa
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

        // ================== OPERACIONES ==================

        /// <summary>
        /// Enruta a depósito / retiro / transferencia según 'tipo'
        /// DEP -> POST cuenta/deposito
        /// RET -> POST cuenta/retiro
        /// TRA -> POST cuenta/transferencia
        /// </summary>
        public bool RealizarDeposito(string cuenta, string monto, string tipo, string cd)
        {
            try
            {
                object request;
                string endpoint;

                switch (tipo)
                {
                    case "DEP": // Depósito
                        request = new { Cuenta = cuenta, Monto = double.Parse(monto) };
                        endpoint = "cuenta/deposito";
                        break;

                    case "RET": // Retiro
                        request = new { Cuenta = cuenta, Monto = double.Parse(monto) };
                        endpoint = "cuenta/retiro";
                        break;

                    case "TRA": // Transferencia
                        request = new { Cuenta = cuenta, Monto = double.Parse(monto), CuentaDestino = cd };
                        endpoint = "cuenta/transferencia";
                        break;

                    default:
                        return false;
                }

                var json = JsonConvert.SerializeObject(request);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                var url = new Uri(new Uri(apiBaseUrl), endpoint);
                var response = httpClient.PostAsync(url, content).Result;

                var responseContent = response.Content.ReadAsStringAsync().Result;
                if (!response.IsSuccessStatusCode)
                    return false;

                var result = JsonConvert.DeserializeObject<dynamic>(responseContent);
                return result != null && result.success == true;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al realizar operación: " + ex.Message);
                return false;
            }
        }

        public bool RealizarRetiro(string cuenta, string monto)
            => RealizarDeposito(cuenta, monto, "RET", null);

        public bool RealizarTransferencia(string cuentaOrigen, string cuentaDestino, string monto)
            => RealizarDeposito(cuentaOrigen, monto, "TRA", cuentaDestino);

        // ================== CONSULTAS ==================

        public string VerMovimientos(string cuenta)
        {
            try
            {
                List<MovimientoModel> movimientos = null;
                CuentaModel cuentaModel = null;

                // Movimientos
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

                // Datos de cuenta
                var cuentaUrl = new Uri(new Uri(apiBaseUrl), $"cuenta/{cuenta}");
                var cuentaResponse = httpClient.GetAsync(cuentaUrl).Result;
                if (cuentaResponse.IsSuccessStatusCode)
                {
                    var cuentaResponseContent = cuentaResponse.Content.ReadAsStringAsync().Result;
                    cuentaModel = JsonConvert.DeserializeObject<CuentaModel>(cuentaResponseContent);
                }

                var resultado = new StringBuilder();

                if (movimientos == null || movimientos.Count == 0)
                {
                    resultado.Append("No se encontraron movimientos para la cuenta: ")
                             .Append(cuenta).AppendLine();
                    return EncloseInBox("MOVIMIENTOS DE LA CUENTA " + cuenta, resultado.ToString());
                }

                if (cuentaModel != null)
                {
                    resultado.AppendFormat("Saldo Actual de la Cuenta: {0:F2}", cuentaModel.DecCuenSaldo)
                             .AppendLine().AppendLine();
                }

                resultado.AppendFormat("{0,-10} {1,-20} {2,-12} {3,-20} {4,-15} {5,-15} {6,-15}",
                        "Nro", "Fecha", "Tipo Mov.", "Descripción", "Importe", "Cta Ref.", "Saldo")
                         .AppendLine();
                resultado.AppendLine(new string('-', 100));

                foreach (var mov in movimientos)
                {
                    var fecha = mov.FechaMovimientoDt?.ToString("dd/MM/yyyy HH:mm")
                                ?? mov.FechaMovimiento ?? "N/A";
                    var cuentaRef = mov.CuentaReferencia ?? "N/A";
                    var tipoDesc = mov.TipoDescripcion ?? "N/A";

                    resultado.AppendFormat("{0,-10} {1,-20} {2,-12} {3,-20} {4,-15:F2} {5,-15} {6,-15:F2}",
                        mov.NumeroMovimiento,
                        fecha,
                        mov.CodigoTipoMovimiento ?? "N/A",
                        tipoDesc,
                        mov.ImporteMovimiento,
                        cuentaRef,
                        mov.Saldo)
                        .AppendLine();
                }

                // Totales
                double totalIngresos = movimientos
                    .Where(m => m.TipoDescripcion != null &&
                                !m.TipoDescripcion.Equals("Retiro", StringComparison.OrdinalIgnoreCase))
                    .Sum(m => m.ImporteMovimiento);

                double totalEgresos = movimientos
                    .Where(m => m.TipoDescripcion != null &&
                                m.TipoDescripcion.Equals("Retiro", StringComparison.OrdinalIgnoreCase))
                    .Sum(m => m.ImporteMovimiento);

                resultado.AppendLine()
                         .AppendLine("RESUMEN:")
                         .AppendFormat("Total Ingresos: {0:F2}", totalIngresos).AppendLine()
                         .AppendFormat("Total Egresos (Retiros): {0:F2}", Math.Abs(totalEgresos)).AppendLine()
                         .AppendFormat("Saldo Neto: {0:F2}", totalIngresos + totalEgresos).AppendLine();

                if (cuentaModel != null)
                {
                    resultado.AppendFormat("Saldo Actual de la Cuenta: {0:F2}", cuentaModel.DecCuenSaldo)
                             .AppendLine();
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
                var url = new Uri(new Uri(apiBaseUrl), $"cuenta/{cuenta}");
                var response = httpClient.GetAsync(url).Result;

                if (!response.IsSuccessStatusCode)
                    return "No se encontraron datos para la cuenta: " + cuenta;

                var responseContent = response.Content.ReadAsStringAsync().Result;
                var cuentaModel = JsonConvert.DeserializeObject<CuentaModel>(responseContent);

                if (cuentaModel == null)
                    return "No se encontraron datos para la cuenta: " + cuenta;

                var resultado = new StringBuilder();
                resultado.AppendFormat("{0,-20} {1,-30}", "Campo", "Valor").AppendLine();
                resultado.AppendLine(new string('-', 50));
                resultado.AppendFormat("{0,-20} {1,-30:F2}", "Saldo", cuentaModel.DecCuenSaldo).AppendLine();
                resultado.AppendFormat("{0,-20} {1,-30}", "Contador Movimientos", cuentaModel.IntCuenContMov).AppendLine();
                resultado.AppendLine(new string('-', 50));

                return EncloseInBox("DATOS DE LA CUENTA " + cuenta, resultado.ToString());
            }
            catch (Exception ex)
            {
                return "Error al obtener los datos de la cuenta: " + ex.Message;
            }
        }

        // ================== UTILIDADES ==================

        private string EncloseInBox(string title, string multilineContent)
        {
            string[] lines = multilineContent
                .Split(new[] { "\r\n", "\r", "\n" }, StringSplitOptions.None);

            int max = title.Length;
            foreach (var l in lines)
                if (l.Length > max) max = l.Length;

            int innerWidth = max;
            string horizontal = new string('-', innerWidth + 2);

            var box = new StringBuilder();
            box.Append('+').Append(horizontal).Append('+').AppendLine();
            box.Append('|').Append(PadCenter(" " + title + " ", innerWidth + 2)).Append('|').AppendLine();
            box.Append('+').Append(horizontal).Append('+').AppendLine();
            foreach (var line in lines)
                box.Append('|').Append(PadRight(" " + line, innerWidth + 2)).Append('|').AppendLine();
            box.Append('+').Append(horizontal).Append('+');

            return box.ToString();
        }

        private string PadRight(string text, int width)
            => text.Length >= width ? text : text.PadRight(width);

        private string PadCenter(string text, int width)
        {
            if (text.Length >= width) return text;
            int total = width - text.Length;
            int left = total / 2;
            int right = total - left;
            return new string(' ', left) + text + new string(' ', right);
        }
    }
}
