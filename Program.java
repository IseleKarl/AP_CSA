

import javax.swing.JFrame;

public class Program
{        
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("OTHELLO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUIFramework());
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
