package Model;

import Physics.Circle;
import Physics.LineSegment;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Flipper extends Observable implements IGizmo {
    private ArrayList<LineSegment> lineSegments;
    private ArrayList<Circle> collisionCircles;
    private int x, y, height, width;
    private double angle;
    private final double initialAngle;
    private final double finalAngle;
    private double angularMomentum;

    Flipper(int x, int y) {
        angle = 0;
        initialAngle = (double) 0;
        finalAngle = 90;
        angularMomentum = 0;
        this.width = 25;
        this.height = 100;
        this.x = x;
        this.y = y;
        lineSegments = initLines();
        collisionCircles = initCircles();
    }

    private ArrayList<LineSegment> initLines() {
        ArrayList<LineSegment> arrayList = new ArrayList<>();
        LineSegment left = new LineSegment(x, y, x, y + height);
        LineSegment top = new LineSegment(x, y, x + width, y);
        LineSegment right = new LineSegment(x + width, y, x + width, y + width);
        LineSegment bottom = new LineSegment(x, y + height, x + width, y + height);
        arrayList.add(left);
        arrayList.add(top);
        arrayList.add(right);
        arrayList.add(bottom);
        return arrayList;
    }

    private ArrayList<Circle> initCircles() {
        ArrayList<Circle> arrayList = new ArrayList<>();
        Circle topLeft = new Circle(x, y, 0);
        Circle botLeft = new Circle(x, y + height, 0);
        Circle topRight = new Circle(x + width, y, 0);
        Circle botRight = new Circle(x + width, y + height, 0);
        arrayList.add(topLeft);
        arrayList.add(botLeft);
        arrayList.add(topRight);
        arrayList.add(botRight);
        return arrayList;
    }

    public void flip() {
        angularMomentum = 5;
        setChanged();
        notifyObservers();
    }

    public void unflip() {
        angularMomentum = -5;
        setChanged();
        notifyObservers();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
        if (angle >= getFinalAngle()) {
            this.angle = getFinalAngle();
            angularMomentum = 0;
        } else if (angle <= getInitialAngle()) {
            this.angle = getInitialAngle();
            angularMomentum = 0;
        }
        setChanged();
        notifyObservers();
    }

    private double getInitialAngle() {
        return initialAngle;
    }

    private double getFinalAngle() {
        return finalAngle;
    }

    public double getAngularMomentum() {
        return angularMomentum;
    }

    public ArrayList<LineSegment> getLines() {
        return lineSegments;
    }

    public ArrayList<Circle> getCollisionCircles() {
        return collisionCircles;
    }


}
