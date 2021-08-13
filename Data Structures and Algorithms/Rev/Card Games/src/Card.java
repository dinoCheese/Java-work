/**
 * Created by Emily on 27/08/14.
 */
public class Card {

    private Suit mySuit;
    private int myNumber;

    public Card(Suit asuit, int aNumber) {
        this.myNumber = aNumber;
        this.mySuit = asuit;
    }

    public void setMySuit() {
        //
    }

    public Suit getMySuit() {
        return mySuit;
    }

    public void setMyNumber() {
        //
    }

    public int getMyNumber() {
        return myNumber;
    }

    public String toString() {

        String numStr = "error";

        try {
            switch (this.myNumber) {
                case (2):
                    numStr = "Two";
                    break;
                case (3):
                    numStr = "Three";
                    break;
                case (4):
                    numStr = "Four";
                    break;
                case (5):
                    numStr = "Five";
                    break;
                case (6):
                    numStr = "Six";
                    break;
                case (7):
                    numStr = "Seven";
                    break;
                case (8):
                    numStr = "Eight";
                    break;
                case (9):
                    numStr = "Nine";
                    break;
                case (1):
                    numStr = "Ace";
                    break;
                case (10):
                    numStr = "Ten";
                    break;
                case (11):
                    numStr = "Jack";
                    break;
                case (12):
                    numStr = "Queen";
                    break;
                case (13):
                    numStr = "King";
                    break;
            }

        } catch (Exception e) {

            System.out.println("That card value is not in the deck. Please try again.");
            System.exit(-1);

        }

        return (numStr + " of " + mySuit.toString());

    }
}

