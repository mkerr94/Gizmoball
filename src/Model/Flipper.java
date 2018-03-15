package Model;

import Physics.PhysicsCircle;
import Physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public abstract class Flipper implements IGizmo {
    private ArrayList<LineSegment> lineSegments;
    private ArrayList<PhysicsCircle> collisionCircles;
    private Color color;
    private int x, y;
    private double angle;
    private final double initialAngle;
    private final double finalAngle;
    private double angularMomentum;
    private int rotation;

    Flipper(int x, int y) {
        this.color = Color.black;
        angle = 0;
        initialAngle = (double) 0;
        finalAngle = 90;
        angularMomentum = 0;
        this.x = x;
        this.y = y;
        lineSegments = initLines();
        collisionCircles = initCircles();
    }

    private ArrayList<LineSegment> initLines() {
        return null;
    }

    private ArrayList<PhysicsCircle> initCircles() {
        return null;
    }

    public void flip() {
        angularMomentum = 5;
    }

    public void unflip() {
        angularMomentum = -5;
    }

    @Override
    public void rotate(){
        if (rotation == 0){
            rotation = 1;
        } else if (rotation == 1){
            rotation = 2;
        } else if (rotation == 2){
            rotation = 3;
        } else if (rotation == 3){
            rotation = 0;
        }
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

    @Override
    public ArrayList<PhysicsCircle> getEndCircles(){
        return collisionCircles;
    }

    @Override
    public Color getColour(){
        return color;
    }

    public int getRotation(){
        return rotation;
    }


}
