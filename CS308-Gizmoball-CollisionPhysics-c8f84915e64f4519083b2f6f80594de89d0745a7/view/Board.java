package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.*;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public  class Board extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected Model gm;
	protected LoadFile fm;

	public Board(int w, int h, LoadFile f) {
		// Observe changes in Model
		f.addObserver(this);
		width = w;
		height = h;
		fm = f;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}



	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		Graphics2D g3 = (Graphics2D) g;

		try {
			fm.tokenizeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		for(Gizmo b : fm.getLoadedGizmos().values()) {
			if (b != null && b.getClass().equals(Square.class)) {
				System.out.println(b);
				g2.setColor(Color.GREEN);
				int x = (int) (b.getExactX() * 20);;
				int y = (int) (b.getExactY() * 20);
				int width = (int) (2 * b.getRadius());
				//int height = b.getHeight();
				g2.fillRect(x, y, 1, 1);
			}

			if (b != null && b.getClass().equals(Circle.class)) {
				System.out.println(b.getType());
				int x = (int) (b.getExactX() * 20);
				int y = (int) (b.getExactY() * 20);
				int width = (int) (2 * b.getRadius());
				g2.fillOval(x, y, width, width);
			}

			if (b != null && b.getClass().equals(Triangle.class)) {
				System.out.println(b.getType());
				int x= (int) (b.getX() * 20);
				int y= (int) (b.getY() * 20);
				int width = (int)(b.getWidth());
				int x2Points[] = {x, x+10,x+20};
				int y2Points[] = {y,y+20,y};
				g2.fillPolygon(x2Points,y2Points,3);
			}

			CircleGizmo cg = new CircleGizmo("meh", 5, 5);
			if (cg != null) {
				g2.setColor(cg.getColour());
				int x = (int) (cg.getExactX() - cg.getRadius());
				int y = (int) (cg.getExactY() - cg.getRadius());
				int width = (int) (2 * cg.getRadius());
				g2.fillOval(x, y, width, width);
			}

			CircleGizmo cg2 = new CircleGizmo("meh", 100, 200);
			if (cg2 != null) {
				g2.setColor(cg2.getColour());
				int x = (int) (cg2.getExactX() - cg2.getRadius());
				int y = (int) (cg2.getExactY() - cg2.getRadius());
				int width = (int) (2 * cg2.getRadius());
				g2.fillOval(x, y, width, width);
			}

			Circle cg3 = new Circle(20, 100);
			if (cg3 != null) {
				g2.setColor(cg3.getColour());
				int x = (int) (cg3.getExactX() - cg3.getRadius());
				int y = (int) (cg3.getExactY() - cg3.getRadius());
				int width = (int) (2 * cg3.getRadius());
				g2.fillOval(x, y, width, width);
			}


		/*SquareGizmo sg = gm.getSquareGizmo();
		if (sg != null) {
			g2.setColor(sg.getColour());
			int x= (int) (sg.getX());
			int y= (int) (sg.getY());
			int width = (int)(sg.getWidth());
			g2.fillRect(x,y,width,width);
		}

		TriangleGizmo tg = gm.getTriangleGizmo();

		if (tg != null) {
			g2.setColor(tg.getColour());
			int x= (int) (tg.getX());
			int y= (int) (tg.getY());
			int width = (int)(tg.getWidth());
			int x2Points[] = {x, x+10,x+20};
			int y2Points[] = {y,y+20,y};
			g2.fillPolygon(x2Points,y2Points,3);

		}*/
		}

	}
	@Override
	public void update(Observable arg0, Object arg1) {
			repaint();
		}
	
}
