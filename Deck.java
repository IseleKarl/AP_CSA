//Karl Isele
package p7_9;

import java.util.ArrayList;

public class Deck 
{
    private ArrayList<Integer> deck;
    private final int maxSize = 52;
    
    public Deck(ArrayList<Integer> inputDeck)
    {
        deck = new ArrayList(maxSize);
        this.deck = inputDeck;
    }
    
    public Deck()
    {
        deck = new ArrayList(maxSize);
        for (int i = 0; i < maxSize; i++)
        {
            deck.add(i);
        }
    }
    
    public ArrayList returnDeck()
    {
        return deck;
    }
    
    public int size()
    {
        return this.deck.size();
    }
    
    public void shuffle()
    {
        int value = (int) Math.random()*52;
        
        ArrayList<Integer> shuffled;
        for (int i = 0; i < deck.size(); i++)
        {
            /*shuffled = new ArrayList(deck.subList(0, i));
            while (shuffled.contains(value))
                value = (int) Math.random()*52;
            deck.set(i,value);*/
        }
    }
}
