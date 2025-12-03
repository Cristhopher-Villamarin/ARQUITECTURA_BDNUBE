using System;
using System.Drawing;
using System.Windows.Forms;
using System.Drawing.Drawing2D;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
public partial class MovimientosItem : UserControl
    {
        public MovimientosItem()
        {
            InitializeComponent();

            this.DoubleBuffered = true;
            this.Margin = new Padding(0, 0, 0, 10);
        }

        // === Propiedades usadas por el controlador ===

        public string Cuenta
        {
            get => lblCuenta.Text;
            set => lblCuenta.Text = $"Cuenta: {value}";
        }

        public string Fecha
        {
            get => lblFecha.Text;
            set => lblFecha.Text = value;
        }

        public string Movimiento
        {
            get => lblDescripcion.Text;
            set => lblDescripcion.Text = value;
        }

        public string Tipo
        {
            get => lblTipo.Text;
            set => lblTipo.Text = value;
        }

        public string Accion
        {
            get => lblAccion.Text;
            set => lblAccion.Text = value;
        }
        public Color AccionColor
        {
            get => lblAccion.ForeColor;
            set => lblAccion.ForeColor = value;
        }

        public Color ImporteColor
        {
            get => lblImporte.ForeColor;
            set => lblImporte.ForeColor = value;
        }


        public string Importe
        {
            get => lblImporte.Text;
            set
            {
                lblImporte.Text = value;

                // Color verde positivo, rojo negativo (si viene con -)
                if (!string.IsNullOrEmpty(value) && value.Trim().StartsWith("-"))
                    lblImporte.ForeColor = Color.FromArgb(220, 53, 69); // rojo
                else
                    lblImporte.ForeColor = Color.FromArgb(25, 135, 84); // verde
            }
        }
        public void MarcarComoDebito()
        {
            lblImporte.ForeColor = Color.Red;
        }

        public void MarcarComoCredito()
        {
            lblImporte.ForeColor = Color.ForestGreen;
        }


        public string SaldoActual
        {
            get => lblSaldo.Text;
            set => lblSaldo.Text = $"Saldo: {value}";
        }

        // === Fondo tarjeta con bordes suaves & línea superior ===
        protected override void OnPaint(PaintEventArgs e)
        {
            base.OnPaint(e);
            var g = e.Graphics;
            g.SmoothingMode = SmoothingMode.AntiAlias;

            var rect = new Rectangle(0, 0, Width - 1, Height - 1);
            int radius = 15;

            using (var path = RoundedRect(rect, radius))
            using (var border = new Pen(Color.FromArgb(230, 230, 250), 1))
            using (var headerBrush = new LinearGradientBrush(
                       new Rectangle(0, 0, Width, 8),
                       Color.FromArgb(118, 75, 162),
                       Color.FromArgb(102, 126, 234),
                       0f))
            using (var backBrush = new SolidBrush(Color.White))
            {
                g.FillPath(backBrush, path);
                g.DrawPath(border, path);

                // franja superior degradada
                var headerRect = new Rectangle(1, 1, Width - 3, 8);
                g.FillRectangle(headerBrush, headerRect);
            }
        }

        private GraphicsPath RoundedRect(Rectangle bounds, int radius)
        {
            int d = radius * 2;
            var path = new GraphicsPath();
            path.AddArc(bounds.X, bounds.Y, d, d, 180, 90);
            path.AddArc(bounds.Right - d, bounds.Y, d, d, 270, 90);
            path.AddArc(bounds.Right - d, bounds.Bottom - d, d, d, 0, 90);
            path.AddArc(bounds.X, bounds.Bottom - d, d, d, 90, 90);
            path.CloseFigure();
            return path;
        }
    }
}
