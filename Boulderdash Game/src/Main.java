/**
 Emily Sellman
 i6045016
 */

import java.util.*;

class Main {

    public static void mainLoop() {

        World world = null;

        // let's use the example world
        //world = new World(10, 10, 7);

        try {
            world = new World(10, 10, 7);
            //world = new World("example.txt");
        }
        catch(Exception e) {
            e.printStackTrace();
            return;
        }

        int outcome = 0;

        while (outcome == 0) {
            System.out.println(world);
            char move = world.getMove();
            outcome = world.applyMove(move);

        }

        System.out.println(world);
        System.out.println("");

        if (outcome == 1)
            System.out.println("Way to go!");
        else
            System.out.println(":(  better luck next time...");
    }

    public static void main(String[] args) {
        System.out.println("Testing file I/O constructor..");
        Tests.testLoadFiles();

        System.out.println("Starting main game...");

        mainLoop();
    }
}

