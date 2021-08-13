/**
 * Created by Varsha on 19-04-2014.
 * Student ID: i6045552
 */

import java.util.*;

public abstract class WorldObject {

    public abstract boolean isEdible(); // returns true if this object is edible

    public abstract boolean hasMass(); // returns true if the object can fall due to gravity

    public abstract boolean isVulnerable(); // returns true if the object gets destroyed if a rock falls on it

    public abstract String toString();

    public boolean canMove() {  // This is a \default implementation" inherited by all subclasses; it must be overridden to deviate from the default.
        return false;
    }   // will be overridden as true by movable classes

    public boolean isPlayer() {
        return false;
    }   // Overridden by Player

    public char getMove() { // returns the move made by a monster, defaults to '?'.
        return '?';
    }   // Overridden by Player & Alien classes

    public int getEmeraldValue() { // returns the number of emeralds this object is worth when eaten, defaults to 0.
        return 0;
    }   // Overridden by Emerald and Diamond classes

    public boolean isMonster() {
        return false;
    }

    public void changeDirection() {
    }

    public int direction() { return 0; }

    public boolean hasPoints() {
        return false;
    }
}

// Subclass Rock(WorldObject)
class Rock extends WorldObject {
    public boolean isEdible() {
        return false;
    }

    public boolean hasMass() {
        return true;
    }

    public boolean isVulnerable() {
        return false;
    }

    public String toString() {
        return "r";
    }
}

// Abstract Subclass for Edible Objects
abstract class EdibleObject extends WorldObject { // Sole purpose of this abstract class is to override isEdible(), which always returns true.

    public boolean isEdible() {
        return true;
    }
}

// Subclass Dirt (Edible)
class Dirt extends EdibleObject {
    public boolean hasMass() {
        return false;
    }

    public boolean isVulnerable() {
        return false;
    }

    public String toString() {
        return "#";
    }
}

// Subclass Space (Edible)
class Space extends EdibleObject {
    public boolean hasMass() {
        return false;
    }

    public boolean isVulnerable() {
        return true;
    }

    public String toString() {
        return ".";
    }
}

// Subclass Emerald (Edible)
class Emerald extends EdibleObject {
    public boolean hasMass() {
        return true;
    }

    public boolean isVulnerable() {
        return false;
    }

    public int getEmeraldValue() { // returns the number of emeralds this object is worth when eaten, defaults to 0.
        return 1;
    }

    public String toString() {
        return "e";
    }

    public boolean hasPoints() {
        return true;
    }
}

// Subclass Diamond (Edible)
class Diamond extends EdibleObject {
    public boolean hasMass() {
        return true;
    }

    public boolean isVulnerable() {
        return true;
    }

    public int getEmeraldValue() { // returns the number of emeralds this object is worth when eaten, defaults to 0.
        return 3;
    }

    public String toString() {
        return "d";
    }

    public boolean hasPoints() {
        return true;
    }
}

// Abstract Subclass for Movable Objects; to override canMove()
abstract class Movable extends WorldObject {
    public boolean canMove() {
        return true;
    }

    public boolean isEdible() {
        return false;
    }

    public boolean hasMass() {
        return false;
    }

    public boolean isVulnerable() {
        return true;
    }
}

// Subclass Player (Movable)
class Player extends Movable {

    public boolean isPlayer() {
        return true;
    }

    public char getMove() {
        Scanner scnanner = new Scanner(System.in);
        return scnanner.next().charAt(0);
    }

    public String toString() {
        return "p";
    }
}

class Monster extends Movable {

    public String toString() {
        return "?";
    }

    public boolean isMonster() {
        return true;
    }

    public void changeDirection() {
        getMove();
    }
}

// Subclass Alien (Monster)
class Alien extends Monster {
    public char getMove() {
        char[] alienmoves = new char[4];
        alienmoves[0] = 'u'; // up
        alienmoves[1] = 'd'; // down
        alienmoves[2] = 'l'; // left
        alienmoves[3] = 'r'; // right

        Random rand = new Random();
        return alienmoves[rand.nextInt(alienmoves.length)];
    }

    public String toString() {
        return "a";
    }
}

// Subclass Bug (Monster)
class Bug extends Monster {
    // 1 = up, 2 = right, 3 = down, 4 = left
    private int direction;

    Bug() {
        direction = 1;
    }

    public char getMove() {
        if (direction == 1) {
            direction = 2;
            return 'r';
        }
        else if (direction == 2) {
            direction = 3;
            return 'd';
        }
        else if (direction == 3) {
            direction = 4;
            return 'l';
        }
        else {
            direction = 4;
            return 'u';
        }
    }

    public int direction() {
        return direction;
    }

    public String toString() {
        return "b";
    }
}

// Subclass Spaceship (Monster)
class Spaceship extends Monster {
    // 1 = up, 2 = right, 3 = down, 4 = left
    private int direction;

    Spaceship() {
        direction = 1;
    }

    public char getMove() {
        if (direction == 1) {
            direction = 4;
            return 'l';
        }
        else if (direction == 2) {
            direction = 1;
            return 'u';
        }
        else if (direction == 3) {
            direction = 2;
            return 'r';
        }
        else {
            direction = 4;
            return 'd';
        }
    }

    public int direction() {
        return direction;
    }

    public String toString() {
        return "s";
    }
}


