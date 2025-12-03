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
    public class OperacionesController
    {
        private readonly HttpClient httpClient;
        private readonly string apiBaseUrl;
        private readonly FrmDeposito _view;
        public OperacionesController()
        {
            httpClient = ApiClient.Client;
            apiBaseUrl = ApiClient.ApiBaseUrl;
        }

        public bool RealizarDeposito(string cuenta, string monto)
        {
            return EnviarOperacion("cuenta/deposito", new
            {
                cuenta,
                monto = double.Parse(monto)
            });
        }

        public bool RealizarRetiro(string cuenta, string monto)
        {
            return EnviarOperacion("cuenta/retiro", new
            {
                cuenta,
                monto = double.Parse(monto)
            });
        }

        public bool RealizarTransferencia(string cuentaOrigen, string cuentaDestino, string monto)
        {
            return EnviarOperacion("cuenta/transferencia", new
            {
                cuenta = cuentaOrigen,
                monto = double.Parse(monto),
                cuentaDestino
            });
        }

        private bool EnviarOperacion(string endpoint, object body)
        {
            try
            {
                var json = JsonConvert.SerializeObject(body);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                var url = new Uri(new Uri(apiBaseUrl), endpoint);
                var response = httpClient.PostAsync(url, content).Result;
                var responseContent = response.Content.ReadAsStringAsync().Result;

                if (!response.IsSuccessStatusCode)
                    return false;

                dynamic result = JsonConvert.DeserializeObject(responseContent);
                // adapta si tu API responde distinto
                return result != null && (result.success == true || result.Success == true);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error en {endpoint}: {ex.Message}");
                return false;
            }
        }
    }
}
