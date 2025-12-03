using Newtonsoft.Json;
using System;
using System.Globalization;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller
{
    public class RetiroController
    {
        private readonly FrmRetiro _view;
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl;

        public RetiroController(FrmRetiro view)
        {
            _view = view ?? throw new ArgumentNullException(nameof(view));
            _httpClient = ApiClient.Client;
            _apiBaseUrl = ApiClient.ApiBaseUrl; // debería ser: https://localhost:7222/api/

            _view.RetirarClicked += OnRetirarClicked;
            _view.VolverMenuClicked += OnVolverMenuClicked;
            _view.SalirClicked += OnSalirClicked;
        }

        private async void OnRetirarClicked(object sender, EventArgs e)
        {
            await RealizarRetiroAsync();
        }

        private void OnVolverMenuClicked(object sender, EventArgs e)
        {
            _view.Close(); // o navegar al menú principal
        }

        private void OnSalirClicked(object sender, EventArgs e)
        {
            // El form ya hace Application.Exit();
        }

        private async Task RealizarRetiroAsync()
        {
            var cuenta = _view.NumeroCuenta?.Trim();
            var montoText = _view.Monto?.Trim();

            // Validaciones
            if (string.IsNullOrEmpty(cuenta))
            {
                _view.MostrarMensajeError("Por favor, ingrese el número de cuenta.");
                return;
            }

            // Usamos InvariantCulture por si el usuario pone punto
            if (!double.TryParse(montoText, NumberStyles.Any, CultureInfo.InvariantCulture, out var monto) || monto <= 0)
            {
                _view.MostrarMensajeError("Ingrese un monto válido mayor a 0.");
                return;
            }

            try
            {
                // Body EXACTO al schema
                var bodyObj = new { cuenta = cuenta, monto = monto };
                var json = JsonConvert.SerializeObject(bodyObj);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                // Endpoint: /api/Cuenta/retiro
                var url = new Uri(new Uri(_apiBaseUrl), "Cuenta/retiro");

                var resp = await _httpClient.PostAsync(url, content);
                var respJson = await resp.Content.ReadAsStringAsync();

                if (!resp.IsSuccessStatusCode)
                {
                    _view.MostrarMensajeError("No se pudo realizar el retiro. Verifique los datos.");
                    return;
                }

                dynamic result = JsonConvert.DeserializeObject(respJson);

                bool success = result != null && (result.success == true || result.Success == true);

                if (!success)
                {
                    string msgError =
                        result != null && (result.mensaje != null || result.Mensaje != null)
                            ? (string)(result.mensaje ?? result.Mensaje)
                            : "No se pudo realizar el retiro.";
                    _view.MostrarMensajeError(msgError);
                    return;
                }

                string mensaje =
                    result.mensaje != null ? (string)result.mensaje :
                    result.Mensaje != null ? (string)result.Mensaje :
                    "Retiro realizado exitosamente";

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
                    // si el tipo no matchea perfecto, simplemente no mostramos saldo
                }

                _view.MostrarMensajeOk(mensaje + saldoTexto);
            }
            catch (Exception ex)
            {
                _view.MostrarMensajeError("Error al realizar el retiro: " + ex.Message);
            }
        }
    }
}
