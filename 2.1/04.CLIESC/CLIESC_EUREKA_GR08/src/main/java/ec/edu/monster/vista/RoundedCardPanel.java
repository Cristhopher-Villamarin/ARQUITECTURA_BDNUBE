package ec.edu.monster.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Panel con bordes redondeados que replica una card del diseño web
 */
public class RoundedCardPanel extends JPanel {
    private int cornerRadius = 20;
    private Color backgroundColor = Color.WHITE;
    private boolean withShadow = true;
    
    public RoundedCardPanel() {
        setOpaque(false);
        setBackground(backgroundColor);
    }
    
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }
    
    public void setWithShadow(boolean shadow) {
        this.withShadow = shadow;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dibujar sombra si está habilitada
        if (withShadow) {
            g2d.setColor(new Color(0, 0, 0, 30));
            RoundRectangle2D shadowRect = new RoundRectangle2D.Float(
                2, 2, getWidth() - 4, getHeight() - 4, cornerRadius, cornerRadius
            );
            g2d.fill(shadowRect);
        }
        
        // Dibujar el panel redondeado
        g2d.setColor(backgroundColor);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
            0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius
        );
        g2d.fill(roundedRectangle);
        
        g2d.dispose();
    }
}


