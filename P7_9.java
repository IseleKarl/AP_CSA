//Karl Isele
package p7_9;

import java.util.Arrays;

public class P7_9 
{
    public static void main(String[] args) 
    {
        Deck deck = new Deck();
        System.out.println("Deck: " + deck.returnDeck());
        
        deck.shuffle();
        System.out.println("Deck: " + deck.returnDeck());
    }
}
