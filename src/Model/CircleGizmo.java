package Model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleGizmo {

    //will change this to use a constructor to get x and y from user
    //and set it here
    double w = 20; //width
    double h = 20; //height
    double x = 150; //x co-ord
    double y = 150; //y-co-ord

    public void paint(Graphics g) {

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.GREEN);

        Ellipse2D circle = new Ellipse2D.Double(x, y, w, h); //allows you to fill colour
        graphics2D.fill(circle);

        graphics2D.draw(new Ellipse2D.Double(x, y, w, h));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
