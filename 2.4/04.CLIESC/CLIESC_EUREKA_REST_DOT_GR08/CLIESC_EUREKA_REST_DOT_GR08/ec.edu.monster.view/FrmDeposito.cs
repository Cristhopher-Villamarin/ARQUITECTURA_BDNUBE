using System;
using System.Drawing;
using System.Windows.Forms;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
  public partial class FrmDeposito : Form
    {
        public event EventHandler DepositarClicked;
        public event EventHandler VolverMenuClicked;
        public event EventHandler SalirClicked;

        public FrmDeposito()
        {
            InitializeComponent();
            new DepositoController(this);
        }

        // === API pública para el controlador ===

        public string NumeroCuenta
        {
            get => txtCuenta.Text.Trim();
            set => txtCuenta.Text = value;
        }

        public string Monto
        {
            get => txtMonto.Text.Trim();
            set => txtMonto.Text = value;
        }

        public void MostrarMensajeOk(string mensaje)
        {
            lblMensaje.Text = mensaje;
            panelMensaje.BackColor = Color.FromArgb(230, 255, 230);
            lblMensaje.ForeColor = Color.FromArgb(27, 94, 32);
            panelMensaje.Visible = true;
        }

        public void MostrarMensajeError(string mensaje)
        {
            lblMensaje.Text = mensaje;
            panelMensaje.BackColor = Color.FromArgb(255, 235, 238);
            lblMensaje.ForeColor = Color.FromArgb(183, 28, 28);
            panelMensaje.Visible = true;
        }

        public void OcultarMensaje()
        {
            panelMensaje.Visible = false;
        }

        // === Eventos de UI ===

        private void btnDepositar_Click(object sender, EventArgs e)
        {
            OcultarMensaje();
            DepositarClicked?.Invoke(this, EventArgs.Empty);
        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            VolverMenuClicked?.Invoke(this, EventArgs.Empty);
            // Aquí decides si cierras o escondes:
            // this.Close();
        }

        private void btnSalir_Click(object sender, EventArgs e)
        {
            SalirClicked?.Invoke(this, EventArgs.Empty);
            Application.Exit();
        }

        // Dibujar degradado del botón Depositar
        private void btnDepositar_Paint(object sender, PaintEventArgs e)
        {
            var btn = (Button)sender;
            var g = e.Graphics;
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;

            var rect = new Rectangle(0, 0, btn.Width, btn.Height);
            using (var brush = new System.Drawing.Drawing2D.LinearGradientBrush(
                       rect,
                       Color.FromArgb(102, 126, 234),   // azul
                       Color.FromArgb(118, 75, 162),    // morado
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

        // Dibujar degradado del botón Salir (rosado)
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

        // Tarjeta blanca redondeada
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

