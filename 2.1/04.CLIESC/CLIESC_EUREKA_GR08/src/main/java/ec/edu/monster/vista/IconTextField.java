package ec.edu.monster.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;

/**
 * Campo de texto con icono dentro, replicando el diseño web
 */
public class IconTextField extends JTextField {
    private String iconText = "";
    private Color iconColor = new Color(153, 153, 153); // #999
    
    public IconTextField() {
        super();
        UIHelper.styleTextField(this);
    }
    
    public void setIcon(String icon) {
        this.iconText = icon;
        repaint();
    }
    
    public void setIconColor(Color color) {
        this.iconColor = color;
        repaint();
    }
    
    public void setPlaceholder(String placeholder) {
        // Swing no soporta placeholder nativo, se puede agregar con un listener
        // Por ahora solo establecemos el tooltip
        setToolTipText(placeholder);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (!iconText.isEmpty()) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(iconColor);
            g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            
            // Posicionar el icono a la izquierda
            Insets insets = getInsets();
            int x = insets.left + 15;
            int y = getHeight() / 2 + 5; // Ajustar para centrar verticalmente
            
            g2d.drawString(iconText, x, y);
            g2d.dispose();
        }
    }
    
    @Override
    public Insets getInsets() {
        Insets insets = super.getInsets();
        if (!iconText.isEmpty()) {
            insets.left = 50; // Espacio para el icono
        }
        return insets;
    }
}

