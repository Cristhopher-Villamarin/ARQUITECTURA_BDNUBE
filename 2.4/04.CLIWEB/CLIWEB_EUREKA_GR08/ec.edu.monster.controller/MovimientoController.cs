using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using CLIWEB_EUREKA_GR08.ec.edu.monster.model;
using CLIWEB_EUREKA_GR08.ec.edu.monster.servicio;

namespace CLIWEB_EUREKA_GR08.ec.edu.monster.controller
{
    public class MovimientoController : Controller
    {
        // Códigos de tipo de movimiento
        private static readonly HashSet<string> COD_EGRESO = new HashSet<string> { "004" }; // Retiro
        private static readonly HashSet<string> COD_INGRESO = new HashSet<string> { "001", "003", "009" }; // Apertura, Depósito, Transferencia

        // GET: Movimiento
        public ActionResult Index()
        {
            if (Session["Username"] == null)
            {
                return RedirectToAction("Index", "Login");
            }
            return View();
        }

        // POST: Movimiento
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

                // Obtener movimientos
                var movimientoRequest = new MovimientoRequest
                {
                    Numcuenta = cuenta.Trim()
                };

                var movimientos = restClient.Post<List<MovimientoModel>>("Movimiento", movimientoRequest) ?? new List<MovimientoModel>();

                // Obtener datos de la cuenta
                var cuentaModel = restClient.Get<CuentaModel>($"Cuenta/{cuenta.Trim()}");

                double saldoActual = cuentaModel?.DecCuenSaldo ?? 0;
                double totalIngresos = 0;
                double totalEgresos = 0;

                // Calcular totales
                foreach (var mov in movimientos)
                {
                    if (mov == null) continue;

                    double importe = mov.ImporteMovimiento;
                    string codigo = mov.CodigoTipoMovimiento ?? "";
                    string descripcion = (mov.TipoDescripcion ?? "").ToLower().Trim();

                    if (COD_EGRESO.Contains(codigo) || descripcion == "retiro")
                    {
                        totalEgresos += importe;
                    }
                    else if (COD_INGRESO.Contains(codigo) || descripcion == "deposito" || descripcion == "depósito"
                             || descripcion == "transferencia" || descripcion == "apertura de cuenta")
                    {
                        totalIngresos += importe;
                    }
                }

                ViewBag.Movimientos = movimientos;
                ViewBag.SaldoActual = saldoActual;
                ViewBag.TotalIngresos = totalIngresos;
                ViewBag.TotalEgresos = totalEgresos;
                ViewBag.SaldoNeto = totalIngresos - totalEgresos;
            }
            catch (Exception ex)
            {
                ViewBag.Error = "Error al conectar con el servidor: " + ex.Message;
            }

            return View();
        }
    }
}

