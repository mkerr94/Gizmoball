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

        if(balls.size() > 1){
        for (Ball ball2: balls) {
            Ball b2 = new Ball(ball2.getExactX() * L, ball2.getExactY() * L, ball2.getVelo().x(), ball2.getVelo().y());
            if(ball2 != ball){
            PhysicsCircle physicsCircle = b2.getCircle();
            time = Geometry.timeUntilBallBallCollision( ballCircle, ballVelocity, physicsCircle, b2.getVelo());
            if (time < shortestTime) {
                shortestTime = time;
              newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1);
               // newVelo = Geometry.reflectBalls(physicsCircle.getCenter(),1, b2.getVelo(),  ballCircle.getCenter(),1, ball.getVelo());
            }}}
        }

        // Handle gizmo collisions
        for (IGizmo gizmo : gizmos) {
            if(gizmo instanceof LeftFlipper) {
                LeftFlipper flipper = new LeftFlipper(gizmo.getX1() * L, gizmo.getY1() * L);
                ArrayList<LineSegment> lineSegments1 = flipper.getLines();
                for (LineSegment lineSegment : lineSegments1) {
                    time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectWall(lineSegment, ball.getVelo(), 1.0);
                    }
                }
               /* for (LineSegment rotatingLine : lineSegments1) {
                    Vect vect = new Vect(flipper.getX1() + L/2 + L/4, flipper.getY1() + L/4);
                    time = Geometry.timeUntilRotatingWallCollision(rotatingLine, vect, -Math.toRadians(1080), ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectRotatingWall(rotatingLine, vect, -Math.toRadians(1080), ballCircle, ball.getVelo());
                    }
                }*/
                ArrayList<PhysicsCircle> physicsCircles = flipper.getEndCircles();
                for (PhysicsCircle physicsCircle : physicsCircles) {
                    time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1.0);
                    }

                }
                ArrayList<PhysicsCircle> physicsCircles2 = flipper.getCircles();
                Circle circle = new Circle((gizmo.getX1() * L) + 15, (gizmo.getY1() * L)+ 15);
                for(PhysicsCircle physicsCircle : physicsCircles2){
                    time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1);
                    }}
            }
            if(gizmo instanceof RightFlipper){
                RightFlipper flipper = new RightFlipper((gizmo.getX1() * L) + 15, gizmo.getY1() * L);
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
               // Circle circle = new Circle((gizmo.getX1() * L) + 15, (gizmo.getY1() * L)+ 15);
                for(PhysicsCircle physicsCircle : physicsCircles2){
                    time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        shortestTime = time;
                        newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1);
                    }}

            }
            // Circle collisions
            if (gizmo instanceof Circle) {
                Circle circle = new Circle((gizmo.getX1() * L) + 15, (gizmo.getY1() * L)+ 15);
                PhysicsCircle physicsCircle = circle.getCircle();
                time = Geometry.timeUntilCircleCollision(physicsCircle, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectCircle(physicsCircle.getCenter(), ballCircle.getCenter(), ball.getVelo(), 1);
                }
            }
            // Square collisions
            if (gizmo instanceof Square) {
                Square square = new Square(gizmo.getX1() * L, gizmo.getY1() * L);
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
                Triangle triangle = new Triangle(gizmo.getX1() * L, gizmo.getY1() * L, gizmo.getRotation());
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
            // Absorber collisions
            if (gizmo instanceof Absorber) {
                for (Ball b : balls) {
                    Absorber absorber = new Absorber(gizmo.getX1() * L, gizmo.getY1() * L, ((Absorber) gizmo).getX2() * L, ((Absorber) gizmo).getY2() * L);
                    LineSegment lineSegment = absorber.getCollisionLine();
                    time = Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
                    if (time < shortestTime) {
                        System.out.println("absorber collision");
                        shortestTime = time;
                        newVelo = Geometry.reflectWall(lineSegment, b.getVelo(), 1.0);
                        model.captureBallsInAbsorber(time, ball, absorber);
                    }
                }
            }

        }
        return new CollisionDetails(shortestTime, newVelo);
    }
}
