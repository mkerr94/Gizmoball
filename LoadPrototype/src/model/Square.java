package model;

import java.awt.*;

public class Square extends Gizmo implements IGizmo {
    private double xpos;
    private double ypos;
    private Color colour;

    public Square (int x, int y)
    {
        super( x, y);
        xpos = x;
        ypos = y;
        colour = Color.RED;
    }

    // Ball specific methods that deal with double precision.
    public double getExactX() {
        return xpos;
    }

    public double getExactY() {
        return ypos;
    }

    public Color getColour() {
        return colour;
    }


}
