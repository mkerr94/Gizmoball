package model;

import java.awt.*;

public class Triangle extends Gizmo {
    private double radius;
    private double xpos;
    private double ypos;
    private Color colour;
    private boolean stopped;
    private String name;

    public Triangle (int x, int y)
    {

        super( x, y,1,1);
        xpos = x;
        ypos = y;
        radius = 10;
        colour = Color.MAGENTA;    }

    public double getRadius() {
        return radius;
    }


    // Ball specific methods that deal with double precision.
    public double getExactX() {
        return xpos;
    }

    public double getExactY() {
        return ypos;
    }

    public void setExactX(double x) {
        xpos = x;
    }

    public void setExactY(double y) {
        ypos = y;
    }

    public boolean stopped() {
        return stopped;
    }

    public Color getColour() {
        return colour;
    }

    public GizmoType getType(){
        return GizmoType.TRIANGLE;
    }

}

