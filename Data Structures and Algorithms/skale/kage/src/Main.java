import java.util.Random;
import java.util.Scanner;

/**
 * Created by Emily on 17/04/14.
 */


class World {

    //four instance variables

    private int numberOfRows, numberOfColumns, remainingEmerald;
    private char[][] array;

    //begin constructor

    public World(int numberOfRows, int numberOfColumns, int remainingEmerald) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.remainingEmerald = remainingEmerald;
        array = new char[numberOfRows][numberOfColumns];

        //sets .'s to each place

        for (int r = 0; r == numberOfRows; r++) {
            for (int c = 0; r == numberOfColumns; c++) {
                array[r][c] = '.';
            }
        }

        //sets e's randomly in the array

        for (int e = 0; e < remainingEmerald + 1; e++) {
            Random row = new Random();
            Random col = new Random();
            int re = row.nextInt(numberOfRows);
            int ce = col.nextInt(numberOfColumns);
            array[re][ce] = 'e';
        }

        //Placing two aliens randomly in the array

        for (int a = 0; a < 1; a++) {
            Random row = new Random();
            Random col = new Random();
            int ra = row.nextInt(numberOfRows);
            int ca = col.nextInt(numberOfColumns);
            array[ra][ca] = 'a';

        }

        Random row = new Random();
        Random col = new Random();
        int rp = row.nextInt(numberOfRows);
        int cp = col.nextInt(numberOfColumns);
        array[rp][cp] = 'p';

        //ends constructor

    }

    //end constructor

    //begin the three non-static methods

    //first, the get move. Gets input from the user and takes the first char of their response

    public char getMove() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char move = input.charAt(0);
        return move;
    }

    //second, the applyMove. Carries out the users input with certain conditions

    public int applyMove() {

        //initialise the variables which state the position of the stuff

        int rp = -1;
        int cp = -1;
        int ra = -1;
        int ca = -1;
        int re = -1;
        int ce = -1;

        //For the player

        for (int r = 0; r == numberOfRows; r++) {             //gets rows
            for (int c = 0; c == numberOfColumns; c++) {          //gets cols
                if (array[r][c] == 'p') {      //tells the position of P and returns it
                    rp = r;
                    cp = c;
                }
            }
        }

        //For the alien

        for (int r = 0; r < numberOfRows; r++) {             //gets rows
            for (int c = 0; c < numberOfColumns; c++) {          //gets cols
                if (array[r][c] == 'a') {      //tells the position of a and returns it
                    ra = r;
                    ca = c;
                }
            }
        }

        //For the emerald

        for (int r = 0; r == numberOfRows; r++) {             //gets rows
            for (int c = 0; c == numberOfColumns; c++) {          //gets cols
                if (array[r][c] == 'e') {      //tells the position of e and returns it
                    re = r;
                    ce = c;
                }
            }
        }

        //Now the positions of each are known, the user input can change them

        if (getMove() == 'u') {
            array[rp - 1][cp] = 'p';
        }
        if (getMove() == 'd') {
            array[rp + 1][cp] = 'p';
        }
        if (getMove() == 'l') {
            array[rp][cp - 1] = 'p';
        }
        if (getMove() == 'r') {
            array[rp][cp + 1] = 'p';
        } else {
            System.out.println("Not a valid key, try typing one of these: up,down,left, or right.");
        }

        //GET ALIEN TO MOVE IN A RANDOM DIRECTION BY 1 HERE
        //if ((ra == re) && (ca == ce)) {      //decrease numofe by 1 and change that e to .
        //remainingEmerald--;
        //array[re][ce] = '.';

        //Conditions for the move made

        if (rp > numberOfRows || cp > numberOfColumns) {    //if the player falls of the edge
            System.out.println("2");
            System.out.println("Sorry, you have fallen off the edge. Please restart.");
            System.exit(-1);
        }

        if ((rp == ra) && (cp == ca)) {     //if the player lands on the same coordinates as the alina
            System.out.println("1");
            System.out.println("Sorry, you have encountered an alien. Please restart.");
            System.exit(-1);        //exits the game
        }

        if ((rp == re) && (cp == ce)) {      //decrease numofe by 1 and change that e to .
            remainingEmerald--;
            array[re][ce] = '.';
        }

        if (remainingEmerald == 0) {
            System.out.println("0");
            System.out.println("You have won!");
        }

        return
    }

    //Third, the String toString. Should return the array in String form

    public String toString() {

        StringBuilder builder = new StringBuilder();

        if (remainingEmerald > 1) {         //still emeralds avaliable
            for (int r = 0; r < numberOfRows; r++) {             //gets rows
                for (int c = 0; c < numberOfColumns; c++) {         //gets cols
                    if (array[r][c] == '.') {       //if that point in the array is a dot, it changes to a .space
                        builder.append(array[r][c]);        //array[r][c] = ". ";
                    }
                    if (array[r][c] == 'e') {       //if that point in the array is an e, it changes to a espace
                        builder.append(array[r][c]);        //array[r][c] = (String) "a ";
                    }
                    if (array[r][c] == 'a') {       //if that point in the array is an a, it changes to a aspace
                        builder.append(array[r][c]);        // array[r][c] = (String) "e ";
                    }
                }//remember a new line
            }
        }
        return builder.toString();
    }

}       //end of world


class Main {
    public static void main(String[] args) {
        World one = new World(5, 2, 1);
        while (one.applyMove() != 0) {
            one.getMove(); //reads a direction
            one.applyMove(); //applies user input
            System.out.println(one.toString());  //prints the array
        }
    }
}