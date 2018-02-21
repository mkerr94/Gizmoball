package View;

import Controller.FlipperController;
import Controller.MagicKeyListener;
import Model.Flipper;
import Model.LeftFlipper;
import Model.RightFlipper;
import Physics.LineSegment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class FlipperPanel extends JPanel implements Observer, ActionListener {
    private static final int TICK_TIME = 5;
    private List<Flipper> flippers; // model

    FlipperPanel(List<Flipper> model, FlipperController controller){
        this.flippers = model;
        this.setBackground(Color.BLACK);
        for (Flipper flipper : flippers) {
            flipper.addObserver(this);
        }
        MagicKeyListener magicKeyListener = new MagicKeyListener(controller);
        addKeyListener(magicKeyListener);
        Timer tickTimer = new Timer(TICK_TIME, this);
        tickTimer.start();
        requestFocus();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Flipper flipper : flippers) {
            int x = flipper.getX();
            int y = flipper.getY();
            double angle = flipper.getAngle();
            int width = flipper.getWidth();
            int height = flipper.getHeight();
            //drawLines(g, flipper);
            if (flipper.getClass().equals(LeftFlipper.class)){
                Graphics2D g2d = (Graphics2D) g.create();
                AffineTransform transform = new AffineTransform();
                transform.rotate(-Math.toRadians(angle), x + width/2, y + width/2);
                g2d.setColor(Color.cyan);
                g2d.setTransform(transform);
                g2d.fillRoundRect(x, y, width, height, 20, 20);
            }
            if (flipper.getClass().equals(RightFlipper.class)){
                Graphics2D g2d = (Graphics2D) g.create();
                AffineTransform transform = new AffineTransform();
                transform.rotate(Math.toRadians(angle), x + width/2, y + width/2);
                g2d.setColor(Color.cyan);
                g2d.setTransform(transform);
                g2d.fillRoundRect(x, y, width, height, 20, 20);
            }
        }
    }

    private void drawLines(Graphics g, Flipper flipper) {
        // TODO update so this draws all the line segments
        ArrayList<LineSegment> lineSegments = flipper.getLines();
        for (LineSegment line :
                lineSegments) {
            g.create();
            g.drawLine((int)line.p1().x(), (int)line.p1().y(), (int)line.p2().x(), (int)line.p2().y());
            g.setColor(Color.WHITE);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Flipper flipper :
                flippers) {
            flipper.setAngle(flipper.getAngle() + flipper.getAngularMomentum() * (double) TICK_TIME/10);
        }
        // TODO update the lineSegment position to follow the flipper's view
    }

    public boolean isFocusable() {
        return true;
    }
}
