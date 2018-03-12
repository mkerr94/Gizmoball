package Model;

import Physics.LineSegment;
import java.util.ArrayList;
import java.util.Observable;

import Physics.PhysicsCircle;
import Physics.Geometry;
import Physics.LineSegment;
import Physics.Vect;

import java.util.*;

public class Model extends Observable {
    private static final int L = 25;
    private List<IGizmo> gizmos;
    private List<LineSegment> lineSegments;

    private ArrayList<VerticalLine> lines;
    private Ball ball;
    private Circle circleGizmo;
    private Walls gws;
    private ArrayList<Square> ls;
    private ArrayList<Triangle> tg;
    private Triangle triangleGizmo;
    private Square squareGizmo;

    public Model(){
        gizmos = new ArrayList<>();
        // Ball position (25, 25) in pixels. Ball velocity (100, 100) pixels per tick
        ball = new Ball(25, 25, 100, 100);

        // Wall size 500 x 500 pixels
        gws = new Walls(0, 0, 500, 500);

        circleGizmo = new Circle(150,50); //, 100,100

        squareGizmo = new Square(50, 300); //, 20

        triangleGizmo = new Triangle(275, 300); //, 20



        // Lines added in Main
        lines = new ArrayList<VerticalLine>();
        ls = new ArrayList<Square>();
        tg = new ArrayList<Triangle>();
    }

    public List<IGizmo> getGizmos(){
        return gizmos;
    }

    /***
     * Adds anything that implements the Gizmo interface to Model's collection of gizmos
     * @param gizmo Gizmo to add to the model's collection
     */
    public void addGizmo(IGizmo gizmo) {
        if (gizmo != null){
            gizmos.add(gizmo);
            setChanged();
            notifyObservers();
        }else{
            System.out.println("null gizmo");
        }
    }

    public void moveBall() {

        double moveTime = 0.05; // 0.05 = 20 times per second as per Gizmoball

        if (ball != null && !ball.stopped()) {

            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                // No collision ...
                ball = movelBallForTime(ball, moveTime);
            } else {
                // We've got a collision in tuc
                ball = movelBallForTime(ball, tuc);
                // Post collision velocity ...
                ball.setVelo(cd.getVelo());
            }

            // Notify observers ... redraw updated view
            this.setChanged();
            this.notifyObservers();
        }

    }

    private Ball movelBallForTime(Ball ball, double time) {

        double newX = 0.0;
        double newY = 0.0;
        double xVel = ball.getVelo().x();
        double yVel = ball.getVelo().y();
        newX = ball.getExactX() + (xVel * time);
        newY = ball.getExactY() + (yVel * time);
        ball.setExactX(newX);
        ball.setExactY(newY);
        return ball;
    }

    private CollisionDetails timeUntilCollision() {
        // Find Time Until Collision and also, if there is a collision, the new speed vector.
        // Create a physics.CircleGizmo from Ball
        PhysicsCircle ballCircle = ball.getCircle();
        Vect ballVelocity = ball.getVelo();
        Vect newVelo = new Vect(0, 0);

        PhysicsCircle gizmoCircle = circleGizmo.getCircle();

        // Now find shortest time to hit a vertical line or a wall line
        double shortestTime = Double.MAX_VALUE;
        double time = 0.0;

        // Time to collide with 4 walls
        ArrayList<LineSegment> lss = gws.getLineSegments();
        for (LineSegment line : lss) {
            time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
            }
        }

        // Time to collide with any vertical lines
		/*for (VerticalLine line : lines) {
			LineSegment ls = line.getLineSeg();
			time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(ls, ball.getVelo(), 1.0);
			}
		}*/
        ArrayList<LineSegment> ls = squareGizmo.getLines();
        for(LineSegment line : ls){
            time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
            }
        }

        ArrayList<PhysicsCircle> c = squareGizmo.getEndCircles();
        for(PhysicsCircle circle : c){
            time  = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectCircle(circle.getCenter(),ballCircle.getCenter(), ball.getVelo(), 1);
            }
        }

        ArrayList<LineSegment> tg = triangleGizmo.getLines();
        for(LineSegment line : tg){
            time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
            }
        }

        ArrayList<PhysicsCircle> ci = triangleGizmo.getEndCircles();
        for(PhysicsCircle circle : ci){
            time  = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectCircle(circle.getCenter(),ballCircle.getCenter(), ball.getVelo(), 1);
            }
        }

        if(circleGizmo != null){

            time = Geometry.timeUntilCircleCollision(gizmoCircle, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;

                newVelo = Geometry.reflectCircle(gizmoCircle.getCenter(),ballCircle.getCenter(), ball.getVelo(), 1);
            }
        }

        return new CollisionDetails(shortestTime, newVelo);
    }

    public Ball getBall() {
        return ball;
    }

    public Circle getCircleGizmo(){return circleGizmo;}

    public Square getSquareGizmo(){return squareGizmo;}

    public Triangle getTriangleGizmo(){return triangleGizmo;}

    public ArrayList<VerticalLine> getLines() {
        return lines;
    }

    public void addLine(VerticalLine l) {
        lines.add(l);
    }

    public void setBallSpeed(int x, int y) {
        ball.setVelo(new Vect(x, y));
    }

    public void clearGizmos() {
        gizmos.clear();
        setChanged();
        notifyObservers();
    }

}
