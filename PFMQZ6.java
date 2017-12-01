//Karl Isele
package pfmqz6;

import java.util.*;

public class PFMQZ6
{
    public static void main(String[] args)
    {
        Scanner kbd = new Scanner(System.in);
        Random rand = new Random();
        
        int die1;
        int die2;
        int rolls;
        long ttlNumRolls = 0;
        double avgNumRolls = 0;
        
        int sum = 0;
        int sumCount;
        
        System.out.println("What is the target summation?");
        System.out.print("> ");
        int goalSum = kbd.nextInt();
        
        System.out.println("How many consecutive sums are required?");
        System.out.print("> ");
        int numConsecutiveSums = kbd.nextInt();
        
        System.out.println("How many trials should be run?");
        System.out.print("> ");
        int numTrials = kbd.nextInt();
        
        for (int counter = 0; counter < numTrials; counter++)
        {
            rolls = 0;
            sumCount = 0;
            
            while (sumCount < numConsecutiveSums)
            {
                die1 = ((int) (Math.random() * 6)) + 1;
                die2 = ((int) (Math.random() * 6)) + 1;
                
                rolls++;
                
                sum = die1 + die2;
                
                if (sum ==  goalSum)
                {
                    sumCount++;
                }
            }
            
            ttlNumRolls += rolls;
        }
        
        avgNumRolls = (double) ttlNumRolls/numTrials;
        
        System.out.println("Target summation: " + goalSum);
        System.out.println("Average number rolls: " + avgNumRolls);
    }
}