//Karl Isele

import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUIProgramPolygon implements GUIApp
{
    ArrayList<Point> points;
    int numPoints;
    
    public GUIProgramPolygon()
    {
        points = new ArrayList();
        numPoints = 0;
    }

    public void update(double elapsed)
    {
        
    }
    
    public void processMouse(MouseEvent e)
    {
        points.add(e.getPoint());
        numPoints++;
    }

    public void processKey(KeyEvent e)
    {
        
    }
    
    public void add(Point p)
    {
        points.add(p);
    }
    
    public boolean isClosed()
    {
        boolean isClosed = false;
        double d;
        
        d = points.get(0).distance(points.get(numPoints));
        
        if (d < 50)
            isClosed = true;
        
        return isClosed;
    }

    public void draw(Graphics2D g2)
    {
        
    }
}
