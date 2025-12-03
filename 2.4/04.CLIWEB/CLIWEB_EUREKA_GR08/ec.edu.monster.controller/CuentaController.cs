using System;
using System.Web.Mvc;
using CLIWEB_EUREKA_GR08.ec.edu.monster.model;
using CLIWEB_EUREKA_GR08.ec.edu.monster.servicio;

namespace CLIWEB_EUREKA_GR08.ec.edu.monster.controller
{
    public class CuentaController : Controller
    {
        // GET: Cuenta
        public ActionResult Index()
        {
            if (Session["Username"] == null)
            {
                return RedirectToAction("Index", "Login");
            }
            return View();
        }

        // POST: Cuenta
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Index(string cuenta)
        {
            if (Session["Username"] == null)
            {
                return RedirectToAction("Index", "Login");
            }

            if (string.IsNullOrWhiteSpace(cuenta))
            {
                ViewBag.Error = "Por favor ingrese el número de cuenta";
                return View();
            }

            try
            {
                var restClient = new RestApiClient();
                var cuentaModel = restClient.Get<CuentaModel>($"Cuenta/{cuenta.Trim()}");

                if (cuentaModel != null)
                {
                    ViewBag.Cuenta = cuentaModel;
                }
                else
                {
                    ViewBag.Error = "No se encontraron datos para la cuenta especificada";
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

