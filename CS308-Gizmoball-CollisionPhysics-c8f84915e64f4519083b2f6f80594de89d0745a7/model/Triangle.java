package model;

import java.awt.*;

public class Triangle extends Gizmo {

    private Color colour;

    public Triangle (int x, int y)
    {

        super( x, y,1,1);
        colour = Color.GREEN;
    }


    public Color getColour(){
        return this.colour;
    }

    public GizmoType getType(){
        return GizmoType.TRIANGLE;
    }

}

