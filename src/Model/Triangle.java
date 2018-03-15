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


    public Triangle(int x, int y) {
        xpos = x;
        ypos = y;
        rotation = 0;
        width = 30;
        colour = Color.GREEN;
        getLines();
        getEndCircles();
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


    public ArrayList<PhysicsCircle> getEndCircles() {
        ArrayList<PhysicsCircle> endCircles = new ArrayList<PhysicsCircle>();
        PhysicsCircle c1 = new PhysicsCircle(xpos, ypos, 0);
        PhysicsCircle c2 = new PhysicsCircle(xpos, ypos, 0);
        PhysicsCircle c3 = new PhysicsCircle(xpos, ypos + width, 0);
        PhysicsCircle c4 = new PhysicsCircle(xpos + width, ypos , 0);
        PhysicsCircle c5 = new PhysicsCircle(xpos, ypos + width, 0);
        PhysicsCircle c6 = new PhysicsCircle(xpos + width, ypos , 0);
        endCircles.add(c1);
        endCircles.add(c2);
        endCircles.add(c3);
        endCircles.add(c4);
        endCircles.add(c5);
        endCircles.add(c6);
        return endCircles;
    }

    @Override
    public int getRotation() {
        return rotation;
    }

    @Override
    public void rotate() {
        if (rotation == 0){
            rotation = 1;
        } else if (rotation == 1){
            rotation = 2;
        } else if (rotation == 2){
            rotation = 3;
        } else if (rotation == 3){
            rotation = 0;
        }
    }


    public int getX() {
        return xpos;
    }

    public int getY() {
        return ypos;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    public int getWidth() {
        return width;
    }

    public Color getColour() {
        return colour;
    }
}
