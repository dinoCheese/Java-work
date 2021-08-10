abstract class Cell {

    public abstract boolean isAlive();
    public static Cell createFromChar(char c) {
        switch(c) {
            case '.': return new liveCell();
            case 'o': return new deadCell();
        }
        return null;
    }

}

class liveCell extends Cell {
    public boolean isAlive() { return true; }
    public String toString() { return ".";}
}

class deadCell extends Cell {
    public boolean isAlive() { return false; }
    public String toString() { return "o";}
}