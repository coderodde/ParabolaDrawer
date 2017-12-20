package net.coderodde.math.analyticgeometry;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class implements a key listener for the {@link ParabolaPanel}.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 20, 2017)
 */
public class ParabolaPanelKeyListener implements KeyListener {

    private ParabolaPanel parabolaPanel;
    
    public void setParabolaPanel(ParabolaPanel parabolaPanel) {
        this.parabolaPanel = parabolaPanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                parabolaPanel.setCenterY(parabolaPanel.getCenterY() + 0.01);
                System.out.println("yo!");
                break;
                
            case KeyEvent.VK_DOWN:
                parabolaPanel.setCenterY(parabolaPanel.getCenterY() - 0.01);
                break;
                
            case KeyEvent.VK_LEFT:
                parabolaPanel.setCenterX(parabolaPanel.getCenterX() + 0.01);
                break;
                
            case KeyEvent.VK_RIGHT:
                parabolaPanel.setCenterX(parabolaPanel.getCenterX() - 0.01);
                break;
                
            case KeyEvent.VK_W:
                double zoom = parabolaPanel.getUnitsPerPixel();
                parabolaPanel.setUnitsPerPixel(zoom * 1.1);
                break;
                
            case KeyEvent.VK_S:
                zoom = parabolaPanel.getUnitsPerPixel();
                parabolaPanel.setUnitsPerPixel(zoom / 1.1);
                break;
        }
        
        parabolaPanel.repaint();
        System.out.println("fsfas");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
