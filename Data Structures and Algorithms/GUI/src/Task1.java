import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//connect coordinates to the cells

class TTDBoard implements world {
    private char[][] board;
    private char turn;

    public TTDBoard() {             //defines what can be used with TTDBoard
        turn = 'x';                 //i.e size and turn
        board = new char[3][3];

        for (int r = 0; r < 3; r++)     //places .'s which means that the cell is empty.
            for (int c = 0; c < 3; c++)
                board[r][c] = '.';
    }

    private void nextTurn() {           //who's turn is it
        if (turn == 'x')
            turn = 'o';
        else
            turn = 'x';
    }

    public void clickCell(int row, int col) {               //if empty and is clicked, notice
        if (board[row][col] == '.') {
            board[row][col] = turn;
            nextTurn();
        }
    }

    public char get(int row, int col) {             //gets the position of the click
        return board[row][col];
    }

    public char curPlayer() {                   //who made the move
        return turn;
    }
}

class MouseEventTrapper implements MouseListener {

    private TTDPanel panel;
    private TTDGameState state;

    public MouseEventTrapper(TTDPanel panel, TTDBoard ttboard) {
        this.panel = panel;
        this.state = ttboard;
    }
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked! " + e.getX() + " " + e.getY());
        //put x's and y's into 3 rows and 3 cols, split it up
        int col = e.getX() / 100;
        int row = e.getY() / 100;
        if (state.get(row,col) == '.'){
            panel.addDoge(state.curPlayer(), row, col);
            state.clickCell(row,col);
        }
    }
        //link a and b to rows and cols between 0 and 2 rows and cols
        //if that very cell is empty

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }

}

class TTDPanel extends JPanel {

    private JLabel[][] labels;

    TTDPanel() {
        // make a 3-by-3 grid layout
        GridLayout layout = new GridLayout(3, 3, 0, 0);
        setLayout(layout);

        // create a label for each cell
        labels = new JLabel[3][3];
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++) {
                // create the label
                labels[r][c] = new JLabel();

                // add the label. the layout chooses where it is placed
                // the gridlayout places them in reading order
                add(labels[r][c]);
            }
    }

    // modify the appropriate label by setting its image icon
    // player should be either 'x' or 'o'
    public void addDoge(char player, int row, int col) {
        labels[row][col].setIcon(new ImageIcon("doge-" + player + ".png"));
        repaint();
    }

}

class GUI {

    private JFrame frame;
    private TTDPanel panel;
    private TTDBoard ttboard;

    public GUI() { }

    public void init() {
        frame = new JFrame("Tic-Tac-Toe, MSC PRA2003 Edition");

        // Each cell is 100x100
        // Add 5 pixels for outer edge and 10 pixels for bar at top
        frame.setPreferredSize(new Dimension(305, 310));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel
        panel = new TTDPanel();
        panel.setSize(305, 310);

        // Add the panel to the frame
        frame.add(panel);

        // Create the Tic-Tac-Doge board
        ttboard = new TTDBoard();

        // add the mouse event trapper to the panel
        panel.addMouseListener(new MouseEventTrapper(panel, ttboard));

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.init();
    }
}