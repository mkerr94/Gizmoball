package Model;

import Physics.Circle;
import Physics.Vect;

import java.awt.Color;


public class CircleGizmo {

    private double radius;
    private double xpos;
    private double ypos;
    private Color colour;
    private boolean stopped;

    // x, y coordinates and x,y velocity
    public CircleGizmo(double x, double y, double xv, double yv) {
        xpos = x; // Centre coordinates
        ypos = y;
        colour = Color.RED;
        radius = 10;
        stopped = true;
    }



    public double getRadius() {
        return radius;
    }

    public Circle getCircle() {
        return new Circle(xpos, ypos, radius);

    }

    // Ball specific methods that deal with double precision.
    public double getExactX() {
        return xpos;
    }

    public double getExactY() {
        return ypos;
    }

    public void setExactX(double x) {
        xpos = x;
    }

    public void setExactY(double y) {
        ypos = y;
    }

    public boolean stopped() {
        return stopped;
    }

    public Color getColour() {
        return colour;
    }

}
