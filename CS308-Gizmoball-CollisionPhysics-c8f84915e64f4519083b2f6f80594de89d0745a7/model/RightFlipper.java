package model;

import java.awt.*;

public class RightFlipper extends Gizmo {
    private Color colour;

    public RightFlipper (int x, int y)
    {

        super( x, y, 1, 1);
        colour = Color.RED;
    }


    public Color getColour(){
        return this.colour;
    }

}