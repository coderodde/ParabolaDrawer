package net.coderodde.math.analyticgeometry;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class implements the mouse listener for the {@link ParabolaPanel}.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 22, 2017)
 */
public class ParabolaPanelMouseListener implements MouseListener,
                                                   MouseMotionListener {

    private final ParabolaPanel parabolaPanel;
    private volatile boolean parabolaVertexPressed = false;
    
    public ParabolaPanelMouseListener(ParabolaPanel parabolaPanel) {
        this.parabolaPanel = parabolaPanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        System.out.println(parabolaPanel.pointContainsParabolaVertex(mouseX, 
                                                                     mouseY));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    
    }   
}
