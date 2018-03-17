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

    // x, y coordinates and x,y velocity
    public Circle(int x, int y) {
        xpos = x; // Centre coordinates
        ypos = y;
        this.x = x;
        this.y = y;
        colour = Color.RED;
        radius = 15;
    }

    public PhysicsCircle getCircle() {
        return new PhysicsCircle(xpos, ypos, radius);
    }

    public Color getColour() {
        return colour;
    }

    @Override
    public int getX1() {
        return x;
    }

    @Override
    public int getY1() {
        return y;
    }

    @Override
    public void setX1(int x1) {
        this.x = x1;
    }

    @Override
    public void setY1(int y1) {
        this.y = y1;
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

    public void updateX(int newX){
        x = newX;
    }

    public void updateY(int newY){
        y = newY;
    }
}
