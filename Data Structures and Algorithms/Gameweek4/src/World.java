/**
 Emily Sellman
 i6045016
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

class World {
    protected int rows, cols, emeraldsRemaining;
    protected WorldObject[][] world;
    protected Random rng;

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

    World(String filename) throws BadFileFormatException, IOException {

        BufferedReader in = null;
        in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

        this.rows = Integer.parseInt(in.readLine());
        this.cols = Integer.parseInt(in.readLine());
        this.emeraldsRemaining = Integer.parseInt(in.readLine());

        world = new WorldObject[rows][cols];

        int row = 0;
        String line = in.readLine();
        int emeraldsInWorld = 0;

        while (line != null) {

            if (row >= rows)
                throw new BadFileFormatException("Too many rows", row, -1);

            if (line.length() != cols)
                throw new BadFileFormatException("Too few or too many columns (" + line.length() + ")", row, -1);

            for (int col = 0; col < cols; col++) {
                char c = line.charAt(col);

                // create the world object
                world[row][col] = WorldObject.createFromChar(c);

                if (world[row][col] == null)
                    throw new BadFileFormatException("Invalid character: " + c, row, col);

                emeraldsInWorld += world[row][col].getEmeraldValue();
            }

            line = in.readLine();

            row++;
        }

        if (row != rows)
            throw new BadFileFormatException("Not enough rows (" + rows + ")", -1, -1);

        if (emeraldsInWorld < emeraldsRemaining)
            throw new BadFileFormatException("Not enough emeralds in the world: " + emeraldsInWorld, -1, -1);

        in.close();
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

    // Returns true if an object with mass falls on the player, false otherwise
    private boolean applyGravity(int exceptRow, int exceptCol) {
        boolean fellOnPlayer = false; // flag to indicate if an object with mass fell on the player

        // apply gravity from bottom-up! So, if there are two rocks, (one on
        // top of the other), they both fall...
        // start at rows-2 because gravity does not apply to objects on bottom row
        for (int r = rows - 2; r >= 0; r--) {
            for (int c = 0; c < cols; c++) {

                if (r == exceptRow && c == exceptCol) {
                    // do not apply gravity here
                    continue;
                }

                int rowBelow = r+1;

                if (world[r][c].hasMass() && world[rowBelow][c].isVulnerable()) {
                    if (world[rowBelow][c].isPlayer())
                        fellOnPlayer = true;

                    world[rowBelow][c] = world[r][c];
                    world[r][c] = new Space();
                }

            }
        }

        return fellOnPlayer;
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
        int antiGravityRow = -1, antiGravityCol = -1;
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

            // check for anti-gravity exception
            int rowAbove = nextRow-1;
            if (inBounds(rowAbove, nextCol) && world[rowAbove][nextCol].hasMass()) {
                antiGravityRow = rowAbove;
                antiGravityCol = nextCol;
            }
        }

        // check for a win
        if (emeraldsRemaining == 0)
            return 1;

        // alien's turn
        nextRow = aRow;
        nextCol = aCol;

        // ... if the aliend is still around, move it
        if (nextRow != -1 && nextCol != -1) {
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
        }

        // now, apply gravity
        boolean fellOnPlayer = applyGravity(antiGravityRow, antiGravityCol);
        if (fellOnPlayer)
            return 2;

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

