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
        // Egreso: Retiro (004), Transferencia Origen (009)
        private static readonly HashSet<string> COD_EGRESO = new HashSet<string> { "004", "009" };
        // Ingreso: Apertura (001), Depósito (003), Transferencia Destino (008)
        private static readonly HashSet<string> COD_INGRESO = new HashSet<string> { "001", "003", "008" };

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

                    // Ajustar descripción para transferencias según el código de movimiento
                    // 009: Transferencia (Cuenta Origen) -> Débito
                    // 008: Transferencia (Cuenta Destino) -> Crédito
                    if (codigo == "009")
                    {
                        mov.TipoDescripcion = "Transferencia - Débito";
                        descripcion = mov.TipoDescripcion.ToLower().Trim();
                    }
                    else if (codigo == "008")
                    {
                        mov.TipoDescripcion = "Transferencia - Crédito";
                        descripcion = mov.TipoDescripcion.ToLower().Trim();
                    }

                    if (COD_EGRESO.Contains(codigo) || descripcion == "retiro")
                    {
                        totalEgresos += importe;
                    }
                    else if (COD_INGRESO.Contains(codigo) || descripcion == "deposito" || descripcion == "depósito"
                             || descripcion == "apertura de cuenta")
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

