import java.io.*;

class FileReader {
    private String inputFile;

    FileReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public void parse() {
        try {
            // open the input stream using the input file name, read the first line
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            String line = in.readLine();

            // read line-by-line, printing out every line
            // readLine() returns null when there are no more lines to read
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }

            // close the input stream
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class FileReaderMain {
    public static void main(String[] args) {
        FileReader fr = new FileReader("pepper.txt");
        fr.parse();
    }
}