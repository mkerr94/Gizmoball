package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.*;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public  class Board extends JPanel{

	private static final int l = 20;
	private int width;
	private int height;
	private LoadFile fl;

	public Board(int w, int h, LoadFile f) {
		width = w;
		height = h;
		fl = f;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		fl.tokenizeFile();

		for(IGizmo b : fl.getLoadedGizmos().values()) {
			if (b != null && b.getClass().equals(Square.class)) {
				g2.setColor(b.getColour());
				int x= (b.getX() * l);
				int y= (b.getY() * l);
				g2.fillRect(x, y, l, l);
			}

			if (b != null && b.getClass().equals(Circle.class)) {
				g2.setColor(b.getColour());
				int x= (b.getX() * l);
				int y= (b.getY() * l);
				g2.fillOval(x, y, l, l);
			}

			if (b != null && b.getClass().equals(Triangle.class)) {
				g2.setColor(b.getColour());
				int x= (b.getX() * l);
				int y= (b.getY() * l);
				if(b.getRotation() == 0){
					int x2Points[] = {x+l, x,x};
					int y2Points[] = {y,y+l,y};
					g2.fillPolygon(x2Points,y2Points,3);
				}

				else if(b.getRotation() == 1){
					int x2Points1[] = {x+l, x,x+l};
					int y2Points1[] = {y,y,y+l};
					g2.fillPolygon(x2Points1,y2Points1,3);
				}
			}

			if (b != null && b.getClass().equals(Ball.class)) {
				g2.setColor(b.getColour());
				int x = (b.getX());
				int y = (b.getY());
				g2.fillOval(x, y,l,l);
			}

			if (b != null && b.getClass().equals(Absorber.class)) {
				g2.setColor(b.getColour());
				int x =(b.getX() * l);;
				int y =(b.getY() * l);
				g2.fillRect(x, y, width, height);
			}

			if (b != null && b.getClass().equals(LeftFlipper.class)) {
				g2.setColor(b.getColour());
				int x =(b.getX() * l);;
				int y =(b.getY() * l);
				g2.fillRect(x, y, (l/2), (l*2));
				g2.fillOval(x, y-4, 10, 15);
				g2.fillOval(x, y+30, 10, 15);
			}

			if (b != null && b.getClass().equals(RightFlipper.class)) {
				g2.setColor(b.getColour());
				int x =(b.getX() * l);;
				int y =(b.getY() * l);
				g2.fillRect(x+30, y, (l/2), (l*2));
				g2.fillOval(x+30, y-4, 10, 15);
				g2.fillOval(x+30, y+30, 10, 15);
			}
		}
	}
}
