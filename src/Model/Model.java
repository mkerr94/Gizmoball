package Model;

import Physics.LineSegment;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import Physics.PhysicsCircle;
import Physics.Geometry;
import Physics.Vect;
import sun.plugin2.message.GetAppletMessage;

import javax.sound.sampled.Line;


public class Model extends Observable {
    private List<IGizmo> gizmos;
    private Ball ball;
    private Walls walls;
    private final int L = 30;

    public Model() {
        gizmos = new ArrayList<>();
        ball = new Ball(30, 30, 50, 50);
    }

    public List<IGizmo> getGizmos() {
        return gizmos;
    }

    public void setWalls(Walls walls) {
        this.walls = walls;
    }

    public void moveBall() {
        double moveTime = 0.05; // 0.05 = 20 times per second as per Gizmoball
        if (ball != null && !ball.stopped()) {
            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                // No collision ...
                ball = moveBallForTime(ball, moveTime);
            } else {
                // We've got a collision in tuc
                ball = moveBallForTime(ball, tuc);
                // Post collision velocity ...
                ball.setVelo(cd.getVelo());
            }
            // Notify observers ... redraw updated view
            // todo apply gravity and friction
            this.setChanged();
            this.notifyObservers();
        }
    }

    private void applyFriction(double time) {
        double mu = 0.025;
        double mu2 = 0.025 / L;

        double vOldX = ball.getVelo().x();
        double vOldY = ball.getVelo().y();

        double vNewX = vOldX * (1 - (mu * time) - (mu2 * vOldX) * time);
        double vNewY = vOldY * (1 - (mu * time) - (mu2 * vOldY) * time);

        Vect vNew = new Vect(vNewX, vNewY);

        ball.setVelo(vNew);

    }

    private void applyGravity(double time) {
        Vect gravityAlteredVelocity = new Vect(ball.getVelo().x(), (ball.getVelo().y() + (25 * L * time)));
        ball.setVelo(gravityAlteredVelocity);
    }

    private Ball moveBallForTime(Ball ball, double time) {
        double newX;
        double newY;
        double xVel = ball.getVelo().x();
        double yVel = ball.getVelo().y();
        newX = ball.getExactX() + (xVel * time);
        newY = ball.getExactY() + (yVel * time);
        ball.setExactX(newX);
        ball.setExactY(newY);
        applyGravity(time);
        applyFriction(time);
        return ball;
    }

    private CollisionDetails timeUntilCollision() {
        // Find Time Until Collision and also, if there is a collision, the new speed vector.
        // Create a physics.CircleGizmo from Ball
        PhysicsCircle ballCircle = ball.getCircle();
        Vect ballVelocity = ball.getVelo();
        Vect newVelo = new Vect(0, 0);
        ArrayList<LineSegment> collisionLines;
        ArrayList<PhysicsCircle> collisionCircles;

        // Now find shortest time to hit a vertical line or a wall line
        double shortestTime = Double.MAX_VALUE;
        double time = 0.0;

        // Time to collide with 4 walls
        ArrayList<LineSegment> lineSegments = walls.getLineSegments();
        for (LineSegment lineSegment : lineSegments) {
            time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectWall(lineSegment, ball.getVelo(), 1.0);
            }
        }

        // Handle gizmo collisions
        for (IGizmo gizmo : gizmos) {
            // Circle collisions
            if (gizmo instanceof Circle) {
                Circle circle = new Circle(gizmo.getX() * L, gizmo.getY() * L);
                PhysicsCircle physicsCircle = circle.getCircle();
                time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1);
                }
            }
            // Square collisions
            if (gizmo instanceof Square) {
                Square square = new Square(gizmo.getX() * L, gizmo.getY() * L);
                ArrayList<LineSegment> lineSegments1 = square.getLines();
                for (LineSegment lineSegment : lineSegments1) {
                    time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectWall(lineSegment, ball.getVelo(), 1.0);
                    }
                }
                ArrayList<PhysicsCircle> physicsCircles = square.getEndCircles();
                for (PhysicsCircle physicsCircle : physicsCircles) {
                    time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1.0);
                    }
                }
            }
            // Triangle collisions
            if (gizmo instanceof Triangle) {
                Triangle triangle = new Triangle(gizmo.getX() * L, gizmo.getY() * L);
                ArrayList<LineSegment> lineSegments1 = triangle.getLines();
                for (LineSegment lineSegment : lineSegments1) {
                    time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectWall(lineSegment, ball.getVelo(), 1.0);
                    }
                }
                ArrayList<PhysicsCircle> physicsCircles = triangle.getEndCircles();
                for (PhysicsCircle physicsCircle : physicsCircles) {
                    time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1.0);
                    }
                }
            }
        }
        return new CollisionDetails(shortestTime, newVelo);
    }

    public Ball getBall() {
        return ball;
    }

    /***
     * Adds anything that implements the Gizmo interface to Model's collection of gizmos
     * @param gizmo Gizmo to add to the model's collection
     */
    public void addGizmo(IGizmo gizmo) {
        if (gizmo != null) {
            gizmos.add(gizmo);
            setChanged();
            notifyObservers();
        } else {
            throw new NullPointerException("Null Gizmo when calling addGizmo()");
        }
    }

    /**
     * Checks if a gizmo already exists at the location of the passed in gizmo.
     * Returns false if a gizmo already exists at the target location and returns true
     * if nothing exists at the target location
     *
     * @param gizmo gizmo to be added to the board
     * @return true if valid placement, false if invalid placement
     */
    public boolean checkGizmoLocation(IGizmo gizmo) {
        for (IGizmo iGizmo : gizmos) {
            if (iGizmo.getX() == gizmo.getX() && iGizmo.getY() == gizmo.getY()) { // if a gizmo already exists in that location
                return false;
            }
        }
        return true;
    }

    /***
     * Empties the collection of gizmos and notifies the view,
     * so that when it repaints there is nothing to paint.
     */
    public void clearGizmos() {
        gizmos.clear();
        setChanged();
        notifyObservers();
    }

    public void deleteGizmo(int x, int y) {
        for (IGizmo iGizmo : gizmos) {
            if (iGizmo.getX() == x && iGizmo.getY() == y) {
                gizmos.remove(iGizmo);
                setChanged();
                notifyObservers();
                break;
            }
        }
    }
}
