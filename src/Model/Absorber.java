package Model;

import Physics.LineSegment;
import Physics.PhysicsCircle;
import java.awt.*;
import java.util.ArrayList;


public class Absorber implements IGizmo {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private LineSegment collisionLine;
    private Color color;

    public Absorber(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        collisionLine = new LineSegment(x1 + 30, y1 + y2, x1 + x2, y1 + y2);
        color = Color.cyan;
    }

    @Override
    public int getX1() {
        return x1;
    }

    @Override
    public int getY1() {
        return y1;
    }

    @Override
    public void setX1(int x1) {
        this.x1 = x1;
    }

    @Override
    public void setY1(int y1) {
        this.y1 = y1;
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
    public void rotate() {}

    @Override
    public Color getColour() {
        return color;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public LineSegment getCollisionLine() {
        return collisionLine;
    }

}
