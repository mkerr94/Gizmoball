package Model;

import java.awt.Color;

import Physics.PhysicsCircle;
import Physics.Vect;


public class Ball {

    private Vect velocity;
    private double radius;
    private double xpos;
    private double ypos;
    private int xOrdinate;
    private int yOrdinate;
    private Color colour;

    private boolean stopped;

    // x, y coordinates and x,y velocity
    public Ball(double x, double y, double xv, double yv) {
        xpos = x; // Centre coordinates
        ypos = y;
        xOrdinate = Math.toIntExact(Math.round(x));
        yOrdinate = Math.toIntExact(Math.round(y));
        colour = Color.BLUE;
        velocity = new Vect(xv, yv);
        radius = 7.5;
        stopped = false;
    }

    public int getxOrdinate() {
        return xOrdinate;
    }

    public void setxOrdinate(int xOrdinate) {
        this.xOrdinate = xOrdinate;
    }

    public int getyOrdinate() {
        return yOrdinate;
    }

    public void setyOrdinate(int yOrdinate) {
        this.yOrdinate = yOrdinate;
    }

    public Vect getVelo() {
        return velocity;
    }

    public void setVelo(Vect v) {
        velocity = v;
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

    public void stop() {
        stopped = true;
    }

    public void start() {
        stopped = false;
    }

    public boolean stopped() {
        return stopped;
    }

    public Color getColour() {
        return colour;
    }

}
