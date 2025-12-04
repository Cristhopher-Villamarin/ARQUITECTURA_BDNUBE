using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.model;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller
{
    public class MovimientoController
    {
        private readonly FrmMovimiento _view;
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl;

        public MovimientoController(FrmMovimiento view)
        {
            _view = view ?? throw new ArgumentNullException(nameof(view));
            _httpClient = ApiClient.Client;
            _apiBaseUrl = ApiClient.ApiBaseUrl;

            _view.BuscarClicked += OnBuscarClicked;
        }

        private async void OnBuscarClicked(object sender, EventArgs e)
        {
            await CargarMovimientosAsync();
        }

        private async Task CargarMovimientosAsync()
        {
            var cuenta = _view.NumeroCuenta?.Trim();

            if (string.IsNullOrEmpty(cuenta))
            {
                _view.MostrarMensaje("Por favor, ingrese un número de cuenta.");
                return;
            }

            try
            {
                // 1) Movimientos
                var movReq = new { numcuenta = cuenta };
                var movJson = JsonConvert.SerializeObject(movReq);
                var movContent = new StringContent(movJson, Encoding.UTF8, "application/json");

                var movUrl = new Uri(new Uri(_apiBaseUrl), "Movimiento");
                var movResp = await _httpClient.PostAsync(movUrl, movContent);

                if (!movResp.IsSuccessStatusCode)
                {
                    _view.MostrarMensaje("No se pudieron obtener los movimientos.");
                    return;
                }

                var movBody = await movResp.Content.ReadAsStringAsync();
                var movimientos = JsonConvert.DeserializeObject<List<MovimientoModel>>(movBody)
                                  ?? new List<MovimientoModel>();

                // 2) Datos de cuenta
                var ctaUrl = new Uri(new Uri(_apiBaseUrl), $"cuenta/{cuenta}");
                var ctaResp = await _httpClient.GetAsync(ctaUrl);
                if (ctaResp.IsSuccessStatusCode)
                {
                    var ctaBody = await ctaResp.Content.ReadAsStringAsync();
                    var cuentaModel = JsonConvert.DeserializeObject<CuentaModel>(ctaBody);
                    if (cuentaModel != null)
                        _view.Saldo = cuentaModel.DecCuenSaldo.ToString("N2");
                }

                // 3) Sin movimientos
                if (movimientos.Count == 0)
                {
                    _view.AgregarMovimientos(new List<MovimientosItem>());
                    _view.TotalMovimientos = "0";
                    _view.MostrarMensaje("No se encontraron movimientos para la cuenta ingresada.");
                    return;
                }

                // 4) Mapear a ítems visuales
                var items = new List<MovimientosItem>();

                foreach (var mov in movimientos)
                {
                    var item = new MovimientosItem();

                    item.Cuenta = cuenta;
                    item.Fecha = mov.FechaMovimientoDt?.ToString("dd/MM/yyyy HH:mm")
                                 ?? mov.FechaMovimiento
                                 ?? string.Empty;

                    // Mostrar algo legible en columna "Movimiento" y "Tipo"
                    item.Movimiento = mov.TipoDescripcion ?? mov.CodigoTipoMovimiento ?? "";
                    item.Tipo = mov.CodigoTipoMovimiento ?? "";

                    // === NUEVO: calcular Crédito / Débito consistente ===
                    string accion = CalcularAccion(mov);
                    item.Accion = accion;

                    item.Importe = mov.ImporteMovimiento.ToString("N2");
                    item.SaldoActual = mov.Saldo.ToString("N2");

                    // Colorear según acción
                    if (accion == "Débito")
                    {
                        item.MarcarComoDebito();  // rojo
                    }
                    else if (accion == "Crédito")
                    {
                        item.MarcarComoCredito(); // verde
                    }
                    else
                    {
                        // fallback: si viene raro, decide por signo
                        if (mov.ImporteMovimiento < 0)
                            item.MarcarComoDebito();
                        else
                            item.MarcarComoCredito();
                    }

                    items.Add(item);
                }

                _view.AgregarMovimientos(items);
                _view.TotalMovimientos = movimientos.Count.ToString();
            }
            catch (Exception ex)
            {
                _view.MostrarMensaje("Error al obtener los movimientos: " + ex.Message);
            }
        }

        /// <summary>
        /// Determina si el movimiento es Crédito o Débito para la cuenta consultada,
        /// usando CodigoTipoMovimiento y/o TipoDescripcion.
        /// </summary>
        private static string CalcularAccion(MovimientoModel mov)
        {
            if (mov == null)
                return "Crédito";

            string cod = mov.CodigoTipoMovimiento?.Trim();
            string desc = mov.TipoDescripcion?.Trim() ?? "";
            string descUp = desc.ToUpperInvariant();

            // 1) Si hay código, usamos los códigos del backend REST actual:
            // 001: Apertura (Crédito inicial)
            // 003: Depósito (Crédito)
            // 004: Retiro (Débito)
            // 008: Transferencia destino / recibida (Crédito)
            // 009: Transferencia origen / enviada (Débito)
            if (!string.IsNullOrEmpty(cod))
            {
                switch (cod)
                {
                    case "001":
                    case "003":
                    case "008":
                        return "Crédito";
                    case "004":
                    case "009":
                        return "Débito";
                }
            }

            // 2) Si solo tenemos descripción textual, usamos palabras clave

            if (descUp.Contains("APERTURA"))
                return "Crédito";

            if (descUp.Contains("DEPOSITO") || descUp.Contains("DEPÓSITO"))
                return "Crédito";

            if (descUp.Contains("RETIRO"))
                return "Débito";

            if (descUp.Contains("CRÉDITO") || descUp.Contains("CREDITO") || descUp.Contains("ABONO"))
                return "Crédito";

            if (descUp.Contains("DÉBITO") || descUp.Contains("DEBITO"))
                return "Débito";

            if (descUp.Contains("TRANSFER"))
            {
                // Si descripción indica recibida/entrante → crédito
                if (descUp.Contains("RECIB") || descUp.Contains("ENTR") || descUp.Contains("A FAVOR"))
                    return "Crédito";

                // En cualquier otro caso de "TRANSFERENCIA" lo tratamos como salida de esta cuenta
                return "Débito";
            }

            // 3) Fallback por signo
            return mov.ImporteMovimiento >= 0 ? "Crédito" : "Débito";
        }
    }
}
