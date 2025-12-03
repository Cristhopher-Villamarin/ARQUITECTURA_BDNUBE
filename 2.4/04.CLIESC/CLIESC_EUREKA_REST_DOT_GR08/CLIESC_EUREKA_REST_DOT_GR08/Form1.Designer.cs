using System.Windows.Forms;
using System.Drawing;
using System.Drawing.Drawing2D;

namespace CLIESC_EUREKA_REST_DOT_GR08
{
    partial class Form1
    {
        private System.ComponentModel.IContainer components = null;

        private Panel panelLeft;
        private PictureBox pictureBoxSully;
        private Panel panelCard;
        private Label lblIconBank;
        private Label lblTitulo;
        private Label lblSubTitulo;
        private Label lblUsuarioTitle;
        private Label lblPasswordTitle;
        private TextBox txtUsuario;
        private TextBox txtContrasena;
        private Button btnLogin;
        private Label lblMensaje;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();

            this.panelLeft = new Panel();
            this.pictureBoxSully = new PictureBox();
            this.panelCard = new Panel();
            this.lblIconBank = new Label();
            this.lblTitulo = new Label();
            this.lblSubTitulo = new Label();
            this.lblUsuarioTitle = new Label();
            this.txtUsuario = new TextBox();
            this.lblPasswordTitle = new Label();
            this.txtContrasena = new TextBox();
            this.btnLogin = new Button();
            this.lblMensaje = new Label();

            this.panelLeft.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxSully)).BeginInit();
            this.panelCard.SuspendLayout();
            this.SuspendLayout();

            // ===== FORM =====
            this.AutoScaleMode = AutoScaleMode.Font;
            this.BackColor = Color.FromArgb(123, 72, 180);
            this.ClientSize = new Size(1280, 720);
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.StartPosition = FormStartPosition.CenterScreen;
            this.Text = "Eureka Bank - Login";
            this.Load += new System.EventHandler(this.Form1_Load);

            // ===== PANEL IZQUIERDO (SULLY) =====
            this.panelLeft.Dock = DockStyle.Left;
            this.panelLeft.Width = 420;
            this.panelLeft.BackColor = Color.Transparent;

            this.pictureBoxSully.Dock = DockStyle.Fill;
            this.pictureBoxSully.SizeMode = PictureBoxSizeMode.Zoom;
            // Asegúrate: recurso se llama "sullyvan" exactamente
            this.pictureBoxSully.Image = global::CLIESC_EUREKA_REST_DOT_GR08.Properties.Resources.sullyvan2;



            this.panelLeft.Controls.Add(this.pictureBoxSully);

            // ===== CARD BLANCA =====
            this.panelCard.Width = 430;
            this.panelCard.Height = 600;
            this.panelCard.Left = (this.ClientSize.Width - this.panelCard.Width) / 2 + 80;
            this.panelCard.Top = (this.ClientSize.Height - this.panelCard.Height) / 2;
            this.panelCard.BackColor = Color.White;
            this.panelCard.BorderStyle = BorderStyle.None;
            this.panelCard.Paint += new PaintEventHandler(this.PanelCard_Paint);
            // ===== LOGO / ÍCONO BANCO (EMOJI) =====
                    this.lblIconBank.AutoSize = true;
                    this.lblIconBank.Font = new Font("Segoe UI Emoji", 34F, FontStyle.Regular);
                    this.lblIconBank.Text = "🏦";
                    this.lblIconBank.BackColor = Color.Transparent;
                    this.lblIconBank.TextAlign = ContentAlignment.MiddleCenter;
                    this.lblIconBank.Width = this.panelCard.Width;
                    this.lblIconBank.Location = new Point(180, 50); // centrado horizontalmente

            // ===== TÍTULO =====
            this.lblTitulo.Text = "Eureka Bank";
            this.lblTitulo.Font = new Font("Segoe UI", 22F, FontStyle.Bold);
            this.lblTitulo.ForeColor = Color.FromArgb(40, 40, 40);
            this.lblTitulo.TextAlign = ContentAlignment.MiddleCenter;
            this.lblTitulo.SetBounds(0, 100, this.panelCard.Width, 45);

            // ===== SUBTÍTULO =====
            this.lblSubTitulo.Text = "Iniciar Sesión";
            this.lblSubTitulo.Font = new Font("Segoe UI", 11F, FontStyle.Regular);
            this.lblSubTitulo.ForeColor = Color.FromArgb(130, 130, 130);
            this.lblSubTitulo.TextAlign = ContentAlignment.MiddleCenter;
            this.lblSubTitulo.SetBounds(0, 155, this.panelCard.Width, 25);

            // ===== LABEL USUARIO =====
            this.lblUsuarioTitle.Text = "Usuario";
            this.lblUsuarioTitle.Font = new Font("Segoe UI", 12F, FontStyle.Bold);
            this.lblUsuarioTitle.ForeColor = Color.FromArgb(90, 90, 90);
            this.lblUsuarioTitle.AutoSize = true;
            this.lblUsuarioTitle.Left = 50;
            this.lblUsuarioTitle.Top = 270;

            // ===== TXT USUARIO =====
            this.txtUsuario.Font = new System.Drawing.Font("Segoe UI", 14F); // tamaño de texto
            this.txtUsuario.Left = 50;
            this.txtUsuario.Top = 300;
            this.txtUsuario.Width = this.panelCard.Width - 100;
            this.txtUsuario.Height = 40; // más alto
            this.txtUsuario.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtUsuario.ForeColor = System.Drawing.Color.FromArgb(80, 80, 80);

            // ===== LABEL PASSWORD =====
            this.lblPasswordTitle.Text = "Contraseña";
            this.lblPasswordTitle.Font = new Font("Segoe UI", 12F, FontStyle.Bold);
            this.lblPasswordTitle.ForeColor = Color.FromArgb(90, 90, 90);
            this.lblPasswordTitle.AutoSize = true;
            this.lblPasswordTitle.Left = 50;
            this.lblPasswordTitle.Top = 350;

            // ===== TXT PASSWORD =====
            this.txtContrasena.Font = new System.Drawing.Font("Segoe UI Semibold", 14F); // tamaño de texto
            this.txtContrasena.Left = 50;
            this.txtContrasena.Top = 390;
            this.txtContrasena.Width = this.panelCard.Width - 100;
            this.txtContrasena.Height = 40; // más alto
            this.txtContrasena.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtContrasena.PasswordChar = '●';

            // ===== BOTÓN LOGIN =====
            this.btnLogin.Text = "➜ Iniciar Sesión";
            this.btnLogin.Font = new Font("Segoe UI Semibold", 11F, FontStyle.Bold);
            this.btnLogin.ForeColor = Color.White;
            this.btnLogin.FlatStyle = FlatStyle.Flat;
            this.btnLogin.FlatAppearance.BorderSize = 0;
            this.btnLogin.Cursor = Cursors.Hand;
            this.btnLogin.SetBounds(50, 490, this.panelCard.Width - 100, 50);
            this.btnLogin.Paint += new PaintEventHandler(this.BtnLogin_Paint);

            // ===== MENSAJE =====
            this.lblMensaje.Text = "";
            this.lblMensaje.Font = new Font("Segoe UI", 9F);
            this.lblMensaje.ForeColor = Color.FromArgb(200, 40, 40);
            this.lblMensaje.TextAlign = ContentAlignment.MiddleCenter;
            this.lblMensaje.SetBounds(25, 410, this.panelCard.Width - 50, 40);

            // ===== ADD CONTROLS =====
            this.panelCard.Controls.Add(this.lblIconBank);
            this.panelCard.Controls.Add(this.lblTitulo);
            this.panelCard.Controls.Add(this.lblSubTitulo);
            this.panelCard.Controls.Add(this.lblUsuarioTitle);
            this.panelCard.Controls.Add(this.txtUsuario);
            this.panelCard.Controls.Add(this.lblPasswordTitle);
            this.panelCard.Controls.Add(this.txtContrasena);
            this.panelCard.Controls.Add(this.btnLogin);
            this.panelCard.Controls.Add(this.lblMensaje);

            this.Controls.Add(this.panelCard);
            this.Controls.Add(this.panelLeft);

            this.panelLeft.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxSully)).EndInit();
            this.panelCard.ResumeLayout(false);
            this.panelCard.PerformLayout();
            this.ResumeLayout(false);
        }

        // ==== DIBUJOS ====

        private void PanelCard_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.SmoothingMode = SmoothingMode.AntiAlias;
            var rect = new Rectangle(0, 0, panelCard.Width - 1, panelCard.Height - 1);
            int radius = 30;

            using (var path = RoundedRect(rect, radius))
            using (var shadow = new SolidBrush(Color.FromArgb(25, 0, 0, 0)))
            using (var pen = new Pen(Color.Transparent))
            {
                // sombra simple
                var shadowRect = rect;
                shadowRect.Offset(3, 5);
                e.Graphics.FillPath(shadow, RoundedRect(shadowRect, radius));

                // card
                e.Graphics.FillPath(Brushes.White, path);
                e.Graphics.DrawPath(pen, path);
            }
        }

        private void BtnLogin_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.SmoothingMode = SmoothingMode.AntiAlias;
            var rect = new Rectangle(0, 0, btnLogin.Width, btnLogin.Height);

            using (var brush = new LinearGradientBrush(
                rect,
                Color.FromArgb(118, 75, 162),
                Color.FromArgb(102, 126, 234),
                0f))
            {
                e.Graphics.FillRectangle(brush, rect);
            }

            TextRenderer.DrawText(
                e.Graphics,
                btnLogin.Text,
                btnLogin.Font,
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
            path.AddArc(bounds.Right - d, bounds.Bottom - d, d, d, 0, 90);
            path.AddArc(bounds.X, bounds.Bottom - d, d, d, 90, 90);
            path.CloseFigure();
            return path;
        }
    }
}
