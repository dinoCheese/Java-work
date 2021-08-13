/**
 * Created by Varsha on 19-04-2014.
 * Student ID: i6045552
 */

public class Main {
    public static void main(String[] args) {
        // Call the test file for BadFileFormatException
        // Tests.testLoadFile();

        /*
        // Initiation of a game
        World game = new World(10, 9, 15, 5, 5);

        System.out.println("Welcome to Emerald Mine, PRA2003 edition!");
        System.out.println("Let's collect some Emeralds!");

        // Initiate move
        int afterMove = 0;

        // Loop for the game
        while (afterMove == 0) {
            System.out.println(game.toString());
            afterMove = game.applyMove(new Player().getMove());
        }

        if (afterMove == 1)
            System.out.println("Amazing! You have won!");
        else if (afterMove == 2)
            System.out.println("Oh no! You died! Game Over!");
        */
        // Create the World
        try {
            World world = new World("example2.txt");
            GUI gui = new GUI();
            gui.init(world);
            Thread sim = new Simulator(world, gui);
            sim.run(); // starts a new concurrent thread
        } catch (BadFileFormatException e) {
            System.out.println(e);
        }
    }
}
