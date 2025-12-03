package ec.edu.monster.vista;

import ec.edu.monster.service.CuentaService;
import ec.edu.monster.ws.CuentaModel;

import javax.swing.*;
import java.awt.*;

public class TransferenciaView extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(TransferenciaView.class.getName());

    private final CuentaService cuentaService = new CuentaService();

    // UI
    private JPanel panelBackground;
    private JPanel panelCard;
    private JPanel messagePanel;
    private JLabel messageLabel;

    private JTextField txtCuentaOrigen;
    private JTextField txtCuentaDestino;
    private JTextField txtMonto;
    private JButton btnTransferir;
    private JButton btnVolverMenu;

    private Color messageBgColor;
    private Color messageFgColor;

    public TransferenciaView() {
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

        // ===== Fondo morado =====
        panelBackground = new JPanel(null);
        panelBackground.setBackground(purple);
        panelBackground.setBounds(0, 0, 1100, 650);
        getContentPane().add(panelBackground);

        // ===== Card blanca =====
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

        // ===== Título =====
        JLabel lblTitulo = new JLabel("Realizar Transferencia");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setBounds(60, 35, 400, 35);
        panelCard.add(lblTitulo);

        // ===== Banner mensajes =====
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

        int y = 145;

        // ===== Cuenta Origen =====
        JLabel lblOrigen = new JLabel("Cuenta Origen");
        lblOrigen.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblOrigen.setForeground(labelGray);
        lblOrigen.setBounds(60, y, 200, 18);
        panelCard.add(lblOrigen);

        y += 25;
        txtCuentaOrigen = new JTextField();
        txtCuentaOrigen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCuentaOrigen.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorder, 2, true),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        txtCuentaOrigen.setBounds(60, y, 860, 45);
        panelCard.add(txtCuentaOrigen);

        // ===== Cuenta Destino =====
        y += 65;
        JLabel lblDestino = new JLabel("Cuenta Destino");
        lblDestino.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDestino.setForeground(labelGray);
        lblDestino.setBounds(60, y, 200, 18);
        panelCard.add(lblDestino);

        y += 25;
        txtCuentaDestino = new JTextField();
        txtCuentaDestino.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCuentaDestino.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorder, 2, true),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        txtCuentaDestino.setBounds(60, y, 860, 45);
        panelCard.add(txtCuentaDestino);

        // ===== Monto =====
        y += 65;
        JLabel lblMonto = new JLabel("$ Monto");
        lblMonto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMonto.setForeground(labelGray);
        lblMonto.setBounds(60, y, 200, 18);
        panelCard.add(lblMonto);

        y += 25;
        txtMonto = new JTextField();
        txtMonto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMonto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorder, 2, true),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        txtMonto.setBounds(60, y, 860, 45);
        panelCard.add(txtMonto);

        // ===== Botón Realizar Transferencia (degradado + redondo) =====
        y += 65;
        btnTransferir = new JButton("Realizar Transferencia");
        btnTransferir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnTransferir.setForeground(Color.WHITE);
        btnTransferir.setBounds(60, y, 860, 50);
        makeGradientButton(btnTransferir, purple, gradientRight);
        panelCard.add(btnTransferir);

        // ===== Botón Volver al Menú (mismo estilo que Depósito/Retiro) =====
        btnVolverMenu = new JButton("Volver al Menú") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c1 = new Color(0x76, 0x4b, 0xa2);
                Color c2 = new Color(0x66, 0x7e, 0xea);
                GradientPaint gp = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2);

                int arc = 22;
                int margin = 3;
                g2.setPaint(gp);
                g2.fillRoundRect(margin, margin,
                        getWidth() - margin * 2,
                        getHeight() - margin * 2,
                        arc, arc);
                g2.dispose();

                super.paintComponent(g);
            }
            @Override
            public boolean isOpaque() { return false; }
        };
        btnVolverMenu.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnVolverMenu.setForeground(Color.WHITE);
        btnVolverMenu.setFocusPainted(false);
        btnVolverMenu.setContentAreaFilled(false);
        btnVolverMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
        btnVolverMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        int volverWidth = 220;
        int volverHeight = 46;
        int volverX = (panelCard.getWidth() - volverWidth) / 2;
        int volverY = 465;
        btnVolverMenu.setBounds(volverX, volverY, volverWidth, volverHeight);
        panelCard.add(btnVolverMenu);

        // ===== Listeners =====
        btnTransferir.addActionListener(e -> realizarTransferencia());
        txtMonto.addActionListener(e -> realizarTransferencia());

        btnVolverMenu.addActionListener(e -> {
            dispose();
        });

        setContentPane(panelBackground);
    }

    // ========= Lógica =========

    private void realizarTransferencia() {
        ocultarMensaje();

        String origen = clean(txtCuentaOrigen.getText());
        String destino = clean(txtCuentaDestino.getText());
        String montoStr = clean(txtMonto.getText());

        if (origen.isEmpty() || destino.isEmpty() || montoStr.isEmpty()) {
            mostrarError("Complete cuenta origen, cuenta destino y monto.");
            return;
        }
        
        if (origen.equals(destino)) {
            mostrarError("La cuenta origen y destino no pueden ser la misma.");
            return;
        }
        
        double monto;
        try {
            monto = Double.parseDouble(montoStr);
        } catch (NumberFormatException ex) {
            mostrarError("El monto debe ser un número válido.");
            return;
        }
        
        if (monto <= 0) {
            mostrarError("El monto debe ser mayor a cero.");
            return;
        }
        
        try {
            // Validar cuentas
            CuentaModel cOrigen = cuentaService.obtenerCuentaPorNumero(origen);
            if (cOrigen == null) {
                mostrarError("No se encontró la cuenta origen: " + origen);
                return;
            }
            
            CuentaModel cDestino = cuentaService.obtenerCuentaPorNumero(destino);
            if (cDestino == null) {
                mostrarError("No se encontró la cuenta destino: " + destino);
                return;
            }
            
            // Validar saldo origen
            if (cOrigen.getDecCuenSaldo() < monto) {
                mostrarError("Saldo insuficiente en cuenta origen. Disponible: $ "
                        + String.format("%.2f", cOrigen.getDecCuenSaldo()));
                return;
            }
            
            // Llamar a tu servicio de transferencia
            boolean exito = cuentaService.realizarTransferencia(origen, destino, montoStr);
            
            if (exito) {
                mostrarExito("Transferencia realizada con éxito.");
                txtMonto.setText("");
            } else {
                mostrarError("Error al realizar la transferencia. Verifique los datos o el saldo.");
            }
            
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al realizar la transferencia", e);
            mostrarError("Error al realizar la transferencia: " + e.getMessage());
        }
    }

    // ========= Helpers mensajes =========

    private void mostrarExito(String texto) {
        messageBgColor = new Color(220, 248, 224);
        messageFgColor = new Color(0, 140, 70);
        messageLabel.setForeground(messageFgColor);
        messageLabel.setText(texto);
        messagePanel.setVisible(true);
        messagePanel.repaint();
    }

    private void mostrarError(String texto) {
        messageBgColor = new Color(252, 226, 226);
        messageFgColor = new Color(180, 40, 40);
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

    // ========= Botón degradado =========

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

        EventQueue.invokeLater(() -> new TransferenciaView().setVisible(true));
    }
}
