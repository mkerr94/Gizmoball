package Model;

import Physics.Vect;

import java.util.*;

public class Model extends Observable {
    private final List<IGizmo> gizmos;
    private final List<Ball> balls;
    private final List<Ball> fireQueue;
    private final List<Flipper> flippers;
    private Walls walls;
    private final int L = 30;
    private int gravityValue;
    private double frictionValue;
    private final CollisionsEngine collisionsEngine;
    private final Map<Integer, IGizmo> keyConnections;

    public Model() {
        gizmos = new ArrayList<>();
        balls = new ArrayList<>();
        flippers = new ArrayList<>();
        fireQueue = new ArrayList<>();
        walls = new Walls(0, 0, 600, 600);
        gravityValue = 25;
        frictionValue = 0.025;
        collisionsEngine = new CollisionsEngine(this);
        keyConnections = new HashMap<>();
    }

    /***
     * getter for this.gizmos
     * @return this.gizmos
     */
    public List<IGizmo> getGizmos() {
        return gizmos;
    }

    /***
     * getter for the walls
     * @return this.walls
     */
    public Walls getWalls() {
        return walls;
    }

    /***
     * getter for this.balls
     * @return this.balls
     */
    public List<Ball> getBalls() {
        return balls;
    }

    /***
     * getter for this.flippers
     * @return this.flippers
     */
    public List<Flipper> getFlippers() {
        return flippers;
    }

    /***
     * setter for the walls of the gameboard
     * @param walls new walls
     */
    public void setWalls(Walls walls) {
        this.walls = walls;
    }

    /***
     * setter for the gravity value in the game
     * @param gravityValue new gravity value
     */
    public void setGravityValue(int gravityValue) {
        this.gravityValue = gravityValue;
    }

    /***
     * setter for the friction value in the game
     * @param frictionValue new friction value
     */
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
    public void applyGravity(double time) {
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
                flippers.add((Flipper) gizmo);
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
     * @param y y-ordinate of ball to add
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
     * @param gizmoToCheck gizmo to be added to the board
     * @return true if valid placement, false if invalid placement
     */
    public boolean checkValidGizmoLocation(IGizmo gizmoToCheck) {
        assert gizmoToCheck != null : "gizmo to check is null";
        // flippers at the edge of the gameboard
        if (gizmoToCheck instanceof LeftFlipper) {
            if (gizmoToCheck.getX1() == 19) {
                return false;
            }
        }
        if (gizmoToCheck instanceof RightFlipper) {
            if (gizmoToCheck.getX1() == 0) {
                return false;
            }
        }

        if (gizmoToCheck instanceof LeftFlipper) {
            if (gizmoToCheck.getX1() >= 20) {
                return false;
            }
        }
        if (gizmoToCheck instanceof RightFlipper) {
            if (gizmoToCheck.getX1() >= 20) {
                return false;
            }
        }

        if (gizmoToCheck instanceof LeftFlipper) {
            if (gizmoToCheck.getY1() >= 19) {
                return false;
            }
        }
        if (gizmoToCheck instanceof RightFlipper) {
            if (gizmoToCheck.getY1() >= 19) {
                return false;
            }
        }

        //Outside of grid
        if (gizmoToCheck instanceof Circle) {
            if (gizmoToCheck.getY1() >= 20 || gizmoToCheck.getX1() >= 20) {
                return false;
            }
        }

        if (gizmoToCheck instanceof Square) {
            if (gizmoToCheck.getY1() >= 20 || gizmoToCheck.getX1() >= 20) {
                return false;
            }
        }

        if (gizmoToCheck instanceof Triangle) {
            if (gizmoToCheck.getY1() >= 20 || gizmoToCheck.getX1() >= 20) {
                return false;
            }
        }

        if(gizmoToCheck instanceof Absorber) {
            if( ((Absorber) gizmoToCheck).getY2() > 20 || ((Absorber) gizmoToCheck).getX2() > 20) {
                return false;
            }
        }

        if(gizmoToCheck instanceof Absorber) {
            if(gizmoToCheck.getX1() + ((Absorber) gizmoToCheck).getX2() > 20 || gizmoToCheck.getY1() + ((Absorber) gizmoToCheck).getY2() > 20)  {
                return false;
            }
        }

        // handle basic gizmos
        for (IGizmo existingGizmo : gizmos) {
            if (existingGizmo.getX1() == gizmoToCheck.getX1() && existingGizmo.getY1() == gizmoToCheck.getY1()) { // if a gizmo already exists in that location
                return false;
            }

            // handle left flippers
            if (gizmoToCheck instanceof LeftFlipper) {
                if (existingGizmo.getX1() - 1 == gizmoToCheck.getX1() && existingGizmo.getY1() == gizmoToCheck.getY1()) {
                    return false;
                } else if (existingGizmo.getX1() - 1 == gizmoToCheck.getX1() && existingGizmo.getY1() - 1 == gizmoToCheck.getY1()) {
                    return false;
                }
                if (existingGizmo.getX1() == gizmoToCheck.getX1() && existingGizmo.getY1() == gizmoToCheck.getY1() + 1) {
                    return false;
                }
            }
            // handle right flippers
            if (gizmoToCheck instanceof RightFlipper) {
                if (existingGizmo.getX1() + 1 == gizmoToCheck.getX1() && existingGizmo.getY1() == gizmoToCheck.getY1()) {
                    return false;
                } else if (existingGizmo.getX1() + 1 == gizmoToCheck.getX1() && existingGizmo.getY1() - 1 == gizmoToCheck.getY1()) {
                    return false;
                }
                if (existingGizmo.getX1() == gizmoToCheck.getX1() && existingGizmo.getY1() == gizmoToCheck.getY1() + 1) {
                    return false;
                }
            }
            // ensure gizmos aren't placed within the range of a rotating flipper
            if (existingGizmo instanceof LeftFlipper) {
                if (existingGizmo.getX1() + 1 == gizmoToCheck.getX1() && existingGizmo.getY1() == gizmoToCheck.getY1()) {
                    return false;
                } else if (existingGizmo.getX1() + 1 == gizmoToCheck.getX1() && existingGizmo.getY1() + 1 == gizmoToCheck.getY1()) {
                    return false;
                }
                if (gizmoToCheck instanceof RightFlipper) {
                    if (existingGizmo.getX1() + 2 == gizmoToCheck.getX1() && existingGizmo.getY1() == gizmoToCheck.getY1()) {
                        return false;
                    } else if (existingGizmo.getX1() + 2 == gizmoToCheck.getX1() && existingGizmo.getY1() + 1 == gizmoToCheck.getY1()) {
                        return false;
                    }
                }
            }
            if (existingGizmo instanceof RightFlipper) {
                if (existingGizmo.getX1() - 1 == gizmoToCheck.getX1() && existingGizmo.getY1() == gizmoToCheck.getY1()) {
                    return false;
                } else if (existingGizmo.getX1() - 1 == gizmoToCheck.getX1() && existingGizmo.getY1() + 1 == gizmoToCheck.getY1()) {
                    return false;
                }
                if (gizmoToCheck instanceof LeftFlipper) {
                    if (existingGizmo.getX1() - 2 == gizmoToCheck.getX1() && existingGizmo.getY1() == gizmoToCheck.getY1()) {
                        return false;
                    } else if (existingGizmo.getX1() - 2 == gizmoToCheck.getX1() && existingGizmo.getY1() + 1 == gizmoToCheck.getY1()) {
                        return false;
                    }
                }

            }
            // handle absorbers
            if (gizmoToCheck instanceof Absorber) {
                Absorber absorber = (Absorber) gizmoToCheck;
                if (existingGizmo.getX1() >= absorber.getX1() && existingGizmo.getX1() <= (absorber.getX1() + absorber.getX2())) {
                    if (existingGizmo.getY1() >= absorber.getY1() && existingGizmo.getY1() <= (absorber.getY1() + absorber.getY2()))
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if gizmo already exists at the x and y location passed in.
     *
     * @param x x ordinate of location to check
     * @param y y ordinate of location to check
     * @return true if valid placement, false if invalid placement
     */
    public boolean checkIfValidBallSpawn(int x, int y) {
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

        for (Flipper flipper : flippers) {
            if (flipper.getX1() == x && flipper.getY1() == y) {
                flippers.remove(flipper);
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
            gizmo.rotate();
            setChanged();
            notifyObservers();
        } else {
            System.out.println("Error, cannot rotate this type of gizmo.");
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
                System.out.println("Ball hit absorber");
                b.setVelo(new Vect(0, 0));
                b.stop();
                b.setExactX(absorber.getX2() - b.getRadius());
                b.setExactY(absorber.getY2() + absorber.getY1() - b.getRadius());
                fireQueue.add(b);
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

    /***
     * Moves a given gizmo to a new location. First checks if a gizmo already exists
     * in the target location.
     * @param gizmo Gizmo you're trying to move
     * @param newX x-ordinate of the target destination
     * @param newY y-ordinate of the target destination
     */
    public void moveGizmo(IGizmo gizmo, int newX, int newY) {
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

    /***
     * checks if an int is a valid key connection
     * @param key the key to validate
     * @return true if valid, false otherwise
     */
    public boolean checkKeyConnection(int key) {
        for (Integer keycode : keyConnections.keySet()) {
            if (keycode == key) {
                return false;
            }
        }
        return true;
    }


    /***
     * Empties the collection of flippers. Used when clear board is called in the view
     */
    public void clearFlippers() {
        flippers.clear();
    }

    /***
     * parses a gizmo's key connection to a string for use when saving a game to file
     * @param gizmoName the gizmo who's connection to be parsed
     * @return a parsed string ready to be written to file
     */
    String stringKeyConnection(IGizmo gizmoName) {
        int x, y;
        for (Integer keycode : keyConnections.keySet()) {
            if (this.keyConnections.containsValue(gizmoName)) {
                x = gizmoName.getX1();
                y = gizmoName.getY1();
                return keycode + " up " + this.keyConnections.get(keycode).toString().substring(6, 7) + x + y;
            }
        }
        return null;
    }

    /***
     * Checks if there any gizmos in the way before rotating a flipper
     * @param rotatingFlipper the flipper to be rotated
     * @return true if the flipper can rotate, false if there are gizmos obstructing the rotate
     */
    public boolean checkValidFlipperRotation(IGizmo rotatingFlipper) {
        int rotation = rotatingFlipper.getRotation();
        rotation = (rotation > 2) ? 0 : rotation + 1;
        System.out.println("Model.checkValidFlipperRotation");
        System.out.println("rotation = " + rotation);
        for (IGizmo existingGizmo : gizmos) {
            switch (rotation) {
                // going from rotation 3 to rotation 0 (unrotated)
                case 0:
                    if (rotatingFlipper instanceof LeftFlipper) {
                        if (existingGizmo.getX1() == rotatingFlipper.getX1() && existingGizmo.getY1() == rotatingFlipper.getY1() + 1) {
                            return false;
                        }
                    } else if (rotatingFlipper instanceof RightFlipper) {
                        if (existingGizmo.getX1() == rotatingFlipper.getX1() && existingGizmo.getY1() == rotatingFlipper.getY1() + 1) {
                            return false;
                        }
                    }
                    break;
                // going from rotation 0 (unrotated) to rotation 1
                case 1:
                    if (rotatingFlipper instanceof LeftFlipper) {
                        if (existingGizmo.getX1() == rotatingFlipper.getX1() - 1 && existingGizmo.getY1() == rotatingFlipper.getY1()) {
                            return false;
                        }
                    } else if (rotatingFlipper instanceof RightFlipper) {
                        if (existingGizmo.getX1() == rotatingFlipper.getX1() - 1 && existingGizmo.getY1() == rotatingFlipper.getY1()) {
                            return false;
                        }
                    }
                    break;
                // going from rotation 1 to rotation 2
                case 2:
                    if (rotatingFlipper instanceof LeftFlipper) {
                        if (existingGizmo.getX1() == rotatingFlipper.getX1() && existingGizmo.getY1() == rotatingFlipper.getY1() - 1) {
                            return false;
                        }
                    } else if (rotatingFlipper instanceof RightFlipper) {
                        if (existingGizmo.getX1() == rotatingFlipper.getX1() && existingGizmo.getY1() == rotatingFlipper.getY1() - 1) {
                            return false;
                        }
                    }
                    break;
                // going from rotation 2 to rotation 3
                case 3:
                    if (rotatingFlipper instanceof LeftFlipper) {
                        if (existingGizmo.getX1() == rotatingFlipper.getX1() + 1 && existingGizmo.getY1() == rotatingFlipper.getY1()) {
                            return false;
                        }
                    } else if (rotatingFlipper instanceof RightFlipper) {
                        if (existingGizmo.getX1() == rotatingFlipper.getX1() + 1 && existingGizmo.getY1() == rotatingFlipper.getY1()) {
                            return false;
                        }
                    }
                    break;
            }
        }
        return true;
    }
}
