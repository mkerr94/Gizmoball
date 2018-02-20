package model;

import java.awt.*;

public class Gizmo implements IGizmo
{
    private int x;
    private int y;
    private int rotation;
    private Color colour;

    public Gizmo(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.rotation = 0;
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public int getRotation () {
        return rotation;
    }

    @Override
    public void rotate() {
        if(rotation == 3)
            rotation = 0;
        else
            rotation++;
    }

    @Override
    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }
}
