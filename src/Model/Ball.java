package Model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball implements IGizmo{

    private int x, y, v;

    public Ball(int x, int y, int v){
        this.x = x;
        this.y = y;
        this.v = v;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public void rotate() {

    }

    @Override
    public void move(int newX, int newY) {

    }

    @Override
    public int getRotation() {
        return 0;
    }

    public int getV(){
        return v;
    }

    public Ellipse2D makeBall(){
       return new Ellipse2D.Double(x, y, 10,2);
    }
}
