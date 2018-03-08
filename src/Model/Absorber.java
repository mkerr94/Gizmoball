package Model;

import Physics.LineSegment;


public class Absorber implements IGizmo {
    private int xOrdinate;
    private int yOrdinate;
    private int width;
    private int height;
    private LineSegment collisionLine;

    public Absorber(int xOrdinate, int yOrdinate, int width, int height) {
        this.xOrdinate = xOrdinate;
        this.yOrdinate = yOrdinate;
        this.width = width;
        this.height = height;
        collisionLine = new LineSegment(xOrdinate, yOrdinate + height, xOrdinate + width, yOrdinate + height);
    }

    @Override
    public int getX() {
        return xOrdinate;
    }

    @Override
    public int getY() {
        return yOrdinate;
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

    @Override
    public void rotate() {

    }

    @Override
    public void move(int newX, int newY) {

    }
    @Override
    public int getRotation() {
        return 0;
    }
}
