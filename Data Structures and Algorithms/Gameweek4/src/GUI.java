/**
 * Created by Emily on 13/05/14.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.*;

class KeyEventTrapper implements KeyListener, MouseListener {

    private TTDPanel EmeraldMinePannel;
    //reference the world created in the file previously

    public KeyEventTrapper(TTDPanel EmeraldMinePannel) {
        this.EmeraldMinePannel = EmeraldMinePannel;
    }

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        //world.applyMove() method;
    }
    public void keyReleased(KeyEvent e) {}


    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

class TTDPanel extends JPanel {

    private JLabel[][] labels;

    TTDPanel() {

    }

    public void addDoge(char player, int row, int col) {
        labels[row][col].setIcon(new ImageIcon("Alien" + ".png"));
        //places images
        //Alien
        //Diamond
        repaint();
    }

}

class GUI {

    private JFrame frame;
    private TTDPanel EmeraldMinePanel;

    public GUI() {
    }

    public void init() {
        frame = new JFrame("Boulderdash");

        // Each cell is 100x100
        // Add 5 pixels for outer edge and 10 pixels for bar at top
        frame.setPreferredSize(new Dimension(100, 100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel
        EmeraldMinePanel = new TTDPanel();
        EmeraldMinePanel.setSize(32, 32);       //*rows and *cols

        // Add the panel to the frame
        frame.add(EmeraldMinePanel);

        // add the mouse event trapper to the panel
        EmeraldMinePanel.addMouseListener(new KeyEventTrapper(EmeraldMinePanel));

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.init();
    }
}


/*
In TTD, the width of each cell 100 pixels and the height was 100 pixels. In Emerald Mine, reduce each dimension
to 32 pixels. Therefore, a world with 20 rows and 20 columns should be 640  640.

Remove the TTD game state and replace it by a reference to the world that is initially created.

Rename the panel to EmeraldMinePanel, and modify it so that the appropriate number of rows and columns are
created that match those in the world.

Replace the image icons by the approriate ones for Emerald Mine (seeEleUM). This may require adding some getters to World.

Instead of using a MouseListener and MouseEventTrapper, create a similar KeyEventTrapper class that implements
KeyListener. The only method that is not empty is keyPressed(KeyEvent e). As in TTD, this method must:

1. Handle the keyboard event,
2. Invoke the world's applyMove() method to change the world accordingly, and
3. Set the icons and refresg the GUI as necessary

The to check which key was e.getKeyCode(): it will return an integer constant representing the key pressed. The
ones of interest are: KeyEvent.

 */