using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller
{
    public static class ApiClient
    {
        private static readonly HttpClient httpClient;
        public static string ApiBaseUrl { get; }

        static ApiClient()
        {
            var baseUrl = "https://localhost:7222/api/";
            if (!baseUrl.EndsWith("/"))
                baseUrl += "/";

            ApiBaseUrl = baseUrl;

            var handler = new HttpClientHandler
            {
                // Ignorar certificado SSL en desarrollo
                ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true
            };

            httpClient = new HttpClient(handler)
            {
                BaseAddress = new Uri(ApiBaseUrl)
            };

            httpClient.DefaultRequestHeaders.Accept.Clear();
            httpClient.DefaultRequestHeaders
                      .Accept
                      .Add(new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public static HttpClient Client => httpClient;
    }
}