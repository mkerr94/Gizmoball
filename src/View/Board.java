package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Model.*;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public  class Board extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;
    protected int width;
    protected int height;
    protected Model gm;

    public Board(int w, int h, Model m) {
        // Observe changes in Model
        m.addObserver(this);
        width = w;
        height = h;
        gm = m;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    // Fix onscreen size
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        Graphics2D g3 = (Graphics2D) g;



        Ball b = gm.getBall();
        if (b != null) {
            g2.setColor(b.getColour());
            int x = (int) (b.getExactX() - b.getRadius());
            int y = (int) (b.getExactY() - b.getRadius());
            int width = (int) (2 * b.getRadius());
            g2.fillOval(x, y, width, width);
        }

        CircleGizmo cg = gm.getCircleGizmo();
        if (cg != null) {
            g2.setColor(cg.getColour());
            int x = (int) (cg.getExactX() - cg.getRadius());
            int y = (int) (cg.getExactY() - cg.getRadius());
            int width = (int) (2 * cg.getRadius());
            g2.fillOval(x, y, width, width);
        }


        SquareGizmo sg = gm.getSquareGizmo();
        if (sg != null) {
            g2.setColor(sg.getColour());
            int x= (int) (sg.getX());
            int y= (int) (sg.getY());
            int width = (int)(sg.getWidth());
            g2.fillRect(x,y,width,width);
        }

        TriangleGizmo tg = gm.getTriangleGizmo();

        if (tg != null) {
            g2.setColor(tg.getColour());
            int x= (int) (tg.getX());
            int y= (int) (tg.getY());
            int width = (int)(tg.getWidth());
            int x2Points[] = {x, x,x+20};
            int y2Points[] = {y+20,y,y};
            g2.fillPolygon(x2Points,y2Points,3);

        }

    }
    @Override
    public void update(Observable arg0, Object arg1) {
        repaint();
    }

}
