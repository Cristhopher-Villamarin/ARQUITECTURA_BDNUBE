using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
   public partial class FrmMovimiento : Form
    {
        private Form FrmMenu;
        public event EventHandler BuscarClicked;

        public FrmMovimiento()
        {
            InitializeComponent();
            FrmMenu = FrmMenu;
            // Controlador (usa las propiedades/métodos públicos del form)
            new MovimientoController(this);

            // Placeholder para la cuenta
            SetPlaceholder(txtCuenta, "Ingrese el número de cuenta");
        }

        // === API pública usada por el controlador ===

        public string NumeroCuenta
        {
            get => IsPlaceholderActive(txtCuenta) ? "" : txtCuenta.Text.Trim();
            set => txtCuenta.Text = value;
        }

        public string Saldo
        {
            set => lblSaldo.Text = value; // ya viene formateado desde el controller
        }

        public string TotalMovimientos
        {
            set => lblTotalMovimientos.Text = value; // idem
        }

        public void AgregarMovimientos(List<MovimientosItem> movimientos)
        {
            flowLayoutPanel.Controls.Clear();
            if (movimientos == null || movimientos.Count == 0)
                return;

            foreach (var movimiento in movimientos)
            {
                movimiento.Margin = new Padding(0, 0, 0, 10);
                flowLayoutPanel.Controls.Add(movimiento);
            }
        }

        public void MostrarMensaje(string mensaje)
        {
            MessageBox.Show(mensaje, "Información",
                MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        // === Eventos internos ===

        private void btnBuscar_Click(object sender, EventArgs e)
        {
            BuscarClicked?.Invoke(this, EventArgs.Empty);
        }

        private void FrmMovimientos_Load(object sender, EventArgs e)
        {
        }

        private void FrmMovimientos_FormClosing(object sender, FormClosingEventArgs e)
        {
            // Si tenemos un menú de donde venimos, lo mostramos al cerrar
            if (FrmMenu != null)
            {
                FrmMenu.Show();
            }
            // No llamamos a Application.Exit() aquí
        }



        // === Estilo: tarjeta redondeada ===
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

        // === Estilo: botón degradado ===
        private void btnBuscar_Paint(object sender, PaintEventArgs e)
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

        // === Placeholder manual ===

        private class PlaceholderState
        {
            public string Text;
        }

        private void SetPlaceholder(TextBox tb, string text)
        {
            tb.Tag = new PlaceholderState { Text = text };
            tb.ForeColor = Color.Gray;
            tb.Text = text;

            tb.GotFocus += RemovePlaceholder;
            tb.LostFocus += ApplyPlaceholderIfEmpty;
        }

        private void RemovePlaceholder(object sender, EventArgs e)
        {
            var tb = sender as TextBox;
            var st = tb?.Tag as PlaceholderState;
            if (tb == null || st == null) return;

            if (tb.ForeColor == Color.Gray && tb.Text == st.Text)
            {
                tb.ForeColor = Color.FromArgb(60, 60, 60);
                tb.Text = "";
            }
        }

        private void ApplyPlaceholderIfEmpty(object sender, EventArgs e)
        {
            var tb = sender as TextBox;
            var st = tb?.Tag as PlaceholderState;
            if (tb == null || st == null) return;

            if (string.IsNullOrWhiteSpace(tb.Text))
            {
                tb.ForeColor = Color.Gray;
                tb.Text = st.Text;
            }
        }

        private bool IsPlaceholderActive(TextBox tb)
        {
            var st = tb.Tag as PlaceholderState;
            return st != null && tb.ForeColor == Color.Gray && tb.Text == st.Text;
        }

        private void btnBuscar_Click_1(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
