import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


class CellGrid extends JPanel {
    private int rows, cols, genCount;
    private Cell[][] grid;
    protected String inputName;

    CellGrid(String inputName) throws IOException, BadFileFormatException {
        this.inputName = inputName;

        // opens the input stream using the input file name
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputName)));

        // reads the first line which contains the number of rows
        String line1 = in.readLine();
        rows = Integer.parseInt(line1);

        // reads the second line which contains the number of columns
        String line2 = in.readLine();
        cols = Integer.parseInt(line2);

        grid = new Cell[rows][cols];

        String line = in.readLine();
        int numRows = 0;

        while (line != null) {
            if (line.length() != cols)
                throw new BadFileFormatException("Wrong number of columns", numRows, -1);

            if (numRows >= rows)
                throw new BadFileFormatException("Too many rows", numRows, -1);

            for (int i = 0; i < cols; i++) {
                char c = line.charAt(i);
                grid[numRows][i] = Cell.createFromChar(c);
            }
            numRows++;
            line = in.readLine();
        }
        in.close();
    }

    public int getGenCount() {return genCount;}

    public boolean inBounds(int r, int c) { return (r >= 0 && r < rows && c >= 0 && c < cols); }


    public void updateGrid() {
        Cell[][] newGrid = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int amountTouching = 0;

                int upRow, downRow, leftCol, rightCol;
                upRow = r - 1;
                downRow = r + 1;
                leftCol = c - 1;
                rightCol = c + 1;

		    /* The next thing we do is check how many live cells the cell is touching.
    		* That means we have to check the cell to the left, right, top, bottom, top-left,
			* top-right, bottom-left, and bottom-right of the cell.*/

                //To get the cell to the right of the cell, we add 1 to the index.
                //But first, we have to make sure that that index is less than the size of the
                //ArrayList (so we don't get an error saying that the index is out of range.)
                if (inBounds(upRow, c) && grid[upRow][c] instanceof liveCell)
                    amountTouching++;
                //To get the cell to the left of the cell, we subtract 1 to the index.
                if (inBounds(downRow, c) && grid[downRow][c] instanceof liveCell)
                    amountTouching++;
                //To get the cell below the cell, we add the number of columns to the index.
                if (inBounds(r, rightCol) && grid[r][rightCol] instanceof liveCell)
                    amountTouching++;
                //To get the cell above the cell, we subtract the number of columns to the index.
                if (inBounds(r, leftCol) && grid[r][leftCol] instanceof liveCell)
                    amountTouching++;
                //To get the cell to the top-right, we add the number of columns and then add 1.
                if (inBounds(downRow, rightCol) && grid[downRow][rightCol] instanceof liveCell)
                    amountTouching++;
                //To get the cell to the top-left, we add the number of columns and then subtract 1.
                if (inBounds(upRow, leftCol) && grid[upRow][leftCol] instanceof liveCell)
                    amountTouching++;
                //To get the cell to the bottom-right, we subtract the number of columns and add 1.
                if (inBounds(downRow, leftCol) && grid[downRow][leftCol] instanceof liveCell)
                    amountTouching++;
                //To get the cell to the bottom-left, we subtract the number of columns and subtract 1.
                if (inBounds(upRow, rightCol) && grid[upRow][rightCol] instanceof liveCell)
                    amountTouching++;

                //If the cell we are on is alive
                if (grid[r][c] instanceof liveCell) {
                    if (amountTouching < 2) //If it is touching less than 2 live cells
                        newGrid[r][c] = new deadCell(); //Then in the new generation it will be dead.
                    else if (amountTouching == 2 || amountTouching == 3) //If it is touching 2 or 3 live cells
                        newGrid[r][c] = new liveCell(); //Then in the new generation it will be alive.
                    else if (amountTouching > 3)
                        newGrid[r][c] = new deadCell(); //Then in the new generation it will be dead.
                } else if (amountTouching == 3) //If the cell is dead and it is touching exactly 3 live cells
                    newGrid[r][c] = new liveCell(); //Then in the new generation it will be alive.
                else
                    newGrid[r][c] = new deadCell(); //If the cell is dead and is not touching exactly 3 live cells,
                //Then in the new generation it will be dead.

            }
        }
        grid = newGrid;
    }

    //dimensions of the grid
    public Dimension getPreferredSize() {
        return new Dimension(rows * 4, cols * 4);
    }

    //color coding


    int n = 0;
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);
        Color gColor = g.getColor();
        g.drawString("Generation: " + genCount++, 0, 10);
        Random rng = new Random();
        int aa = rng.nextInt(255);
        int bb = rng.nextInt(255);
        int cc = rng.nextInt(255);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (grid[r][c] instanceof liveCell) {
                    g.setColor(new Color(aa, bb, cc));
                    g.fillOval(c * 4, r * 4, 4, 4);
                }
            }
        }
       /* if (n < 250) {
            n = n + 5;

        }
        else if (n >= 250) {
            while (n <= 250 && n >= 0) {
                n = n - 5;
            }
        }
        g.setColor(gColor);*/
    }

    public static void main(String[] args) throws IOException, LineUnavailableException, BadFileFormatException {
        int n = 0;
        while (n <= 2) {
            JFrame frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            String string = "example" + n + ".txt";

            final CellGrid GameOfLife = new CellGrid(string);



            frame.getContentPane().add(GameOfLife);

            frame.setLocationByPlatform(true);
            frame.pack();
            frame.setVisible(true);
            int generation = GameOfLife.getGenCount();



            Timer time = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameOfLife.updateGrid();
                    GameOfLife.repaint();
                }
            });
            //time.start();


            ByteArrayOutputStream byteArrayOutputStream;
            TargetDataLine targetDataLine;
            int count;
            // array of bytes
            byte tempBuffer[] = new byte[8000];
            int noise, speed = 1000;
            // converting bytes into shorts
            short convert[] = new short[tempBuffer.length];

            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                while(generation <= 50) {
                    // describing the audio format
                    AudioFormat audioFormat = new AudioFormat(8000.0F, 16, 1, true, true);
                    // TargetDataLine subinterface of DataLine to capture the audio
                    // making an DataLine.Info object to specify info about the line
                    DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
                    // TargetDataLine - input stream
                    targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                    // opening the audio capturing
                    targetDataLine.open(audioFormat);
                    // initializing - starting the capturing
                    targetDataLine.start();
                    // reads from the buffer
                    count = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
                    // writes to the data line
                    byteArrayOutputStream.write(tempBuffer, 0, count);
                    try {
                        noise = 0;
                        for (int i = 0; i < tempBuffer.length; i++) {
                            convert[i] = tempBuffer[i];
                            if (convert[i] == 0) {
                                noise++;            // counts zeros
                            }
                        }

                        System.out.println(noise);
                        if (noise > 2200) {
                            speed = 1000;
                        } else if (noise > 2050 && noise <= 2200) {
                            speed = 950;
                        } else if (noise > 1900 && noise <= 2050) {
                            speed = 900;
                        } else if (noise > 1750 && noise <= 1900) {
                            speed = 850;
                        } else if (noise > 1600 && noise <= 1750) {
                            speed = 800;
                        } else if (noise > 1450 && noise <= 1600) {
                            speed = 750;
                        } else if (noise > 1300 && noise <= 1450) {
                            speed = 700;
                        } else if (noise > 1150 && noise <= 1300) {
                            speed = 650;
                        } else if (noise > 1000 && noise <= 1150) {
                            speed = 600;
                        } else if (noise > 850 && noise <= 1000) {
                            speed = 550;
                        } else if (noise > 700 && noise <= 850) {
                            speed = 500;
                        } else if (noise > 550 && noise <= 700) {
                            speed = 450;
                        } else if (noise > 400 && noise <= 550) {
                            speed = 400;
                        } else if (noise > 250 && noise <= 400) {
                            speed = 350;
                        } else if (noise > 100 && noise <= 250) {
                            speed = 300;
                        } else if (noise > 75 && noise <= 100) {
                            speed = 250;
                        } else if (noise > 50 && noise <= 75) {
                            speed = 200;
                        } else if (noise > 25 && noise <= 50) {
                            speed = 150;
                        } else if (noise >= 0 && noise <= 25) {
                            speed = 100;
                        }

                        time.start();
                        time.setDelay(speed);

                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    Thread.sleep(0);
                    targetDataLine.close();
                    generation++;
                }
                n++;
                if (n > 2)
                    n = 0;

            } catch (Exception e) {
                System.out.println(e);
            }
        }


    }
}