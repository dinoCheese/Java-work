/**
 * Created by Emily on 13/03/14.
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;

class Point {

    private int X;                  // Field?
    private int Y;                  // Field?

    Point(int X, int Y) {             //Constructor

        this.X = X;
        this.Y = Y;
    }

    public int getX() {             //methods - getters
        return X;
    }

    public int getY() {             //methods - getters
        return Y;
    }

    public void setX(int newX) {             //methods - setters
        X = newX;
    }

    public void setY(int newY) {             //methods - setters
        Y = newY;
    }

    public void translate(int deltaX, int deltaY){
        deltaX = 100;
        deltaY = 50;
        X = X + deltaX;
        Y = Y + deltaY;
    }
}


class BackPanel extends JPanel {
    private LinkedList<Point> pointList;

    public BackPanel() {
        pointList = new LinkedList<Point>();
    }

    public synchronized void addPoint(Point p) {
        // convert coordinate systems
        Point cp = new Point(p.getX() + 300, (300 - p.getY()) - 10);
        for (Point lp : pointList) {
            if (lp.getX() == cp.getX() && lp.getY() == cp.getY())
                return;
        }
        pointList.add(cp);
    }

    public void paint(Graphics g) {
        // origin is at 300, 300
        synchronized (this) {
            for (Point p : pointList) {
                int topLeftX = p.getX() - 1;
                int topLeftY = p.getY() - 1;
                g.fillRect(topLeftX, topLeftY, 3, 3);
            }
        }
    }
}

class DrawingBoard {

    private JFrame frame;
    private BackPanel panel;

    public DrawingBoard() {
        frame = new JFrame("Drawing Board");
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new BackPanel();
        panel.setSize(600, 600);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public void drawPoint(Point p) {
        panel.addPoint(p);
        frame.repaint();
    }
}

public class DrawingTest {

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DrawingBoard db = new DrawingBoard();
        double x, y;
        Point p = new Point(0, 0);

        // Sine function test
        x = -100;
        while (x < 100) {
            y = -Math.sqrt( 10000 - Math.pow(x,2.0));
            p.setX((int) x);
            p.setY((int) y);
            p.translate(100, 50);
            db.drawPoint(p);
            sleep(0);
            y = Math.sqrt( 10000 - Math.pow(x,2.0));
            p.setX((int) x);
            p.setY((int) y);

            p.translate(100, 50);

            db.drawPoint(p);
            sleep(0);
            x += 0.05;
        }




    }
}
