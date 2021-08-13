

class BadFileFormatException extends Exception {
    private String problem;
    private int row;
    private int col;

    BadFileFormatException(String problem, int row, int col) {
        this.problem = problem;
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "BadFileFormatException: " + problem + "/n" +  "Row: " + row + "/n" +  "Col: " + col;
    }
}