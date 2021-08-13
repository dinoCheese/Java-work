/**
 * Created by Emily on 21/04/14.
 * i6045016
 */


class Main {
    public static void main(String[] args) {
        World Game = new World(10, 10, 9);


        int outcome = 0;

        while (outcome == 0) {
            System.out.println(Game);
            char move = Game.getMove();
            outcome = Game.applyMove(move);
        }

        System.out.println(Game);
        System.out.println("");

        if (outcome == 1)
            System.out.println("Way to go!");
        else
            System.out.println(":(  better luck next time...");
    }
}