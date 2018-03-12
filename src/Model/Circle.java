package Model;

import Model.Gizmo;
import Model.IGizmo;
import Physics.PhysicsCircle;
import Physics.PhysicsCircle;
import Physics.Vect;

import java.awt.Color;


public class Circle extends Gizmo implements IGizmo {

    private double radius;
    private double xpos;
    private double ypos;
    private Color colour;
    private boolean stopped;

    // x, y coordinates and x,y velocity
    public Circle(int x, int y) {
        super(x,y);
        xpos = x; // Centre coordinates
        ypos = y;
        colour = Color.RED;
        radius = 12.5;
        stopped = true;
    }

    public double getRadius() {
        return radius;
    }

    public PhysicsCircle getCircle() {
        return new PhysicsCircle(xpos, ypos, radius);

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
