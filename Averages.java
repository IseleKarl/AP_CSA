//Karl Isele

package averages;

import java.util.ArrayList;
import java.util.Arrays;

public class Averages 
{
    public static void main(String[] args) 
    {
        int[] values = new int[10];
        for (int i = 0; i < values.length-2; i++)
        {
            values[i] = (int)(Math.random()*10)+1;
        }
        
        System.out.println("Values: " + Arrays.toString(values));
        ArrayList<Integer> modes = AverageMethods.modes(values);
        System.out.println("Modes: " + Arrays.toString(modes.toArray()));
    }
}
