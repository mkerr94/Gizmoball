package View;

import Model.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameBoard extends JPanel implements Observer {
    private Mode mode;
    private Model model;
    private Ball ball;
    private List<IGizmo> gizmos;
    private int L = 30;
    private int width;
    private int height;

    GameBoard(int w, int h, Model model, Mode mode){
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.model = model;
        this.mode = mode;
        ball = model.getBall();
        width = w;
        height = h;
        gizmos = model.getGizmos();
        model.setWalls(new Walls(0, 0, this.width, this.height));
        model.addObserver(this);
        setBorder(new LineBorder(Color.BLACK));
        setBackground(Color.white);
        requestFocus();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Color yellow = new Color(255,255,0);
        if (mode == Mode.BUILD) printGridLines(g2);
        drawBall(g2);

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
                /*
                -15 makes the collisions look much better.
                Without -15 collisions are 15 pixels off, but the circle doesn't draw
                in the middle of a grid-square, it draws on the cross section of 4 grid
                squares. todo update circle collisions
                 */
                int x= (gizmo.getX() * L - 15);
                int y= (gizmo.getY() * L - 15);
                g2.fillOval(x, y, L, L);
            }

            if (gizmo instanceof Triangle) {
                g2.setColor(gizmo.getColour());
                int x= (gizmo.getX() * L);
                int y= (gizmo.getY() * L);
                if(gizmo.getRotation() == 0){
                    int x2Points[] = {x+ L, x,x};
                    int y2Points[] = {y,y+ L,y};
                    g2.fillPolygon(x2Points,y2Points,3);
                }
                else if(gizmo.getRotation() == 1){
                    int x2Points1[] = {x+ L, x,x+ L};
                    int y2Points1[] = {y,y,y+ L};
                    g2.fillPolygon(x2Points1,y2Points1,3);
                }
            }
            if (gizmo instanceof Absorber) {
                g2.setColor(gizmo.getColour());
                int x =(gizmo.getX() * L);
                int y =(gizmo.getY() * L);
                g2.fillRect(x, y, ((Absorber) gizmo).getWidth() * L, ((Absorber) gizmo).getHeight() * L);
            }
            if (gizmo instanceof LeftFlipper) {
                g2.setColor(yellow);
                int x =(gizmo.getX() * L);
                int y =(gizmo.getY() * L);
                g2.fillRect(x, y, (L /2), (L *2));
                g2.fillOval(x, y-4, 10, 15);
                g2.fillOval(x, y+30, 10, 15);
            }
            if (gizmo instanceof RightFlipper) {
                g2.setColor(yellow);
                int x =(gizmo.getX() * L);;
                int y =(gizmo.getY() * L);
                g2.fillRect(x+30, y, (L /2), (L *2));
                g2.fillOval(x+30, y-4, 10, 15);
                g2.fillOval(x+30, y+30, 10, 15);
            }
        }

    }

    private void drawBall(Graphics2D g2) {
        g2.setColor(ball.getColour());
        int x = (int) (ball.getExactX() - ball.getRadius());
        int y = (int) (ball.getExactY() - ball.getRadius());
        g2.fillOval(x, y, L, L);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        this.repaint();
    }

    private void printGridLines(Graphics2D g2) {
    // todo fix the grid lines
        int lines = L;
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

    private void printGizmoList(){
        for (IGizmo gizmo : gizmos) {
            System.out.println("gizmo.getClass().toString() = " + gizmo.getClass().toString());
        }
    }

    public boolean isFocusable() {
        return true;
    }
}