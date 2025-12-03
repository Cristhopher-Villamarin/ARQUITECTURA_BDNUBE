using System;
using System.Drawing;
using System.Windows.Forms;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
    partial class FrmMenu
    {
        private System.ComponentModel.IContainer components = null;

        private Panel panelTop;
        private Label lblLogo;
        private Button btnSalir;
        private Panel panelCard;
        private Label lblBienvenidaIcon;
        private Label lblBienvenidaTexto;
        private Label lblSubtitulo;
        private Button btnDeposito;
        private Button btnRetiro;
        private Button btnTransferencia;
        private Button btnDatos;
        private Button btnMovimiento;
        private PictureBox pictureBoxSully;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();

            this.panelTop = new Panel();
            this.lblLogo = new Label();
            this.btnSalir = new Button();
            this.panelCard = new Panel();
            this.lblBienvenidaIcon = new Label();
            this.lblBienvenidaTexto = new Label();
            this.lblSubtitulo = new Label();
            this.btnDeposito = new Button();
            this.btnRetiro = new Button();
            this.btnTransferencia = new Button();
            this.btnDatos = new Button();
            this.btnMovimiento = new Button();
            this.pictureBoxSully = new PictureBox();

            // ===== FORM =====
            this.SuspendLayout();
            this.AutoScaleMode = AutoScaleMode.Font;
            this.BackColor = Color.FromArgb(120, 70, 170); // morado fondo
            this.ClientSize = new Size(1280, 720);
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.StartPosition = FormStartPosition.CenterScreen;
            this.Text = "Eureka Bank - Menú Principal";
            this.Load += new System.EventHandler(this.FrmMenu_Load);

            // ===== PANEL TOP =====
            this.panelTop.Dock = DockStyle.Top;
            this.panelTop.Height = 70;
            this.panelTop.BackColor = Color.White;
            this.panelTop.Padding = new Padding(30, 0, 30, 0);

            // Logo texto
            this.lblLogo.AutoSize = true;
            this.lblLogo.Font = new Font("Segoe UI Semibold", 18F, FontStyle.Bold);
            this.lblLogo.ForeColor = Color.FromArgb(92, 62, 156);
            this.lblLogo.Text = "Eureka Bank";
            this.lblLogo.Location = new Point(30, 18);

            // Botón Salir (arriba derecha)
            this.btnSalir.Text = "Salir";
            this.btnSalir.Font = new Font("Segoe UI Semibold", 10F, FontStyle.Bold);
            this.btnSalir.ForeColor = Color.White;
            this.btnSalir.Size = new Size(100, 36);
            this.btnSalir.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            this.btnSalir.FlatStyle = FlatStyle.Flat;
            this.btnSalir.FlatAppearance.BorderSize = 0;
            this.btnSalir.Cursor = Cursors.Hand;
            this.btnSalir.Location = new Point(this.ClientSize.Width - 30 - this.btnSalir.Width, 18);
            this.btnSalir.BackColor = Color.HotPink;
            this.btnSalir.Click += new System.EventHandler(this.BtnSalir_Click);

            this.panelTop.Controls.Add(this.lblLogo);
            this.panelTop.Controls.Add(this.btnSalir);

            // ===== PANEL CARD =====
            this.panelCard.Width = 980;
            this.panelCard.Height = 420;
            this.panelCard.Left = (this.ClientSize.Width - this.panelCard.Width) / 2;
            this.panelCard.Top = 120;
            this.panelCard.BackColor = Color.Transparent;
            this.panelCard.Paint += new PaintEventHandler(this.PanelCard_Paint);

            // ===== TÍTULO BIENVENIDA =====
            this.lblBienvenidaIcon.AutoSize = true;
            this.lblBienvenidaIcon.Font = new Font("Segoe UI Emoji", 20F);
            this.lblBienvenidaIcon.ForeColor = Color.FromArgb(40, 40, 40);
            this.lblBienvenidaIcon.Text = "🏠";
            this.lblBienvenidaIcon.Location = new Point(70, 45);

            this.lblBienvenidaTexto.AutoSize = true;
            this.lblBienvenidaTexto.Font = new Font("Segoe UI", 22F, FontStyle.Bold);
            this.lblBienvenidaTexto.ForeColor = Color.FromArgb(40, 40, 40);
            this.lblBienvenidaTexto.Text = "Bienvenido, MONSTER";
            this.lblBienvenidaTexto.Location = new Point(120, 40);

            this.lblSubtitulo.AutoSize = true;
            this.lblSubtitulo.Font = new Font("Segoe UI", 11F);
            this.lblSubtitulo.ForeColor = Color.FromArgb(110, 110, 110);
            this.lblSubtitulo.Text = "Seleccione una opción del menú para continuar";
            this.lblSubtitulo.Location = new Point(72, 90);

            // ===== BOTONES CARD (alineados con espacio correcto) =====
            int cardWidth = 150;
            int cardHeight = 180;
            int firstLeft = 30;
            int topButtons = 150;
            int spacing = 40; // espacio horizontal

            // Depósito
            this.btnDeposito.Size = new Size(cardWidth, cardHeight);
            this.btnDeposito.Location = new Point(firstLeft, topButtons);
            this.btnDeposito.Text = "Depósito";
            this.btnDeposito.Tag = "⬇";
            this.btnDeposito.Font = new Font("Segoe UI Semibold", 10F, FontStyle.Bold);
            this.btnDeposito.FlatStyle = FlatStyle.Flat;
            this.btnDeposito.FlatAppearance.BorderSize = 0;
            this.btnDeposito.Cursor = Cursors.Hand;
            this.btnDeposito.Paint += new PaintEventHandler(this.GradientButton_Paint);
            this.btnDeposito.Click += new EventHandler(this.btnDeposito_Click);

            // Retiro
            this.btnRetiro.Size = new Size(cardWidth, cardHeight);
            this.btnRetiro.Location = new Point(firstLeft + (cardWidth + spacing) * 1, topButtons);
            this.btnRetiro.Text = "Retiro";
            this.btnRetiro.Tag = "⬆";
            this.btnRetiro.Font = new Font("Segoe UI Semibold", 10F, FontStyle.Bold);
            this.btnRetiro.FlatStyle = FlatStyle.Flat;
            this.btnRetiro.FlatAppearance.BorderSize = 0;
            this.btnRetiro.Cursor = Cursors.Hand;
            this.btnRetiro.Paint += new PaintEventHandler(this.GradientButton_Paint);
            this.btnRetiro.Click += new EventHandler(this.btnRetiro_Click);

            // Transferencia
            this.btnTransferencia.Size = new Size(cardWidth, cardHeight);
            this.btnTransferencia.Location = new Point(firstLeft + (cardWidth + spacing) * 2, topButtons);
            this.btnTransferencia.Text = "Transferencia";
            this.btnTransferencia.Tag = "⇄";
            this.btnTransferencia.Font = new Font("Segoe UI Semibold", 10F, FontStyle.Bold);
            this.btnTransferencia.FlatStyle = FlatStyle.Flat;
            this.btnTransferencia.FlatAppearance.BorderSize = 0;
            this.btnTransferencia.Cursor = Cursors.Hand;
            this.btnTransferencia.Paint += new PaintEventHandler(this.GradientButton_Paint);
            this.btnTransferencia.Click += new EventHandler(this.btnTransferencia_Click);

            // Datos de Cuenta
            this.btnDatos.Size = new Size(cardWidth, cardHeight);
            this.btnDatos.Location = new Point(firstLeft + (cardWidth + spacing) * 3, topButtons);
            this.btnDatos.Text = "Datos de Cuenta";
            this.btnDatos.Tag = "ⓘ";
            this.btnDatos.Font = new Font("Segoe UI Semibold", 10F, FontStyle.Bold);
            this.btnDatos.FlatStyle = FlatStyle.Flat;
            this.btnDatos.FlatAppearance.BorderSize = 0;
            this.btnDatos.Cursor = Cursors.Hand;
            this.btnDatos.Paint += new PaintEventHandler(this.GradientButton_Paint);
            this.btnDatos.Click += new EventHandler(this.btnDatos_Click);

            // Movimientos (corregido: queda dentro del card con espacio)
            this.btnMovimiento.Size = new Size(cardWidth, cardHeight);
            this.btnMovimiento.Location = new Point(firstLeft + (cardWidth + spacing) * 4, topButtons);
            this.btnMovimiento.Text = "Movimientos";
            this.btnMovimiento.Tag = "☰";
            this.btnMovimiento.Font = new Font("Segoe UI Semibold", 10F, FontStyle.Bold);
            this.btnMovimiento.FlatStyle = FlatStyle.Flat;
            this.btnMovimiento.FlatAppearance.BorderSize = 0;
            this.btnMovimiento.Cursor = Cursors.Hand;
            this.btnMovimiento.Paint += new PaintEventHandler(this.GradientButton_Paint);
            this.btnMovimiento.Click += new EventHandler(this.btnMovimiento_Click);

            // ===== SULLY ABAJO =====
            this.pictureBoxSully.Size = new Size(260, 140);
            this.pictureBoxSully.SizeMode = PictureBoxSizeMode.Zoom;
            this.pictureBoxSully.Image = Properties.Resources.mikeysullivan2; // ya la tienes en Resources
            this.pictureBoxSully.Anchor = AnchorStyles.Bottom;
            this.pictureBoxSully.Location = new Point(
                (this.ClientSize.Width - this.pictureBoxSully.Width) / 2,
                this.ClientSize.Height - this.pictureBoxSully.Height - 30);

            // Add to panelCard
            this.panelCard.Controls.Add(this.lblBienvenidaIcon);
            this.panelCard.Controls.Add(this.lblBienvenidaTexto);
            this.panelCard.Controls.Add(this.lblSubtitulo);
            this.panelCard.Controls.Add(this.btnDeposito);
            this.panelCard.Controls.Add(this.btnRetiro);
            this.panelCard.Controls.Add(this.btnTransferencia);
            this.panelCard.Controls.Add(this.btnDatos);
            this.panelCard.Controls.Add(this.btnMovimiento);

            // Add to Form
            this.Controls.Add(this.panelCard);
            this.Controls.Add(this.panelTop);
            this.Controls.Add(this.pictureBoxSully);

            this.ResumeLayout(false);
        }

        #endregion
    }
}
