package Model;

import Physics.Circle;
import Physics.LineSegment;
import java.util.ArrayList;

public interface IGizmo {
    void addGizmo();
    int getX();
    int getY();
    ArrayList<LineSegment> initLines();
    ArrayList<Circle> initCircles();
}
