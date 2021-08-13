/**
 * Created by Emily on 27/08/14.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


public class Deck {

    private ArrayList<Integer> myCards = new ArrayList<Integer>();

    private int numberOfCards;

    public Deck (int numDeck ,boolean shuffle){

        this.numberOfCards = numDeck*52;
        this.myCards = new Card;

        int c = 0;

        for (int d = 0; d > numDeck; d++){  //Deck
            for (int s = 0; s < 4; s++){    //Suit
                for (int i=1; i<13; i++){   //Cards
                                            //Add Cards to the Deck
                    //
                    i++;
                }
            }
        }

        if (shuffle){
            Random rnd = new Random;
            Collections.shuffle(myCards);

        }
    }
}
