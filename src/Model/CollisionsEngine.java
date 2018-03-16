package Model;

import Physics.Geometry;
import Physics.LineSegment;
import Physics.PhysicsCircle;
import Physics.Vect;

import java.util.ArrayList;
import java.util.List;

class CollisionsEngine {
    private Model model;
    private List<Ball> balls;
    private List<IGizmo> gizmos;
    private Walls walls;
    private int L = 30;

    CollisionsEngine(Model model){
        this.model = model;
        balls = model.getBalls();
        walls = model.getWalls();
        gizmos = model.getGizmos();
    }

    CollisionDetails timeUntilCollision(Ball ball) {
        // Find Time Until Collision and also, if there is a collision, the new speed vector.
        // Create a physics.CircleGizmo from Ball

        PhysicsCircle ballCircle = ball.getCircle();
        Vect ballVelocity = ball.getVelo();
        Vect newVelo = new Vect(0, 0);

        // Now find shortest time to hit a vertical line or a wall line
        double shortestTime = Double.MAX_VALUE;
        double time;

        // Time to collide with 4 walls
        ArrayList<LineSegment> lineSegments = walls.getLineSegments();
        for (LineSegment lineSegment : lineSegments) {
            time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectWall(lineSegment, ball.getVelo(), 1.0);
            }
        }

        for (Ball b: balls) {
            Ball b2 = new Ball(b.getExactX() * L, b.getExactY() * L,50,50);
            PhysicsCircle physicsCircle = b2.getCircle();
            time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), b.getVelo(), 1);
            }
        }

        // Handle gizmo collisions
        for (IGizmo gizmo : gizmos) {
            if(gizmo instanceof LeftFlipper) {
                LeftFlipper flipper = new LeftFlipper(gizmo.getX() * L, gizmo.getY() * L);
                ArrayList<LineSegment> lineSegments1 = flipper.getLines();
                for (LineSegment lineSegment : lineSegments1) {
                    time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectWall(lineSegment, ball.getVelo(), 1.0);

                    }
                }
                ArrayList<PhysicsCircle> physicsCircles = flipper.getEndCircles();
                for (PhysicsCircle physicsCircle : physicsCircles) {
                    time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1.0);
                    }

                }
                ArrayList<PhysicsCircle> physicsCircles2 = flipper.getCircles();
                Circle circle = new Circle((gizmo.getX() * L) + 15, (gizmo.getY() * L)+ 15);
                for(PhysicsCircle physicsCircle : physicsCircles2){
                    time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1);
                    }}
            }

            if(gizmo instanceof RightFlipper){
                RightFlipper flipper = new RightFlipper(gizmo.getX() * L, gizmo.getY() * L);
                ArrayList<LineSegment> lineSegments1 = flipper.getLines();
                for (LineSegment lineSegment : lineSegments1) {
                    time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectWall(lineSegment, ball.getVelo(), 1.0);

                    }
                }
                ArrayList<PhysicsCircle> physicsCircles = flipper.getEndCircles();
                for (PhysicsCircle physicsCircle : physicsCircles) {
                    time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1.0);
                    }

                }
                ArrayList<PhysicsCircle> physicsCircles2 = flipper.getCircles();
                Circle circle = new Circle((gizmo.getX() * L) + 15, (gizmo.getY() * L)+ 15);
                for(PhysicsCircle physicsCircle : physicsCircles2){
                    time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1);
                    }}

            }
            // Circle collisions
            if (gizmo instanceof Circle) {
                Circle circle = new Circle((gizmo.getX() * L) + 15, (gizmo.getY() * L)+ 15);
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
            // Triangle collisions todo handle rotated triangle collisions
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
            // Absorber collisions todo make sure absorber functions properly
            if (gizmo instanceof Absorber) {
                for (Ball b : balls) {
                    Absorber absorber = new Absorber(gizmo.getX() * L, gizmo.getY() * L, ((Absorber) gizmo).getWidth() * L, ((Absorber) gizmo).getHeight() * L);
                    LineSegment lineSegment = absorber.getCollisionLine();
                    time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectWall(lineSegment, b.getVelo(), 1.0);
                    }
                    model.captureBallsInAbsorber(time, ball, absorber);
                }
            }

        }
        return new CollisionDetails(shortestTime, newVelo);
    }
}
