/**
 * Created by Emily on 05/02/14.
 */
public class Wellington {
    public static void newLine() {
        System.out.println("");
    }
    public static void threeLine() {
        newLine(); newLine(); newLine();
    }
    public static void main(String[] args) {
        System.out.println("First line.");
        threeLine();
        System.out.println("Second line.");
    }
}
