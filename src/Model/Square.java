package Model;

import Physics.PhysicsCircle;
import Physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;


public class Square extends Gizmo implements IGizmo {

    private int xpos;
    private int ypos;
    private int width;
    private LineSegment ls;
    private Color colour;


    public Square(int x, int y) {
        super(x,y);
        xpos = x;
        ypos = y;
        width = 25;
        colour = Color.MAGENTA;
        getLines();
        getEndCircles();
    }

    public LineSegment getLineSegs() {
        return ls;
    }

    public ArrayList<LineSegment> getLines() {

        ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
        LineSegment l1 = new LineSegment(xpos, ypos, xpos, ypos + width);
        LineSegment l2 = new LineSegment(xpos + width, ypos, xpos + width, ypos + width);
        LineSegment l3 = new LineSegment(xpos, ypos + width, xpos + width, ypos + width);
        LineSegment l4 = new LineSegment(xpos, ypos, xpos + width, ypos);
        lines.add(l1);
        lines.add(l2);
        lines.add(l3);
        lines.add(l4);
        return lines;

    }


    public ArrayList<PhysicsCircle> getEndCircles() {
        ArrayList<PhysicsCircle> endCircles = new ArrayList<PhysicsCircle>();
        PhysicsCircle c1 = new PhysicsCircle(xpos, ypos + width, 0);
        PhysicsCircle c2 = new PhysicsCircle(xpos, ypos, 0);
        PhysicsCircle c3 = new PhysicsCircle(xpos + width, ypos, 0);
        PhysicsCircle c4 = new PhysicsCircle(xpos + width, ypos + width, 0);
        PhysicsCircle c5 = new PhysicsCircle(xpos, ypos , 0);
        PhysicsCircle c6 = new PhysicsCircle(xpos, ypos + width, 0);
        PhysicsCircle c7 = new PhysicsCircle(xpos + width, ypos, 0);
        PhysicsCircle c8 = new PhysicsCircle(xpos + width, ypos + width, 0);
        endCircles.add(c1);
        endCircles.add(c2);
        endCircles.add(c3);
        endCircles.add(c4);
        endCircles.add(c5);
        endCircles.add(c6);
        endCircles.add(c7);
        endCircles.add(c8);
        return endCircles;
    }


    public int getX() {
        return xpos;
    }

    public int getY() {
        return ypos;
    }

    public int getWidth() {
        return width;
    }

    public Color getColour() {
        return colour;
    }
}
