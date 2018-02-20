package model;

import java.awt.*;

public class RightFlipper extends Gizmo implements IGizmo {

    private Color colour;

    public RightFlipper (int x, int y)
    {
        super( x, y);
        colour = Color.YELLOW;
    }

    public Color getColour() {
        return colour;
    }

}