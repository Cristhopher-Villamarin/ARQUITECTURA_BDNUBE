package ec.edu.monster.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Menú principal con estilo Eureka Bank + Monsters Inc
 */
public class MenuView extends JFrame {

    private JPanel bgPanel;
    private JPanel headerPanel;
    private JPanel cardPanel;

    private JButton btnDeposito;
    private JButton btnRetiro;
    private JButton btnTransferencia;
    private JButton btnDatosCuenta;
    private JButton btnMovimientos;
    private JButton btnCerrarSesion;

    private JLabel lblUserName;

    private static final Color PURPLE = new Color(0x76, 0x4b, 0xa2);
    private static final Color CARD_BLUE = new Color(0x5f, 0x82, 0xff);

    private String username;

    public MenuView() {
        this("Usuario");
    }

    public MenuView(String username) {
        this.username = username;
        initComponents();
        configurarEventos();
    }

    private void initComponents() {
        // FRAME
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        // PANEL FONDO
        bgPanel = new JPanel(null);
        bgPanel.setBackground(PURPLE);
        setContentPane(bgPanel);

        // HEADER
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(248, 248, 255));
        headerPanel.setBounds(0, 0, 1280, 60);

        JLabel lblLogo = new JLabel("Eureka Bank");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblLogo.setForeground(new Color(80, 120, 255));
        lblLogo.setBounds(30, 10, 300, 40);
        headerPanel.add(lblLogo);

        lblUserName = new JLabel(username != null ? username.toUpperCase() : "MONSTER");
        lblUserName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUserName.setForeground(new Color(70, 70, 70));
        lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUserName.setBounds(950, 18, 100, 24);
        headerPanel.add(lblUserName);

        btnCerrarSesion = new JButton("Salir");
        btnCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setBackground(new Color(255, 77, 135));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(BorderFactory.createEmptyBorder(6, 18, 6, 18));
        btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrarSesion.setBounds(1065, 14, 90, 32);
        headerPanel.add(btnCerrarSesion);

        bgPanel.add(headerPanel);

        // TARJETA BLANCA
        cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Sombra suave
                g2.setColor(new Color(0, 0, 0, 25));
                g2.fillRoundRect(8, 8, getWidth() - 16, getHeight() - 16, 40, 40);

                // Tarjeta blanca
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
        cardPanel.setLayout(null);
        cardPanel.setBounds(70, 90, 1140, 340);
        cardPanel.setOpaque(false);
        bgPanel.add(cardPanel);

        // TÍTULO
        JLabel lblTitulo = new JLabel("Bienvenido, " + (username != null ? username.toUpperCase() : "MONSTER"));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setBounds(80, 40, 500, 30);
        cardPanel.add(lblTitulo);

        JLabel lblSub = new JLabel("Seleccione una opción del menú para continuar");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSub.setForeground(new Color(120, 120, 120));
        lblSub.setBounds(80, 75, 600, 25);
        cardPanel.add(lblSub);

        // PANEL BOTONES
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new GridLayout(1, 5, 30, 0)); // espacio entre tarjetas
        panelBotones.setBounds(80, 130, 980, 170); // margen a la derecha => el último no pega al borde
        cardPanel.add(panelBotones);

        // Crear botones tipo card
        btnDeposito = createMenuButton("Depósito",
                "Realizar un depósito a una cuenta");

        btnRetiro = createMenuButton("Retiro",
                "Realizar un retiro de una cuenta");

        btnTransferencia = createMenuButton("Transferencia",
                "Transferir dinero entre cuentas");

        btnDatosCuenta = createMenuButton("Datos de Cuenta",
                "Consultar información de una cuenta");

        btnMovimientos = createMenuButton("Movimientos",
                "Ver historial de movimientos");

        panelBotones.add(btnDeposito);
        panelBotones.add(btnRetiro);
        panelBotones.add(btnTransferencia);
        panelBotones.add(btnDatosCuenta);
        panelBotones.add(btnMovimientos);

        // IMAGEN MIKey + SULLIVAN ABAJO (centrada)
        try {
            ImageIcon baseIcon = new ImageIcon(getClass().getResource("/images/mikeysullivan.png"));
            int imgW = 420;
            int imgH = (int) ((double) baseIcon.getIconHeight() / baseIcon.getIconWidth() * imgW);
            Image scaled = baseIcon.getImage().getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
            JLabel sullyBottom = new JLabel(new ImageIcon(scaled));

            int x = (1280 - imgW) / 2;
            int y = 430; // debajo de la tarjeta, sobre fondo morado

            sullyBottom.setBounds(x, y, imgW, imgH);
            bgPanel.add(sullyBottom);
        } catch (Exception e) {
            System.err.println("No se pudo cargar /images/mikeysullivan.png: " + e.getMessage());
        }
    }

    /**
     * Crea un botón tipo tarjeta con bordes redondeados.
     */
    private JButton createMenuButton(String titulo, String sub) {
        String html = "<html><center>"
                + "<span style='font-size:16px; font-weight:bold;'>" + titulo + "</span><br>"
                + "<span style='font-size:11px; font-weight:normal;'>" + sub + "</span>"
                + "</center></html>";

        JButton btn = new JButton(html) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public void setContentAreaFilled(boolean b) {
                // ignorar para mantener el fondo custom
            }
        };

        btn.setForeground(Color.WHITE);
        btn.setBackground(CARD_BLUE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(18, 10, 18, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.CENTER);

        return btn;
    }

    private void configurarEventos() {
        btnDeposito.addActionListener(evt -> {
            DepositoView depositoView = new DepositoView();
            depositoView.setLocationRelativeTo(null);
            depositoView.setVisible(true);
        });

        btnRetiro.addActionListener(evt -> {
            RetiroView retiroView = new RetiroView();
            retiroView.setLocationRelativeTo(null);
            retiroView.setVisible(true);
        });

        btnTransferencia.addActionListener(evt -> {
            TransferenciaView transferenciaView = new TransferenciaView();
            transferenciaView.setLocationRelativeTo(null);
            transferenciaView.setVisible(true);
        });

        btnMovimientos.addActionListener(evt -> {
            MovimientoView movimientoView = new MovimientoView();
            movimientoView.setLocationRelativeTo(null);
            movimientoView.setVisible(true);
        });

        btnDatosCuenta.addActionListener(evt -> {
            CuentaView cuentaView = new CuentaView();
            cuentaView.setLocationRelativeTo(null);
            cuentaView.setVisible(true);
        });

        btnCerrarSesion.addActionListener(evt -> {
            // Volver al login
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                LoginView login = new LoginView();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            });
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
        } catch (Exception ignored) { }

        EventQueue.invokeLater(() -> new MenuView().setVisible(true));
    }
}
