package Model;

import Physics.PhysicsCircle;
import Physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public abstract class Flipper implements IGizmo {
    private Color color;
    private int x, y;
    private double angle;
    private final double initialAngle;
    private final double finalAngle;
    private double angularMomentum;
    private int rotation;
    private int width;
    private int height;

    Flipper(int x, int y) {
        this.color = Color.black;
        angle = 0;
        initialAngle = (double) 0;
        finalAngle = 90;
        angularMomentum = 0;
        this.x = x;
        this.y = y;
        width= 15;
        height=30;
        getLines();
        getCircles();
        getEndCircles();
    }

    public ArrayList<LineSegment> getLines() {
        ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
        LineSegment l1 = new LineSegment(x, y, x, y + height);
        LineSegment l2 = new LineSegment(x + width, y, x + width, y + height);
        LineSegment l3 = new LineSegment(x, y + height, x + width, y + height);
        LineSegment l4 = new LineSegment(x, y, x + width, y);
        lines.add(l1);
        lines.add(l2);
        lines.add(l3);
        lines.add(l4);
        return lines;
    }

    public ArrayList<PhysicsCircle> getCircles() {
        ArrayList<PhysicsCircle> Circles = new ArrayList<PhysicsCircle>();
        PhysicsCircle c1 = new PhysicsCircle(x +7.5, y , 7.5);
        PhysicsCircle c2 = new PhysicsCircle(x+7.5, y+height, 7.5);
            return Circles;
        }

    public ArrayList<PhysicsCircle> getEndCircles() {
        ArrayList<PhysicsCircle> endCircles = new ArrayList<PhysicsCircle>();
        PhysicsCircle c1 = new PhysicsCircle(x, y + height, 0);
        PhysicsCircle c2 = new PhysicsCircle(x, y, 0);
        PhysicsCircle c3 = new PhysicsCircle(x + width, y, 0);
        PhysicsCircle c4 = new PhysicsCircle(x + width, y + height, 0);
        PhysicsCircle c5 = new PhysicsCircle(x, y , 0);
        PhysicsCircle c6 = new PhysicsCircle(x, y + height, 0);
        PhysicsCircle c7 = new PhysicsCircle(x + width, y, 0);
        PhysicsCircle c8 = new PhysicsCircle(x + width, y + height, 0);
        endCircles.add(c1);
        endCircles.add(c2);
        endCircles.add(c3);
        endCircles.add(c4);
        endCircles.add(c5);
        endCircles.add(c6);
        endCircles.add(c7);
        endCircles.add(c8);
        return endCircles;
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

    @Override
    public void setX(int x){
        this.x = x;
    }

    @Override
    public void setY(int y){
        this.y = y;
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

    @Override
    public Color getColour(){
        return color;
    }

    public int getRotation(){
        return rotation;
    }


}
