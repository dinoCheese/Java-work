/**
 Emily Sellman
 i6045016
 */

import java.util.*;

class Main {
    public static void main(String[] args) {
        World world = new World(10, 10, 7);

        int outcome = 0;

        while (outcome == 0) {

            System.out.println(world);
            char move = world.getMove();
            outcome = world.applyMove(move);
            world.applyGravity(0,0);

        }

        System.out.println(world);
        System.out.println("");

        if (outcome == 1)
            System.out.println("Way to go!");
        else
            System.out.println(":(  better luck next time...");
    }
}

