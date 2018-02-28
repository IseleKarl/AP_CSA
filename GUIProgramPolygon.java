//Karl Isele

import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUIProgramPolygon implements GUIApp
{
    Polygon polygon;
    boolean enterPressed;
    
    public GUIProgramPolygon()
    {
        polygon = new Polygon();
        enterPressed = false;
    }

    public void update(double elapsed)
    {
        
    }
    
    public void processMouse(MouseEvent e)
    {
        if (!polygon.isClosed(enterPressed))
            polygon.add(e.getPoint());
    }

    public void processKey(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_P && !polygon.isClosed(enterPressed))
            System.out.println(polygon.toString());
        
        if (e.getKeyCode() == KeyEvent.VK_C && !polygon.isClosed(enterPressed))
            polygon.clear();
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !polygon.isClosed(enterPressed))
            enterPressed = true;
    }

    public void draw(Graphics2D g2)
    {
        polygon.draw(g2,enterPressed);
    }
}
