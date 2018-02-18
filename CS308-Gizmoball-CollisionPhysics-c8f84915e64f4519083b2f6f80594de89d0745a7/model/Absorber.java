package model;

import java.awt.*;

public class Absorber extends Gizmo {
    private Color colour;

    public Absorber (int x, int y)
    {

        super( x, y, 1, 1);
        colour = Color.RED;
    }


    public Color getColour(){
        return this.colour;
    }

}