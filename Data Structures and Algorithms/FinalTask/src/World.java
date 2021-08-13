/**
 * Created by Varsha on 19-04-2014.
 * Student ID: i6045552
 */

import java.io.*;
import java.util.*;

class World implements WorldState {
    private int rows;
    private int cols;
    private int emldleft; // # emeralds
    private int rocks;
    private int diamonds;
    private WorldObject[][] world;


    // Constructor method to initialize World randomly
    World(int rows, int cols, int emldleft, int diamonds, int rocks) {
        this.rows = rows;
        this.cols = cols;
        this.emldleft = emldleft - 3;
        this.diamonds = diamonds;
        this.rocks = rocks;
        world = new WorldObject[rows][cols];

        initializeWorld();
    }

    // Constructor for World, if it has to be taken from a text file
    World(String file) throws BadFileFormatException {
        try {
            // To read in a file
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line = in.readLine();
            // Assign rows from file
            rows = Integer.parseInt(line);

            line = in.readLine();
            // Assign cols from file
            cols = Integer.parseInt(line);

            world = new WorldObject[rows][cols];

            line = in.readLine();
            // Assign emldleft from file
            emldleft = Integer.parseInt(line);

            line = in.readLine();
            // Keep track of the row being read, so that when exception is caught, the row number can be printed.
            int row = 0;
            while (line != null) {
                for (int c = 0; c < cols; c++) {
                    // To check if char's String is from World Object
                    // Is there an asier way??
                    if (Character.toString(line.charAt(c)).equals(new Rock().toString()))
                        world[row][c] = new Rock();
                    else if (Character.toString(line.charAt(c)).equals(new Player().toString()))
                        world[row][c] = new Player();
                    else if (Character.toString(line.charAt(c)).equals(new Alien().toString()))
                        world[row][c] = new Alien();
                    else if (Character.toString(line.charAt(c)).equals(new Space().toString()))
                        world[row][c] = new Space();
                    else if (Character.toString(line.charAt(c)).equals(new Dirt().toString()))
                        world[row][c] = new Dirt();
                    else if (Character.toString(line.charAt(c)).equals(new Emerald().toString()))
                        world[row][c] = new Emerald();
                    else if (Character.toString(line.charAt(c)).equals(new Diamond().toString()))
                        world[row][c] = new Diamond();
                    else if (Character.toString(line.charAt(c)).equals(new Bug().toString()))
                        world[row][c] = new Bug();
                    else if (Character.toString(line.charAt(c)).equals(new Spaceship().toString()))
                        world[row][c] = new Spaceship();
                    else
                        throw new BadFileFormatException(line, row + 1, c + 1);

                }
                row++;
                line = in.readLine();
            }

            in.close();

        }
        // Catch input-output exception & print it out
        catch (IOException e) {
            e.printStackTrace();
        }
        // Catch a bad file format & print it out
        catch (BadFileFormatException e) {
            e.printStackTrace();
        }
    }


    private void initializeWorld() {
        // Create dirt everywhere in World
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                world[i][j] = new Dirt();
            }
        }
        Random rdm = new Random();

        // Player to a random position
        world[rdm.nextInt(rows)][rdm.nextInt(cols)] = new Player();

        // Initialization integers for random row & column numbers generations
        int rowNum, colNum;

        // To add alien to a random place, without replacing the player
        int numAlien = 1;
        while (numAlien != 0)

        {
            rowNum = rdm.nextInt(rows);
            colNum = rdm.nextInt(cols);

            // If the random array index position is not occupied by the player, add alien
            if (!world[rowNum][colNum].isPlayer()) {
                world[rowNum][colNum] = new Alien();
                numAlien--;
            }
        }

        // To add diamonds at random places
        int diaPlaced = diamonds;
        while (diaPlaced != 0) {
            rowNum = rdm.nextInt(rows);
            colNum = rdm.nextInt(cols);

            // If the random array index position is not occupied by the player, alien or diamond, add a diamond
            if (!((world[rowNum][colNum].isVulnerable() && world[rowNum][colNum].canMove()) ||
                    (world[rowNum][colNum].isEdible() && world[rowNum][colNum].hasMass()))) {
                world[rowNum][colNum] = new Diamond();
                diaPlaced--;
            }
        }

        // To add emeralds at random places
        int emldPlaced = emldleft - diamonds * 2;   // Slack for Player, so he can afford to lose 3 points & still win
        while (emldPlaced != 0) {
            rowNum = rdm.nextInt(rows);
            colNum = rdm.nextInt(cols);

            // If the position is not already occupied by the player, alien, diamond or emerald, add an emerald.
            if (!((world[rowNum][colNum].isVulnerable() && world[rowNum][colNum].canMove()) ||
                    (world[rowNum][colNum].isEdible() && world[rowNum][colNum].hasMass()))) {
                world[rowNum][colNum] = new Emerald();
                emldPlaced--;
            }
        }

        int rockPlaced = rocks;
        while (rockPlaced != 0) {
            rowNum = rdm.nextInt(rows);
            colNum = rdm.nextInt(cols);

            // If the position is not already occupied by the player, alien, diamond, emerald or rock, add an emerald
            if (!((world[rowNum][colNum].isVulnerable() && world[rowNum][colNum].canMove()) ||
                    (world[rowNum][colNum].isEdible() && world[rowNum][colNum].hasMass()) ||
                    world[rowNum][colNum].hasMass())) {
                world[rowNum][colNum] = new Rock();
                rockPlaced--;
            }
        }
    }

    // Getters for World
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getEmldleft() {
        return emldleft;
    }

    public WorldObject get(int r, int c) {
        return world[r][c];
    }

    // Setter
    public void set(int r, int c, WorldObject element) {
        world[r][c] = element;
    }

    // To return characters in World as a multiple line string
    public String toString() {
        String str = "";
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                str += world[r][c].toString();
                str += " ";
            }
            // new line after each row
            str += "\n";
        }
        return str + "\n" + "Emeralds left: " + emldleft;
    }

    public boolean inBounds(int r, int c) {
        return (r >= 0 && r < rows && c >= 0 && c < cols);
    }

    // Method to find position of (the first of) a WorldObject
    public int[] findPos(WorldObject object) {
        int row = -1;
        int col = -1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (world[r][c].toString().equals(object.toString())) {
                    row = r;
                    col = c;
                }
            }
        }
        return new int[]{row, col};
    }

    // To be completed
    public boolean moveMonsters() {
        boolean monsterMurder = false;

        synchronized (this) {
            LinkedList<Integer> monsterRows = new LinkedList<Integer>();
            LinkedList<Integer> monsterCols = new LinkedList<Integer>();

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (world[r][c].isMonster()) {
                        monsterRows.add(r);
                        monsterCols.add(c);
                    }
                }
            }

            Random rng = new Random();

            while (monsterRows.size() > 0) {
                // choose one at random so there's no preference as to which monster moves first
                int index = rng.nextInt(monsterRows.size());
                int row = monsterRows.remove(index);
                int col = monsterCols.remove(index);

                WorldObject monster = world[row][col];

                char move = monster.getMove();
                while (wayBlocked(monster, row, col, move)) {
                    world[row][col].changeDirection();
                }
                if (moveAlien(move, row, col) == 2) {
                    monsterMurder = true;
                    return monsterMurder;
                }
            }
        }
        return monsterMurder;
    }

    // Method to find move to bring a movable object1 closer to object2
    public char bestMove(WorldObject one, WorldObject two) {
        char bestmove = 'u';
        /*
        Stuff to find shortest path length
        Use isBlah() methods to avoid running into non-space stuff
         */
        return bestmove;
    }

    public int[] giveMove(char move, int fromRow, int fromCol) {
        // Initialization of row & col to go to
        int toRow = -1;
        int toCol = -1;

        if (inBounds(fromRow, fromCol)) {
            if (move == 'r' && fromCol < cols - 1) {
                toRow = fromRow;
                toCol = fromCol + 1;

            } else if (move == 'l' && fromCol > 0) {
                toRow = fromRow;
                toCol = fromCol - 1;

            } else if (move == 'u' && fromRow > 0) {
                toRow = fromRow - 1;
                toCol = fromCol;

            } else if (move == 'd' && fromRow < rows - 1) {
                toRow = fromRow + 1;
                toCol = fromCol;

            } else {
                // If move is invalid, then a negative array is returned
                return new int[]{-2, -2};
            }
        }
        return new int[]{toRow, toCol};
    }

    public boolean wayBlocked(WorldObject obj, int fromRow, int fromCol, char move) {
        int toRow = giveMove(move, fromRow, fromCol)[0];
        int toCol = giveMove(move, fromRow, fromCol)[1];

        if (world[toRow][toCol] instanceof Player || world[toRow][toCol].hasPoints() || (world[toRow][toCol].isEdible() && world[toRow][toCol].isVulnerable()))
            return false;
        else
            return true;
    }

    public int applyMove(char move) {
        synchronized (this) {
            // char move = new Player().getMove();

            // Code to find the current positions of the alien & player, and store them
            int playFromRow = -1;
            int playFromCol = -1;

            // Find player & alien, and assign the from & to row values
            playFromRow = findPos(new Player())[0];
            playFromCol = findPos(new Player())[1];

            // Code for moving player

            // Position to which player shall be going
            int playToRow, playToCol;

            int[] playMove = giveMove(move, playFromRow, playFromCol);

            int noGravityRow = -1;
            int noGravityCol = -1;

            if (playMove[0] != -2 && playMove[1] != -2) {
                playToRow = playMove[0];
                playToCol = playMove[1];

                if (!world[playToRow][playToCol].isPlayer() && world[playToRow][playToCol].canMove())
                    return 2;
                else {  // Code to move player to desired position
                    // If a rock is found, nothing is done
                    if (!world[playToRow][playToCol].isEdible()) {
                        return 0;
                    } else {
                        // If an emerald is found, emldleft is decremented
                        if (world[playToRow][playToCol].isEdible() && world[playToRow][playToCol].hasMass()) {
                            emldleft -= world[playToRow][playToCol].getEmeraldValue();
                            noGravityRow = playToRow - 1;
                            noGravityCol = playToCol;
                        }
                        if (emldleft <= 0)
                            emldleft = 0;
                        // Move the player and replace the 'p' at the previous position to a space
                        world[playToRow][playToCol] = world[playFromRow][playFromCol];
                        world[playFromRow][playFromCol] = new Space();
                    }
                }
            }

            // To check if all emeralds have been collected
            if (emldleft == 0)
                return 1;

            /*if (moveMonsters())
                return 2;
            */
            if (applyGravity(noGravityRow, noGravityCol))
                return 2;
        }

        // Game not over, yet
        return 0;
    }

    // Code for moving alien after player moves
    public int moveAlien(char move, int alienFromRow, int alienFromCol) {
        synchronized (this) {
            if (inBounds(alienFromRow, alienFromCol)) {
                int[] alienMove = giveMove(move, alienFromRow, alienFromCol);

                if (alienMove[0] != -2 && alienMove[1] != -2) {
                    int alienToRow = alienMove[0];
                    int alienToCol = alienMove[1];

                    if (world[alienToRow][alienToCol].isPlayer())
                        return 2;       // Game Over if alien lands on the player's position
                    else {
                        // Alien can move only if the place it wants to move to is a shiny object or space
                        //if (world[alienToRow][alienToCol].isEdible() &&
                        //        (world[alienToRow][alienToCol].hasMass() || world[alienToRow][alienToCol].isVulnerable())) {
                        if (emldleft <= 0)
                            emldleft = 0;
                        world[alienToRow][alienToCol] = world[alienFromRow][alienFromCol];
                        world[alienFromRow][alienFromCol] = new Space();
                        //}
                    }
                }
            }
            return 0;
        }
    }

    // Returns true if an object with mass falls on the player, false otherwise
    public boolean applyGravity(int exceptRow, int exceptCol) {
        // flag to indicate if an object with mass fell on the player
        boolean fellOnPlayer = false;

        synchronized (this) {
            // start at rows-2 because gravity does not apply to objects on bottom row
            for (int r = rows - 2; r >= 0; r--) {
                for (int c = 0; c < cols; c++) {
                    if (r == exceptRow && c == exceptCol)
                        continue; // Do not apply gravity here
                    if (world[r][c].hasMass() && world[r + 1][c].isVulnerable()) {
                        if (world[r + 1][c].isPlayer()) {
                            fellOnPlayer = true;
                        }
                        world[r + 1][c] = world[r][c];
                        world[r][c] = new Space();
                    }
                }
            }
        }
        return fellOnPlayer;
    }
}