import java.io.*;

class FileReader {
    private String inputFile;

    FileReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public void parse() {
        try {
            // opens the input stream using the input file name, read the first line
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            String line = in.readLine();
            PrintStream out = new PrintStream("filtered-pepper.txt");

            // read line-by-line, printing out every line
            // readLine() returns null when there are no more lines to read
            while (line != null) {
                String k = "heat";
                if (line.indexOf(k)> 0) {
                    out.println(line);
                }
                line = in.readLine();
            }
            in.close();
            out.close();
        } catch (Exception e) {
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


//a file is created that holds the information that you selected