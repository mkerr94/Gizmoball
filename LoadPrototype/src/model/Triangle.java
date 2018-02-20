package model;

import java.awt.*;

public class Triangle extends Gizmo implements IGizmo {
    private Color colour;

    public Triangle (int x, int y)
    {
        super( x, y);
        colour = Color.BLUE ;    }

    public Color getColour() {
        return colour;
    }

}

