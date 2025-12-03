using System;
using System.Drawing;
using System.Windows.Forms;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller;
namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
 public partial class FrmDatos : Form
    {
        public event EventHandler ConsultarClicked;
        public event EventHandler VolverMenuClicked;
        public event EventHandler SalirClicked;

        public FrmDatos()
        {
            InitializeComponent();
            // Cuando tengas el controlador:
            new DatosController(this);
        }

        // ===== API pública para el controlador =====

        public string NumeroCuenta
        {
            get => txtCuenta.Text.Trim();
            set => txtCuenta.Text = value;
        }

        public string CodigoCuenta
        {
            set => lblCodCuentaValue.Text = value;
        }

        public string Saldo
        {
            set => lblSaldoValue.Text = value;
        }

        public string ContadorMovimientos
        {
            set => lblContadorValue.Text = value;
        }

        public string FechaCreacion
        {
            set => lblFechaValue.Text = value;
        }

        public string Estado
        {
            set => lblEstadoValue.Text = value;
        }

        public string Sucursal
        {
            set => lblSucursalValue.Text = value;
        }

        public void MostrarPanelInfo(bool visible)
        {
            panelInfo.Visible = visible;
        }

        public void MostrarMensajeError(string mensaje)
        {
            MessageBox.Show(mensaje, "Información", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        // ===== Eventos UI =====

        private void btnConsultar_Click(object sender, EventArgs e)
        {
            ConsultarClicked?.Invoke(this, EventArgs.Empty);
        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            VolverMenuClicked?.Invoke(this, EventArgs.Empty);
            // this.Close(); // si quieres cerrar aquí
        }

        private void btnSalir_Click(object sender, EventArgs e)
        {
            SalirClicked?.Invoke(this, EventArgs.Empty);
            Application.Exit();
        }

        // ===== Estilos =====

        private void panelCard_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;

            var rect = new Rectangle(0, 0, panelCard.Width - 1, panelCard.Height - 1);
            int radius = 30;

            using (var path = RoundedRect(rect, radius))
            using (var pen = new Pen(Color.Transparent, 1))
            {
                g.FillPath(Brushes.White, path);
                g.DrawPath(pen, path);
            }
        }

        private void panelInfo_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;

            // Rectángulo completo del panel
            var rect = new Rectangle(0, 0, panelInfo.Width - 1, panelInfo.Height - 1);

            // Fondo degradado en TODO el panel
            using (var brush = new System.Drawing.Drawing2D.LinearGradientBrush(
                       rect,
                       Color.FromArgb(118, 75, 162),   // morado
                       Color.FromArgb(102, 126, 234),  // azul
                       System.Drawing.Drawing2D.LinearGradientMode.Horizontal))
            {
                g.FillRectangle(brush, rect);
            }

            // Bordes redondeados sólo como contorno visual
            int radius = 30;
            radius = Math.Min(radius, Math.Min(rect.Width / 2, rect.Height / 2));

            using (var path = RoundedRect(rect, radius))
            using (var pen = new Pen(Color.FromArgb(118, 75, 162), 1))
            {
                g.DrawPath(pen, path);
            }
        }

        private void btnConsultar_Paint(object sender, PaintEventArgs e)
        {
            var btn = (Button)sender;
            var g = e.Graphics;
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
            var rect = new Rectangle(0, 0, btn.Width, btn.Height);

            using (var brush = new System.Drawing.Drawing2D.LinearGradientBrush(
                       rect,
                       Color.FromArgb(102, 126, 234),
                       Color.FromArgb(118, 75, 162),
                       0f))
            {
                g.FillRectangle(brush, rect);
            }

            TextRenderer.DrawText(
                g,
                btn.Text,
                btn.Font,
                rect,
                Color.White,
                TextFormatFlags.HorizontalCenter | TextFormatFlags.VerticalCenter);
        }

        private void btnSalir_Paint(object sender, PaintEventArgs e)
        {
            var btn = (Button)sender;
            var g = e.Graphics;
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
            var rect = new Rectangle(0, 0, btn.Width, btn.Height);

            using (var brush = new System.Drawing.Drawing2D.LinearGradientBrush(
                       rect,
                       Color.FromArgb(255, 109, 151),
                       Color.FromArgb(255, 82, 120),
                       0f))
            {
                g.FillRectangle(brush, rect);
            }

            TextRenderer.DrawText(
                g,
                btn.Text,
                btn.Font,
                rect,
                Color.White,
                TextFormatFlags.HorizontalCenter | TextFormatFlags.VerticalCenter);
        }

        private System.Drawing.Drawing2D.GraphicsPath RoundedRect(Rectangle bounds, int radius)
        {
            int d = radius * 2;
            var path = new System.Drawing.Drawing2D.GraphicsPath();
            path.AddArc(bounds.X, bounds.Y, d, d, 180, 90);
            path.AddArc(bounds.Right - d, bounds.Y, d, d, 270, 90);
            path.AddArc(bounds.X, bounds.Bottom - d, d, d, 0, 90);
            path.AddArc(bounds.X, bounds.Bottom - d, d, d, 90, 90);
            path.CloseFigure();
            return path;
        }
    }
}

