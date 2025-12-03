using System;
using System.Globalization;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller
{
    public class TransferenciaController
    {
        private readonly FrmTransferencia _view;
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl;

        public TransferenciaController(FrmTransferencia view)
        {
            _view = view ?? throw new ArgumentNullException(nameof(view));
            _httpClient = ApiClient.Client;
            _apiBaseUrl = ApiClient.ApiBaseUrl; // ej: https://localhost:7222/api/

            _view.TransferirClicked += OnTransferirClicked;
            _view.VolverMenuClicked += OnVolverMenuClicked;
            _view.SalirClicked += OnSalirClicked;
        }

        private async void OnTransferirClicked(object sender, EventArgs e)
        {
            await RealizarTransferenciaAsync();
        }

        private void OnVolverMenuClicked(object sender, EventArgs e)
        {
            _view.Close(); // o navega al menú principal
        }

        private void OnSalirClicked(object sender, EventArgs e)
        {
            // El form ya llama a Application.Exit();
        }

        private async Task RealizarTransferenciaAsync()
        {
            var cuentaOrigen = _view.CuentaOrigen?.Trim();
            var cuentaDestino = _view.CuentaDestino?.Trim();
            var montoText = _view.Monto?.Trim();

            // === Validaciones ===
            if (string.IsNullOrEmpty(cuentaOrigen))
            {
                _view.MostrarMensajeError("Por favor, ingrese la cuenta origen.");
                return;
            }

            if (string.IsNullOrEmpty(cuentaDestino))
            {
                _view.MostrarMensajeError("Por favor, ingrese la cuenta destino.");
                return;
            }

            if (cuentaOrigen == cuentaDestino)
            {
                _view.MostrarMensajeError("La cuenta origen y destino no pueden ser la misma.");
                return;
            }

            if (!double.TryParse(montoText, NumberStyles.Any, CultureInfo.InvariantCulture, out var monto) || monto <= 0)
            {
                _view.MostrarMensajeError("Ingrese un monto válido mayor a 0.");
                return;
            }

            try
            {
                // Body EXACTO al schema del API
                var bodyObj = new
                {
                    cuenta = cuentaOrigen,
                    monto = monto,
                    cuentaDestino = cuentaDestino
                };

                var json = JsonConvert.SerializeObject(bodyObj);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                // Endpoint: /api/Cuenta/transferencia
                var url = new Uri(new Uri(_apiBaseUrl), "Cuenta/transferencia");

                var resp = await _httpClient.PostAsync(url, content);
                var respJson = await resp.Content.ReadAsStringAsync();

                if (!resp.IsSuccessStatusCode)
                {
                    _view.MostrarMensajeError("No se pudo realizar la transferencia. Verifique los datos.");
                    return;
                }

                dynamic result = JsonConvert.DeserializeObject(respJson);

                bool success = result != null && (result.success == true || result.Success == true);

                if (!success)
                {
                    string msgError =
                        result != null && (result.mensaje != null || result.Mensaje != null)
                            ? (string)(result.mensaje ?? result.Mensaje)
                            : "No se pudo realizar la transferencia.";
                    _view.MostrarMensajeError(msgError);
                    return;
                }

                string mensaje =
                    result.mensaje != null ? (string)result.mensaje :
                    result.Mensaje != null ? (string)result.Mensaje :
                    "Transferencia realizada exitosamente";

                string saldoTexto = "";
                try
                {
                    if (result.saldo != null)
                        saldoTexto = $"  Saldo actual: {((double)result.saldo):N2}";
                    else if (result.Saldo != null)
                        saldoTexto = $"  Saldo actual: {((double)result.Saldo):N2}";
                }
                catch
                {
                    // si el tipo viene raro, simplemente no mostramos saldo
                }

                _view.MostrarMensajeOk(mensaje + saldoTexto);
            }
            catch (Exception ex)
            {
                _view.MostrarMensajeError("Error al realizar la transferencia: " + ex.Message);
            }
        }
    }
}
