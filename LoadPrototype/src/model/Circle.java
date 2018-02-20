package model;

import java.awt.*;

public class Circle extends Gizmo implements IGizmo {
    private Color colour;

    public Circle(int x, int y) {
        super(x, y);
        colour = Color.GREEN;
    }

    public Color getColour() {
        return colour;
    }

}

