package ec.edu.monster.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

/**
 * Helper class para crear componentes UI con estilo moderno que replica el diseño web
 */
public class UIHelper {
    
    // Colores del diseño web
    public static final Color COLOR_FONDO = new Color(118, 75, 162); // #764ba2
    public static final Color COLOR_GRADIENTE_1 = new Color(102, 126, 234); // #667eea
    public static final Color COLOR_GRADIENTE_2 = new Color(118, 75, 162); // #764ba2
    public static final Color COLOR_BLANCO = Color.WHITE;
    public static final Color COLOR_TEXTO = new Color(51, 51, 51); // #333
    public static final Color COLOR_TEXTO_SECUNDARIO = new Color(102, 102, 102); // #666
    public static final Color COLOR_BORDE = new Color(224, 224, 224); // #e0e0e0
    public static final Color COLOR_INGRESOS = new Color(17, 153, 142); // #11998e
    public static final Color COLOR_EGRESOS = new Color(245, 87, 108); // #f5576c
    public static final Color COLOR_AZUL = new Color(79, 172, 254); // #4facfe
    public static final Color COLOR_ALERTA_ERROR = new Color(255, 204, 204); // #fee
    public static final Color COLOR_ERROR = new Color(204, 51, 51); // #c33
    public static final Color COLOR_ALERTA_SUCCESS = new Color(238, 255, 238); // #efe
    public static final Color COLOR_SUCCESS = new Color(51, 204, 51); // #3c3
    
    // Fuente
    public static final Font FONT_POPPINS = new Font("Poppins", Font.PLAIN, 14);
    public static final Font FONT_POPPINS_BOLD = new Font("Poppins", Font.BOLD, 14);
    public static final Font FONT_TITULO = new Font("Poppins", Font.BOLD, 28);
    
    /**
     * Crea un botón con gradiente que replica el diseño web
     */
    public static JButton createGradientButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Crear gradiente
                GradientPaint gradient = new GradientPaint(
                    0, 0, COLOR_GRADIENTE_1,
                    getWidth(), 0, COLOR_GRADIENTE_2
                );
                g2d.setPaint(gradient);
                
                // Dibujar botón redondeado
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                    0, 0, getWidth(), getHeight(), 10, 10
                );
                g2d.fill(roundedRectangle);
                
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        
        button.setForeground(COLOR_BLANCO);
        button.setFont(FONT_POPPINS_BOLD);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(new EmptyBorder(16, 20, 16, 20));
        
        return button;
    }
    
    /**
     * Crea un panel con bordes redondeados (card)
     */
    public static JPanel createRoundedCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(COLOR_BLANCO);
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                    0, 0, getWidth(), getHeight(), 20, 20
                );
                g2d.fill(roundedRectangle);
                g2d.dispose();
            }
        };
        card.setOpaque(false);
        card.setBackground(COLOR_BLANCO);
        card.setBorder(new EmptyBorder(30, 30, 30, 30));
        return card;
    }
    
    /**
     * Crea un panel con gradiente de fondo
     */
    public static JPanel createGradientPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, COLOR_GRADIENTE_1,
                    getWidth(), 0, COLOR_GRADIENTE_2
                );
                g2d.setPaint(gradient);
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                    0, 0, getWidth(), getHeight(), 15, 15
                );
                g2d.fill(roundedRectangle);
                g2d.dispose();
            }
        };
        panel.setOpaque(false);
        return panel;
    }
    
    /**
     * Estiliza un campo de texto para que se vea como el diseño web
     */
    public static void styleTextField(JTextField textField) {
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 2),
            BorderFactory.createEmptyBorder(14, 50, 14, 20)
        ));
        textField.setFont(FONT_POPPINS);
        textField.setBackground(COLOR_BLANCO);
        textField.setForeground(COLOR_TEXTO);
        textField.setOpaque(true);
    }
    
    /**
     * Estiliza un campo de contraseña
     */
    public static void stylePasswordField(JPasswordField passwordField) {
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 2),
            BorderFactory.createEmptyBorder(14, 50, 14, 20)
        ));
        passwordField.setFont(FONT_POPPINS);
        passwordField.setBackground(COLOR_BLANCO);
        passwordField.setForeground(COLOR_TEXTO);
        passwordField.setOpaque(true);
    }
    
    /**
     * Crea un panel de stat card (para estadísticas)
     */
    public static JPanel createStatCard(Color backgroundColor, String title, String value) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(backgroundColor);
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                    0, 0, getWidth(), getHeight(), 15, 15
                );
                g2d.fill(roundedRectangle);
                g2d.dispose();
            }
        };
        card.setOpaque(false);
        card.setLayout(new java.awt.BorderLayout());
        card.setBorder(new EmptyBorder(24, 24, 24, 24));
        
        javax.swing.JLabel titleLabel = new javax.swing.JLabel(title);
        titleLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(255, 255, 255, 230));
        
        javax.swing.JLabel valueLabel = new javax.swing.JLabel(value);
        valueLabel.setFont(new Font("Poppins", Font.BOLD, 32));
        valueLabel.setForeground(COLOR_BLANCO);
        
        card.add(titleLabel, java.awt.BorderLayout.NORTH);
        card.add(valueLabel, java.awt.BorderLayout.CENTER);
        
        return card;
    }
    
    /**
     * Iconos Unicode como alternativa a FontAwesome
     */
    public static class Icons {
        public static final String USER = "👤";
        public static final String LOCK = "🔒";
        public static final String UNIVERSITY = "🏛️";
        public static final String ARROW_DOWN = "⬇️";
        public static final String ARROW_UP = "⬆️";
        public static final String EXCHANGE = "🔄";
        public static final String INFO = "ℹ️";
        public static final String LIST = "📋";
        public static final String SEARCH = "🔍";
        public static final String CHECK = "✓";
        public static final String ARROW_LEFT = "←";
        public static final String CREDIT_CARD = "💳";
        public static final String DOLLAR = "$";
        public static final String CALENDAR = "📅";
        public static final String BUILDING = "🏢";
        public static final String EXCLAMATION = "⚠️";
        public static final String HISTORY = "📜";
    }
}


