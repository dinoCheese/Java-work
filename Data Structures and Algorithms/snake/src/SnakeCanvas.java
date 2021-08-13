/**
 * Created by Emily on 05/05/14.
 */

import java.awt.*;
import java.util.LinkedList;


public class SnakeCanvas extends Canvas implements Runnable {

    private final int boxHeight = 5;
    private final int boxWidth = 5;
    private final int gridHeight = 30;
    private final int gridWidth = 30;

    private LinkedList<Point> snake;
    private Point fruit;

    private Thread runThread;           //multitasking
    private Graphics globalGraphics;

    public void paint (Graphics g){
        snake = new LinkedList<Point>();
        fruit = new Point();

        globalGraphics = g.create();
        if (runThread == null){
            runThread = new Thread(this);
            runThread.start();      //runs thread on an external memory space
        }
    }

    int area1 = gridWidth * boxWidth;
    int area2 = gridHeight * boxHeight;

    public void DrawGrid(Graphics g) {
        //outer rectangle
        g.drawRect(0, 0, (area1), (area2));
        for (int x = boxHeight; x < (area1); x += boxWidth) {
            g.drawLine(x, 0, x, area2);
        }
        //horizontal lines
        for (int y = boxHeight; y < area2; y += boxHeight) {
            g.drawLine(0, y, area1, y);
        }
    }

    public void DrawFruit(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(fruit.x, fruit.y, boxWidth, boxHeight);
    }

    public void DrawSnake(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x, p.y, boxWidth, boxHeight);
        }
        g.setColor(Color.BLACK);
    }

    public void Draw(Graphics g) {
        DrawGrid(g);
        DrawSnake(g);
        DrawFruit(g);
    }

    public void Move() {
    }

    public void run() {
        //anything in the loop always runs
        while (true) {
            Move();
            Draw(globalGraphics);

            try{
                Thread.currentThread();
                Thread.sleep(100);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
