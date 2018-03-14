package Model;

import Physics.LineSegment;
import Physics.PhysicsCircle;

import java.awt.*;
import java.util.ArrayList;


public class Absorber implements IGizmo {
    private int xOrdinate;
    private int yOrdinate;
    private int width;
    private int height;
    private LineSegment collisionLine;
    private Color color;

    public Absorber(int xOrdinate, int yOrdinate, int width, int height) {
        this.xOrdinate = xOrdinate;
        this.yOrdinate = yOrdinate;
        this.width = width;
        this.height = height;
        collisionLine = new LineSegment(xOrdinate, yOrdinate, xOrdinate + width, yOrdinate);
        color = Color.cyan;
    }

    @Override
    public int getX() {
        return xOrdinate;
    }

    @Override
    public int getY() {
        return yOrdinate;
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
        // cannot rotate an absorber
    }

    @Override
    public Color getColour() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public LineSegment getCollisionLine() {
        return collisionLine;
    }

}
