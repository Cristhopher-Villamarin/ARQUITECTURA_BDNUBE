using System;
using System.Web.Mvc;
using CLIWEB_EUREKA_GR08.ec.edu.monster.model;
using CLIWEB_EUREKA_GR08.ec.edu.monster.servicio;

namespace CLIWEB_EUREKA_GR08.ec.edu.monster.controller
{
    public class TransferenciaController : Controller
    {
        // GET: Transferencia
        public ActionResult Index()
        {
            if (Session["Username"] == null)
            {
                return RedirectToAction("Index", "Login");
            }
            return View();
        }

        // POST: Transferencia
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Index(string cuentaOrigen, string cuentaDestino, string monto)
        {
            if (Session["Username"] == null)
            {
                return RedirectToAction("Index", "Login");
            }

            if (string.IsNullOrWhiteSpace(cuentaOrigen) || string.IsNullOrWhiteSpace(cuentaDestino) || string.IsNullOrWhiteSpace(monto))
            {
                ViewBag.Error = "Por favor complete todos los campos";
                return View();
            }

            if (cuentaOrigen.Trim() == cuentaDestino.Trim())
            {
                ViewBag.Error = "La cuenta origen y destino no pueden ser la misma";
                return View();
            }

            // Validar que el monto sea un número válido y positivo
            if (!double.TryParse(monto, out double montoDouble) || montoDouble <= 0)
            {
                ViewBag.Error = "El monto debe ser un número positivo válido";
                return View();
            }

            try
            {
                var restClient = new RestApiClient();
                var transferenciaRequest = new TransferenciaRequest
                {
                    Cuenta = cuentaOrigen.Trim(),
                    CuentaDestino = cuentaDestino.Trim(),
                    Monto = montoDouble
                };

                var response = restClient.Post<OperacionResponse>("Cuenta/transferencia", transferenciaRequest);

                if (response != null && response.Success)
                {
                    ViewBag.Success = response.Mensaje ?? "Transferencia realizada con éxito";
                    ModelState.Clear();
                }
                else
                {
                    ViewBag.Error = response?.Mensaje ?? "Error al realizar la transferencia. Verifique los datos de las cuentas y el saldo disponible en la cuenta origen";
                }
            }
            catch (Exception ex)
            {
                ViewBag.Error = "Error al conectar con el servidor: " + ex.Message;
            }

            return View();
        }
    }
}

