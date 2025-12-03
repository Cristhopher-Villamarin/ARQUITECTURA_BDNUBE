using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Newtonsoft.Json;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller
{
    public class LoginController
    {
        private readonly Form1 _view;
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl = "https://localhost:7222/api/"; // tu API

        public LoginController(Form1 view)
        {
            _view = view ?? throw new ArgumentNullException(nameof(view));

            var handler = new HttpClientHandler
            {
                // solo para desarrollo (ignora certificado)
                ServerCertificateCustomValidationCallback = (msg, cert, chain, errors) => true
            };

            _httpClient = new HttpClient(handler)
            {
                BaseAddress = new Uri(_apiBaseUrl)
            };
        }

        public async Task IniciarSesionAsync()
        {
            var usuario = _view.Usuario?.Trim();
            var password = _view.Contrasena?.Trim();

            if (string.IsNullOrEmpty(usuario) || string.IsNullOrEmpty(password))
            {
                MostrarMensaje("Ingrese usuario y contraseña.", false);
                return;
            }

            try
            {
                var body = new
                {
                    Username = usuario,
                    Password = password
                };

                var json = JsonConvert.SerializeObject(body);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                var response = await _httpClient.PostAsync("login", content);
                var responseText = await response.Content.ReadAsStringAsync();

                if (!response.IsSuccessStatusCode)
                {
                    MostrarMensaje("Usuario o contraseña incorrectos.", false);
                    return;
                }

                dynamic result = JsonConvert.DeserializeObject(responseText);

                // Ajusta estas propiedades según responda tu API
                bool success = result != null && (result.success == true || result.ok == true);

                if (success)
                {
                    MostrarMensaje("Autenticación exitosa. Redirigiendo...", true);

                    // Abrir menú
                    var menu = new FrmMenu(usuario);

                    // opcional: cuando se cierre el menú, mostrar login de nuevo
                    menu.FormClosed += (s, e) => _view.Show();

                    menu.Show();
                    _view.Hide();
                }
                else
                {
                    MostrarMensaje("Usuario o contraseña incorrectos.", false);
                }
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error de conexión con el servidor: " + ex.Message, false);
            }
        }

        private void MostrarMensaje(string msg, bool exito)
        {
            _view.Mensaje = msg;
            _view.MensajeColor = exito
                ? System.Drawing.Color.FromArgb(0, 150, 90)
                : System.Drawing.Color.FromArgb(200, 40, 40);
        }
    }
}
