package model;

import java.awt.Color;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Ball extends Gizmo implements IGizmo{

	private Color colour;


	public Ball(double x, double y, double xv, double yv) {
		super((int)x, (int)y);
		colour = Color.BLUE;
	}

	public Color getColour() {
		return colour;
	}

}
