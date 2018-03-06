package View;

import Controller.FlipperListener;
import Controller.MagicKeyListener;
import Model.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.List;

class GameBoard extends JPanel implements Observer, ActionListener {
    private static final int TICK_TIME = 5;
    private Model model; // model
    private FlipperListener controller;
    private List<Gizmo> gizmos;
    private List<Flipper> flippers;

    GameBoard(Model model){
        this.model = model;
        this.gizmos = this.model.getGizmos();
        flippers = new ArrayList<>();
        for (Gizmo gizmo : gizmos) {
            if (gizmo.getClass().equals(RightFlipper.class) || gizmo.getClass().equals(LeftFlipper.class)) {
                flippers.add((Flipper)gizmo);
            }
        }
        System.out.println("flippers size = " + flippers.size());
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
        System.out.println("test");
        for (Gizmo gizmo : gizmos) {
            // paint flippers
            if (gizmo.getClass().equals(Flipper.class)) {
                System.out.println("worked");
                Flipper flipper = (Flipper) gizmo;
                int x = flipper.getX();
                int y = flipper.getY();
                double angle = flipper.getAngle();
                int width = flipper.getWidth();
                int height = flipper.getHeight();
                if (flipper.getClass().equals(LeftFlipper.class)) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    AffineTransform transform = new AffineTransform();
                    transform.rotate(-Math.toRadians(angle), x + width / 2, y + width / 2);
                    g2d.setColor(Color.cyan);
                    g2d.setTransform(transform);
                    g2d.fillRoundRect(x, y, width, height, 20, 20);
                }
                if (flipper.getClass().equals(RightFlipper.class)) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    AffineTransform transform = new AffineTransform();
                    transform.rotate(Math.toRadians(angle), x + width / 2, y + width / 2);
                    g2d.setColor(Color.cyan);
                    g2d.setTransform(transform);
                    g2d.fillRoundRect(x, y, width, height, 20, 20);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Gizmo gizmo : gizmos) {
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