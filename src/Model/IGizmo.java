package Model;


import Physics.LineSegment;
import Physics.PhysicsCircle;

import java.awt.*;
import java.util.ArrayList;

public interface IGizmo {

    int getX();

    int getY();

    ArrayList<LineSegment> getLines();

    ArrayList<PhysicsCircle> getEndCircles();

    int getRotation();

    void rotate();

    Color getColour();
}
