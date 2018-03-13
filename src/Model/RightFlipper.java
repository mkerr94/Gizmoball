package Model;

import Physics.PhysicsCircle;

import java.awt.*;
import java.util.ArrayList;

public class RightFlipper extends Flipper {

    public RightFlipper(int x, int y) {
        super(x, y);
    }

    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public void rotate() {

    }

    @Override
    public Color getColour() {
        return null;
    }

    @Override
    public ArrayList<PhysicsCircle> getEndCircles() {
        return null;
    }
}
