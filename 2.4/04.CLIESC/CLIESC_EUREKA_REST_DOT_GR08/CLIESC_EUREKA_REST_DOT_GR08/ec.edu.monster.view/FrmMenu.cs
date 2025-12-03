using System;
using System.Drawing;
using System.Windows.Forms;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
   public partial class FrmMenu : Form
    {
        private readonly string _usuario;

        public FrmMenu(string usuario)
        {
            _usuario = string.IsNullOrWhiteSpace(usuario) ? "MONSTER" : usuario;
            InitializeComponent();
        }

        private void FrmMenu_Load(object sender, EventArgs e)
        {
            lblBienvenidaTexto.Text = $"Bienvenido, {_usuario.ToUpper()}";
        }

        private void BtnSalir_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        // ====== CARD BLANCA REDONDEADA ======
        private void PanelCard_Paint(object sender, PaintEventArgs e)
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

        // ====== BOTONES GRADIENTE ======
        private void GradientButton_Paint(object sender, PaintEventArgs e)
        {
            var btn = sender as Button;
            if (btn == null) return;

            e.Graphics.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
            var rect = new Rectangle(0, 0, btn.Width, btn.Height);

            // Fondo con esquinas suaves (opcionalmente puedes redondear más)
            using (var brush = new System.Drawing.Drawing2D.LinearGradientBrush(
                rect,
                Color.FromArgb(116, 142, 255),
                Color.FromArgb(96, 123, 240),
                0f))
            using (var path = new System.Drawing.Drawing2D.GraphicsPath())
            {
                int radius = 26;
                int d = radius * 2;
                path.AddArc(0, 0, d, d, 180, 90);
                path.AddArc(rect.Width - d, 0, d, d, 270, 90);
                path.AddArc(rect.Width - d, rect.Height - d, d, d, 0, 90);
                path.AddArc(0, rect.Height - d, d, d, 90, 90);
                path.CloseFigure();

                e.Graphics.FillPath(brush, path);
            }

            // Icono
            string icon = btn.Tag as string ?? "";
            if (!string.IsNullOrEmpty(icon))
            {
                using (var iconFont = new Font("Segoe UI Emoji", 24F, FontStyle.Regular))
                {
                    var iconSize = TextRenderer.MeasureText(icon, iconFont);
                    var iconRect = new Rectangle(
                        (btn.Width - iconSize.Width) / 2,
                        18,
                        iconSize.Width,
                        iconSize.Height);

                    TextRenderer.DrawText(
                        e.Graphics,
                        icon,
                        iconFont,
                        iconRect,
                        Color.White,
                        TextFormatFlags.HorizontalCenter | TextFormatFlags.VerticalCenter);
                }
            }

            // Texto (título debajo del icono)
            using (var textFont = new Font("Segoe UI Semibold", 11F, FontStyle.Bold))
            {
                var textRect = new Rectangle(8, 80, btn.Width - 16, btn.Height - 100);

                TextRenderer.DrawText(
                    e.Graphics,
                    btn.Text,
                    textFont,
                    textRect,
                    Color.White,
                    TextFormatFlags.HorizontalCenter | TextFormatFlags.Top);
            }
        }

        private System.Drawing.Drawing2D.GraphicsPath RoundedRect(Rectangle bounds, int radius)
        {
            int d = radius * 2;
            var path = new System.Drawing.Drawing2D.GraphicsPath();
            path.AddArc(bounds.X, bounds.Y, d, d, 180, 90);
            path.AddArc(bounds.Right - d, bounds.Y, d, d, 270, 90);
            path.AddArc(bounds.Right - d, bounds.Bottom - d, d, d, 0, 90);
            path.AddArc(bounds.X, bounds.Bottom - d, d, d, 90, 90);
            path.CloseFigure();
            return path;
        }

        // ====== CLICKS (aquí luego abres tus forms reales) ======
        private void btnDeposito_Click(object sender, EventArgs e)
        {

            using (var frm = new FrmDeposito())
            {
                frm.StartPosition = FormStartPosition.CenterParent;
                frm.ShowDialog(this);
            }

        }

        private void btnRetiro_Click(object sender, EventArgs e)
        {

            using (var frm = new FrmRetiro())
            {
                frm.StartPosition = FormStartPosition.CenterParent;
                frm.ShowDialog(this);
            }

        }

        private void btnTransferencia_Click(object sender, EventArgs e)
        {
            using (var frm = new FrmTransferencia())
            {
                frm.StartPosition = FormStartPosition.CenterParent;
                frm.ShowDialog(this);
            }
        }

        private void btnDatos_Click(object sender, EventArgs e)
        {
            using (var frm = new FrmDatos())
            {
                frm.StartPosition = FormStartPosition.CenterParent;
                frm.ShowDialog(this);
            }

        }

        private void btnMovimiento_Click(object sender, EventArgs e)
        {
            using (var frm = new FrmMovimiento())
            {
                frm.StartPosition = FormStartPosition.CenterParent;
                frm.ShowDialog(this);
            }
        }
    }
}

