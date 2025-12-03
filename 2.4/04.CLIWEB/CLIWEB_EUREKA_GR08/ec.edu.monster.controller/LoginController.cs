using System;
using System.Web.Mvc;
using CLIWEB_EUREKA_GR08.ec.edu.monster.model;
using CLIWEB_EUREKA_GR08.ec.edu.monster.servicio;

namespace CLIWEB_EUREKA_GR08.ec.edu.monster.controller
{
    public class LoginController : Controller
    {
        // GET: Login
        public ActionResult Index()
        {
            // Si ya está autenticado, redirigir al menú
            if (Session["Username"] != null)
            {
                return RedirectToAction("Index", "Home");
            }
            return View();
        }

        // POST: Login
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Index(string username, string password)
        {
            if (string.IsNullOrWhiteSpace(username) || string.IsNullOrWhiteSpace(password))
            {
                ViewBag.Error = "Por favor complete todos los campos";
                return View();
            }

            try
            {
                var restClient = new RestApiClient();
                var loginRequest = new LoginRequest
                {
                    Username = username.Trim(),
                    Password = password.Trim()
                };

                var response = restClient.Post<LoginResponse>("Login", loginRequest);

                if (response != null && response.Success)
                {
                    Session["Username"] = username.Trim();
                    return RedirectToAction("Index", "Home");
                }
                else
                {
                    ViewBag.Error = response?.Message ?? "Credenciales incorrectas";
                    return View();
                }
            }
            catch (Exception ex)
            {
                ViewBag.Error = "Error al conectar con el servidor: " + ex.Message;
                return View();
            }
        }

        // GET: Logout
        public ActionResult Logout()
        {
            Session.Clear();
            Session.Abandon();
            return RedirectToAction("Index", "Login");
        }
    }
}

