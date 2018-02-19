package model;

import java.util.ArrayList;
import java.util.Observable;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Model extends Observable {

	private ArrayList<VerticalLine> lines;
	private ArrayList<Absorber> ls;
	private Ball ball;
	private Absorber absorber;
	private Walls gws;
	private Vect newVelo;

	public Model() {

		// Ball position (25, 25) in pixels. Ball velocity (100, 100) pixels per tick
		ball = new Ball(25, 25, 100, 100);

		// Wall size 500 x 500 pixels
		gws = new Walls(0, 0, 500, 500);

		absorber = new Absorber(0,490, 10, 500);

		// Lines added in Main
		lines = new ArrayList<VerticalLine>();
		ls = new ArrayList<Absorber>();

	}

	public void applyGravity(double time){

		//Vnew = Vold + gravity * delta_t. (delta-t is tick time or tuc).

		double gravAcc = 25;

		Vect gravVelocity = new Vect(ball.getVelo().x(), (ball.getVelo().y() + (gravAcc)));
		ball.setVelo(gravVelocity);

	}

	public void applyFriction (double time){

		//Vnew = Vold * (1 - mu * delta_t - mu2 * |Vold| * delta_t) Once for X and once for Y
		//The default value of mu should be 0.025 per second.
		//The default value of mu2 should be 0.025 per L.
		double mu = 0.025;
		double mu2 = 0.025;
		//delta_t is the time the ball is moving for - tick time or tuc.

		double vOldX = ball.getVelo().x();
		double vOldY = ball.getVelo().y();

		Vect oldVelo = new Vect(vOldX, vOldY);
		Vect newVelo = oldVelo.times(1 - mu * time - mu2 * time);

		double fricVelX = vOldX * (1 - mu * time - (mu2 * vOldX) * time);
		double fricVelY = vOldY * (1 - mu * time - mu2 * vOldY * time);

		//You apply this equation to both the x component of velocity and the y component - 2 lines of code.

		Vect fricVelo = new Vect(fricVelX, fricVelY);
		ball.setVelo(newVelo);

		System.out.println("New Veloicty = " + fricVelo);

	}



	public void moveBall(double time) {

		double moveTime = 0.05; // 0.05 = 20 times per second as per Gizmoball

		if (ball != null && !ball.stopped() ) {

			CollisionDetails cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if (cd != null) {
				System.out.println("Velocity =  " + ball.getVelo());
				if (tuc > moveTime) {
					// No collision ...
					ball = movelBallForTime(ball, moveTime);
				} else {
					// We've got a collision in tuc
					ball = movelBallForTime(ball, tuc);
					applyGravity(time);
					applyFriction(time);
					// Post collision velocity ...
					ball.setVelo(cd.getVelo());
				}

				// Notify observers ... redraw updated view
				this.setChanged();
				this.notifyObservers();
			}
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
		applyFriction(time);
		applyGravity(time);
		return ball;
	}

	private CollisionDetails timeUntilCollision() {
		// Find Time Until Collision and also, if there is a collision, the new speed vector.
		// Create a physics.Circle from Ball
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelo();
		Vect newVelo = new Vect(0, 0);

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
		for (VerticalLine line : lines) {
			LineSegment ls = line.getLineSeg();
			time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(ls, ball.getVelo(), 1.0);
			}
		}

		ArrayList<LineSegment> ls = absorber.getLines();
		for(LineSegment line : ls){
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
			}
			if(time <= 0.1 && !ball.stopped()) {
				ball = new Ball(480, absorber.getY(), 0, 0);
				this.setChanged();
				this.notifyObservers();
				ball.stop();
			}
		}

		return new CollisionDetails(shortestTime, newVelo);
	}

	public void fireBall() {
		if (ball.stopped()) {
			ball.start();

			Vect ballFire = new Vect(0, 200);
			ball.setVelo(ballFire);
		}
	}

	public Ball getBall() {
		return ball;
	}

	public Absorber getAbsorber() { return absorber; }

	public ArrayList<VerticalLine> getLines() {
		return lines;
	}

	public void addLine(VerticalLine l) {
		lines.add(l);
	}

	public void setBallSpeed(int x, int y) {
		ball.setVelo(new Vect(x, y));
	}
}
