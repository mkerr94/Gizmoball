package model;

import java.awt.*;

public class Absorber extends Gizmo implements IGizmo{
    private Color colour;

    public Absorber (int x, int y, int x2, int y2)
    {

        super( x, y);
        colour = Color.MAGENTA;
    }

    public Color getColour() {
        return colour;
    }

}