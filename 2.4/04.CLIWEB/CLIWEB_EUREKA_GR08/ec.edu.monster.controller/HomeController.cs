using System.Web.Mvc;

namespace CLIWEB_EUREKA_GR08.ec.edu.monster.controller
{
    public class HomeController : Controller
    {
        // GET: Home
        public ActionResult Index()
        {
            // Verificar autenticación
            if (Session["Username"] == null)
            {
                return RedirectToAction("Index", "Login");
            }

            ViewBag.Username = Session["Username"].ToString();
            return View();
        }
    }
}

