using System;
using System.Drawing;
using System.Windows.Forms;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
    public partial class FrmTransferencia : Form
    {
        public event EventHandler TransferirClicked;
        public event EventHandler VolverMenuClicked;
        public event EventHandler SalirClicked;

        public FrmTransferencia()
        {
            InitializeComponent();
            // Cuando tengas el controlador:
            new TransferenciaController(this);
        }

        // === API pública para el controlador ===

        public string CuentaOrigen
        {
            get => txtCuentaOrigen.Text.Trim();
            set => txtCuentaOrigen.Text = value;
        }

        public string CuentaDestino
        {
            get => txtCuentaDestino.Text.Trim();
            set => txtCuentaDestino.Text = value;
        }

        public string Monto
        {
            get => txtMonto.Text.Trim();
            set => txtMonto.Text = value;
        }

        public void MostrarMensajeOk(string mensaje)
        {
            panelMensaje.BackColor = Color.FromArgb(230, 255, 230);
            lblMensaje.ForeColor = Color.FromArgb(27, 94, 32);
            lblMensaje.Text = mensaje;
            panelMensaje.Visible = true;
        }

        public void MostrarMensajeError(string mensaje)
        {
            panelMensaje.BackColor = Color.FromArgb(255, 235, 238);
            lblMensaje.ForeColor = Color.FromArgb(183, 28, 28);
            lblMensaje.Text = mensaje;
            panelMensaje.Visible = true;
        }

        public void OcultarMensaje()
        {
            panelMensaje.Visible = false;
        }

        // === Eventos de UI ===

        private void btnTransferir_Click(object sender, EventArgs e)
        {
            OcultarMensaje();
            TransferirClicked?.Invoke(this, EventArgs.Empty);
        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            VolverMenuClicked?.Invoke(this, EventArgs.Empty);
            // aquí puedes hacer this.Close() o navegar al menú
        }

        private void btnSalir_Click(object sender, EventArgs e)
        {
            SalirClicked?.Invoke(this, EventArgs.Empty);
            Application.Exit();
        }

        // === Estilos ===

        private void btnTransferir_Paint(object sender, PaintEventArgs e)
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



