package ec.edu.monster.vista;

import ec.edu.monster.controlador.MainController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private JPanel panelBackground;
    private JPanel panelCard;
    private JTextField lblUsername;
    private JPasswordField lblPassword;
    private JButton btnLogin;
    private JLabel messageLabel;
    private JLabel jLabelTitulo;
    private JLabel jLabelSubtitulo;
    private JLabel jLabelUserText;
    private JLabel jLabelPassText;
    private JLabel jLabelSully;
    
    private final MainController mainController = new MainController();
    private String usuarioActual;

    public LoginView() {
        initComponents();
    }

    private void initComponents() {
        // FRAME más grande
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        Color purple = new Color(0x76, 0x4b, 0xa2);
        Color inputBorder = new Color(220, 220, 220);
        Color labelColor = new Color(110, 110, 110);

        // PANEL FONDO
        panelBackground = new JPanel();
        panelBackground.setBackground(purple);
        panelBackground.setLayout(null);
        panelBackground.setBounds(0, 0, 1100, 650);
        getContentPane().add(panelBackground);

        // SULLY pegado a la izquierda y un poco más grande
        jLabelSully = new JLabel();
        jLabelSully.setHorizontalAlignment(SwingConstants.LEFT);
        jLabelSully.setVerticalAlignment(SwingConstants.BOTTOM);
        try {
            ImageIcon sullyIcon = new ImageIcon(getClass().getResource("/images/sullyvan.png"));
            // Ajusta según el tamaño real de tu imagen
            Image scaled = sullyIcon.getImage().getScaledInstance(280, 560, Image.SCALE_SMOOTH);
            jLabelSully.setIcon(new ImageIcon(scaled));
        } catch (Exception e) {
            System.err.println("No se pudo cargar /images/sullyvan.png: " + e.getMessage());
        }
        // x = 0 para que no quede espacio
        jLabelSully.setBounds(0, 60, 280, 560);
        panelBackground.add(jLabelSully);

        // CARD BLANCA centrada
        panelCard = new JPanel() {
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
        panelCard.setLayout(null);
        // un poco más grande también
        panelCard.setBounds(330, 40, 470, 560);
        panelBackground.add(panelCard);

        // TÍTULO
        jLabelTitulo = new JLabel("Eureka Bank", SwingConstants.CENTER);
        jLabelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        jLabelTitulo.setForeground(new Color(40, 40, 40));
        jLabelTitulo.setBounds(0, 80, 470, 30);
        panelCard.add(jLabelTitulo);

        // SUBTÍTULO
        jLabelSubtitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        jLabelSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jLabelSubtitulo.setForeground(new Color(130, 130, 130));
        jLabelSubtitulo.setBounds(0, 112, 470, 25);
        panelCard.add(jLabelSubtitulo);

        int left = 60;
        int widthField = 350;
        int fieldHeight = 42;
        int y = 170;

        // LABEL USUARIO
        jLabelUserText = new JLabel("Usuario");
        jLabelUserText.setFont(new Font("Segoe UI", Font.BOLD, 13));
        jLabelUserText.setForeground(labelColor);
        jLabelUserText.setBounds(left, y, widthField, 18);
        panelCard.add(jLabelUserText);

        y += 24;

        // CAMPO USUARIO
        lblUsername = new JTextField();
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorder, 2, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        lblUsername.setBounds(left, y, widthField, fieldHeight);
        panelCard.add(lblUsername);

        y += 70;

        // LABEL CONTRASEÑA
        jLabelPassText = new JLabel("Contraseña");
        jLabelPassText.setFont(new Font("Segoe UI", Font.BOLD, 13));
        jLabelPassText.setForeground(labelColor);
        jLabelPassText.setBounds(left, y, widthField, 18);
        panelCard.add(jLabelPassText);

        y += 24;

        // CAMPO CONTRASEÑA
        lblPassword = new JPasswordField();
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorder, 2, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        lblPassword.setBounds(left, y, widthField, fieldHeight);
        panelCard.add(lblPassword);

        y += 70;

        // BOTÓN LOGIN (sin iconito)
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(purple);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBounds(left, y, widthField, 48);
        panelCard.add(btnLogin);

        y += 55;

        // MENSAJE
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        messageLabel.setForeground(new Color(200, 0, 0));
        messageLabel.setBounds(40, y, 390, 20);
        panelCard.add(messageLabel);

        // Acción del botón con MainController (misma lógica)
        btnLogin.addActionListener(e -> {
            String username = lblUsername.getText();
            String passwordString = new String(lblPassword.getPassword());
            if (username.isEmpty() || passwordString.isEmpty()) {
                messageLabel.setText("Ingrese usuario y contraseña.");
                return;
            }
            boolean ok = mainController.iniciarSesion(username, passwordString);
            if (ok) {
                usuarioActual = username;
                this.setVisible(false);
                java.awt.EventQueue.invokeLater(() -> {
                    MenuView mv = new MenuView(usuarioActual);
                    mv.setLocationRelativeTo(null);
                    mv.setVisible(true);
                    this.dispose();
                });
            } else {
                messageLabel.setText("Credenciales inválidas.");
            }
        });

        setContentPane(panelBackground);
    }

    // Getters para compatibilidad
    public JTextField getTxtUsername() {
        return lblUsername;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
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

        EventQueue.invokeLater(() -> new LoginView().setVisible(true));
    }
}
