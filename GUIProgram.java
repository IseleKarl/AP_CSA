

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Arrays;
import javax.swing.*;
//import static GUIFramework.*;

public class GUIProgram implements GUIApp
{
    int [][] chips;
    static final int BLANK=0;
    static final int BLACK=1;
    static final int WHITE=2;
    static final int SIZE=8;
    int turn;
    boolean gameOver;
    Font bigFont;
    double gameTime;
    
    public GUIProgram()
    { 
        chips = new int[SIZE][SIZE];
        chips[SIZE/2-1][SIZE/2-1]=WHITE;
        chips[SIZE/2-1][SIZE/2]=BLACK;
        chips[SIZE/2][SIZE/2-1]=BLACK;
        chips[SIZE/2][SIZE/2]=WHITE; 
        
        bigFont = new Font("Courier", Font.BOLD, 55);
        turn = BLACK;
    }

    public void update(double elapsed)
    {
        gameTime += elapsed;
    }
    
    public void processMouse(MouseEvent e)
    {
        if(!gameOver)
        {
            int r = Math.min(e.getY()/getCellSize(),SIZE-1);
            int c = Math.min(e.getX()/getCellSize(),SIZE-1);
            if(isValidTurn(turn,r,c))
            {
                chips[r][c]=turn;
                flipCells(r,c);
                nextTurn();
            }
            if(!moreValidTurns())
                gameOver = true;            
        }

    }

    public void processKey(KeyEvent e)
    {
        // end the game for testing purposes
        if(e.getKeyCode()==KeyEvent.VK_SPACE && !gameOver)
        {
            // display chips array 
            for(int i=0; i<chips.length; ++i)
                System.out.println(Arrays.toString(chips[i]));
            System.out.println((int)gameTime); 
            gameOver = true;
        }
   
    }

    public void draw(Graphics2D g2)
    {
        // draw the grid lines
        g2.setColor(Color.black);
        int cellSize = getCellSize();       
        for(int i=1; i<SIZE; ++i)
            g2.drawLine(0,cellSize*i,GUIFramework.SCREEN_WIDTH,cellSize*i);
        for(int i=1; i<SIZE; ++i)
            g2.drawLine(cellSize*i,0,cellSize*i,GUIFramework.SCREEN_WIDTH);       
        
        // display the chips
        for(int r=0; r<chips.length; ++r)
            for(int c=0; c<chips[r].length; ++c)
                if(chips[r][c]==BLACK)
                {
                    g2.setColor(Color.BLACK);
                    g2.fillOval(cellSize*c, cellSize*r, cellSize, cellSize);
                }
                else if(chips[r][c]==WHITE)
                {
                    g2.setColor(Color.WHITE);
                    g2.fillOval(cellSize*c, cellSize*r, cellSize, cellSize);                   
                } 
        
        if(gameOver)
        {
            g2.setColor(Color.RED);
            g2.setFont(bigFont);
            g2.drawString("Game Over!", GUIFramework.SCREEN_WIDTH/4,
                    GUIFramework.SCREEN_HEIGHT/4);
        }
    }
    
    public int getCellSize()
    {
        int cellSize = GUIFramework.SCREEN_HEIGHT/SIZE;
        return cellSize;
    }
    
    // alternates between BLACK and WHITE turn
    public void nextTurn()
    {
        if(turn==BLACK)
            turn=WHITE;
        else
            turn=BLACK;
    }
    
    /*
        Returns true if the cell located at r,c is blank and 
        is adjacent to at least one occupied cell of opposite color
        and will result in at least one flip
    */
    public boolean isValidTurn(int t, int r, int c)
    {
        boolean valid = false;
        
        /*if (chips[r][c]==BLANK && surroundsChips(t,r,c))
        {
            valid = true;
        }*/
        
        if (chips[r][c] == BLACK)
            valid = true;
        
        return valid;
    }
    
    public boolean surroundsChips(int t, int r, int c)
    {
        boolean surroundsChips = false;
        int opponentChip;
        int adjacentChip;
        
        if (turn == BLACK)
        {
            opponentChip = WHITE;
        }
        else
        {
            opponentChip = BLACK;
        }
        
        if (!surroundsChips)
        {
            int i = 1;
            do
            {
                adjacentChip = chips[r+i][c];
                i++;
            }
            while(opponentChip == adjacentChip);
        }
        
        return surroundsChips;
    }
    
    // Makes the required flips to "surrounded" cells
    public void flipCells(int r, int c)
    {
        
    }
    
    /* 
        Returns true when there are no more valid moves remaining
        on the board
        You must at minimum check that there remains at least on
        BLANK cell remaining
    */
    public boolean moreValidTurns()
    {
        return true;
    }
}
