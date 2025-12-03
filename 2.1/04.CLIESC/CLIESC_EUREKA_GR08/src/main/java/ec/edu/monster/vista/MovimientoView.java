package ec.edu.monster.vista;

import ec.edu.monster.controlador.MovimientoController;

import javax.swing.*;
import java.awt.*;

public class MovimientoView extends JFrame {

    // Panels base
    private JPanel panelBackground;
    private JPanel panelCard;

    // Buscador
    private JTextField txtCuenta;
    private JButton btnBuscar;

    // Acciones extra
    private JButton btnVolverMenu;

    // Scroll para resultados
    private JScrollPane jScrollPane1;

    // Label fantasma que tenías como non-visual, lo dejamos pero no lo usamos
    private JLabel background;

    public MovimientoView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // ===== FRAME =====
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        Color purple = new Color(0x76, 0x4b, 0xa2);
        Color cardBg = Color.WHITE;
        Color labelColor = new Color(90, 90, 90);
        Color inputBorder = new Color(230, 230, 230);

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
                g2.setColor(cardBg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() { return false; }
        };
        panelCard.setLayout(null);
        panelCard.setBounds(60, 40, 980, 540);
        panelBackground.add(panelCard);


        // ====== LABEL "Número de Cuenta" ======
        JLabel lblNumCuenta = new JLabel("Número de Cuenta");
        lblNumCuenta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNumCuenta.setForeground(labelColor);
        lblNumCuenta.setBounds(40, 70, 300, 18);
        panelCard.add(lblNumCuenta);

        // ====== TEXTFIELD CUENTA ======
        txtCuenta = new JTextField();
        txtCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCuenta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorder, 2, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        txtCuenta.setBounds(40, 95, 900, 40);
        panelCard.add(txtCuenta);

        // ===== BOTÓN CONSULTAR MOVIMIENTOS (GRADIENTE + REDONDO) =====
        btnBuscar = new JButton("Consultar Movimientos");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBounds(40, 145, 900, 40);
        makeGradientButton(btnBuscar); // aplica degradado y bordes redondos
        panelCard.add(btnBuscar);

        // ====== SCROLL DE RESULTADOS ======
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);

        // Panel inicial para el scroll (MovimientoController lo reemplaza)
        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
        panelResultados.setBackground(cardBg);
        jScrollPane1.setViewportView(panelResultados);

        jScrollPane1.setBounds(40, 200, 900, 260);
        panelCard.add(jScrollPane1);

        // ===== BOTÓN VOLVER AL MENÚ (GRADIENTE + FRANJA BLANCA) =====
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
        int volverY = 480; // ajusta si quieres
        btnVolverMenu.setBounds(volverX, volverY, volverWidth, volverHeight);
        panelCard.add(btnVolverMenu);

        // Consultar movimientos
        btnBuscar.addActionListener(e -> {
            MovimientoController controller = new MovimientoController();
            String cuenta = txtCuenta.getText();

            if (cuenta == null || cuenta.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Por favor, ingrese un número de cuenta válido.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            controller.cargarMovimientos(cuenta, this);
        });

        // Enter = consultar
        txtCuenta.addActionListener(e -> btnBuscar.doClick());

        // Volver al menú
        btnVolverMenu.addActionListener(e -> {
            dispose();
        });

        setContentPane(panelBackground);
    }

    /**
     * Celdas de movimiento con estilo morado/redondeado.
     * Usado desde MovimientoController.
     */
    public JPanel crearCelda(String cuenta,
                             String fecha,
                             String movimiento,
                             String descripcion,
                             String tipo,
                             String importe,
                             String saldoDespues) {

        Color purple = new Color(0x76, 0x4b, 0xa2);

        JPanel panelCelda = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(248, 248, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 26, 26);
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };

        panelCelda.setLayout(new BorderLayout());
        panelCelda.setMaximumSize(new Dimension(880, 120));
        panelCelda.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        panelCelda.setBackground(new Color(248, 248, 255));

        // Panel interior
        JPanel datosPanel = new JPanel(new GridLayout(3, 2, 8, 4));
        datosPanel.setOpaque(false);

        JLabel lblCuenta = new JLabel(cuenta);
        lblCuenta.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JLabel lblDescripcion = new JLabel(descripcion);
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDescripcion.setForeground(new Color(80, 80, 80));

        JLabel lblFecha = new JLabel(fecha);
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFecha.setForeground(new Color(120, 120, 120));

        JLabel lblTipo = new JLabel(tipo);
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTipo.setForeground(new Color(120, 120, 120));

        JLabel lblMovimiento = new JLabel(movimiento);
        lblMovimiento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMovimiento.setForeground(new Color(120, 120, 120));

        JLabel lblImporte = new JLabel(importe);
        lblImporte.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblImporte.setForeground(purple);

        JLabel lblSaldo = new JLabel(saldoDespues);
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSaldo.setForeground(new Color(0, 153, 102));

        datosPanel.add(lblCuenta);
        datosPanel.add(lblDescripcion);
        datosPanel.add(lblFecha);
        datosPanel.add(lblTipo);
        datosPanel.add(lblMovimiento);
        datosPanel.add(lblImporte);

        panelCelda.add(datosPanel, BorderLayout.CENTER);

        // Saldo al pie, alineado a la derecha
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.add(lblSaldo, BorderLayout.EAST);

        panelCelda.add(footer, BorderLayout.SOUTH);

        // Margen entre celdas
        panelCelda.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(6, 6, 6, 6),
                panelCelda.getBorder()
        ));

        return panelCelda;
    }
    
    private void makeGradientButton(JButton button) {
        Color left = new Color(0x76, 0x4b, 0xa2);
        Color right = new Color(0x66, 0x7e, 0xea);

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

                // texto centrado
                super.paint(g, c);
            }
        });
    }

    // Getter para que MovimientoController pueda reemplazar el contenido del scroll
    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) { }

        EventQueue.invokeLater(() -> new MovimientoView().setVisible(true));
    }
}
