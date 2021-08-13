/**
 Emily Sellman i6045016
 */

import java.util.Random;
import java.util.Scanner;
import java.io.*;

class World {
    private int rows, cols, emeraldsRemaining;
    private WorldObject[][] world;
    private Random rng;
    private String inputfile;       // represents the initial board

    class BadFileFormatException extends Exception {
        protected String issue;
        protected int col, row;

        BadFileFormatException(String issue, int col, int row) {
            this.issue = issue;
            this.col = col;
            this.row = row;
        }

        public String toString() {
            return "BadFileFormatException: " + issue + "\n" + "in Row: " + row + "\n" + "in Col: " + col;
        }
    }

    World(String inputfile) throws BadFileFormatException {
        this.inputfile = inputfile;
        throw new BadFileFormatException("Nope", rows, cols);
    }

    public void parse() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputfile)));
            if (inputfile.endsWith(".txt")) {
                File inputfile = new File("World.txt");
                PrintStream out;
                out = new PrintStream("filtered-pepper.txt");

                while (world != null) {
                    System.out.println(world.toString());
                }
                out.close();
            //} else (inputfile.endsWith(".txt") == false){
                throw new BadFileFormatException("That format is not a .txt file", rows, cols);
            }
            //end of try
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BadFileFormatException e) {
            e.printStackTrace();
        }
    }

                                /*
Write a test method that creates a new world by using this new constructor for the example file.

Print out the board using the world's toString() method to be certain that it is being read correctly.

Then, create three other board files with illegal formats to make sure that the exception is being thrown in each case.
                                */

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

    public boolean applyGravity(int exceptRow, int exceptCol) {
        boolean fellOnPlayer = false;
        for (int r = rows - 2; r >= 0; r--) {//look at a position starting at the bottom left
            for (int c = 0; c < cols; c++) {
                if ((world[r][c].isPlayer()) && (world[r - 1][c].hasMass())) {
                    fellOnPlayer = true;
                }
                if ((world[r + 1][c].isVulnerable()) && (world[r][c].hasMass())) {
                    world[r + 1][c] = world[r][c];
                    world[r][c] = new Space();
                }
            }
        }
        return fellOnPlayer;
    }

    private void initialCreation() {

        // place the player
        int randRow = rng.nextInt(rows);
        int randCol = rng.nextInt(cols);
        world[randRow][randCol] = new Player();

        // try placing the alien..
        // repeat the process until a location is generated that is not the same as the player's location
        while (true) {
            randRow = rng.nextInt(rows);
            randCol = rng.nextInt(cols);

            if (world[randRow][randCol].isOpen()) {
                world[randRow][randCol] = new Alien();
                break;
            }
        }

        // first place the emeralds
        for (int e = 0; e < emeraldsRemaining + 1; e++) {
            randRow = rng.nextInt(rows);
            randCol = rng.nextInt(cols);

            // if it's not open, start over
            if (!(world[randRow][randCol].isOpen())) {
                e--;
                continue;
            }

            world[randRow][randCol] = new Emerald();
        }

        // diamonds
        emeraldsRemaining += 9;

        for (int d = 0; d < 3; d++) {
            randRow = rng.nextInt(rows);
            randCol = rng.nextInt(cols);

            // if it's not open, start over
            if (!(world[randRow][randCol].isOpen())) {
                d--;
                continue;
            }

            world[randRow][randCol] = new Diamond();
        }

        // rocks
        int numRocks = 4 + rng.nextInt(3);

        for (int r = 0; r < numRocks; r++) {
            randRow = rng.nextInt(rows);
            randCol = rng.nextInt(cols);

            // if it's not open, start over
            if (!(world[randRow][randCol].isOpen())) {
                r--;
                continue;
            }

            world[randRow][randCol] = new Rock();
        }

    }

    public boolean inBounds(int r, int c) {
        return (r >= 0 && r < rows && c >= 0 && c < cols);
    }

    public char getMove() {
        System.out.print("Where to? ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        if (line.length() == 0)
            return ' ';

        return line.charAt(0);
    }

    public int applyMove(char move) {
        // first, find the player and alien locations
        // could also save these as instance variables, instead of finding them each time
        int pRow = -1, pCol = -1;
        int aRow = -1, aCol = -1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (world[r][c].isPlayer()) {
                    pRow = r;
                    pCol = c;
                } else if (world[r][c].isMonster()) {
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
        if (!inBounds(nextRow, nextCol) || world[nextRow][nextCol].isMonster())
            return 2;

        // if stepping into a square edible square, decrease by the point value
        if (world[nextRow][nextCol].isEdible()) {
            emeraldsRemaining -= world[nextRow][nextCol].getEmeraldValue();

            // do not go below 0
            if (emeraldsRemaining <= 0)
                emeraldsRemaining = 0;

            // now change the world
            world[pRow][pCol] = new Space();
            world[nextRow][nextCol] = new Player();
        }

        // check for a win
        if (emeraldsRemaining == 0)
            return 1;

        // alien's turn
        nextRow = aRow;
        nextCol = aCol;

        // ... if the alien is still around...
        if (nextRow == -1 && nextCol == -1)
            return 0;

        char amove = world[aRow][aCol].getMove();

        if (amove == 'd')
            nextRow++;
        else if (amove == 'u')
            nextRow--;
        else if (amove == 'l')
            nextCol--;
        else if (amove == 'r')
            nextCol++;

        if (!inBounds(nextRow, nextCol)) {
            // ladies and gentlemen, the alien is floating in space..
            world[aRow][aCol] = new Space();
        } else if (world[nextRow][nextCol].isEdible()) {
            world[aRow][aCol] = new Space();

            if (world[nextRow][nextCol].isPlayer()) {
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
        String str = "Emeralds remaining: " + emeraldsRemaining + "\n";

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                str += world[r][c];
            }
            str += "\n";
        }
        return str;
    }
}

//Tests.testLoadFile(); calls the tests
