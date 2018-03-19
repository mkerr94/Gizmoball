package Model;

import Physics.PhysicsCircle;
import Physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

public class Triangle implements IGizmo{
    private int xpos;
    private int ypos;
    private int rotation;
    private int width;
    private Color colour;
    ArrayList<LineSegment> lines = new ArrayList<>();
    ArrayList<PhysicsCircle> endCircles = new ArrayList<>();


    public Triangle(int x, int y, int rotation) {
        xpos = x;
        ypos = y;
        this.rotation = rotation;
        width = 30;
        colour = Color.GREEN;
        createLines();
        createCircles();
        getLines();
        getEndCircles();
    }

    public ArrayList<LineSegment> getLines() {

        return lines;
    }

    public ArrayList<PhysicsCircle> getEndCircles() {

        return endCircles;
    }

    public void createLines() {
        lines.clear();
        switch (rotation){
            case 0:
                LineSegment l1 = new LineSegment(xpos, ypos, xpos + width, ypos);
                LineSegment l2 = new LineSegment(xpos + width, ypos, xpos, ypos + width);
                LineSegment l3 = new LineSegment(xpos, ypos + width, xpos, ypos);
                lines.add(l1);
                lines.add(l2);
                lines.add(l3);
                break;
            case 1:
                LineSegment l4 = new LineSegment(xpos, ypos, xpos + width, ypos);
                LineSegment l5 = new LineSegment(xpos + width, ypos, xpos + width, ypos + width);
                LineSegment l6 = new LineSegment(xpos + width, ypos + width, xpos, ypos);
                lines.add(l4);
                lines.add(l5);
                lines.add(l6);
                break;
            case 2:
                LineSegment l7 = new LineSegment(xpos, ypos + width, xpos + width, ypos);
                LineSegment l8 = new LineSegment(xpos + width, ypos, xpos + width, ypos + width);
                LineSegment l9 = new LineSegment(xpos + width, ypos + width, xpos, ypos + width);
                lines.add(l7);
                lines.add(l8);
                lines.add(l9);
                break;
            case 3:
                LineSegment l10 = new LineSegment(xpos, ypos, xpos, ypos + width);
                LineSegment l11 = new LineSegment(xpos, ypos + width, xpos + width, ypos + width);
                LineSegment l12 = new LineSegment(xpos + width, ypos + width, xpos, ypos);
                lines.add(l10);
                lines.add(l11);
                lines.add(l12);

        }

    }

    public void createCircles() {
        endCircles.clear();
        switch (this.rotation){
            case 0:
                PhysicsCircle c1 = new PhysicsCircle(xpos, ypos, 0);
                PhysicsCircle c2 = new PhysicsCircle(xpos + width, ypos, 0);
                PhysicsCircle c3 = new PhysicsCircle(xpos, ypos + width, 0);
                endCircles.add(c1);
                endCircles.add(c2);
                endCircles.add(c3);
                break;
            case 1:
                PhysicsCircle c4 = new PhysicsCircle(xpos, ypos, 0);
                PhysicsCircle c5 = new PhysicsCircle(xpos + width, ypos, 0);
                PhysicsCircle c6 = new PhysicsCircle(xpos + width, ypos + width, 0);
                endCircles.add(c4);
                endCircles.add(c5);
                endCircles.add(c6);
                break;
            case 2:
                PhysicsCircle c7 = new PhysicsCircle(xpos + width, ypos, 0);
                PhysicsCircle c8 = new PhysicsCircle(xpos, ypos + width, 0);
                PhysicsCircle c9 = new PhysicsCircle(xpos + width, ypos + width, 0);
                endCircles.add(c7);
                endCircles.add(c8);
                endCircles.add(c9);
                break;
            case 3:
                PhysicsCircle c10 = new PhysicsCircle(xpos, ypos, 0);
                PhysicsCircle c11 = new PhysicsCircle(xpos, ypos, 0);
                PhysicsCircle c12 = new PhysicsCircle(xpos, ypos + width, 0);
                endCircles.add(c10);
                endCircles.add(c11);
                endCircles.add(c12);

        }
    }
    @Override
    public int getRotation() {
        return rotation;
    }

    @Override
    public void rotate() {
        endCircles.clear();
        lines.clear();
        createCircles();
        createLines();
        if (rotation == 1) {
            rotation = 2;
        } else if (rotation == 2) {
            rotation = 3;
        } else if (rotation == 3) {
            rotation = 0;
        } else if (rotation == 0) {
            rotation = 1;
        }
    }

    public int getX1() {
        return xpos;
    }

    public int getY1() {
        return ypos;
    }

    @Override
    public void setX1(int x1) {
        this.xpos = x1;
    }

    @Override
    public void setY1(int y1) {
        this.ypos = y1;
    }

    public int getWidth() {
        return width;
    }

    public Color getColour() {
        return colour;
    }
}
