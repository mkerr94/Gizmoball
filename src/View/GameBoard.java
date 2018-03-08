package View;

import Controller.FlipperListener;
import Model.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class GameBoard extends JPanel implements Observer, ActionListener {
    private static final int TICK_TIME = 5;
    private char mode;
    private Model model; // model
    private FlipperListener controller;
    private List<Flipper> flippers;
    private List<IGizmo> gizmos;
    private int L = 25;
    private int width;
    private int height;

    private Color green = new Color(0, 255, 0);
    private Color red = new Color(255, 0, 0);
    private Color black = new Color(0, 0, 255);
    private Color blue = new Color(0, 0, 255);
    private Color yellow = new Color(255,255,0);


    GameBoard(int w, int h, Model model, char mode){
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.model = model;
        this.mode = mode;
        width = w;
        height = h;
        flippers = new ArrayList<>();
        gizmos = model.getGizmos();
        model.addObserver(this);
        setBorder(new LineBorder(Color.RED));
        if (mode == 'r') {
            Timer tickTimer = new Timer(TICK_TIME, this);
            tickTimer.start();
        }
        printGizmoList();
        requestFocus();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
            Graphics2D g2 = (Graphics2D) g;

            int lines = (2*L) / 2;
        if (mode == 'b') {
            for (int i = 1; i < lines - 4; i++) {
                int x = i * lines;
                g2.drawLine(x, 0, x, height);
                g2.drawLine(0, x, width, x);
            }
        }


            for(IGizmo b : gizmos) {
                if (b != null && b.getClass().equals(Square.class)) {
                    g2.setColor(red);
                    int x= (b.getX() * L);
                    int y= (b.getY() * L);
                    g2.fillRect(x, y, L, L);
                }

                if (b != null && b.getClass().equals(Circle.class)) {
                    g2.setColor(green);
                    int x= (b.getX() * L);
                    int y= (b.getY() * L);
                    g2.fillOval(x, y, L, L);
                }

                if (b != null && b.getClass().equals(Triangle.class)) {
                    g2.setColor(blue);
                    int x= (b.getX() * L);
                    int y= (b.getY() * L);
                    if(b.getRotation() == 0){
                        int x2Points[] = {x+ L, x,x};
                        int y2Points[] = {y,y+ L,y};
                        g2.fillPolygon(x2Points,y2Points,3);
                    }
                    else if(b.getRotation() == 1){
                        int x2Points1[] = {x+ L, x,x+ L};
                        int y2Points1[] = {y,y,y+ L};
                        g2.fillPolygon(x2Points1,y2Points1,3);
                    }
                }
                if (b != null && b.getClass().equals(Ball.class)) {
                    g2.setColor(red);
                    int x = (b.getX());
                    int y = (b.getY());
                    g2.fillOval(x, y, L, L);
                }
                if (b != null && b.getClass().equals(Absorber.class)) {
                    g2.setColor(black);
                    int x =(b.getX() * L);;
                    int y =(b.getY() * L);
                    g2.fillRect(x, y, 20*L, L);
                }
                if (b != null && b.getClass().equals(LeftFlipper.class)) {
                    g2.setColor(yellow);
                    int x =(b.getX() * L);;
                    int y =(b.getY() * L);
                    g2.fillRect(x, y, (L /2), (L *2));
                    g2.fillOval(x, y-4, 10, 15);
                    g2.fillOval(x, y+30, 10, 15);
                }
                if (b != null && b.getClass().equals(RightFlipper.class)) {
                    g2.setColor(yellow);
                    int x =(b.getX() * L);;
                    int y =(b.getY() * L);
                    g2.fillRect(x+30, y, (L /2), (L *2));
                    g2.fillOval(x+30, y-4, 10, 15);
                    g2.fillOval(x+30, y+30, 10, 15);
                }
            }
        }


    private void printGizmoList(){
        for (IGizmo gizmo : gizmos) {
            System.out.println("GizmoList: "+ gizmo.getClass().toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
        printGizmoList();
    }


    public boolean isFocusable() {
        return true;
    }
}