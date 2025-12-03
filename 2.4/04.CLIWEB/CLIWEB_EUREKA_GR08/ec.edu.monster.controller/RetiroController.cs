using System;
using System.Web.Mvc;
using CLIWEB_EUREKA_GR08.ec.edu.monster.model;
using CLIWEB_EUREKA_GR08.ec.edu.monster.servicio;

namespace CLIWEB_EUREKA_GR08.ec.edu.monster.controller
{
    public class RetiroController : Controller
    {
        // GET: Retiro
        public ActionResult Index()
        {
            if (Session["Username"] == null)
            {
                return RedirectToAction("Index", "Login");
            }
            return View();
        }

        // POST: Retiro
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Index(string cuenta, string monto)
        {
            if (Session["Username"] == null)
            {
                return RedirectToAction("Index", "Login");
            }

            if (string.IsNullOrWhiteSpace(cuenta) || string.IsNullOrWhiteSpace(monto))
            {
                ViewBag.Error = "Por favor complete todos los campos";
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
                var retiroRequest = new RetiroRequest
                {
                    Cuenta = cuenta.Trim(),
                    Monto = montoDouble
                };

                var response = restClient.Post<OperacionResponse>("Cuenta/retiro", retiroRequest);

                if (response != null && response.Success)
                {
                    ViewBag.Success = response.Mensaje ?? "Retiro realizado con éxito";
                    ModelState.Clear();
                }
                else
                {
                    ViewBag.Error = response?.Mensaje ?? "Error al realizar el retiro. Verifique los datos de la cuenta y el saldo disponible";
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

