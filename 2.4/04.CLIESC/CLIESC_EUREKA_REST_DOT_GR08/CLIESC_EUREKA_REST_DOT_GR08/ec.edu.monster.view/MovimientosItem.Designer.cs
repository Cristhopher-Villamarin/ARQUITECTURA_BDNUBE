using System.Drawing;
using System.Windows.Forms;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
  partial class MovimientosItem
    {
        private System.ComponentModel.IContainer components = null;

        private Label lblCuenta;
        private Label lblFecha;
        private Label lblDescripcion;
        private Label lblSaldo;
        private Label lblTipo;
        private Label lblAccion;
        private Label lblImporte;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();

            this.lblCuenta = new Label();
            this.lblFecha = new Label();
            this.lblDescripcion = new Label();
            this.lblSaldo = new Label();
            this.lblTipo = new Label();
            this.lblAccion = new Label();
            this.lblImporte = new Label();

            // ==== USERCONTROL ====
            this.BackColor = Color.White;
            this.Size = new Size(900, 80);   // ancho grande, bajo
            this.Margin = new Padding(0, 0, 0, 10);

            // ==== lblCuenta (izquierda arriba) ====
            this.lblCuenta.AutoSize = true;
            this.lblCuenta.Font = new Font("Segoe UI Semibold", 9.5F, FontStyle.Bold);
            this.lblCuenta.ForeColor = Color.FromArgb(40, 40, 40);
            this.lblCuenta.Location = new Point(18, 15);
            this.lblCuenta.Text = "Cuenta: 00000000";

            // ==== lblFecha (debajo cuenta) ====
            this.lblFecha.AutoSize = true;
            this.lblFecha.Font = new Font("Segoe UI", 8.8F);
            this.lblFecha.ForeColor = Color.FromArgb(120, 120, 120);
            this.lblFecha.Location = new Point(18, 38);
            this.lblFecha.Text = "Fecha: -";

            // ==== lblDescripcion (debajo fecha) ====
            this.lblDescripcion.AutoSize = true;
            this.lblDescripcion.Font = new Font("Segoe UI", 8.8F);
            this.lblDescripcion.ForeColor = Color.FromArgb(120, 120, 120);
            this.lblDescripcion.Location = new Point(18, 56);
            this.lblDescripcion.Text = "Descripción / Movimiento";

            // ==== lblSaldo (arriba centro) ====
            this.lblSaldo.AutoSize = true;
            this.lblSaldo.Font = new Font("Segoe UI Semibold", 9F, FontStyle.Bold);
            this.lblSaldo.ForeColor = Color.FromArgb(54, 79, 199);
            this.lblSaldo.Location = new Point(340, 18);
            this.lblSaldo.Text = "Saldo: -";

            // ==== lblTipo (arriba derecha) ====
            this.lblTipo.AutoSize = true;
            this.lblTipo.Font = new Font("Segoe UI", 8.5F);
            this.lblTipo.ForeColor = Color.FromArgb(90, 90, 90);
            this.lblTipo.Location = new Point(650, 18);
            this.lblTipo.Text = "Tipo: -";

            // ==== lblAccion (medio derecha) ====
            this.lblAccion.AutoSize = true;
            this.lblAccion.Font = new Font("Segoe UI", 8.5F);
            this.lblAccion.ForeColor = Color.FromArgb(90, 90, 90);
            this.lblAccion.Location = new Point(650, 36);
            this.lblAccion.Text = "Acción: -";

            // ==== lblImporte (abajo derecha, resalta) ====
            this.lblImporte.AutoSize = true;
            this.lblImporte.Font = new Font("Segoe UI Semibold", 10F, FontStyle.Bold);
            this.lblImporte.ForeColor = Color.FromArgb(25, 135, 84);
            this.lblImporte.Location = new Point(650, 54);
            this.lblImporte.Text = "$0.00";

            // ==== ADD CONTROLS ====
            this.Controls.Add(this.lblCuenta);
            this.Controls.Add(this.lblFecha);
            this.Controls.Add(this.lblDescripcion);
            this.Controls.Add(this.lblSaldo);
            this.Controls.Add(this.lblTipo);
            this.Controls.Add(this.lblAccion);
            this.Controls.Add(this.lblImporte);
        }
    }
}
