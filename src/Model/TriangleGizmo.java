package Model;

import Physics.Circle;
import Physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

public class TriangleGizmo {

    private int xpos;
    private int ypos;
    private int width;
    private LineSegment ls;
    private Color colour;


    public TriangleGizmo(int x, int y, int w) {
        xpos = x;
        ypos = y;
        width = w;
        colour = Color.GREEN;
        getLines();
        getEndCircles();
    }

    public LineSegment getLineSegs() {
        return ls;
    }

    public ArrayList<LineSegment> getLines() {

        ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
        LineSegment l1 = new LineSegment(xpos, ypos + width, xpos + width, ypos);
        LineSegment l2 = new LineSegment(xpos, ypos, xpos, ypos + width);
        LineSegment l3 = new LineSegment(xpos, ypos, xpos + width, ypos);

        lines.add(l1);
        lines.add(l2);
        lines.add(l3);

        return lines;

    }


    public ArrayList<Circle> getEndCircles() {
        ArrayList<Circle> endCircles = new ArrayList<Circle>();
        Circle c1 = new Circle(xpos, ypos, 0);
        Circle c2 = new Circle(xpos, ypos, 0);
        Circle c3 = new Circle(xpos, ypos + width, 0);
        Circle c4 = new Circle(xpos + width, ypos , 0);
        Circle c5 = new Circle(xpos, ypos + width, 0);
        Circle c6 = new Circle(xpos + width, ypos , 0);
        endCircles.add(c1);
        endCircles.add(c2);
        endCircles.add(c3);
        endCircles.add(c4);
        endCircles.add(c5);
        endCircles.add(c6);
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
