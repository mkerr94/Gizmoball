package model;

import java.awt.*;

public class LeftFlipper extends Gizmo implements IGizmo {
    private Color colour;

    public LeftFlipper (int x, int y)
    {
        super( x, y);
        colour = Color.YELLOW;
    }

    public Color getColour() {
        return colour;
    }

}