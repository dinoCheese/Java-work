/**
Emily Sellman
 i6045016
 */

import java.util.Random;
import java.util.Scanner;

abstract class WorldObject {

    // returns true if this object is edible, false otherwise
    public abstract boolean isEdible();

    // returns true if the object can fall (due to gravity), false otherwise
    public abstract boolean hasMass();

    //returns true if the object gets destroyed if a rock falls on it, false otherwise
    public abstract boolean isVulnerable();

    // can this object move?
    public boolean canMove() {
        return false; // immobile by default, must be overridden
    }

    // get the move
    public char getMove() {
        return '?';
    }

    // is this object the player?
    public boolean isPlayer() {
        return false; // by default, no.. must be overridden
    }

    // is this object a monster?
    public boolean isMonster() {
        return (canMove() && !isPlayer());
    }

    // how much is this object worth, in emeralds?
    public int getEmeraldValue() {
        return 0;  // 0 by default, must be overridden
    }

    // is this space or dirt?
    public boolean isOpen() {
        return false;
    }

    // create one from its character representation; returns null if invalid character is passed
    public static WorldObject createFromChar(char c) {
        // switch acts similar to an if chain, but with some subtle differences
        // see sec 3.6 in the book, before you attempt to use it yourself!
        switch(c) {
            case '.': return new Space();
            case '#': return new Dirt();
            case 'e': return new Emerald();
            case 'd': return new Diamond();
            case 'r': return new Rock();
            case 'a': return new Alien();
            case 'p': return new Player();
        }

        return null;
    }

}

abstract class Moveable extends WorldObject {
    public boolean canMove() { return true; }
}

abstract class EdibleObject extends WorldObject {
    public boolean isEdible() { return true; }
}

class Space extends WorldObject {
    public boolean isOpen() { return true; }
    public boolean isEdible() { return true; }
    public boolean hasMass() { return false; }
    public boolean isVulnerable() { return true; }
    public String toString() { return "."; }
}

class Rock extends WorldObject {
    public boolean isEdible() { return false; }
    public boolean hasMass() { return true; }
    public boolean isVulnerable() { return false; }
    public String toString() { return "r"; }
}

class Dirt extends EdibleObject {
    public boolean isOpen() { return true; }
    public boolean hasMass() { return false; }
    public boolean isVulnerable() { return false; }
    public int getEmeraldValue() { return 0; }
    public String toString() { return "#"; }
}

class Emerald extends EdibleObject {
    public boolean hasMass() { return true; }
    public boolean isVulnerable() { return false; }
    public int getEmeraldValue() { return 1; }
    public String toString() { return "e"; }
}

class Diamond extends EdibleObject {
    public boolean hasMass() { return true; }
    public boolean isVulnerable() { return true; }
    public int getEmeraldValue() { return 3; }
    public String toString() { return "d"; }
}

class Alien extends Moveable {
    // best to create a single rand. number generator at the start
    private static Random rng = new Random();

    public boolean isEdible() { return false; }
    public boolean hasMass() { return false; }
    public boolean isVulnerable() { return true; }

    public char getMove() {
        int roll = rng.nextInt(4);

        if (roll == 0) return 'u';
        else if (roll == 1) return 'r';
        else if (roll == 2) return 'd';
        else if (roll == 3) return 'l';
        else return '?';
    }

    public String toString() { return "a"; }
}

class Player extends Moveable {
    // best to create a single rand. number generator at the start
    private static Random rng = new Random();

    public boolean isPlayer() { return true; }
    public boolean isEdible() { return false; }
    public boolean hasMass() { return false; }
    public boolean isVulnerable() { return true; }

    public char getMove() {
        System.out.print("Where to? ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line.charAt(0);
    }

    public String toString() { return "p"; }
}

