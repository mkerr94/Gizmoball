package model;

import java.awt.*;

public class LeftFlipper extends Gizmo {
    private Color colour;

    public LeftFlipper (int x, int y)
    {

        super( x, y, 1, 1);
        colour = Color.RED;
    }


    public Color getColour(){
        return this.colour;
    }

}