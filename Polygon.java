//Karl Isele

import java.awt.*;
import java.util.*;

public class Polygon 
{
    ArrayList<Point> points;

    int pointSize;
    static boolean isCleared;
    
    public Polygon()
    {
        points = new ArrayList();
        pointSize = 10;
    }
    
    @Override
    public String toString()
    {
        String polyString = "";
        
        for (int i = 0; i < points.size(); i++)
        {
            polyString += "(" + points.get(i).x + "," + points.get(i).y + ")";
        }
        
        return polyString;
    }
    
    public void add(Point p)
    {
        points.add(p);
    }
    
    public boolean isClosed(boolean enterPressed)
    {
        boolean isClosed = false;
        double d;
        
        if (points.size() > 1)
        {
            d = points.get(0).distance(points.get(points.size()-1));

            if (d < 50 || enterPressed)
                isClosed = true;
        }
        else
        {
            isClosed = false;
        }
        
        return isClosed;
    }
    
    public void draw(Graphics2D g2, boolean enterPressed)
    {
        if (!isClosed(enterPressed))
        {
            for (int i = 0; i < points.size(); i++)
            {
                g2.fillOval(points.get(i).x-pointSize/2, points.get(i).y-pointSize/2, pointSize, pointSize);
            }
        }
        
        if (isClosed(enterPressed))
        {
            if (!enterPressed)
            {
                drawLines(g2,1);
            }
            else
            {
                drawLines(g2,0);
            }
        }
    }
    
    public void drawLines(Graphics2D g2, int offset)
    {
        for (int i = 1; i < points.size()-offset; i++)
                {
                    g2.drawLine(points.get(i-1).x,points.get(i-1).y,points.get(i).x,points.get(i).y);
                }
                g2.drawLine(points.get(0).x,points.get(0).y,points.get(points.size()-(1+offset)).x,points.get(points.size()-(1+offset)).y);
    }
    
    public void clear()
    {
        points.clear();
        isCleared = true;
    }
}
