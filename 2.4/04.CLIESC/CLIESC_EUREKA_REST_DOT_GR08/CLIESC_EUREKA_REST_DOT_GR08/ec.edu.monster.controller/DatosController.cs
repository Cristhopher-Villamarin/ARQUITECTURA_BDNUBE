using System;
using System.Net.Http;
using System.Threading.Tasks;
using Newtonsoft.Json;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller
{
    public class DatosController
    {
        private readonly FrmDatos _view;
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl;

        public DatosController(FrmDatos view)
        {
            _view = view ?? throw new ArgumentNullException(nameof(view));
            _httpClient = ApiClient.Client;
            _apiBaseUrl = ApiClient.ApiBaseUrl; // ej: https://localhost:7222/api/

            _view.ConsultarClicked += OnConsultarClicked;
            _view.VolverMenuClicked += OnVolverMenuClicked;
            _view.SalirClicked += OnSalirClicked;
        }

        private async void OnConsultarClicked(object sender, EventArgs e)
        {
            await ConsultarCuentaAsync();
        }

        private void OnVolverMenuClicked(object sender, EventArgs e)
        {
            _view.Close(); // o navega a tu menú principal
        }

        private void OnSalirClicked(object sender, EventArgs e)
        {
            // El form ya hace Application.Exit() en su handler
        }

        private async Task ConsultarCuentaAsync()
        {
            var cuenta = _view.NumeroCuenta?.Trim();

            if (string.IsNullOrEmpty(cuenta))
            {
                _view.MostrarPanelInfo(false);
                _view.MostrarMensajeError("Por favor, ingrese un número de cuenta.");
                return;
            }

            try
            {
                // Endpoint: GET /api/Cuenta/{codigoCuenta}
                var url = new Uri(new Uri(_apiBaseUrl), $"Cuenta/{cuenta}");

                var resp = await _httpClient.GetAsync(url);
                var respJson = await resp.Content.ReadAsStringAsync();

                if (!resp.IsSuccessStatusCode)
                {
                    _view.MostrarPanelInfo(false);
                    _view.MostrarMensajeError("No se encontró la cuenta especificada.");
                    return;
                }

                // El response:
                // {
                //   "chrCuenCodigo": "00100001",
                //   "chrMoneCodigo": "01",
                //   "chrSucucodigo": "001",
                //   "chrEmplCreaCuenta": "0004",
                //   "chrClieCodigo": "00005",
                //   "decCuenSaldo": 6500,
                //   "dttCuenFechaCreacion": "2008-01-06T00:00:00",
                //   "vchCuenEstado": "ACTIVO",
                //   "intCuenContMov": 15,
                //   "chrCuenClave": "123456"
                // }

                dynamic data = JsonConvert.DeserializeObject(respJson);
                if (data == null)
                {
                    _view.MostrarPanelInfo(false);
                    _view.MostrarMensajeError("No se pudieron leer los datos de la cuenta.");
                    return;
                }

                // Mapear a la UI
                _view.CodigoCuenta = (string)(data.chrCuenCodigo ?? "");
                _view.Saldo = data.decCuenSaldo != null
                    ? ((decimal)data.decCuenSaldo).ToString("N2")
                    : "-";

                _view.ContadorMovimientos = data.intCuenContMov != null
                    ? ((int)data.intCuenContMov).ToString()
                    : "-";

                if (data.dttCuenFechaCreacion != null)
                {
                    DateTime fecha;
                    if (DateTime.TryParse((string)data.dttCuenFechaCreacion, out fecha))
                        _view.FechaCreacion = fecha.ToString("dd/MM/yyyy");
                    else
                        _view.FechaCreacion = (string)data.dttCuenFechaCreacion;
                }
                else
                {
                    _view.FechaCreacion = "-";
                }

                _view.Estado = (string)(data.vchCuenEstado ?? "-");
                _view.Sucursal = (string)(data.chrSucucodigo ?? "-");

                // Mostrar panel con la info
                _view.MostrarPanelInfo(true);
            }
            catch (Exception ex)
            {
                _view.MostrarPanelInfo(false);
                _view.MostrarMensajeError("Error al consultar la cuenta: " + ex.Message);
            }
        }
    }
}
