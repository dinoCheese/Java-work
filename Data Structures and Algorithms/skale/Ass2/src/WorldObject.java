/**
 * Created by Emily on 17/04/14.
 * i6045016
 */

import java.util.*;

abstract class WorldObject {

    public abstract boolean isEdible();

    public abstract boolean hasMass();

    public abstract boolean isVulnerable();

    public boolean canMove() {
        return false;
    }

    public boolean isPlayer(){
        return false;
    }

    public char getMove() {
        return '?';
    }

    public int getEmeraldValue() {
        return 0;
    }
}

abstract class Movable extends WorldObject {

    public boolean canMove() {
        return true;
    }
}

abstract class EdibleObject extends WorldObject {

    public boolean isEdible() {
        return true;
    }
}

class Dirt extends EdibleObject {

    public char Dirt() {
        return '#';
    }

    public boolean isEdible() {
        return true;
    }

    public boolean hasMass() {
        return false;
    }

    public boolean isVulnerable() {
        return false;
    }

    public String toString() {
        return super.toString();
    }
}

class Emerald extends EdibleObject {

    public boolean hasMass() {
        return true;
    }
    public boolean isVulnerable() {
        return false;
    }
    public int getEmeraldValue(){
        int EmeraldValue = 1;
        return EmeraldValue;
    }
    public char Emerald() {
        return 'e';
    }
    public String toString() {
        return super.toString();
    }
}

class Diamond extends EdibleObject {

    public boolean hasMass() {
        return true;
    }

    public boolean isVulnerable() {
        return true;
    }

    public int getEmeraldValue(){
        int EmeraldValue = 3;
        return EmeraldValue;
    }

    public char Diamond() {
        return 'd';
    }
    public String toString() {
        return super.toString();
    }
}

class Alien extends Movable {

    public char Alien() {
        return 'a';
    }

    public char getMove() {

        // Alien make a single move randomly
        //produce a random number between 1 and 4
        //if 1 row++, if 2 row --, if 3 col++, if 4 row--
        return 0;
    }

    public boolean hasMass() {
        return true;
    }

    public boolean isVulnerable() {
        return false;
    }

    public boolean isEdible() {
        return false;
    }
    public String toString() {
        return super.toString();
    }
}           //missing the movingness of the alien
            //getMove() returns a random direction (same as eariler/before

class Player extends WorldObject {

    public char Player() {
        return 'p';
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

    public boolean isPlayer() {
        return true;
    }

    public char getMove() {
        Scanner scanner = new Scanner(System.in);
        char move;
        String Line = scanner.nextLine();
        return Line.charAt(0);
    }
    public String toString() {
        return super.toString();
    }
}

class Space extends WorldObject{

    public char space() {
        return '.';
    }
    public boolean isEdible() {
        return true;
    }

    public boolean hasMass() {
        return false;
    }

    public boolean isVulnerable() {
        return true;
    }
    public String toString() {
        return super.toString();
    }
}

class Rock extends WorldObject {

    public char rock() {
        return 'r';
    }

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
        return super.toString();
    }
}

