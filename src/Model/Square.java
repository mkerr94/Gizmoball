package Model;

import Physics.PhysicsCircle;
import Physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;


public class Square implements IGizmo {

    private int xpos;
    private int ypos;
    private int width;
    private Color colour;


    public Square(int x, int y) {
        xpos = x;
        ypos = y;
        width = 30;
        colour = Color.MAGENTA;
        getLines();
        getEndCircles();
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

    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public void rotate() {

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

    public void updateX(int x){
        this.xpos = x;
    }

    public void updateY(int y){
        this.ypos = y;
    }

}
