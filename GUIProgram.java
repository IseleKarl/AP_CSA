//Neil Wise, modified by Karl Isele

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
//import static GUIFramework.*;

public class GUIProgram implements GUIApp
{
    int [][] chips;
    ArrayList<int[]> capturedChips;
    ArrayList<int[]> capturedGroup;
    static final int BLANK=0;
    static final int BLACK=1;
    static final int WHITE=2;
    static final int SIZE=8;
    int turn;
    boolean gameOver;
    Font bigFont;
    double gameTime;
    
    Rectangle rect;
    
    public GUIProgram()
    {
        chips = new int[SIZE][SIZE];
        chips[SIZE/2-1][SIZE/2-1]=WHITE;
        chips[SIZE/2-1][SIZE/2]=BLACK;
        chips[SIZE/2][SIZE/2-1]=BLACK;
        chips[SIZE/2][SIZE/2]=WHITE; 
        
        bigFont = new Font("Courier", Font.BOLD, 55);
        turn = BLACK;
        
        rect = new Rectangle(0,0,GUIFramework.SCREEN_WIDTH,GUIFramework.SCREEN_HEIGHT);
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
                
                flipCells(turn,r,c);
                System.out.println("player chip: [" + r + ", " + c + "]");
                System.out.println("captured chips: " + Arrays.toString(capturedChips.get(0)));
                
                turn = oppositeColor(turn);
            }
            if(!moreValidTurns(turn))
            {
                gameOver = true;
                System.out.println("game over");
            }
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
    {/*
        g2.setColor(Color.BLUE);
        g2.fill(rect);*/
        
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
            String winner = winner();
            g2.drawString(winner + " wins", GUIFramework.SCREEN_WIDTH/4,
                    GUIFramework.SCREEN_HEIGHT/3);
        }
    }
    
    public int getCellSize()
    {
        int cellSize = GUIFramework.SCREEN_HEIGHT/SIZE;
        return cellSize;
    }
    
    // returns the opposite color
    public int oppositeColor(int color)
    {
        if(color==BLACK)
            return WHITE;
        else
            return BLACK;
    }
    
    /*
        Returns true if the cell located at r,c is blank and 
        is adjacent to at least one occupied cell of opposite color
        and will result in at least one flip
    */
    public boolean isValidTurn(int t, int r, int c)
    {
        int neighbor = oppositeColor(t);        
        return chips[r][c]==BLANK 
                && hasValidNeighbor(neighbor,r,c) 
                && !capturedChips(t,r,c).isEmpty();
    }
    
    public boolean hasValidNeighbor(int color, int r, int c)
    {
        boolean isOK = false; 
        // check the 8 neighbors for the required color
        if(checkColor(color,r+1,c) || checkColor(color,r-1,c) ||
           checkColor(color,r,c+1) || checkColor(color,r,c-1) ||
           checkColor(color,r-1,c-1) || checkColor(color,r-1,c+1) ||
           checkColor(color,r+1,c-1) || checkColor(color,r+1,c+1))
                isOK=true;
        return isOK;
    }
    
    // returns true if cell r,c contains the given color
    public boolean checkColor(int color, int r, int c)
    {
        // take advantage of short-circuit evaluation!
        return r<SIZE && c<SIZE && r>=0 && c>=0 
                && chips[r][c]==color;
    }
    
    // return true if placing color in cell r,c will result in one or more 
    // cells flipping their color
    public ArrayList<int[]> capturedChips(int color, int r, int c)
    {
        capturedChips = new ArrayList();
        String capturedGroupString;
        
        for (int d = 0; d < 8; d++)
        {
            capturedGroupString = "";
            
            checkDirection(color,r,c,d);
            for (int i = 0; i < capturedGroup.size(); i++)
            {
                capturedGroupString += Arrays.toString(capturedGroup.get(i));
                capturedChips.add(capturedGroup.get(i));
            }
            
            System.out.println("captured group: " + capturedGroupString);
        }
        
        return capturedChips;
    }
    
    public void checkDirection(int color, int r, int c, int d)
    {
        capturedGroup = new ArrayList();
        
        int[] adjacentChip = new int[2];
        ArrayList<int[]> adjacentOpponentChips = new ArrayList();
        
        int ri = 0;
        int ci = 0;
        
        boolean isOpponentChipFound = true;
        boolean isPlayerChipFound = false;
        
        if (d < 2)  //row group
        {
            while (c + ci >= 0 && c + ci < SIZE && !isPlayerChipFound && isOpponentChipFound)
            {
                ci += Math.pow(-1,((d+1)%2));   //if d is even then subtract increments
                
                adjacentChip[0] = r + ri;
                adjacentChip[1] = c + ci;
                
                System.out.print("adjacentChip is: ");
                System.out.println(Arrays.toString(adjacentChip));
                
                if (c + ci >= 0 && c + ci < SIZE)
                {
                    if (chips[adjacentChip[0]][adjacentChip[1]] == color)
                    {
                        isPlayerChipFound = true;
                    }
                    else
                    {
                        if (chips[adjacentChip[0]][adjacentChip[1]] == oppositeColor(color))
                        { 
                            adjacentOpponentChips.add(new int[]{adjacentChip[0],adjacentChip[1]});
                            System.out.println("captured");
                        }
                        else
                            isOpponentChipFound = false;
                    }
                }
            }
            
            if (isPlayerChipFound)
            {
                capturedGroup = adjacentOpponentChips;
            }
        } 
        else if (d < 4) //column group
        {
            while (r + ri >= 0 && r + ri < SIZE && !isPlayerChipFound && isOpponentChipFound)
            {   
                ri += Math.pow(-1,((d+1)%2));
                
                adjacentChip[0] = r + ri;
                adjacentChip[1] = c + ci;
                
                if (r + ri >= 0 && r + ri < SIZE)
                {
                    if (chips[adjacentChip[0]][adjacentChip[1]] == color)
                    {
                        isPlayerChipFound = true;
                    }
                    else
                    {
                        if (chips[adjacentChip[0]][adjacentChip[1]] == oppositeColor(color))
                        { 
                            adjacentOpponentChips.add(new int[]{adjacentChip[0],adjacentChip[1]});
                            System.out.println("captured");
                        }
                        else
                            isOpponentChipFound = false;
                    }
                }
            }
            
            if (isPlayerChipFound)
            {
                capturedGroup = adjacentOpponentChips;
            }
        }
        else if (d < 6) //y=x group
        {
            while (r + ri >= 0 && c + ci >= 0 && r + ri < SIZE && c + ci < SIZE && !isPlayerChipFound && isOpponentChipFound)
            {
                ri += Math.pow(-1,((d+1)%2));
                ci += Math.pow(-1,((d+1)%2));
                
                adjacentChip[0] = r + ri;
                adjacentChip[1] = c + ci;
                
                if (r + ri >= 0 && r + ri < SIZE && c + ci >= 0 && c + ci < SIZE)
                {
                    if (chips[adjacentChip[0]][adjacentChip[1]] == color)
                    {
                        isPlayerChipFound = true;
                    }
                    else
                    {
                        if (chips[adjacentChip[0]][adjacentChip[1]] == oppositeColor(color))
                        { 
                            adjacentOpponentChips.add(new int[]{adjacentChip[0],adjacentChip[1]});
                            System.out.println("captured");
                        }
                        else
                            isOpponentChipFound = false;
                    }
                }
            }
            
            if (isPlayerChipFound)
            {
                capturedGroup = adjacentOpponentChips;
            }
        }
        else    //y=-x group
        {
            while (r + ri >= 0 && c + ci >= 0 && r + ri < SIZE && c + ci < SIZE && !isPlayerChipFound && isOpponentChipFound)
            {
                ri += Math.pow(-1,((d)%2));
                ci += Math.pow(-1,((d+1)%2));
                
                adjacentChip[0] = r + ri;
                adjacentChip[1] = c + ci;
                
                if (r + ri >= 0 && r + ri < SIZE && c + ci >= 0 && c + ci < SIZE)
                {
                    if (chips[adjacentChip[0]][adjacentChip[1]] == color)
                    {
                        isPlayerChipFound = true;
                    }
                    else
                    {
                        if (chips[adjacentChip[0]][adjacentChip[1]] == oppositeColor(color))
                        { 
                            adjacentOpponentChips.add(new int[]{adjacentChip[0],adjacentChip[1]});
                            System.out.println("captured");
                        }
                        else
                            isOpponentChipFound = false;
                    }
                }
            }
            
            if (isPlayerChipFound)
            {
                capturedGroup = adjacentOpponentChips;
            }
        }
    }
    
    // Makes the required flips to "surrounded" cells
    public void flipCells(int t, int r, int c)
    {
        for (int i = 0; i < capturedChips.size(); i++)
        {
            chips[(capturedChips.get(i))[0]][(capturedChips.get(i))[1]] = t;
        }
    }

    // Returns true when there are no more valid moves remaining on the board
    public boolean moreValidTurns(int t)
    {
        boolean validTurn = false;
        int r = 0;
        int c;
        
        while (r < SIZE && !validTurn)
        {
            c = 0;
            while (c < SIZE && !validTurn)
            {
                validTurn = isValidTurn(t,r,c);
                c++;
            }
            r++;
        }
        return validTurn;
    }
    
    public String winner()
    {
        String winner = "White";
        int numWhite = 0;
        int numBlack = 0;
        
        int r = 0;
        int c;
        
        while (r < SIZE && numWhite <= 32)
        {
            c = 0;
            while (c < SIZE && numWhite <= 32)
            {
                if (chips[r][c] == WHITE)
                    numWhite++;
                else if (chips[r][c] == BLACK)
                    numBlack++;
                c++;
            }
            r++;
        }
        
        if (numWhite < numBlack)
            winner = "Black";
        
        return winner;
    }
}
