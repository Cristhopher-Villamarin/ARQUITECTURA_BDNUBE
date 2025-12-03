using System.Drawing;
using System.Windows.Forms;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
    partial class FrmTransferencia
    {
        private System.ComponentModel.IContainer components = null;

        private Panel panelBackground;
        private Panel panelCard;
        private Label lblTitulo;
        private Panel panelMensaje;
        private Label lblMensaje;
        private Label lblCuentaOrigenTitulo;
        private Label lblCuentaDestinoTitulo;
        private Label lblMontoTitulo;
        private TextBox txtCuentaOrigen;
        private TextBox txtCuentaDestino;
        private TextBox txtMonto;
        private Button btnTransferir;
        private Button btnVolver;
        private Label lblLogo;
        private Label lblUser;
        private Button btnSalir;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        private void InitializeComponent()
        {
            this.panelBackground = new System.Windows.Forms.Panel();
            this.lblLogo = new System.Windows.Forms.Label();
            this.lblUser = new System.Windows.Forms.Label();
            this.btnSalir = new System.Windows.Forms.Button();
            this.panelCard = new System.Windows.Forms.Panel();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.panelMensaje = new System.Windows.Forms.Panel();
            this.lblMensaje = new System.Windows.Forms.Label();
            this.lblCuentaOrigenTitulo = new System.Windows.Forms.Label();
            this.txtCuentaOrigen = new System.Windows.Forms.TextBox();
            this.lblCuentaDestinoTitulo = new System.Windows.Forms.Label();
            this.txtCuentaDestino = new System.Windows.Forms.TextBox();
            this.lblMontoTitulo = new System.Windows.Forms.Label();
            this.txtMonto = new System.Windows.Forms.TextBox();
            this.btnTransferir = new System.Windows.Forms.Button();
            this.btnVolver = new System.Windows.Forms.Button();
            this.panelBackground.SuspendLayout();
            this.panelCard.SuspendLayout();
            this.panelMensaje.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelBackground
            // 
            this.panelBackground.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(118)))), ((int)(((byte)(75)))), ((int)(((byte)(162)))));
            this.panelBackground.Controls.Add(this.lblLogo);
            this.panelBackground.Controls.Add(this.lblUser);
            this.panelBackground.Controls.Add(this.btnSalir);
            this.panelBackground.Controls.Add(this.panelCard);
            this.panelBackground.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelBackground.Location = new System.Drawing.Point(0, 0);
            this.panelBackground.Name = "panelBackground";
            this.panelBackground.Padding = new System.Windows.Forms.Padding(40);
            this.panelBackground.Size = new System.Drawing.Size(1280, 720);
            this.panelBackground.TabIndex = 0;
            // 
            // lblLogo
            // 
            this.lblLogo.AutoSize = true;
            this.lblLogo.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold);
            this.lblLogo.ForeColor = System.Drawing.Color.White;
            this.lblLogo.Location = new System.Drawing.Point(52, 25);
            this.lblLogo.Name = "lblLogo";
            this.lblLogo.Size = new System.Drawing.Size(195, 32);
            this.lblLogo.TabIndex = 0;
            this.lblLogo.Text = "🏦 Eureka Bank";
            // 
            // lblUser
            // 
            this.lblUser.AutoSize = true;
            this.lblUser.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblUser.ForeColor = System.Drawing.Color.White;
            this.lblUser.Location = new System.Drawing.Point(1040, 30);
            this.lblUser.Name = "lblUser";
            this.lblUser.Size = new System.Drawing.Size(72, 19);
            this.lblUser.TabIndex = 1;
            this.lblUser.Text = "MONSTER";
            // 
            // btnSalir
            // 
            this.btnSalir.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(128)))), ((int)(((byte)(128)))));
            this.btnSalir.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnSalir.FlatAppearance.BorderSize = 0;
            this.btnSalir.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnSalir.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold);
            this.btnSalir.ForeColor = System.Drawing.Color.White;
            this.btnSalir.Location = new System.Drawing.Point(1140, 24);
            this.btnSalir.Name = "btnSalir";
            this.btnSalir.Size = new System.Drawing.Size(90, 32);
            this.btnSalir.TabIndex = 2;
            this.btnSalir.Text = "⏏ Salir";
            this.btnSalir.UseVisualStyleBackColor = false;
            this.btnSalir.Click += new System.EventHandler(this.btnSalir_Click);
            this.btnSalir.Paint += new System.Windows.Forms.PaintEventHandler(this.btnSalir_Paint);
            // 
            // panelCard
            // 
            this.panelCard.BackColor = System.Drawing.Color.White;
            this.panelCard.Controls.Add(this.lblTitulo);
            this.panelCard.Controls.Add(this.panelMensaje);
            this.panelCard.Controls.Add(this.lblCuentaOrigenTitulo);
            this.panelCard.Controls.Add(this.txtCuentaOrigen);
            this.panelCard.Controls.Add(this.lblCuentaDestinoTitulo);
            this.panelCard.Controls.Add(this.txtCuentaDestino);
            this.panelCard.Controls.Add(this.lblMontoTitulo);
            this.panelCard.Controls.Add(this.txtMonto);
            this.panelCard.Controls.Add(this.btnTransferir);
            this.panelCard.Controls.Add(this.btnVolver);
            this.panelCard.Location = new System.Drawing.Point(60, 110);
            this.panelCard.Name = "panelCard";
            this.panelCard.Padding = new System.Windows.Forms.Padding(40, 30, 40, 30);
            this.panelCard.Size = new System.Drawing.Size(1160, 560);
            this.panelCard.TabIndex = 3;
            this.panelCard.Paint += new System.Windows.Forms.PaintEventHandler(this.panelCard_Paint);
            // 
            // lblTitulo
            // 
            this.lblTitulo.AutoSize = true;
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 22F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(51)))), ((int)(((byte)(51)))), ((int)(((byte)(51)))));
            this.lblTitulo.Location = new System.Drawing.Point(50, 40);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(376, 41);
            this.lblTitulo.TabIndex = 0;
            this.lblTitulo.Text = "⟷ Realizar Transferencia";
            // 
            // panelMensaje
            // 
            this.panelMensaje.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(230)))), ((int)(((byte)(255)))), ((int)(((byte)(230)))));
            this.panelMensaje.Controls.Add(this.lblMensaje);
            this.panelMensaje.Location = new System.Drawing.Point(50, 100);
            this.panelMensaje.Name = "panelMensaje";
            this.panelMensaje.Size = new System.Drawing.Size(1040, 50);
            this.panelMensaje.TabIndex = 1;
            this.panelMensaje.Visible = false;
            // 
            // lblMensaje
            // 
            this.lblMensaje.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lblMensaje.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblMensaje.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(27)))), ((int)(((byte)(94)))), ((int)(((byte)(32)))));
            this.lblMensaje.Location = new System.Drawing.Point(0, 0);
            this.lblMensaje.Name = "lblMensaje";
            this.lblMensaje.Padding = new System.Windows.Forms.Padding(15, 0, 0, 0);
            this.lblMensaje.Size = new System.Drawing.Size(1040, 50);
            this.lblMensaje.TabIndex = 0;
            this.lblMensaje.Text = "Transferencia realizada con éxito";
            this.lblMensaje.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // lblCuentaOrigenTitulo
            // 
            this.lblCuentaOrigenTitulo.AutoSize = true;
            this.lblCuentaOrigenTitulo.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblCuentaOrigenTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(85)))), ((int)(((byte)(85)))), ((int)(((byte)(85)))));
            this.lblCuentaOrigenTitulo.Location = new System.Drawing.Point(50, 180);
            this.lblCuentaOrigenTitulo.Name = "lblCuentaOrigenTitulo";
            this.lblCuentaOrigenTitulo.Size = new System.Drawing.Size(105, 19);
            this.lblCuentaOrigenTitulo.TabIndex = 2;
            this.lblCuentaOrigenTitulo.Text = "Cuenta Origen";
            // 
            // txtCuentaOrigen
            // 
            this.txtCuentaOrigen.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(235)))), ((int)(((byte)(243)))), ((int)(((byte)(255)))));
            this.txtCuentaOrigen.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtCuentaOrigen.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.txtCuentaOrigen.Location = new System.Drawing.Point(50, 210);
            this.txtCuentaOrigen.Name = "txtCuentaOrigen";
            this.txtCuentaOrigen.Multiline = true;
            this.txtCuentaOrigen.Size = new System.Drawing.Size(1040, 45);
            this.txtCuentaOrigen.TabIndex = 3;
            // 
            // lblCuentaDestinoTitulo
            // 
            this.lblCuentaDestinoTitulo.AutoSize = true;
            this.lblCuentaDestinoTitulo.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblCuentaDestinoTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(85)))), ((int)(((byte)(85)))), ((int)(((byte)(85)))));
            this.lblCuentaDestinoTitulo.Location = new System.Drawing.Point(50, 260);
            this.lblCuentaDestinoTitulo.Name = "lblCuentaDestinoTitulo";
            this.lblCuentaDestinoTitulo.Size = new System.Drawing.Size(109, 19);
            this.lblCuentaDestinoTitulo.TabIndex = 4;
            this.lblCuentaDestinoTitulo.Text = "Cuenta Destino";
            // 
            // txtCuentaDestino
            // 
            this.txtCuentaDestino.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(235)))), ((int)(((byte)(243)))), ((int)(((byte)(255)))));
            this.txtCuentaDestino.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtCuentaDestino.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.txtCuentaDestino.Location = new System.Drawing.Point(50, 290);
            this.txtCuentaDestino.Name = "txtCuentaDestino";
            this.txtCuentaDestino.Multiline = true;
            this.txtCuentaDestino.Size = new System.Drawing.Size(1040, 45);
            this.txtCuentaDestino.TabIndex = 5;
            // 
            // lblMontoTitulo
            // 
            this.lblMontoTitulo.AutoSize = true;
            this.lblMontoTitulo.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblMontoTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(85)))), ((int)(((byte)(85)))), ((int)(((byte)(85)))));
            this.lblMontoTitulo.Location = new System.Drawing.Point(50, 340);
            this.lblMontoTitulo.Name = "lblMontoTitulo";
            this.lblMontoTitulo.Size = new System.Drawing.Size(65, 19);
            this.lblMontoTitulo.TabIndex = 6;
            this.lblMontoTitulo.Text = "$ Monto";
            // 
            // txtMonto
            // 
            this.txtMonto.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(235)))), ((int)(((byte)(243)))), ((int)(((byte)(255)))));
            this.txtMonto.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtMonto.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.txtMonto.Location = new System.Drawing.Point(50, 370);
            this.txtMonto.Name = "txtMonto";
            this.txtMonto.Multiline = true;
            this.txtMonto.Size = new System.Drawing.Size(1040, 45);
            this.txtMonto.TabIndex = 7;
            // 
            // btnTransferir
            // 
            this.btnTransferir.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(118)))), ((int)(((byte)(75)))), ((int)(((byte)(162)))));
            this.btnTransferir.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnTransferir.FlatAppearance.BorderSize = 0;
            this.btnTransferir.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnTransferir.Font = new System.Drawing.Font("Segoe UI", 11F, System.Drawing.FontStyle.Bold);
            this.btnTransferir.ForeColor = System.Drawing.Color.White;
            this.btnTransferir.Location = new System.Drawing.Point(50, 430);
            this.btnTransferir.Name = "btnTransferir";
            this.btnTransferir.Size = new System.Drawing.Size(1040, 55);
            this.btnTransferir.TabIndex = 8;
            this.btnTransferir.Text = "✓ Realizar Transferencia";
            this.btnTransferir.UseVisualStyleBackColor = false;
            this.btnTransferir.Click += new System.EventHandler(this.btnTransferir_Click);
            this.btnTransferir.Paint += new System.Windows.Forms.PaintEventHandler(this.btnTransferir_Paint);
            // 
            // btnVolver
            // 
            this.btnVolver.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(245)))), ((int)(((byte)(245)))), ((int)(((byte)(245)))));
            this.btnVolver.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVolver.FlatAppearance.BorderSize = 0;
            this.btnVolver.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVolver.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnVolver.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(85)))), ((int)(((byte)(85)))), ((int)(((byte)(85)))));
            this.btnVolver.Location = new System.Drawing.Point(480, 500);
            this.btnVolver.Name = "btnVolver";
            this.btnVolver.Size = new System.Drawing.Size(180, 40);
            this.btnVolver.TabIndex = 9;
            this.btnVolver.Text = "← Volver al Menú";
            this.btnVolver.UseVisualStyleBackColor = false;
            this.btnVolver.Click += new System.EventHandler(this.btnVolver_Click);
            // 
            // FrmTransferencia
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(1280, 720);
            this.Controls.Add(this.panelBackground);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "FrmTransferencia";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Eureka Bank - Realizar Transferencia";
            this.panelBackground.ResumeLayout(false);
            this.panelBackground.PerformLayout();
            this.panelCard.ResumeLayout(false);
            this.panelCard.PerformLayout();
            this.panelMensaje.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion
    }
}
