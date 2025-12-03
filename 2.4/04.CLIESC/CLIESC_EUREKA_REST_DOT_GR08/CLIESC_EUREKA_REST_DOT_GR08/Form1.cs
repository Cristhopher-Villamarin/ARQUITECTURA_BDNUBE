using System;
using System.Drawing;
using System.Threading.Tasks;
using System.Windows.Forms;
using CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.controller;

namespace CLIESC_EUREKA_REST_DOT_GR08
{
    public partial class Form1 : Form
    {
        private LoginController _controller;

        public Form1()
        {
            InitializeComponent();
            _controller = new LoginController(this);

            // Configurar placeholders manuales
            SetPlaceholder(txtUsuario, "Ingrese su usuario");
            SetPlaceholder(txtContrasena, "Ingrese su contraseña", isPassword: true);

            btnLogin.Click += async (s, e) => await BtnLogin_ClickAsync();
        }

        // Expuestos al controlador
        public string Usuario
        {
            get => IsPlaceholderActive(txtUsuario) ? "" : txtUsuario.Text;
            set => txtUsuario.Text = value;
        }

        public string Contrasena
        {
            get => IsPlaceholderActive(txtContrasena) ? "" : txtContrasena.Text;
            set => txtContrasena.Text = value;
        }

        public string Mensaje
        {
            get => lblMensaje.Text;
            set => lblMensaje.Text = value;
        }

        public Color MensajeColor
        {
            get => lblMensaje.ForeColor;
            set => lblMensaje.ForeColor = value;
        }
        private void LblTitulo_Paint(object sender, PaintEventArgs e)
{
    e.Graphics.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;

    var rect = lblTitulo.ClientRectangle;
    if (rect.Width <= 0 || rect.Height <= 0) return;

    using (var brush = new System.Drawing.Drawing2D.LinearGradientBrush(
               rect,
               Color.FromArgb(102, 126, 234),   // azul
               Color.FromArgb(118, 75, 162),   // morado
               0f))
    {
        var sf = new StringFormat
        {
            Alignment = StringAlignment.Center,
            LineAlignment = StringAlignment.Center
        };

        e.Graphics.DrawString(
            lblTitulo.Text,
            lblTitulo.Font,
            brush,
            rect,
            sf);
    }
}


        private async Task BtnLogin_ClickAsync()
        {
            lblMensaje.Text = "";
            await _controller.IniciarSesionAsync();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        }

        // ==== PLACEHOLDERS (para .NET Framework) ====

        private void SetPlaceholder(TextBox tb, string text, bool isPassword = false)
        {
            tb.Tag = new PlaceholderState
            {
                Text = text,
                IsPassword = isPassword
            };

            tb.ForeColor = Color.Gray;
            tb.Text = text;
            if (isPassword)
                tb.PasswordChar = '\0';

            tb.GotFocus += RemovePlaceholder;
            tb.LostFocus += ApplyPlaceholderIfEmpty;
        }

        private void RemovePlaceholder(object sender, EventArgs e)
        {
            var tb = sender as TextBox;
            if (tb == null) return;
            var st = tb.Tag as PlaceholderState;
            if (st == null) return;

            if (tb.ForeColor == Color.Gray && tb.Text == st.Text)
            {
                tb.Text = "";
                tb.ForeColor = Color.FromArgb(60, 60, 60);
                if (st.IsPassword)
                    tb.PasswordChar = '●';
            }
        }

        private void ApplyPlaceholderIfEmpty(object sender, EventArgs e)
        {
            var tb = sender as TextBox;
            if (tb == null) return;
            var st = tb.Tag as PlaceholderState;
            if (st == null) return;

            if (string.IsNullOrWhiteSpace(tb.Text))
            {
                tb.ForeColor = Color.Gray;
                tb.Text = st.Text;
                if (st.IsPassword)
                    tb.PasswordChar = '\0';
            }
        }

        private bool IsPlaceholderActive(TextBox tb)
        {
            var st = tb.Tag as PlaceholderState;
            return st != null && tb.ForeColor == Color.Gray && tb.Text == st.Text;
        }

        private class PlaceholderState
        {
            public string Text { get; set; }
            public bool IsPassword { get; set; }
        }
    }
}
