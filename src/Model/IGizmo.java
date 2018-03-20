package Model;

import Physics.LineSegment;
import Physics.PhysicsCircle;

import java.awt.*;
import java.util.ArrayList;

public interface IGizmo {

    int getX1();

    int getY1();

    void setX1(int x1);

    void setY1(int y1);

    ArrayList<LineSegment> getLines();

    ArrayList<PhysicsCircle> getEndCircles();

    int getRotation();

    void rotate();

    Color getColour();

}
