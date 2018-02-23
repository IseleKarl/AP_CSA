

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


public interface GUIApp 
{
    public void update(double elapsed);
    public void draw(Graphics2D g2);
    public void processKey(KeyEvent e);
    public void processMouse(MouseEvent e);  
}
