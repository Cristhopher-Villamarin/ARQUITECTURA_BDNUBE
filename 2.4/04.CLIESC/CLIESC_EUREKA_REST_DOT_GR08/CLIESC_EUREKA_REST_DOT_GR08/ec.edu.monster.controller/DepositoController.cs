using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller
{
    public class DepositoController
    {
        private readonly FrmDeposito _view;
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl;

        public DepositoController(FrmDeposito view)
        {
            _view = view ?? throw new ArgumentNullException(nameof(view));
            _httpClient = ApiClient.Client;
            _apiBaseUrl = ApiClient.ApiBaseUrl;

            _view.DepositarClicked += OnDepositarClicked;
            _view.VolverMenuClicked += OnVolverMenuClicked;
            _view.SalirClicked += OnSalirClicked;
        }

        private async void OnDepositarClicked(object sender, EventArgs e)
        {
            await RealizarDepositoAsync();
        }

        private void OnVolverMenuClicked(object sender, EventArgs e)
        {
            // Aquí decides qué hacer: volver al menú principal, etc.
            _view.Close();
        }

        private void OnSalirClicked(object sender, EventArgs e)
        {
            // Ya lo maneja el form con Application.Exit();
        }

        private async Task RealizarDepositoAsync()
        {
            var cuenta = _view.NumeroCuenta?.Trim();
            var montoText = _view.Monto?.Trim();

            // Validaciones básicas
            if (string.IsNullOrEmpty(cuenta))
            {
                _view.MostrarMensajeError("Por favor, ingrese el número de cuenta.");
                return;
            }

            if (!double.TryParse(montoText, out var monto) || monto <= 0)
            {
                _view.MostrarMensajeError("Ingrese un monto válido mayor a 0.");
                return;
            }

            try
            {
                // Construir body EXACTO al schema
                var bodyObj = new
                {
                    cuenta = cuenta,
                    monto = monto
                };

                var json = JsonConvert.SerializeObject(bodyObj);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                // Endpoint: /api/Cuenta/deposito
                var url = new Uri(new Uri(_apiBaseUrl), "Cuenta/deposito");

                var resp = await _httpClient.PostAsync(url, content);
                var respJson = await resp.Content.ReadAsStringAsync();

                if (!resp.IsSuccessStatusCode)
                {
                    _view.MostrarMensajeError("No se pudo realizar el depósito. Verifique los datos.");
                    return;
                }

                dynamic result = JsonConvert.DeserializeObject(respJson);

                bool success = result != null &&
                               (result.success == true || result.Success == true);

                if (!success)
                {
                    string msgError =
                        result != null && (result.mensaje != null || result.Mensaje != null)
                            ? (string)(result.mensaje ?? result.Mensaje)
                            : "No se pudo realizar el depósito.";
                    _view.MostrarMensajeError(msgError);
                    return;
                }

                // Éxito
                string mensaje =
                    result.mensaje != null ? (string)result.mensaje :
                    result.Mensaje != null ? (string)result.Mensaje :
                    "Depósito realizado exitosamente";

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
                _view.MostrarMensajeError("Error al realizar el depósito: " + ex.Message);
            }
        }
    }
}
