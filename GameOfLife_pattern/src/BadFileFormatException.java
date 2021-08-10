/**
 * Created by Nina Troppmair on 13-6-14.
 */
public class BadFileFormatException extends Exception {
    private String errorMsg;
    private int row, col;

    public BadFileFormatException(String errorMsg, int row, int col) {
        this.errorMsg = errorMsg;
        this.row = row;
        this.col = col;
    }

    public String toString() {
        String str = "Error: " + errorMsg;

        if (row >= 0)
            str += (", row: " + row);

        if (col >= 0)
            str += (", col:" + col);

        return str;
    }
}

