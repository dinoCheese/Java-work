import java.io.IOException;

/**
 * PRA2003, Emerald Mine solution.
 * Author: Marc Lanctot.
 *
 * Note: if you use this code, put your name and ID in this header!
 */

public class Tests {
    public static void testLoadFile(String filename) {
        try {
            System.out.println("Trying file: " + filename);
            World world = new World(filename);
            System.out.println("Successfully loaded world: ");
            System.out.println(world);
        }
        catch (BadFileFormatException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testLoadFiles() {
        Tests.testLoadFile("example.txt"); // should work
        Tests.testLoadFile("bad-file1.txt"); // invalid character 'x'
        Tests.testLoadFile("bad-file2.txt"); // too many rows
        Tests.testLoadFile("bad-file3.txt"); // too few rows
        Tests.testLoadFile("bad-file4.txt"); // too few columns
        Tests.testLoadFile("bad-file5.txt"); // too many columns
        Tests.testLoadFile("bad-file6.txt"); // not enough emeralds
    }
}
