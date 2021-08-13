
import java.util.*;

class Worlds {
    private int rows, cols, emeraldsRemaining;
    private char[][] worlds;
    private Random rng;

    Worlds(int rows, int cols, int emeralds) {
        this.rows = rows;
        this.cols = cols;
        this.emeraldsRemaining = emeralds;

        worlds = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                worlds[r][c] = '.';
            }
        }

        rng = new Random();

        initialCreation();
    }

    private void initialCreation() {

        // place the player
        int randRow = rng.nextInt(rows);
        int randCol = rng.nextInt(cols);
        worlds[randRow][randCol] = 'p';

        // try placing the alien..
        // repeat the process until a location is generated that is not the same as the player's location
        while (true) {
            randRow = rng.nextInt(rows);
            randCol = rng.nextInt(cols);

            if (worlds[randRow][randCol] == '.') {
                worlds[randRow][randCol] = 'a';
                break;
            }
        }

        // first place the emeralds
        for (int e = 0; e < emeraldsRemaining+1; e++) {
            randRow = rng.nextInt(rows);
            randCol = rng.nextInt(cols);

            // if we randomly hit an already placed emerald, start over
            if (worlds[randRow][randCol] != '.') {
                e--;
                continue;
            }

            worlds[randRow][randCol] = 'e';
        }
    }

    public boolean inBounds(int r, int c) {
        return (r >= 0 && r < rows && c >= 0 && c < cols);
    }

    public char getMove() {
        System.out.print("Where to? ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line.charAt(0);
    }

    public int applyMove(char move) {
        // first, find the player and alien locations
        // could also save these as instance variables, instead of finding them each time
        int pRow = -1, pCol = -1;
        int aRow = -1, aCol = -1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (worlds[r][c] == 'p') {
                    pRow = r;
                    pCol = c;
                }
                else if (worlds[r][c] == 'a') {
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
        if (!inBounds(nextRow, nextCol) || worlds[nextRow][nextCol] == 'a')
            return 2;

        // if stepping into a square with an emerald, decrease remaining by 1
        if (worlds[nextRow][nextCol] == 'e')
            emeraldsRemaining--;

        // now change the worlds
        worlds[pRow][pCol] = '.';
        worlds[nextRow][nextCol] = 'p';

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
            worlds[aRow][aCol] = '.';
        }
        else {
            worlds[aRow][aCol] = '.';

            if (worlds[nextRow][nextCol] == 'p') {
                // captured!
                worlds[nextRow][nextCol] = 'a';
                return 2;
            }
            else {
                worlds[nextRow][nextCol] = 'a';
            }
        }

        return 0;
    }

    public String toString() {
        String str = "Emeralds remaining: " + emeraldsRemaining + "\n";

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                str += worlds[r][c];
            }

            str += "\n";
        }

        return str;
    }
}

public class NIAM {
    public static void main(String[] args) {
        Worlds worlds = new Worlds(10, 10, 7);

        int outcome = 0;

        while (outcome == 0) {
            System.out.println(worlds);
            char move = worlds.getMove();
            outcome = worlds.applyMove(move);
        }

        System.out.println(worlds);
        System.out.println("");

        if (outcome == 1)
            System.out.println("Way to go!");
        else
            System.out.println(":(  better luck next time...");
    }
}

