package View;

import Model.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;

public class GameBoard extends JPanel implements Observer {
    private Mode mode;
    private Model model;
    private List<IGizmo> gizmos;
    private List<Ball> balls;
    private int L = 30;
    private int width;
    private int height;

    /***
     * Initialises a board, builds collisions walls on the board, registers the board
     * as an observer of the model.
     * @param w width of the game board
     * @param h height of the game board
     * @param model reference of Model
     * @param mode the mode the game is in, can either be BUILD or RUN. See View.Mode
     */
    GameBoard(int w, int h, Model model, Mode mode){
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.model = model;
        this.mode = mode;
        balls = model.getBalls();
        width = w;
        height = h;
        gizmos = model.getGizmos();
        model.setWalls(new Walls(0, 0, this.width, this.height));
        model.addObserver(this);
        setBorder(new EtchedBorder(Color.black, Color.black));
        setBackground(Color.white);
        requestFocus();
    }

    /***
     * Paints all of the gizmos from the model. See Model.gizmos.
     * Also draws the ball (see paintBalls(g2)) and gridlines if the game
     * is in buildmode.
     * @param g Java2d Graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // paint all of the gizmos from the model
        for(IGizmo gizmo : gizmos) {
            assert gizmo != null;
            if (gizmo instanceof Square) {
                g2.setColor(gizmo.getColour());
                int x= (gizmo.getX() * L);
                int y= (gizmo.getY() * L);
                g2.fillRect(x, y, L, L);
            }
            if (gizmo instanceof Circle) {
                g2.setColor(gizmo.getColour());
                int x= (gizmo.getX() * L);
                int y= (gizmo.getY() * L);
                g2.fillOval(x, y, L, L);
            }
            if (gizmo instanceof Triangle) {
                g2.setColor(gizmo.getColour());
                int x= (gizmo.getX() * L);
                int y= (gizmo.getY() * L);
                if(gizmo.getRotation() == 0){
                    int x2Points[] = {x + L, x, x};
                    int y2Points[] = {y, y + L, y};
                    g2.fillPolygon(x2Points, y2Points,3);
                }
                else if(gizmo.getRotation() == 1){
                    int x2Points1[] = {x + L, x, x + L};
                    int y2Points1[] = {y, y, y + L};
                    g2.fillPolygon(x2Points1, y2Points1,3);
                }
                else if (gizmo.getRotation() == 2){
                    int x2Points1[] = {x + L, x, x + L};
                    int y2Points1[] = {y, y + L, y + L};
                    g2.fillPolygon(x2Points1, y2Points1,3);
                }
                else if (gizmo.getRotation() == 3){
                    int x2Points1[] = {x + L, x, x};
                    int y2Points1[] = {y+L, y, y + L};
                    g2.fillPolygon(x2Points1, y2Points1,3);
                }
            }
            if (gizmo instanceof Absorber) {
                g2.setColor(gizmo.getColour());
                int x =(gizmo.getX() * L);
                int y =(gizmo.getY() * L);
                g2.fillRect(x, y, ((Absorber) gizmo).getWidth() * L , ((Absorber) gizmo).getHeight() * L );
            }
            if (gizmo instanceof LeftFlipper) {
                LeftFlipper leftFlipper = new LeftFlipper(gizmo.getX(), gizmo.getY());
                int x = leftFlipper.getX() * L;
                int y = leftFlipper.getY() * L;
                double angle = leftFlipper.getAngle();
                Graphics2D g2d = (Graphics2D) g.create();
                AffineTransform transform = new AffineTransform();
                transform.rotate(-Math.toRadians(angle), x + width/2, y + width/2);
                g2d.setColor(leftFlipper.getColour());
                g2d.setTransform(transform);
                g2d.fillRoundRect(x, y, L/2, 2*L, 20, 20);
            }
            if (gizmo instanceof RightFlipper) {
                RightFlipper rightFlipper = new RightFlipper(gizmo.getX(), gizmo.getY());
                int x = rightFlipper.getX() * L;
                int y = rightFlipper.getY() * L;
                double angle = rightFlipper.getAngle();
                Graphics2D g2d = (Graphics2D) g.create();
                AffineTransform transform = new AffineTransform();
                transform.rotate(-Math.toRadians(angle), x + width/2, y + width/2);
                g2d.setColor(Color.red);
                g2d.setTransform(transform);
                g2d.fillRoundRect(x + L/2, y, L/2, 2*L, 20, 20);
            }
        }
        paintBalls(g2);
        if (mode == Mode.BUILD) paintGridLines(g2);
    }

    /**
     * Draws the ball on the board
     * @param g2 JavaGraphics2d object from paintComponent
     */
    private void paintBalls(Graphics2D g2) {
        for (Ball b : balls) {
            g2.setColor(b.getColour());
            int x = (int) b.getExactX();
            int y = (int) b.getExactY();
            g2.fillOval(x, y, L / 2, L / 2);
        }
    }

    /***
     * Prints gridlines on the board, creating a 20x20 grid.
     * Each grid square is of length L.
     * @param g2 JavaGraphics2d object from paintComponent
     */
    private void paintGridLines(Graphics2D g2) {
        int lines = L;
        g2.setColor(Color.BLACK);
        for (int i = 1; i <= 20; i++) {
            int x = i * lines;
            g2.drawLine(x, 0, x, height);
            g2.drawLine(0, x, width, x);
        }
    }

    // Fix onscreen size
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    /***
     * Needed to make the board have default key focus
     * @return true if focusable, false if not focusable. Should always be true for GameBoard.
     */
    public boolean isFocusable() {
        return true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        this.repaint();
    }
}