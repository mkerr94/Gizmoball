package model;
import java.awt.*;

public class Absorber {

    private int width;
    private int height;
    private int xpos;
    private int ypos;
    private Color colour;

    public Absorber(int x, int y, int absHeight, int absWidth){
        xpos = x;
        ypos = y;
        height = absHeight;
        width = absWidth;
        colour = Color.PINK;
    }

    public int getX() {
        return xpos;
    }

    public int getY() {
        return ypos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColour() {
        return colour;
    }


}

