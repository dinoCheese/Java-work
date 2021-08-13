/**
 * Created by Emily on 19/03/14.
 */

import java.util.*;

import com.sun.beans.editors.IntegerEditor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

//creating Card, using encapsulation

class Card {

    private String value;
    private String suit;
    private boolean faceup = true;

    //Creating the constructors

    Card(String value, String suit, boolean faceup) {
        this.value = value;
        this.suit = suit;
        this.faceup = faceup;
    }

    //method (to flip)

    public void flip() {
        if (faceup == true) {
            faceup = false;
        } else {
            faceup = true;
        }
    }

    //method//represents a card//returns a string

    public String toString() {
        if (faceup) {
            return value + suit;
        } else {
            return "??";
        }
    }

    //finds the minimum values of the cards

    public int getMinValue() {
        if (value == "A") {
            return 1;
        } else if (value == "Q" || value == "K" || value == "J") {
            return 10;
        } else return (Integer.parseInt(value));
    }

    //Getters

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }
}

//creating the Deck

class Deck {

    private LinkedList<Card> deck = new LinkedList<Card>();
    private String[] suit = {" Diamonds", " Hearts", " Clubs", " Spades"};
    private String[] value = {"A", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "J", "Q", "K"};

    Deck() {
        for (int s = 0; s < 4; s++) {
            for (int n = 0; n < 13; n++) {
                deck.add(new Card(value[n], suit[s], true));
            }
        }
    }

    //removed top card from the list and returns it

    public Card deal() {
        return deck.remove(0);
    }

    //Shuffles list

    public void shuffle() {
        Collections.shuffle(deck);
    }

    //Returns string representation of the linked list

    public String toString() {
        return (deck.element().getValue() + deck.element().getSuit() + " ");
    }
}


public class Blackjack {

    public static int getMinValue(LinkedList<Card> Deck) {
        int value = 0;
        for (int p = 0; p > Deck.size(); p++) {
            value = Deck.get(p).getMinValue() + value;

        }
        return value;
    }

    //testing phase begins

    public static void testCard()

    {        //method
        Card Carl = new Card("A", "S", true);
        Card David = new Card("7", "C", false);
        System.out.println(Carl.toString() + " " + David.toString());
        System.out.println(Carl.getMinValue());
        System.out.println(David.getMinValue());
    }

    public static void testDeck() {
        Deck testDeckName = new Deck();
        testDeckName.shuffle();
        for (int i = 0; i < 52; i++) {
            System.out.println(testDeckName.toString());
            testDeckName.deal();
        }
    }

    //testing phase ends

    public static void main(String[] args) {
        //testDeck();
        Deck deck1 = new Deck();
        deck1.shuffle();
        LinkedList<Card> dealerHand = new LinkedList<Card>();
        LinkedList<Card> playerHand = new LinkedList<Card>();

        //setup phase

        Card k = deck1.deal();
        k.flip();       //make facedown
        dealerHand.add(k);
        dealerHand.add(deck1.deal());

        Card m = deck1.deal();
        playerHand.add(m);
        playerHand.add(deck1.deal());

        //player phase
        System.out.println(deck1.toString());
        deck1.deal();
        System.out.println("Welcome one, welcome all, this is Blackjack! ");
        System.out.println("Dealers hand: " + dealerHand.get(0) + "  " + dealerHand.get(1));
        System.out.println("Players hand: " + playerHand.get(0) + "  " + playerHand.get(1));
        System.out.println("Would the player like to hit? Or to Stand? Type H for hit, and S for stand.");
        Scanner input = new Scanner(System.in);                     //Make a scanner
        String hitOrStand = input.nextLine();

        //Checks the value of the hand of both the dealer and the player

        if (getMinValue(dealerHand) > 21) {
            System.out.println("Woah there, you've exceeded the value limit of 21, Bust.");
            System.out.println(getMinValue(dealerHand));
            System.exit(-1);
        } else if (getMinValue(dealerHand) > 21) {
            System.out.println("Woah there, the dealer had exceeded the value limit of 21. You win!");
            System.exit(-1);

            //if no one has exceeded 21 then carry out the choice of hit or stand
            //getMinValue(LinkedList<Card> Deck)


        } else if (getMinValue(playerHand) < 21) {

            //if hit has been chosen

            if (hitOrStand == "H") {
                System.out.println("Players hand: " + playerHand.get(2));
                System.out.println(getMinValue(playerHand));

                //if stand has been chosen

            } else if (hitOrStand == "S") {
                System.out.println("Your hand was worth: " + getMinValue(playerHand));
                System.out.println("The dealers hand was worth: " + getMinValue(dealerHand));


                //if neither H or S were chosen, typo or misunderstanding, restart the game

            } else {
                System.out.println("You have made a typing mistake, please start the game again. Thank you.");
                System.exit(-1);
            }
        }


    }
}




/*/
 */