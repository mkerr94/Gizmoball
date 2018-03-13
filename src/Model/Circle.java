package Model;

import Physics.LineSegment;
import Physics.PhysicsCircle;

import java.awt.Color;
import java.util.ArrayList;


public class Circle implements IGizmo {
    private double radius;
    private double xpos;
    private int x;
    private double ypos;
    private int y;
    private Color colour;
    private boolean stopped;

    // x, y coordinates and x,y velocity
    public Circle(int x, int y) {
        xpos = x; // Centre coordinates
        ypos = y;
        this.x = x;
        this.y = y;
        colour = Color.RED;
        radius = 15;
        stopped = true;
    }

    public PhysicsCircle getCircle() {
        return new PhysicsCircle(xpos, ypos, radius);
    }

    public Color getColour() {
        return colour;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ArrayList<LineSegment> getLines() {
        return null;
    }

    @Override
    public ArrayList<PhysicsCircle> getEndCircles() {
        return null;
    }

    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public void rotate() {

    }
}
