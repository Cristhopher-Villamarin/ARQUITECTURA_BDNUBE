package ec.edu.monster.vista;

import ec.edu.monster.service.CuentaService;
import ec.edu.monster.ws.CuentaModel;

import javax.swing.*;
import java.awt.*;

public class RetiroView extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(RetiroView.class.getName());

    private final CuentaService cuentaService = new CuentaService();
    private CuentaModel cuentaActual = null;
    private String numeroCuentaActual = null;

    // UI
    private JPanel panelBackground;
    private JPanel panelCard;
    private JTextField txtNumeroCuenta;
    private JTextField txtMonto;
    private JButton btnBuscarCuenta;
    private JButton btnRetirar;
    private JButton btnVolverMenu;

    private JLabel lblCuentaInfo;
    private JLabel lblSaldoInfo;

    // Banner mensajes
    private JPanel messagePanel;
    private JLabel messageLabel;
    private Color messageBgColor;
    private Color messageFgColor;

    public RetiroView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        Color purple = new Color(0x76, 0x4b, 0xa2);
        Color gradientRight = new Color(0x66, 0x7e, 0xea);
        Color labelGray = new Color(110, 110, 110);
        Color inputBorder = new Color(220, 230, 255);

        // ===== FONDO MORADO =====
        panelBackground = new JPanel(null);
        panelBackground.setBackground(purple);
        panelBackground.setBounds(0, 0, 1100, 650);
        getContentPane().add(panelBackground);

        // ===== CARD BLANCA =====
        panelCard = new JPanel(null) {
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
            public boolean isOpaque() { return false; }
        };
        panelCard.setBounds(60, 40, 980, 540);
        panelBackground.add(panelCard);

        // ===== TÍTULO =====
        JLabel lblTitulo = new JLabel("Realizar Retiro");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setBounds(60, 35, 400, 35);
        panelCard.add(lblTitulo);

        // ===== BANNER MENSAJE =====
        messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 8)) {
            @Override
            protected void paintComponent(Graphics g) {
                if (messageBgColor != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(messageBgColor);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() { return false; }
        };
        messageLabel = new JLabel();
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messagePanel.add(messageLabel);
        messagePanel.setVisible(false);
        messagePanel.setBounds(60, 90, 860, 40);
        panelCard.add(messagePanel);

        // ===== LABEL NÚMERO DE CUENTA =====
        JLabel lblNumCuenta = new JLabel("Número de Cuenta");
        lblNumCuenta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNumCuenta.setForeground(labelGray);
        lblNumCuenta.setBounds(60, 145, 200, 18);
        panelCard.add(lblNumCuenta);

        // ===== INPUT NÚMERO DE CUENTA =====
        txtNumeroCuenta = new JTextField();
        txtNumeroCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNumeroCuenta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorder, 2, true),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        txtNumeroCuenta.setBounds(60, 170, 860, 45);
        panelCard.add(txtNumeroCuenta);

        // ===== BOTÓN CONSULTAR CUENTA =====
        btnBuscarCuenta = new JButton("Consultar Cuenta");
        btnBuscarCuenta.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnBuscarCuenta.setForeground(Color.WHITE);
        btnBuscarCuenta.setBounds(60, 230, 860, 45);
        makeGradientButton(btnBuscarCuenta, purple, gradientRight);
        panelCard.add(btnBuscarCuenta);

        // ===== INFO CUENTA / SALDO =====
        lblCuentaInfo = new JLabel("");
        lblCuentaInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblCuentaInfo.setForeground(new Color(80, 80, 80));
        lblCuentaInfo.setBounds(60, 285, 400, 18);
        panelCard.add(lblCuentaInfo);

        lblSaldoInfo = new JLabel("");
        lblSaldoInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSaldoInfo.setForeground(new Color(40, 40, 40));
        lblSaldoInfo.setBounds(60, 305, 400, 20);
        panelCard.add(lblSaldoInfo);

        // ===== LABEL MONTO =====
        JLabel lblMonto = new JLabel("$ Monto a retirar");
        lblMonto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMonto.setForeground(labelGray);
        lblMonto.setBounds(60, 335, 200, 18);
        panelCard.add(lblMonto);

        // ===== INPUT MONTO =====
        txtMonto = new JTextField();
        txtMonto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMonto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorder, 2, true),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        txtMonto.setBounds(60, 360, 860, 45);
        panelCard.add(txtMonto);

        // ===== BOTÓN RETIRAR =====
        btnRetirar = new JButton("Realizar Retiro");
        btnRetirar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRetirar.setForeground(Color.WHITE);
        btnRetirar.setBounds(60, 425, 860, 50);
        makeGradientButton(btnRetirar, purple, gradientRight);
        panelCard.add(btnRetirar);

        // ===== BOTÓN VOLVER MENÚ =====
        btnVolverMenu = new JButton("Volver al Menú");
        btnVolverMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolverMenu.setForeground(new Color(80, 80, 80));
        btnVolverMenu.setFocusPainted(false);
        btnVolverMenu.setContentAreaFilled(false);
        btnVolverMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolverMenu.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 2, true),
                BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        btnVolverMenu.setBounds((panelCard.getWidth() - 200) / 2, 485, 200, 40);
        panelCard.add(btnVolverMenu);

        // ===== LISTENERS =====

        btnBuscarCuenta.addActionListener(e -> buscarCuenta());
        txtNumeroCuenta.addActionListener(e -> buscarCuenta());

        btnRetirar.addActionListener(e -> realizarRetiro());
        txtMonto.addActionListener(e -> realizarRetiro());

        btnVolverMenu.addActionListener(e -> {
            dispose();
        });

        setContentPane(panelBackground);
    }

    // ========= LÓGICA =========

    private void buscarCuenta() {
        ocultarMensaje();
        String numeroCuenta = clean(txtNumeroCuenta.getText());
        
        if (numeroCuenta.isEmpty()) {
            mostrarError("Por favor, ingrese un número de cuenta.");
            cuentaActual = null;
            numeroCuentaActual = null;
            limpiarInfoCuenta();
            return;
        }
        
        try {
            CuentaModel cuentaModel = cuentaService.obtenerCuentaPorNumero(numeroCuenta);
            
            if (cuentaModel == null) {
                mostrarError("No se encontraron datos para la cuenta: " + numeroCuenta);
                cuentaActual = null;
                numeroCuentaActual = null;
                limpiarInfoCuenta();
                return;
            }
            
            cuentaActual = cuentaModel;
            numeroCuentaActual = numeroCuenta;
            
            lblCuentaInfo.setText("Cuenta: " + numeroCuenta);
            lblSaldoInfo.setText("Saldo disponible: $ " +
                    String.format("%.2f", cuentaModel.getDecCuenSaldo()));

            txtMonto.setText("");
            mostrarExito("Cuenta encontrada. Ingrese el monto a retirar.");

        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error al obtener los datos de la cuenta", ex);
            mostrarError("Error al obtener los datos de la cuenta: " + ex.getMessage());
            cuentaActual = null;
            numeroCuentaActual = null;
            limpiarInfoCuenta();
        }
    }

    private void realizarRetiro() {
        ocultarMensaje();

        if (cuentaActual == null || numeroCuentaActual == null) {
            mostrarError("Primero debe consultar una cuenta válida.");
            return;
        }
        
        String montoStr = clean(txtMonto.getText());
        if (montoStr.isEmpty()) {
            mostrarError("Por favor, ingrese un monto a retirar.");
            return;
        }
        
        try {
            double monto = Double.parseDouble(montoStr);
            if (monto <= 0) {
                mostrarError("El monto debe ser mayor a cero.");
                return;
            }
            
            // Obtener saldo actualizado
            CuentaModel cuentaActualizada = cuentaService.obtenerCuentaPorNumero(numeroCuentaActual);
            if (cuentaActualizada == null) {
                mostrarError("No se pudo obtener la información actualizada de la cuenta.");
                return;
            }
            cuentaActual = cuentaActualizada;
            
            double saldoActual = cuentaActual.getDecCuenSaldo();
            lblSaldoInfo.setText("Saldo disponible: $ " + String.format("%.2f", saldoActual));

            if (saldoActual < monto) {
                mostrarError("Saldo insuficiente. Disponible: $ "
                        + String.format("%.2f", saldoActual)
                        + " | Solicitado: $ " + String.format("%.2f", monto));
                return;
            }
            
            // Llamar al método de retiro
            boolean exito = cuentaService.realizarRetiro(numeroCuentaActual, montoStr);
            
            if (exito) {
                mostrarExito("Retiro realizado con éxito.");
                CuentaModel despues = cuentaService.obtenerCuentaPorNumero(numeroCuentaActual);
                if (despues != null) {
                    cuentaActual = despues;
                    lblSaldoInfo.setText("Saldo disponible: $ " +
                            String.format("%.2f", despues.getDecCuenSaldo()));
                }
                txtMonto.setText("");
            } else {
                mostrarError("Error al realizar el retiro. Verifique que tenga fondos suficientes.");
            }
            
        } catch (NumberFormatException e) {
            mostrarError("Por favor, ingrese un monto válido (número).");
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al realizar el retiro", e);
            mostrarError("Error al realizar el retiro: " + e.getMessage());
        }
    }

    private void limpiarInfoCuenta() {
        lblCuentaInfo.setText("");
        lblSaldoInfo.setText("");
    }

    // ========= BANNERS =========

    private void mostrarExito(String texto) {
        messageBgColor = new Color(220, 248, 224);      // verde suave
        messageFgColor = new Color(0, 140, 70);         // verde fuerte
        messageLabel.setForeground(messageFgColor);
        messageLabel.setText(texto);
        messagePanel.setVisible(true);
        messagePanel.repaint();
    }

    private void mostrarError(String texto) {
        messageBgColor = new Color(252, 226, 226);      // rojo suave
        messageFgColor = new Color(180, 40, 40);        // rojo fuerte
        messageLabel.setForeground(messageFgColor);
        messageLabel.setText(texto);
        messagePanel.setVisible(true);
        messagePanel.repaint();
    }

    private void ocultarMensaje() {
        messagePanel.setVisible(false);
        messageLabel.setText("");
        messageBgColor = null;
        messageFgColor = null;
    }

    private String clean(String s) {
        return s == null ? "" : s.trim();
    }

    // ========= BOTONES DEGRADADO =========

    private void makeGradientButton(JButton button, Color left, Color right) {
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, left, c.getWidth(), c.getHeight(), right);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 18, 18);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        EventQueue.invokeLater(() -> new RetiroView().setVisible(true));
    }
}
