//Karl Isele
package diceroll;

import java.util.*;

public class DiceRoll 
{
    public static void main(String[] args) 
    {
        Scanner kbd = new Scanner(System.in);
        Random rand = new Random();
        
        int die1;
        int die2;
        
        int sum = 0;
        int sumCount = 0;
        
        System.out.println("What is the goal sum?");
        System.out.print("> ");
        int goalSum = kbd.nextInt();
        
        System.out.println("How many trials should be run?");
        System.out.print("> ");
        int trials = kbd.nextInt();
        
        for (int rolls = 0; rolls < trials; rolls++)
        {
            die1 = rand.nextInt(6) + 1;
            die2 = rand.nextInt(6) + 1;
            
            sum = die1 + die2;
            
            if (goalSum==sum)
            {
                sumCount++;
            }
        }
        
        double experimentalProbability = ((double) sumCount/trials)*100;
        float expectedProbability;
        
        die1 = 1;
        die2 = 1;
        sumCount = 0;
        
        while (die2<7)
        {
            while (die1<7)
            {
                sum = die1 + die2;

                if (sum == goalSum)
                {
                    sumCount++;
                }

                die1++;
            }
            die1 = 1;
            die2++;
        }
        
        expectedProbability = ((float)sumCount/36)*100;
        
        System.out.println("Experimental probability: " + experimentalProbability + "%");
        System.out.println("Expected probability: " + expectedProbability + "%, or " + sumCount + "/36");
    }
}
