/**
 * Created by Emily on 17/04/14.
 * i6045016
 */

import java.util.*;

class World {
    private int rows, cols, emeraldsRemaining;
    private WorldObject[][] world;
    private Random rng;

    World(int rows, int cols, int emeralds) {
        this.rows = rows;
        this.cols = cols;
        this.emeraldsRemaining = emeralds;

        world = new WorldObject[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                world[r][c] = new Dirt();
            }
        }
        rng = new Random();
        initialCreation();
    }

    private void initialCreation() {

        //placing dirt in every space
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                world[r][c] = new Dirt();
            }
        }

        // place the player just once in a random row and a random column
        int randRow = rng.nextInt(rows);
        int randCol = rng.nextInt(cols);
        world[randRow][randCol] = new Player();

        while (true) {
            randRow = rng.nextInt(rows);
            randCol = rng.nextInt(cols);
            //while not player at that very position
            //While row & col doesn't have a player in it, then place everything

            //placing alien
            world[randRow][randCol] = new Alien();
            //placing Diamonds
            world[randRow][randCol] = new Diamond(); world[randRow][randCol] = new Diamond();
            world[randRow][randCol] = new Diamond();
            //placing rocks
            world[randRow][randCol] = new Rock();world[randRow][randCol] = new Rock();
            world[randRow][randCol] = new Rock();world[randRow][randCol] = new Rock();
            //placing spaces
            world[randRow][randCol] = new Space();

            // placing emerald
            for (int e = 0; e < emeraldsRemaining + 1; e++) {
                if (world[randRow][randCol] != new Player()) {
                    world[randRow][randCol] = new Emerald();
                }
                // if we randomly hit an already placed emerald, start over
                else if (world[randRow][randCol] == new Emerald()) {        //not sure about the new bit
                    e--;
                }
            }
        }
    }

    public boolean inBounds(int r, int c) {

        for (r = 0; r < rows; r++) {
            for (c = 0; c < cols; c++) {
                if (world[r][c] == new Player()) {
                    int toRow = -1;
                    int toCol = -1;
                    toRow = r;
                    toCol = c;
                }
            }

            for (r = 0; r < rows; r++) {
                for (c = 0; c < cols; c++) {
                    int fromRow = -1;
                    int fromCol = -1;
                    fromRow = r;
                    fromCol = c;
                }
            }
            int toRow = -1;
            int toCol = -1;

            if (!inBounds(toRow, toCol)) {
                System.out.println("Oops, you've fallen off! Please restart.");
                System.exit(-1);
                } else if (world[toRow][toCol].canMove() && !world[toRow][toCol].isPlayer()) {
                System.out.println("Oops, you've hit a monster! Please restart.");
                System.exit(-1);
            } else if (!world[toRow][toCol].isEdible()) {
                System.out.println("You can't eat that!");
                // do nothing.. destination is not edible!
            } else if (world[toRow][toCol].isEdible()) {
                emeraldsRemaining--;
                // decrease the emeralds remaining
                emeraldsRemaining -= world[toRow][toCol].getEmeraldValue();

                if (emeraldsRemaining <= 0)
                    emeraldsRemaining = 0;

                int fromRow = -1;
                int fromCol = -1;

                // move the player
                world[toRow][toCol] = world[fromRow][fromCol];

                world[fromRow][fromCol] = new Space();
            }

        }
        return (r >= 0 && r < rows && c >= 0 && c < cols);
    }


    public char getMove() {
        System.out.print("Where to? ");
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        return line.charAt(0);
    }


    public int applyMove(char move) {


        /*Change your applyMove() method so that when the player or alien moves over an edible object, it collects the
        emerald value for it (if it's the player), moves the player / alien there, and changes the place that it's coming from
        to space, like so:*/


        // first, find the player and alien locations
        // could also save these as instance variables, instead of finding them each time
        int pRow = -1, pCol = -1;
        int aRow = -1, aCol = -1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (world[r][c] == new Player()) {
                    pRow = r;
                    pCol = c;
                } else if (world[r][c] == new Alien()) {
                    aRow = r;
                    aCol = c;
                }
            }
        }

        // ok, now move them. player first
        int nextRow = pRow, nextCol = pCol;

        if (move == 'u')
            nextRow--;
        else if (move == 'd')
            nextRow++;
        else if (move == 'l')
            nextCol--;
        else if (move == 'r')
            nextCol++;

        // player dies if they step out of bounds or into the alien
        if (!inBounds(nextRow, nextCol) || world[nextRow][nextCol] == new Alien())
            return 2;

        // if stepping into a square with an emerald, decrease remaining by 1
        if (world[nextRow][nextCol] == new Emerald()){
            emeraldsRemaining--;
            new Emerald().getEmeraldValue();

        }

        // now change the world
        world[pRow][pCol] = new Dirt();
        world[nextRow][nextCol] = new Player();

        // check for a win
        if (emeraldsRemaining == 0)
            return 1;

        // alien's turn
        nextRow = aRow;
        nextCol = aCol;

        // ... if it's still around...
        if (nextRow == -1 && nextCol == -1)
            return 0;

        int roll = rng.nextInt(4);

        if (roll == 0)
            nextRow++;
        else if (roll == 1)
            nextRow--;
        else if (roll == 2)
            nextCol--;
        else if (roll == 3)
            nextCol++;

        if (!inBounds(nextRow, nextCol)) {
            // ladies and gentlemen, the alien is floating in space..
            world[aRow][aCol] = new Dirt();
        } else {
            world[aRow][aCol] = new Dirt();

            if (world[nextRow][nextCol] == new Player()) {
                // captured!
                world[nextRow][nextCol] = new Alien();
                return 2;
            } else {
                world[nextRow][nextCol] = new Alien();
            }
        }
        return 0;
    }

    public String toString() {

        //call to String for every WorldObject
        String str = "Emeralds remaining: " + emeraldsRemaining + "\n";

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                str = str + world[r][c];
            }

            str += "\n";
        }

        return str;
    }
}

