package ec.edu.monster.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 * Botón personalizado con gradiente que replica el diseño web
 */
public class GradientButton extends JButton {
    private Color color1 = UIHelper.COLOR_GRADIENTE_1;
    private Color color2 = UIHelper.COLOR_GRADIENTE_2;
    
    public GradientButton(String text) {
        super(text);
        setForeground(Color.WHITE);
        setFont(new Font("Poppins", Font.BOLD, 16));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorder(new EmptyBorder(16, 20, 16, 20));
    }
    
    public void setGradientColors(Color c1, Color c2) {
        this.color1 = c1;
        this.color2 = c2;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        GradientPaint gradient = new GradientPaint(
            0, 0, color1,
            getWidth(), 0, color2
        );
        g2d.setPaint(gradient);
        
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
            0, 0, getWidth(), getHeight(), 10, 10
        );
        g2d.fill(roundedRectangle);
        
        g2d.dispose();
        super.paintComponent(g);
    }
}


