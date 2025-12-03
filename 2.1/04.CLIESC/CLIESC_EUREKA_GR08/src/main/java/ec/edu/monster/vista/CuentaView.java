package ec.edu.monster.vista;

import ec.edu.monster.service.CuentaService;
import ec.edu.monster.ws.CuentaModel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class CuentaView extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(CuentaView.class.getName());

    private final CuentaService cuentaService = new CuentaService();

    // Componentes
    private JPanel panelBackground;
    private JPanel panelCardTop;
    private JPanel panelInfoCard;
    private JTextField txtNumeroCuenta;
    private JButton btnBuscarCuenta;
    private javax.swing.JButton btnVolverMenu;

    private JLabel lblTitulo;
    private JLabel lblNumeroCuentaLabel;
    private JLabel lblCuentaEncontrada;
    private JLabel lblSaldoLabel;
    private JLabel lblSaldoEncontrado;
    private JLabel lblMovimientosLabel;
    private JLabel lblMovimientosEncontrado;

    public CuentaView() {
        initComponents();
    }

    private void initComponents() {
        // Frame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        Color purple = new Color(0x76, 0x4b, 0xa2);
        Color gradientRight = new Color(113, 137, 255);
        Color textGray = new Color(120, 120, 120);

        // Fondo morado
        panelBackground = new JPanel(null);
        panelBackground.setBackground(purple);
        panelBackground.setBounds(0, 0, 1100, 650);
        getContentPane().add(panelBackground);

        // CARD BLANCA PRINCIPAL (contiene todo)
        panelCardTop = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        panelCardTop.setBounds(40, 20, 1020, 580);
        panelBackground.add(panelCardTop);

        // TÍTULO
        lblTitulo = new JLabel("Consultar Datos de Cuenta");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setBounds(40, 25, 500, 35);
        panelCardTop.add(lblTitulo);

        // LABEL "Número de Cuenta"
        JLabel lblNumero = new JLabel("Número de Cuenta");
        lblNumero.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNumero.setForeground(textGray);
        lblNumero.setBounds(40, 80, 200, 18);
        panelCardTop.add(lblNumero);

        // TEXTFIELD grande
        txtNumeroCuenta = new JTextField();
        txtNumeroCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNumeroCuenta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 255), 2, true),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        txtNumeroCuenta.setBounds(40, 105, 940, 45);
        panelCardTop.add(txtNumeroCuenta);

        // BOTÓN CONSULTAR (barra morada)
        btnBuscarCuenta = new JButton("Consultar");
        btnBuscarCuenta.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnBuscarCuenta.setForeground(Color.WHITE);
        btnBuscarCuenta.setFocusPainted(false);
        btnBuscarCuenta.setBorderPainted(false);
        btnBuscarCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscarCuenta.setBounds(40, 165, 940, 45);
        // degradado visual simple usando UI personalizada
        btnBuscarCuenta = makeGradientButton(btnBuscarCuenta, purple, gradientRight);
        panelCardTop.add(btnBuscarCuenta);

        // CARD DE INFORMACIÓN (degradado)
        panelInfoCard = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(
                        0, 0, purple,
                        getWidth(), getHeight(), gradientRight
                );
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        panelInfoCard.setBounds(40, 230, 940, 250);
        panelCardTop.add(panelInfoCard);

        // Título card info
        JLabel lblInfoTitle = new JLabel("Información de la Cuenta");
        lblInfoTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblInfoTitle.setForeground(Color.WHITE);
        lblInfoTitle.setBounds(40, 25, 400, 30);
        panelInfoCard.add(lblInfoTitle);

        // # Código de Cuenta
        JLabel lblCodigoCuentaTitle = new JLabel("# Código de Cuenta:");
        lblCodigoCuentaTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCodigoCuentaTitle.setForeground(Color.WHITE);
        lblCodigoCuentaTitle.setBounds(40, 80, 160, 20);
        panelInfoCard.add(lblCodigoCuentaTitle);

        lblCuentaEncontrada = new JLabel("");
        lblCuentaEncontrada.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCuentaEncontrada.setForeground(Color.WHITE);
        lblCuentaEncontrada.setBounds(40, 105, 200, 24);
        panelInfoCard.add(lblCuentaEncontrada);

        // $ Saldo
        lblSaldoLabel = new JLabel("$ Saldo:");
        lblSaldoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSaldoLabel.setForeground(Color.WHITE);
        lblSaldoLabel.setBounds(320, 80, 80, 20);
        panelInfoCard.add(lblSaldoLabel);

        lblSaldoEncontrado = new JLabel("");
        lblSaldoEncontrado.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblSaldoEncontrado.setForeground(Color.WHITE);
        lblSaldoEncontrado.setBounds(320, 105, 200, 26);
        panelInfoCard.add(lblSaldoEncontrado);

        // Contador Movimientos
        lblMovimientosLabel = new JLabel("Contador de Movimientos:");
        lblMovimientosLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMovimientosLabel.setForeground(Color.WHITE);
        lblMovimientosLabel.setBounds(650, 80, 220, 20);
        panelInfoCard.add(lblMovimientosLabel);

        lblMovimientosEncontrado = new JLabel("");
        lblMovimientosEncontrado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMovimientosEncontrado.setForeground(Color.WHITE);
        lblMovimientosEncontrado.setBounds(650, 105, 200, 24);
        panelInfoCard.add(lblMovimientosEncontrado);

        // Inicialmente ocultar el panel de información
        panelInfoCard.setVisible(false);

        // ===== BOTÓN VOLVER AL MENÚ =====
        btnVolverMenu = new JButton("Volver al Menú") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c1 = new Color(0x76, 0x4b, 0xa2);
                Color c2 = new Color(0x66, 0x7e, 0xea);
                GradientPaint gp = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
                g2.dispose();
                super.paintComponent(g);
            }
            @Override
            public boolean isOpaque() { return false; }
        };
        btnVolverMenu.setFocusPainted(false);
        btnVolverMenu.setBorderPainted(false);
        btnVolverMenu.setContentAreaFilled(false);
        btnVolverMenu.setForeground(Color.WHITE);
        btnVolverMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolverMenu.setBorder(new LineBorder(Color.WHITE, 4, true));
        btnVolverMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // posición y agregado
        int btnWidth = 220;
        int btnHeight = 48;

        int x = (panelCardTop.getWidth() - btnWidth) / 2;
        int y = panelInfoCard.getY() + panelInfoCard.getHeight() + 20;

        btnVolverMenu.setBounds(x, y, btnWidth, btnHeight);
        panelCardTop.add(btnVolverMenu);

        // ===== ACCIONES =====

        // Consultar
        btnBuscarCuenta.addActionListener(e -> buscarCuenta());

        // Enter en el campo número
        txtNumeroCuenta.addActionListener(e -> buscarCuenta());

        // Volver al menú
        btnVolverMenu.addActionListener(e -> {
            dispose();
        });

        setContentPane(panelBackground);
    }

    private void buscarCuenta() {
        String numeroCuenta = txtNumeroCuenta.getText() != null
                ? txtNumeroCuenta.getText().trim()
                : "";
        
        if (numeroCuenta.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un número de cuenta.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            panelInfoCard.setVisible(false);
            return;
        }
        
        try {
            CuentaModel cuentaModel = cuentaService.obtenerCuentaPorNumero(numeroCuenta);
            
            if (cuentaModel == null) {
                limpiarLabels();
                panelInfoCard.setVisible(false);
                JOptionPane.showMessageDialog(this,
                        "No se encontraron datos para la cuenta: " + numeroCuenta,
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            lblCuentaEncontrada.setText(numeroCuenta);
            lblSaldoEncontrado.setText(String.format("$ %.2f", cuentaModel.getDecCuenSaldo()));
            lblMovimientosEncontrado.setText(String.valueOf(cuentaModel.getIntCuenContMov()));
            panelInfoCard.setVisible(true);

        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE,
                    "Error al obtener los datos de la cuenta", ex);
            JOptionPane.showMessageDialog(this,
                    "Error al obtener los datos de la cuenta: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            limpiarLabels();
            panelInfoCard.setVisible(false);
        }
    }

    private void limpiarLabels() {
        lblCuentaEncontrada.setText("");
        lblSaldoEncontrado.setText("");
        lblMovimientosEncontrado.setText("");
    }

    /**
     * Botón con "pseudo" degradado sencillo.
     */
    private JButton makeGradientButton(JButton base, Color left, Color right) {
        base.setContentAreaFilled(false);
        base.setOpaque(false);
        base.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        base.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, left, c.getWidth(), c.getHeight(), right);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 12, 12);
                g2.dispose();

                super.paint(g, c);
            }
        });
        return base;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }

        EventQueue.invokeLater(() -> new CuentaView().setVisible(true));
    }
}
