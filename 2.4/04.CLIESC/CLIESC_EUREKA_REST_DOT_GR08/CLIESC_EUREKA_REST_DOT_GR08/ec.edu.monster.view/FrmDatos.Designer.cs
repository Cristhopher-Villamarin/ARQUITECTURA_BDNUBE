using System.Drawing;
using System.Windows.Forms;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.view
{
    partial class FrmDatos
    {
        private System.ComponentModel.IContainer components = null;

        private Panel panelBackground;
        private Panel panelCard;
        private Label lblLogo;
        private Label lblUser;
        private Button btnSalir;
        private Label lblTitulo;
        private Label lblCuentaTitulo;
        private TextBox txtCuenta;
        private Button btnConsultar;

        private Panel panelInfo;
        private Label lblInfoTitulo;

        private Label lblCodCuentaLabel;
        private Label lblCodCuentaValue;

        private Label lblSaldoLabel;
        private Label lblSaldoValue;

        private Label lblContadorLabel;
        private Label lblContadorValue;

        private Label lblFechaLabel;
        private Label lblFechaValue;

        private Label lblEstadoLabel;
        private Label lblEstadoValue;

        private Label lblSucursalLabel;
        private Label lblSucursalValue;

        private Button btnVolver;

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
            this.lblCuentaTitulo = new System.Windows.Forms.Label();
            this.txtCuenta = new System.Windows.Forms.TextBox();
            this.btnConsultar = new System.Windows.Forms.Button();
            this.panelInfo = new System.Windows.Forms.Panel();
            this.lblInfoTitulo = new System.Windows.Forms.Label();
            this.lblCodCuentaLabel = new System.Windows.Forms.Label();
            this.lblCodCuentaValue = new System.Windows.Forms.Label();
            this.lblSaldoLabel = new System.Windows.Forms.Label();
            this.lblSaldoValue = new System.Windows.Forms.Label();
            this.lblContadorLabel = new System.Windows.Forms.Label();
            this.lblContadorValue = new System.Windows.Forms.Label();
            this.lblFechaLabel = new System.Windows.Forms.Label();
            this.lblFechaValue = new System.Windows.Forms.Label();
            this.lblEstadoLabel = new System.Windows.Forms.Label();
            this.lblEstadoValue = new System.Windows.Forms.Label();
            this.lblSucursalLabel = new System.Windows.Forms.Label();
            this.lblSucursalValue = new System.Windows.Forms.Label();
            this.btnVolver = new System.Windows.Forms.Button();
            this.panelBackground.SuspendLayout();
            this.panelCard.SuspendLayout();
            this.panelInfo.SuspendLayout();
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
            this.panelCard.Controls.Add(this.lblCuentaTitulo);
            this.panelCard.Controls.Add(this.txtCuenta);
            this.panelCard.Controls.Add(this.btnConsultar);
            this.panelCard.Controls.Add(this.panelInfo);
            this.panelCard.Controls.Add(this.btnVolver);
            this.panelCard.Location = new System.Drawing.Point(60, 90);
            this.panelCard.Name = "panelCard";
            this.panelCard.Padding = new System.Windows.Forms.Padding(40, 30, 40, 30);
            this.panelCard.Size = new System.Drawing.Size(1160, 600);
            this.panelCard.TabIndex = 3;
            this.panelCard.Paint += new System.Windows.Forms.PaintEventHandler(this.panelCard_Paint);
            // 
            // lblTitulo
            // 
            this.lblTitulo.AutoSize = true;
            this.lblTitulo.Font = new System.Drawing.Font("Segoe UI", 22F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(51)))), ((int)(((byte)(51)))), ((int)(((byte)(51)))));
            this.lblTitulo.Location = new System.Drawing.Point(40, 20);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(442, 41);
            this.lblTitulo.TabIndex = 0;
            this.lblTitulo.Text = "ℹ Consultar Datos de Cuenta";
            // 
            // lblCuentaTitulo
            // 
            this.lblCuentaTitulo.AutoSize = true;
            this.lblCuentaTitulo.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblCuentaTitulo.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(85)))), ((int)(((byte)(85)))), ((int)(((byte)(85)))));
            this.lblCuentaTitulo.Location = new System.Drawing.Point(40, 80);
            this.lblCuentaTitulo.Name = "lblCuentaTitulo";
            this.lblCuentaTitulo.Size = new System.Drawing.Size(135, 19);
            this.lblCuentaTitulo.TabIndex = 1;
            this.lblCuentaTitulo.Text = "Número de Cuenta";
            // 
            // txtCuenta
            // 
            this.txtCuenta.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(235)))), ((int)(((byte)(243)))), ((int)(((byte)(255)))));
            this.txtCuenta.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtCuenta.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.txtCuenta.Location = new System.Drawing.Point(40, 110);
            this.txtCuenta.Name = "txtCuenta";
            this.txtCuenta.Multiline = true;
            this.txtCuenta.Size = new System.Drawing.Size(1080, 42);
            this.txtCuenta.TabIndex = 2;
            // 
            // btnConsultar
            // 
            this.btnConsultar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(118)))), ((int)(((byte)(75)))), ((int)(((byte)(162)))));
            this.btnConsultar.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnConsultar.FlatAppearance.BorderSize = 0;
            this.btnConsultar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnConsultar.Font = new System.Drawing.Font("Segoe UI", 11F, System.Drawing.FontStyle.Bold);
            this.btnConsultar.ForeColor = System.Drawing.Color.White;
            this.btnConsultar.Location = new System.Drawing.Point(40, 172);
            this.btnConsultar.Name = "btnConsultar";
            this.btnConsultar.Size = new System.Drawing.Size(1080, 50);
            this.btnConsultar.TabIndex = 3;
            this.btnConsultar.Text = "🔍 Consultar";
            this.btnConsultar.UseVisualStyleBackColor = false;
            this.btnConsultar.Click += new System.EventHandler(this.btnConsultar_Click);
            this.btnConsultar.Paint += new System.Windows.Forms.PaintEventHandler(this.btnConsultar_Paint);
            // 
            // panelInfo
            // 
            this.panelInfo.BackColor = System.Drawing.Color.Transparent;
            this.panelInfo.Controls.Add(this.lblInfoTitulo);
            this.panelInfo.Controls.Add(this.lblCodCuentaLabel);
            this.panelInfo.Controls.Add(this.lblCodCuentaValue);
            this.panelInfo.Controls.Add(this.lblSaldoLabel);
            this.panelInfo.Controls.Add(this.lblSaldoValue);
            this.panelInfo.Controls.Add(this.lblContadorLabel);
            this.panelInfo.Controls.Add(this.lblContadorValue);
            this.panelInfo.Controls.Add(this.lblFechaLabel);
            this.panelInfo.Controls.Add(this.lblFechaValue);
            this.panelInfo.Controls.Add(this.lblEstadoLabel);
            this.panelInfo.Controls.Add(this.lblEstadoValue);
            this.panelInfo.Controls.Add(this.lblSucursalLabel);
            this.panelInfo.Controls.Add(this.lblSucursalValue);
            this.panelInfo.Location = new System.Drawing.Point(40, 225);
            this.panelInfo.Name = "panelInfo";
            this.panelInfo.Size = new System.Drawing.Size(1080, 260);
            this.panelInfo.TabIndex = 4;
            this.panelInfo.Visible = false;
            this.panelInfo.Paint += new System.Windows.Forms.PaintEventHandler(this.panelInfo_Paint);
            // 
            // lblInfoTitulo
            // 
            this.lblInfoTitulo.AutoSize = true;
            this.lblInfoTitulo.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.lblInfoTitulo.ForeColor = System.Drawing.Color.White;
            this.lblInfoTitulo.Location = new System.Drawing.Point(30, 20);
            this.lblInfoTitulo.Name = "lblInfoTitulo";
            this.lblInfoTitulo.Size = new System.Drawing.Size(310, 30);
            this.lblInfoTitulo.TabIndex = 0;
            this.lblInfoTitulo.Text = "💳 Información de la Cuenta";
            // 
            // lblCodCuentaLabel
            // 
            this.lblCodCuentaLabel.AutoSize = true;
            this.lblCodCuentaLabel.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblCodCuentaLabel.ForeColor = System.Drawing.Color.White;
            this.lblCodCuentaLabel.Location = new System.Drawing.Point(30, 70);
            this.lblCodCuentaLabel.Name = "lblCodCuentaLabel";
            this.lblCodCuentaLabel.Size = new System.Drawing.Size(145, 19);
            this.lblCodCuentaLabel.TabIndex = 1;
            this.lblCodCuentaLabel.Text = "# Código de Cuenta:";
            // 
            // lblCodCuentaValue
            // 
            this.lblCodCuentaValue.AutoSize = true;
            this.lblCodCuentaValue.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblCodCuentaValue.ForeColor = System.Drawing.Color.White;
            this.lblCodCuentaValue.Location = new System.Drawing.Point(30, 95);
            this.lblCodCuentaValue.Name = "lblCodCuentaValue";
            this.lblCodCuentaValue.Size = new System.Drawing.Size(15, 19);
            this.lblCodCuentaValue.TabIndex = 2;
            this.lblCodCuentaValue.Text = "-";
            // 
            // lblSaldoLabel
            // 
            this.lblSaldoLabel.AutoSize = true;
            this.lblSaldoLabel.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblSaldoLabel.ForeColor = System.Drawing.Color.White;
            this.lblSaldoLabel.Location = new System.Drawing.Point(320, 70);
            this.lblSaldoLabel.Name = "lblSaldoLabel";
            this.lblSaldoLabel.Size = new System.Drawing.Size(63, 19);
            this.lblSaldoLabel.TabIndex = 3;
            this.lblSaldoLabel.Text = "$ Saldo:";
            // 
            // lblSaldoValue
            // 
            this.lblSaldoValue.AutoSize = true;
            this.lblSaldoValue.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold);
            this.lblSaldoValue.ForeColor = System.Drawing.Color.White;
            this.lblSaldoValue.Location = new System.Drawing.Point(320, 92);
            this.lblSaldoValue.Name = "lblSaldoValue";
            this.lblSaldoValue.Size = new System.Drawing.Size(20, 25);
            this.lblSaldoValue.TabIndex = 4;
            this.lblSaldoValue.Text = "-";
            // 
            // lblContadorLabel
            // 
            this.lblContadorLabel.AutoSize = true;
            this.lblContadorLabel.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblContadorLabel.ForeColor = System.Drawing.Color.White;
            this.lblContadorLabel.Location = new System.Drawing.Point(650, 70);
            this.lblContadorLabel.Name = "lblContadorLabel";
            this.lblContadorLabel.Size = new System.Drawing.Size(188, 19);
            this.lblContadorLabel.TabIndex = 5;
            this.lblContadorLabel.Text = "Contador de Movimientos:";
            // 
            // lblContadorValue
            // 
            this.lblContadorValue.AutoSize = true;
            this.lblContadorValue.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblContadorValue.ForeColor = System.Drawing.Color.White;
            this.lblContadorValue.Location = new System.Drawing.Point(650, 95);
            this.lblContadorValue.Name = "lblContadorValue";
            this.lblContadorValue.Size = new System.Drawing.Size(15, 19);
            this.lblContadorValue.TabIndex = 6;
            this.lblContadorValue.Text = "-";
            // 
            // lblFechaLabel
            // 
            this.lblFechaLabel.AutoSize = true;
            this.lblFechaLabel.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblFechaLabel.ForeColor = System.Drawing.Color.White;
            this.lblFechaLabel.Location = new System.Drawing.Point(30, 140);
            this.lblFechaLabel.Name = "lblFechaLabel";
            this.lblFechaLabel.Size = new System.Drawing.Size(159, 19);
            this.lblFechaLabel.TabIndex = 7;
            this.lblFechaLabel.Text = "📅 Fecha de Creación:";
            // 
            // lblFechaValue
            // 
            this.lblFechaValue.AutoSize = true;
            this.lblFechaValue.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblFechaValue.ForeColor = System.Drawing.Color.White;
            this.lblFechaValue.Location = new System.Drawing.Point(30, 165);
            this.lblFechaValue.Name = "lblFechaValue";
            this.lblFechaValue.Size = new System.Drawing.Size(15, 19);
            this.lblFechaValue.TabIndex = 8;
            this.lblFechaValue.Text = "-";
            // 
            // lblEstadoLabel
            // 
            this.lblEstadoLabel.AutoSize = true;
            this.lblEstadoLabel.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblEstadoLabel.ForeColor = System.Drawing.Color.White;
            this.lblEstadoLabel.Location = new System.Drawing.Point(320, 140);
            this.lblEstadoLabel.Name = "lblEstadoLabel";
            this.lblEstadoLabel.Size = new System.Drawing.Size(81, 19);
            this.lblEstadoLabel.TabIndex = 9;
            this.lblEstadoLabel.Text = "ℹ Estado:";
            // 
            // lblEstadoValue
            // 
            this.lblEstadoValue.AutoSize = true;
            this.lblEstadoValue.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblEstadoValue.ForeColor = System.Drawing.Color.White;
            this.lblEstadoValue.Location = new System.Drawing.Point(320, 165);
            this.lblEstadoValue.Name = "lblEstadoValue";
            this.lblEstadoValue.Size = new System.Drawing.Size(15, 19);
            this.lblEstadoValue.TabIndex = 10;
            this.lblEstadoValue.Text = "-";
            // 
            // lblSucursalLabel
            // 
            this.lblSucursalLabel.AutoSize = true;
            this.lblSucursalLabel.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.lblSucursalLabel.ForeColor = System.Drawing.Color.White;
            this.lblSucursalLabel.Location = new System.Drawing.Point(650, 140);
            this.lblSucursalLabel.Name = "lblSucursalLabel";
            this.lblSucursalLabel.Size = new System.Drawing.Size(92, 19);
            this.lblSucursalLabel.TabIndex = 11;
            this.lblSucursalLabel.Text = "🏢 Sucursal:";
            // 
            // lblSucursalValue
            // 
            this.lblSucursalValue.AutoSize = true;
            this.lblSucursalValue.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblSucursalValue.ForeColor = System.Drawing.Color.White;
            this.lblSucursalValue.Location = new System.Drawing.Point(650, 165);
            this.lblSucursalValue.Name = "lblSucursalValue";
            this.lblSucursalValue.Size = new System.Drawing.Size(15, 19);
            this.lblSucursalValue.TabIndex = 12;
            this.lblSucursalValue.Text = "-";
            // 
            // btnVolver
            // 
            this.btnVolver.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(245)))), ((int)(((byte)(245)))), ((int)(((byte)(245)))));
            this.btnVolver.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnVolver.FlatAppearance.BorderSize = 0;
            this.btnVolver.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnVolver.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnVolver.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(85)))), ((int)(((byte)(85)))), ((int)(((byte)(85)))));
            this.btnVolver.Location = new System.Drawing.Point(490, 510);
            this.btnVolver.Name = "btnVolver";
            this.btnVolver.Size = new System.Drawing.Size(180, 40);
            this.btnVolver.TabIndex = 5;
            this.btnVolver.Text = "← Volver al Menú";
            this.btnVolver.UseVisualStyleBackColor = false;
            this.btnVolver.Click += new System.EventHandler(this.btnVolver_Click);
            // 
            // FrmDatos
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(1280, 720);
            this.Controls.Add(this.panelBackground);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "FrmDatos";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Eureka Bank - Consultar Datos de Cuenta";
            this.panelBackground.ResumeLayout(false);
            this.panelBackground.PerformLayout();
            this.panelCard.ResumeLayout(false);
            this.panelCard.PerformLayout();
            this.panelInfo.ResumeLayout(false);
            this.panelInfo.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion
    }
}
