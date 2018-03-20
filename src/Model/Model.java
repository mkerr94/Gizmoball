package Model;

import Physics.Vect;

import java.util.*;

public class Model extends Observable {
    private List<IGizmo> gizmos;
    private List<Ball> balls;
    private List<Ball> fireQueue;
    private List<Flipper> flippers;
    private Walls walls;
    private final int L = 30;
    private int gravityValue;
    private double frictionValue;
    private CollisionsEngine collisionsEngine;
    private Map<Integer, IGizmo> keyConnections;

    public Model() {
        gizmos = new ArrayList<>();
        balls = new ArrayList<>();
        flippers = new ArrayList<>();
        fireQueue = new ArrayList();
        walls = new Walls(0, 0, 600, 600);
        gravityValue = 25;
        frictionValue = 0.025;
        collisionsEngine = new CollisionsEngine(this);
        keyConnections = new HashMap<>();
    }

    public List<IGizmo> getGizmos() {
        return gizmos;
    }

    Walls getWalls() {
        return walls;
    }

    public void setWalls(Walls walls) {
        this.walls = walls;
    }

    public void setGravityValue(int gravityValue) {
        this.gravityValue = gravityValue;
    }

    public void setFrictionValue(double frictionValue) {
        this.frictionValue = frictionValue;
    }

    /***
     * This method is called inside the run listener for every timer tick.
     * This is the main physics loop - checks for collisions using the collisions engine
     * and either handles the collision or moves the ball in the usual fashion.
     * See CollisionsEngine.timeUntilCollision and this.moveBallForTime
     */
    public void moveBall() {
        double moveTime = 0.05; // 0.05 = 20 times per second as per Gizmoball
        for (Ball b : balls) {
            if (b != null && !b.stopped()) {
                CollisionDetails cd = collisionsEngine.timeUntilCollision(b);
                double tuc = cd.getTuc();
                if (tuc > moveTime) {
                    // No collision ...
                    b = moveBallForTime(b, moveTime);
                } else {
                    // We've got a collision in tuc
                    b = moveBallForTime(b, tuc);
                    // Post collision velocity ...
                    b.setVelo(cd.getVelo());
                }
                // Notify observers ... redraw updated view
                this.setChanged();
                this.notifyObservers();
            }
        }
        applyGravity(moveTime);
        applyFriction(moveTime);
    }

    /***
     * Moves a given ball for a given period of time
     * @param ball the ball to move
     * @param time the time to move the ball for. Should be 0.05 according to spec
     * @return the ball with an updated position according to its velocity and the time to move for
     */
    private Ball moveBallForTime(Ball ball, double time) {
        double newX;
        double newY;
        double xVel = ball.getVelo().x();
        double yVel = ball.getVelo().y();
        newX = ball.getExactX() + (xVel * time);
        newY = ball.getExactY() + (yVel * time);
        ball.setExactX(newX);
        ball.setExactY(newY);
        return ball;
    }

    /***
     * Applies friction to the velocity of the ball so it slows down
     * over time. Simulates friction of a pinball machine.
     * @param time How often the timer updates. Aim for 20 times per second (0.05)
     */
    private void applyFriction(double time) {
        for (Ball ball : balls) {
            double mu = 0.025;
            double mu2 = frictionValue / L;
            double vxOld = ball.getVelo().x();
            double vyOld = ball.getVelo().y();
            double vxNew = vxOld * (1 - (mu * time) - (mu2 * vxOld) * time);
            double vyNew = vyOld * (1 - (mu * time) - (mu2 * vyOld) * time);
            Vect vNew = new Vect(vxNew, vyNew);
            ball.setVelo(vNew);
        }
    }

    /***
     * Applies gravity to the y component of the ball's velocity, such that the y component
     * slows down over time.
     * @param time How often the timer updates. Aim for 20 times per second (0.05)
     */
    private void applyGravity(double time) {
        for (Ball ball : balls) {
            Vect gravityAlteredVelocity = new Vect(ball.getVelo().x(), (ball.getVelo().y() + (gravityValue * L * time)));
            ball.setVelo(gravityAlteredVelocity);
        }
    }

    /***
     * Adds anything that implements the Gizmo interface to Model's collection of gizmos
     * @param gizmo Gizmo to add to the model's collection
     */
    public void addGizmo(IGizmo gizmo) {
        if (gizmo != null) {
            gizmos.add(gizmo);
            if (gizmo instanceof Flipper) {
                flippers.add((Flipper)gizmo);
            }
            setChanged();
            notifyObservers();
        } else {
            throw new NullPointerException("Null Gizmo when calling addGizmo()");
        }
    }

    /***
     * Adds a new ball to the collection of balls
     * @param x x-ordinate of ball to add
     * @param y y-ordinate of ball to ad
     */
    public void addBall(double x, double y, double xv, double yv) {
        Ball ball = new Ball(x, y, xv, yv);
        balls.add(ball);
        setChanged();
        notifyObservers();
    }

    /**
     * Checks if a gizmo already exists at the location of the passed in gizmo.
     * Returns false if a gizmo already exists at the target location and returns true
     * if nothing exists at the target location
     *
     * @param gizmoToAdd gizmo to be added to the board
     * @return true if valid placement, false if invalid placement
     */
    public boolean checkGizmoLocation(IGizmo gizmoToAdd) {
        // flippers at the edge of the gameboard
        if (gizmoToAdd instanceof LeftFlipper) {
            if (gizmoToAdd.getX1() == 19) {
                return false;
            }
        }
        if (gizmoToAdd instanceof RightFlipper) {
            if (gizmoToAdd.getX1() == 0) {
                return false;
            }
        }
        // handle basic gizmos
        for (IGizmo existingGizmo : gizmos) {
            if (existingGizmo.getX1() == gizmoToAdd.getX1() && existingGizmo.getY1() == gizmoToAdd.getY1()) { // if a gizmo already exists in that location
                return false;
            }
            // handle left flippers
            if (gizmoToAdd instanceof LeftFlipper) {
                if (existingGizmo.getX1() - 1 == gizmoToAdd.getX1() && existingGizmo.getY1() == gizmoToAdd.getY1()) {
                    return false;
                } else if (existingGizmo.getX1() - 1 == gizmoToAdd.getX1() && existingGizmo.getY1() - 1 == gizmoToAdd.getY1()) {
                    return false;
                }
            }
            // handle right flippers
            if (gizmoToAdd instanceof RightFlipper) {
                if (existingGizmo.getX1() + 1 == gizmoToAdd.getX1() && existingGizmo.getY1() == gizmoToAdd.getY1()) {
                    return false;
                } else if (existingGizmo.getX1() + 1 == gizmoToAdd.getX1() && existingGizmo.getY1() - 1 == gizmoToAdd.getY1()) {
                    return false;
                }
            }
            // ensure gizmos aren't placed within the range of a rotating flipper
            if (existingGizmo instanceof LeftFlipper) {
                if (existingGizmo.getX1() + 1 == gizmoToAdd.getX1() && existingGizmo.getY1() == gizmoToAdd.getY1()) {
                    return false;
                } else if (existingGizmo.getX1() + 1 == gizmoToAdd.getX1() && existingGizmo.getY1() + 1 == gizmoToAdd.getY1()) {
                    return false;
                }
                if (gizmoToAdd instanceof RightFlipper) {
                    if (existingGizmo.getX1() + 2 == gizmoToAdd.getX1() && existingGizmo.getY1() == gizmoToAdd.getY1()) {
                        return false;
                    } else if (existingGizmo.getX1() + 2 == gizmoToAdd.getX1() && existingGizmo.getY1() + 1 == gizmoToAdd.getY1()) {
                        return false;
                    }
                }
            }
            if (existingGizmo instanceof RightFlipper) {
                if (existingGizmo.getX1() - 1 == gizmoToAdd.getX1() && existingGizmo.getY1() == gizmoToAdd.getY1()) {
                    return false;
                } else if (existingGizmo.getX1() - 1 == gizmoToAdd.getX1() && existingGizmo.getY1() + 1 == gizmoToAdd.getY1()) {
                    return false;
                }
                if (gizmoToAdd instanceof LeftFlipper) {
                    if (existingGizmo.getX1() - 2 == gizmoToAdd.getX1() && existingGizmo.getY1() == gizmoToAdd.getY1()) {
                        return false;
                    } else if (existingGizmo.getX1() - 2 == gizmoToAdd.getX1() && existingGizmo.getY1() + 1 == gizmoToAdd.getY1()) {
                        return false;
                    }
                }

            }
            //todo Get functionality of adding absorbers
            // handle absorbers
            if (gizmoToAdd instanceof Absorber) {
                if (existingGizmo.getX1() >= gizmoToAdd.getX1() && existingGizmo.getY1() >= gizmoToAdd.getY1()) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * Checks if gizmo already exists at the x and y location passed in.
     *
     * @param x
     * @param y
     * @return true if valid placement, false if invalid placement
     */
    public boolean checkGizmoLocation(int x, int y) {
        for (IGizmo iGizmo : gizmos) {
            if (iGizmo.getX1() == x && iGizmo.getY1() == y) {
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
        balls.clear();
        setChanged();
        notifyObservers();
    }

    /***
     * Deletes the gizmo or ball at the given location
     * @param x x ordinate of target gizmo/ball
     * @param y y ordinate of target gizmo/ball
     */
    public void deleteGizmoOrBall(int x, int y) {
        for (IGizmo iGizmo : gizmos) {
            if (iGizmo.getX1() == x && iGizmo.getY1() == y) {
                gizmos.remove(iGizmo);
                setChanged();
                notifyObservers();
                break;
            }

        }
        for (Ball ball : balls) {
            if (ball.getxOrdinate() / 30 == x && ball.getyOrdinate() / 30 == y) {
                balls.remove(ball);
                setChanged();
                notifyObservers();
                break;
            }
        }
    }

    /***
     * Rotates a gizmo around its square
     * @param gizmo the gizmo to rotate. Must be either a triangle or flipper to rotate
     */
    public void rotateGizmo(IGizmo gizmo) {
        if (gizmo instanceof Triangle) {
            gizmo.rotate();
            setChanged();
            notifyObservers();
        } else if (gizmo instanceof Flipper) {
            //todo rotate flipper
            System.out.println("rotate flipper");
        } else {
            System.out.println("cannot rotate this gizmo");
        }
    }

    /***
     * Returns the gizmo at the given location if it exists
     * @param x x ordinate of location of target gizmo
     * @param y y ordinate of location of target gizmo
     * @return the gizmo at the target location
     */
    public IGizmo getGizmo(int x, int y) {
        for (IGizmo iGizmo : gizmos) {
            if (iGizmo.getX1() == x && iGizmo.getY1() == y) {
                return iGizmo;
            }
        }
        return null;
    }

    public Flipper getFlipper(int x, int y){
        for(Flipper flipper : flippers){
            if(flipper.getX1() == x && flipper.getY1() == y){
                return flipper;
            }
        }
        return null;
    }

    /***
     * Method that's called inside CollisionsEngine.timeUntilCollision which handles the
     * behaviour of a ball colliding with an absorber.
     * @param time the tick time, should be 0.05 according to spec
     * @param ball the ball to handle
     * @param absorber the absorber to handle
     */
    void captureBallsInAbsorber(double time, Ball ball, Absorber absorber) {
        for (Ball b : balls) {
            if (time <= 0.1 && !ball.stopped()) {
                //ball = new Ball(absorber.getX2() - 1 * L, absorber.getY2() - 0.5 * L, -10 * L, -10 * L);
                System.out.println("Ball hit absorber");
                b.stop();
                b.setExactX(absorber.getX2() - b.getRadius());
                b.setExactY(absorber.getY1());
                fireQueue.add(b);
                //balls.remove(b);
                System.out.println("Balls to be fired" + fireQueue.size());
                this.setChanged();
                this.notifyObservers();
            }
        }
    }

    /***
     * Fires the ball from an absorber. This is called inside the RunListener when
     * the user triggers the fire option.
     */
    public void fireBall() {
        for (Ball ball : balls) {
            if (ball.stopped()) {
                ball.start();
                fireQueue.remove(ball);
                Vect ballFire = new Vect(0, -30 * L);
                ball.setVelo(ballFire);
            }
        }
    }

    public void resetBall() {
        for (Ball ball : balls) {
            ball = new Ball(30, 30, 50, 50);
        }
    }

    /***
     * Moves a given gizmo to a new location. First checks if a gizmo already exists
     * in the target location.
     * @param gizmo Gizmo you're trying to move
     * @param newX x-ordinate of the target destination
     * @param newY y-ordinate of the target destination
     */
    public void moveGizmo(IGizmo gizmo, int newX, int newY) {
        //todo Fix move gizmo over new gizmos (Absorber and flipper)
        IGizmo iGizmo = getGizmo(newX, newY);
        if (iGizmo == null) {
            gizmo.setX1(newX);
            gizmo.setY1(newY);
            setChanged();
            notifyObservers();
        } else {
            throw new NullPointerException("Null gizmo moved to moveGizmo");
        }
    }

    public void moveFlipper(Flipper flipper, int newX, int newY){
        Flipper f = getFlipper(newX,newY);
        if(f == null){
            flipper.setX1(newX);
            flipper.setY1(newY);
            setChanged();
            notifyObservers();
        }
        else{
            throw new NullPointerException("Null flipper moved to moveFlipper");
        }
    }

    /***
     * connects a keyCode to a gizmo
     * @param keyCode keycode that will trigget the gizmo
     * @param gizmoToConnect gizmo to be connected to the keycode
     */
    public void addKeyConnection(int keyCode, IGizmo gizmoToConnect) {
        if (gizmoToConnect != null) {
            keyConnections.put(keyCode, gizmoToConnect);
        } else {
            throw new NullPointerException("Null gizmo passed to addKeyConnection()");
        }
    }

    public void removeKeyConnection(int keyCode) {

        keyConnections.remove(keyCode);

    }

    private void printKeyConnections() {
        for (Integer keycode : keyConnections.keySet()) {
                System.out.print("keycode: " + keycode);
            System.out.println("gizmo: " + keyConnections.get(keycode).getClass().toString());
        }
    }

    public boolean checkKeyConnection(int key) {
        for (Integer keycode : keyConnections.keySet()) {
            if (keycode == key) {
                return false;
            }
        }
        return true;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public List<Flipper> getFlippers() {
        return flippers;
    }

    public void clearFlippers() {
        flippers.clear();
    }

    public IGizmo findKeyConnections(IGizmo gizmoName) {
        if (this.keyConnections.containsValue(gizmoName))
            return this.keyConnections.get(gizmoName);
        return null;
    }
}
