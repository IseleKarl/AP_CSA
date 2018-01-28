//Karl Isele
package averages;

import java.util.ArrayList;
import java.util.Collections;

public class AverageMethods 
{
    public static ArrayList<Integer> modes(int[] v)
    {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (int element : v)
        {
            values.add(element);
        }
        
        ArrayList<Integer> modes = new ArrayList<Integer>();
        
        ArrayList<Integer> mutableCopy = new ArrayList<Integer>();
        for (int element : v)
        {
            mutableCopy.add(element);
        }
        
        ArrayList<Integer> frequencies = new ArrayList<Integer>(mutableCopy.size());
        int maxFrequency = 0;
        
        for (int i = 0; i < mutableCopy.size(); i++)
        {
            frequencies.add(i, Collections.frequency(mutableCopy, mutableCopy.get(i)));
        }
        
        for (int i = 0; i < frequencies.size(); i++)
        {
            maxFrequency = Math.max(maxFrequency, frequencies.get(i));
        }
        
        for (int i = 0; i < mutableCopy.size(); i++)
        {
            if (Collections.frequency(mutableCopy, mutableCopy.get(i)) > 1)
            {
                mutableCopy.remove(i);
            }
        }
        
        for (int i = 0; i < mutableCopy.size(); i++)
        {
            if (Collections.frequency(values, mutableCopy.get(i)) == maxFrequency)
            {
                modes.add(mutableCopy.get(i));
            }
        }
        
        return modes;
    }
}