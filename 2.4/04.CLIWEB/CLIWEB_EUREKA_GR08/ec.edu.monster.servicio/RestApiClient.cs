using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Text;
using CLIWEB_EUREKA_GR08.ec.edu.monster.model;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace CLIWEB_EUREKA_GR08.ec.edu.monster.servicio
{
    public class RestApiClient
    {
        private readonly string baseUrl;
        private readonly JsonSerializerSettings jsonSettings;

        public RestApiClient()
        {
            // Base URL del servicio RESTful
            baseUrl = "https://localhost:7222/api/";
            
            // Configurar JSON para ser case-insensitive
            jsonSettings = new JsonSerializerSettings
            {
                ContractResolver = new DefaultContractResolver
                {
                    NamingStrategy = new CamelCaseNamingStrategy()
                },
                NullValueHandling = NullValueHandling.Ignore
            };
        }

        public T Get<T>(string endpoint)
        {
            try
            {
                ServicePointManager.ServerCertificateValidationCallback = (sender, certificate, chain, sslPolicyErrors) => true;
                ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls12 | SecurityProtocolType.Tls11 | SecurityProtocolType.Tls;

                string url = baseUrl + endpoint;
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
                request.Method = "GET";
                request.ContentType = "application/json";
                request.Accept = "application/json";

                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    if (response.StatusCode == HttpStatusCode.OK)
                    {
                        using (StreamReader reader = new StreamReader(response.GetResponseStream()))
                        {
                            string json = reader.ReadToEnd();
                            return JsonConvert.DeserializeObject<T>(json, jsonSettings);
                        }
                    }
                    else if (response.StatusCode == HttpStatusCode.NotFound)
                    {
                        return default(T);
                    }
                    else
                    {
                        throw new Exception($"Error en la petición: {response.StatusCode}");
                    }
                }
            }
            catch (WebException ex)
            {
                if (ex.Response is HttpWebResponse errorResponse)
                {
                    using (StreamReader reader = new StreamReader(errorResponse.GetResponseStream()))
                    {
                        string errorJson = reader.ReadToEnd();
                        throw new Exception($"Error en la petición: {errorJson}");
                    }
                }
                throw new Exception($"Error al conectar con el servidor: {ex.Message}");
            }
        }

        public T Post<T>(string endpoint, object data)
        {
            ServicePointManager.ServerCertificateValidationCallback = (sender, certificate, chain, sslPolicyErrors) => true;
            ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls12 | SecurityProtocolType.Tls11 | SecurityProtocolType.Tls;

            string url = baseUrl + endpoint;
            string jsonData = JsonConvert.SerializeObject(data);

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.Method = "POST";
            request.ContentType = "application/json";
            request.Accept = "application/json";

            byte[] dataBytes = Encoding.UTF8.GetBytes(jsonData);
            request.ContentLength = dataBytes.Length;

            using (Stream requestStream = request.GetRequestStream())
            {
                requestStream.Write(dataBytes, 0, dataBytes.Length);
            }

            try
            {
                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    using (StreamReader reader = new StreamReader(response.GetResponseStream()))
                    {
                        string json = reader.ReadToEnd();
                        // Usar configuración case-insensitive para deserializar
                        return JsonConvert.DeserializeObject<T>(json, jsonSettings);
                    }
                }
            }
            catch (WebException ex)
            {
                if (ex.Response is HttpWebResponse errorResponse)
                {
                    using (StreamReader reader = new StreamReader(errorResponse.GetResponseStream()))
                    {
                        string errorJson = reader.ReadToEnd();
                        
                        // Para códigos como 401 (Unauthorized), intentar deserializar como el tipo esperado
                        // ya que el servidor puede devolver un objeto con success=false
                        if (errorResponse.StatusCode == HttpStatusCode.Unauthorized || 
                            errorResponse.StatusCode == HttpStatusCode.BadRequest)
                        {
                            try
                            {
                                // Intentar deserializar como el tipo esperado (ej: LoginResponse)
                                // Usar configuración case-insensitive
                                var result = JsonConvert.DeserializeObject<T>(errorJson, jsonSettings);
                                if (result != null)
                                {
                                    return result;
                                }
                            }
                            catch
                            {
                                // Si no se puede deserializar, continuar con el manejo de error
                            }
                        }
                        
                        // Manejo de error estándar
                        try
                        {
                            var errorObj = JsonConvert.DeserializeObject<dynamic>(errorJson);
                            if (errorObj != null)
                            {
                                if (errorObj.message != null)
                                {
                                    throw new Exception(errorObj.message.ToString());
                                }
                                if (errorObj.Message != null)
                                {
                                    throw new Exception(errorObj.Message.ToString());
                                }
                            }
                            throw new Exception($"Error en la petición: {errorJson}");
                        }
                        catch (Exception innerEx)
                        {
                            // Si ya es una excepción con mensaje, lanzarla tal cual
                            if (innerEx.Message != null && !innerEx.Message.StartsWith("Error en la petición:"))
                            {
                                throw;
                            }
                            // Si no, lanzar la excepción original de WebException
                            throw new Exception($"Error en la petición: {errorJson}");
                        }
                    }
                }
                throw new Exception($"Error al conectar con el servidor: {ex.Message}");
            }
        }
    }
}

