package View;

import Controller.FlipperListener;
import Controller.MagicKeyListener;
import Model.*;
import javax.swing.*;
import javax.swing.Timer;
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
    private List<Flipper> flippers;
    private FlipperListener flipperListener;

    /***
     * Initialises a board, builds collisions walls on the board, registers the board
     * as an observer of the model.
     * @param w width of the game board
     * @param h height of the game board
     * @param model reference of Model
     */
    GameBoard(int w, int h, Model model){
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.model = model;
        balls = model.getBalls();
        width = w;
        height = h;
        gizmos = model.getGizmos();
        flippers = model.getFlippers();
        model.setWalls(new Walls(0, 0, this.width, this.height));
        model.addObserver(this);
        registerAsFlipperObserver();
        flipperListener = new FlipperListener(model, this);
        MagicKeyListener magicKeyListener = new MagicKeyListener(flipperListener);
        this.addKeyListener(magicKeyListener);
        setBorder(new EtchedBorder(Color.black, Color.black));
        setBackground(Color.white);
        requestFocus();
    }

    public void registerAsFlipperObserver() {
        for (IGizmo gizmo : gizmos) {
            if (gizmo instanceof LeftFlipper) {
                LeftFlipper leftFlipper = new LeftFlipper(gizmo.getX1(), gizmo.getY1());
                leftFlipper.addObserver(this);
            } else if (gizmo instanceof RightFlipper) {
                RightFlipper rightFlipper = new RightFlipper(gizmo.getX1(), gizmo.getY1());
                rightFlipper.addObserver(this);
            }
        }
    }

    /***
     * Paints all of the gizmos from the model. See Model.gizmos.
     * Also draws the ball (see paintBalls(g2)) and gridlines if the game
     * is in buildmode.
     * todo fix painting glitch where flippers move half an L between modes
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
                int x= (gizmo.getX1() * L);
                int y= (gizmo.getY1() * L);
                g2.fillRect(x, y, L, L);
            }
            if (gizmo instanceof Circle) {
                g2.setColor(gizmo.getColour());
                int x= (gizmo.getX1() * L);
                int y= (gizmo.getY1() * L);
                g2.fillOval(x, y, L, L);
            }
            if (gizmo instanceof Triangle) {
                g2.setColor(gizmo.getColour());
                int x= (gizmo.getX1() * L);
                int y= (gizmo.getY1() * L);
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
                else {
                    int x2Points[] = {x + L, x, x};
                    int y2Points[] = {y, y + L, y};
                    g2.fillPolygon(x2Points, y2Points,3);
                }
            }
            if (gizmo instanceof Absorber) {
                g2.setColor(gizmo.getColour());
                int x =(gizmo.getX1() * L);
                int y =(gizmo.getY1() * L);
                g2.fillRect(x, y, ((Absorber) gizmo).getX2() * L , ((Absorber) gizmo).getY2() * L );
            }
        }
        paintFlippers(g2);
        paintBalls(g2);
        if (mode == Mode.BUILD) paintGridLines(g2);
    }

    private void paintFlippers(Graphics2D g2) {
        for (Flipper flipper : flippers) {
            int x = flipper.getX1() * L;
            int y = flipper.getY1() * L;
            double angle = flipper.getAngle();
            int width = L/2;
            int height = 2*L;
            if (flipper instanceof LeftFlipper) {
                Graphics2D g2d = (Graphics2D) g2.create();
                AffineTransform transform = new AffineTransform();
                transform.rotate(-Math.toRadians(angle), x + width / 2, y);
                g2d.setColor(Color.cyan);
                g2d.setTransform(transform);
                g2d.fillRoundRect(x, y, width, height, 20, 20);
            }
            if (flipper instanceof RightFlipper) {
                Graphics2D g2d = (Graphics2D) g2.create();
                AffineTransform transform = new AffineTransform();
                transform.rotate(Math.toRadians(angle), x + L/2 + width / 2, y);
                g2d.setColor(Color.cyan);
                g2d.setTransform(transform);
                g2d.fillRoundRect(x + L/2, y, width, height, 20, 20);
            }
        }
    }

    /**
     * Draws the ball on the board
     * @param g2 JavaGraphics2d object from paintComponent
     */
    private void paintBalls(Graphics2D g2) {
        for (Ball b : balls) {
            g2.setColor(b.getColour());
            int x = (int) b.getExactX() * L;
            int y = (int) b.getExactY() * L;
            g2.fillOval(x, y, L/2, L/2);
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

    /***
     * Used to maintain the size of the gameBoard
     * @return the dimension of the fixed size of the GameBoard
     */
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

    void setMode(Mode mode){
        this.mode = mode;
    }

    public void updateFlipperListener() {
        if (flipperListener != null) {
            flipperListener.update();
        }
    }

    public void initFlipperListeners(Timer timer) {
        flipperListener.setTimer(timer);
    }
}