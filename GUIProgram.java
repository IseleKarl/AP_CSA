

import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUIProgram implements GUIApp
{
    ArrayList<Point> points;
    
    public GUIProgram()
    {
        points = new ArrayList();
    }

    public void update(double elapsed)
    {
        
    }
    
    public void processMouse(MouseEvent e)
    {
        points.add(e.getPoint());
    }

    public void processKey(KeyEvent e)
    {
        
    }
    
    public void add(Point p)
    {
        
    }
    
    public boolean isClosed()
    {
        boolean isClosed = false;
        int dx;
        int dy;
        
        return isClosed;
    }

    public void draw(Graphics2D g2)
    {
        
    }
}
