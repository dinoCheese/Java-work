/**
 * Created by Varsha on 30-04-2014.
 * Student ID: i6045552
 */


// Catch a bad file format
class BadFileFormatException extends Exception {
    private String issue;
    private int row, col;

    // Constructor
    BadFileFormatException(String issue, int row, int col) {
        this.issue = issue;
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "BadFileFormatException in " + issue + "; row " + row + ", column " + col;
    }
}
