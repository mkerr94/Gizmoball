package Model;

import Physics.PhysicsCircle;
import Physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public abstract class Flipper extends Observable implements IGizmo {
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
        rotation = 0;
        initialAngle = (double) 0;
        finalAngle = 90;
        angularMomentum = 0;
        this.x = x;
        this.y = y;
        width= 15;
        height=60;
        getLines();
        getCircles();
        getEndCircles();
    }

    public ArrayList<LineSegment> getLines() {
        ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
        LineSegment l1 = new LineSegment(x, y+7.5, x, y + height-7.5);
        LineSegment l2 = new LineSegment(x + width, y+7.5, x + width, y + height-7.5);
        LineSegment l3 = new LineSegment(x, y + height -7.5, x + width, y + height-7.5);
        LineSegment l4 = new LineSegment(x, y+7.5, x + width, y+7.5);
        lines.add(l1);
        lines.add(l2);
        lines.add(l3);
        lines.add(l4);
        return lines;
    }

    public ArrayList<PhysicsCircle> getCircles() {
        ArrayList<PhysicsCircle> Circles = new ArrayList<PhysicsCircle>();
        PhysicsCircle c1 = new PhysicsCircle(x +7.5, y+7.5 , 7.5);
        PhysicsCircle c2 = new PhysicsCircle(x+7.5, y+height-7.5, 7.5);
            return Circles;
        }

    public ArrayList<PhysicsCircle> getEndCircles() {
        ArrayList<PhysicsCircle> endCircles = new ArrayList<PhysicsCircle>();
        PhysicsCircle c1 = new PhysicsCircle(x, y + height-7.5, 0);
        PhysicsCircle c2 = new PhysicsCircle(x, y+7.5, 0);
        PhysicsCircle c3 = new PhysicsCircle(x + width, y+7.5, 0);
        PhysicsCircle c4 = new PhysicsCircle(x + width, y + height -7.5, 0);
        PhysicsCircle c5 = new PhysicsCircle(x, y+7.5 , 0);
        PhysicsCircle c6 = new PhysicsCircle(x, y + height-7.5, 0);
        PhysicsCircle c7 = new PhysicsCircle(x + width, y+7.5, 0);
        PhysicsCircle c8 = new PhysicsCircle(x + width, y + height -7.5 , 0);
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
        angularMomentum = 54;
        setChanged();
        notifyObservers();
    }

    public void unflip() {
        angularMomentum = -54;
        setChanged();
        notifyObservers();
    }

    @Override
    public void rotate(){
        System.out.println("Flipper.rotate");
        rotation ++;
        if (rotation > 3) {
            rotation = 0;
        }
    }

    @Override
    public int getX1() {
        return x;
    }

    @Override
    public int getY1() {
        return y;
    }

    @Override
    public void setX1(int x1){
        this.x = x1;
    }

    @Override
    public void setY1(int y1){
        this.y = y1;
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

    @Override
    public Color getColour(){
        return color;
    }

    public int getRotation(){
        return rotation;
    }


}
