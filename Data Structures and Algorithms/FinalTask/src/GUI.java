/**
 * Created by Varsha on 13-05-2014.
 * Student ID: i6045552
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

interface WorldState {
    // Interface methods

    public int getRows();

    public int getCols();

    public int getEmldleft();

    public WorldObject get(int r, int c);

    public void set(int r, int c, WorldObject element);

    public String toString();

    public int[] giveMove(char move, int fromRow, int fromCol);

    public int applyMove(char move);
}

class KeyEventTrapper implements KeyListener {

    private EmeraldMinePanel panel;
    private World world;

    KeyEventTrapper(EmeraldMinePanel panel, World state) {
        this.panel = panel;
        this.world = state;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        // See what key was pressed
        int moveKey = e.getKeyCode();

        // Initialize move by player
        char move = ' ';

        if (moveKey == KeyEvent.VK_UP)
            move = 'u';
        else if (moveKey == KeyEvent.VK_DOWN)
            move = 'd';
        else if (moveKey == KeyEvent.VK_LEFT)
            move = 'l';
        else if (moveKey == KeyEvent.VK_RIGHT)
            move = 'r';

        // Apply Move
        int playState = world.applyMove(move);
        if (playState == 0)
            System.out.println("Emeralds left: " + world.getEmldleft());
        else if (playState == 1) {
            System.out.println("CONGRATULATIONS! You have won Emerald Mine, Level 0!");
            System.exit(-1);
        } else if (playState == 2) {
            System.out.println("Oh no! You died!");
            System.exit(-1);
        }

        // Refresh panel after move
        panel.drawWorld(world);
    }

    public void keyReleased(KeyEvent e) {
    }
}

class EmeraldMinePanel extends JPanel {

    private JLabel[][] labels;

    EmeraldMinePanel(World world) {
        int row = world.getRows();
        int col = world.getCols();

        // make a row-by-col grid layout
        GridLayout layout = new GridLayout(row, col, 0, 0);
        setLayout(layout);

        // create a label for each cell
        labels = new JLabel[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // create the label
                labels[r][c] = new JLabel();

                // add the label. the layout chooses where it is placed
                // the gridlayout places them in reading order
                add(labels[r][c]);
            }
        }
    }

    // modify the appropriate label by setting its image icon
    public synchronized void drawWorld(World world) {
        for (int r = 0; r < world.getRows(); r++) {
            for (int c = 0; c < world.getCols(); c++) {
                if (world.get(r, c) instanceof Dirt) {
                    labels[r][c].setIcon(new ImageIcon("dirt.png"));
                    repaint();
                } else if (world.get(r, c) instanceof Space) {
                    labels[r][c].setIcon(new ImageIcon("space.png"));
                    repaint();
                } else if (world.get(r, c) instanceof Emerald) {
                    labels[r][c].setIcon(new ImageIcon("emerald.png"));
                    repaint();
                } else if (world.get(r, c) instanceof Diamond) {
                    labels[r][c].setIcon(new ImageIcon("diamond.png"));
                    repaint();
                } else if (world.get(r, c) instanceof Rock) {
                    labels[r][c].setIcon(new ImageIcon("rock.png"));
                    repaint();
                } else if (world.get(r, c) instanceof Player) {
                    labels[r][c].setIcon(new ImageIcon("player.png"));
                    repaint();
                } else if (world.get(r, c) instanceof Alien) {
                    labels[r][c].setIcon(new ImageIcon("alien.png"));
                    repaint();
                } else if (world.get(r, c) instanceof Bug) {
                    new Bug();
                    // 1 = up, 2 = right, 3 = down, 4 = left
                    if (world.get(r, c).direction() == 1) {
                        labels[r][c].setIcon(new ImageIcon("bugU.png"));
                        repaint();
                    }
                    if (world.get(r, c).direction() == 2) {
                        labels[r][c].setIcon(new ImageIcon("bugR.png"));
                        repaint();
                    }
                    if (world.get(r, c).direction() == 3) {
                        labels[r][c].setIcon(new ImageIcon("bugD.png"));
                        repaint();
                    }
                    if (world.get(r, c).direction() == 4) {
                        labels[r][c].setIcon(new ImageIcon("bugL.png"));
                        repaint();
                    }
                } else if (world.get(r, c) instanceof Spaceship) {
                    new Spaceship();
                    // 1 = up, 2 = right, 3 = down, 4 = left
                    if (world.get(r, c).direction() == 1) {
                        labels[r][c].setIcon(new ImageIcon("spaceshipU.png"));
                    }
                    if (world.get(r, c).direction() == 2) {
                        labels[r][c].setIcon(new ImageIcon("spaceshipR.png"));
                    }
                    if (world.get(r, c).direction() == 3) {
                        labels[r][c].setIcon(new ImageIcon("spaceshipD.png"));
                    }
                    if (world.get(r, c).direction() == 4) {
                        labels[r][c].setIcon(new ImageIcon("spaceshipL.png"));
                    }
                }
            }
        }
    }
}

class GUI {

    private JFrame frame;
    private EmeraldMinePanel panel;
    private World world;

    public GUI() {
    }

    public void redraw(World world) {
        panel.drawWorld(world);
    }

    public void init(World world) {
        frame = new JFrame("Emerald Mine, MSC PRA2003 Edition");


        int panelRow = world.getRows() * 32 + 5;
        int panelCol = world.getCols() * 32 + 10;

        // Each cell is 32x32
        // Add 5 pixels for outer edge and 10 pixels for bar at top
        frame.setPreferredSize(new Dimension(panelRow, panelCol));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel
        panel = new EmeraldMinePanel(world);
        panel.setSize(panelRow, panelCol);

        // Add the panel to the frame
        frame.add(panel);

        // Add components
        redraw(world);

        // add the key event trapper
        frame.addKeyListener(new KeyEventTrapper(panel, world));

        frame.pack();
        frame.setVisible(true);
    }
}