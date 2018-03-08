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
    private Model model; // model
    private FlipperListener controller;
    //private HashMap<String, Model.IGizmo> gizmos;
    private List<Flipper> flippers;
    private List<IGizmo> gizmos;
    private int L = 25;
    private int width;
    private int height;
    private LoadFile loadedFile;

    private Color green = new Color(0, 255, 0);
    private Color red = new Color(255, 0, 0);
    private Color black = new Color(0, 0, 255);
    private Color blue = new Color(0, 0, 255);
    private Color yellow = new Color(255,255,0);

    public GameBoard(int w, int h, Model model){
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.model = model;
        width = w;
        height = h;
        flippers = new ArrayList<>();
        gizmos = model.getGizmos();
        for (IGizmo gizmo : gizmos) {
            if (gizmo.getClass().equals(RightFlipper.class) || gizmo.getClass().equals(LeftFlipper.class)) {
                flippers.add((Flipper)gizmo);
            }
        }
        setLayout(new FlowLayout(4));
        for (int i = 0; i < 4; i++) {
            add(new JLabel(Integer.toString(i)));
        }
        model.addObserver(this);
        setBorder(new LineBorder(Color.RED));
        Timer tickTimer = new Timer(TICK_TIME, this);
        tickTimer.start();
        requestFocus();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("test: paint method");
            Graphics2D g2 = (Graphics2D) g;
            for(IGizmo b : loadedFile.getLoadedGizmos().values()) {
                if (b != null && b.getClass().equals(Square.class)) {
                    g2.setColor(red);
                    System.out.println("found Square: paint method");
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
                    g2.fillRect(x, y, 20, L);
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



    @Override
    public void actionPerformed(ActionEvent e) {
        for (IGizmo gizmo : gizmos) {
            if (gizmo instanceof Flipper) {
                Flipper flipper = (Flipper) gizmo;
                flipper.setAngle(flipper.getAngle() + flipper.getAngularMomentum() * (double) TICK_TIME/10);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }


    public boolean isFocusable() {
        return true;
    }
}