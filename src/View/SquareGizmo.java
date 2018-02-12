package View;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SquareGizmo {

    public void paint(Graphics g) {

        double w = 20; //width
        double h = 20; //height

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setPaint(Color.BLUE);

        Rectangle2D rect1 = new Rectangle2D.Double(50,50, w, h); //allows you to fill colour
        Rectangle2D rect2 = new Rectangle2D.Double(100,50, w, h); //allows you to fill colour
        graphics2D.fill(rect1);
        graphics2D.fill(rect2);

        graphics2D.draw(new Rectangle2D.Double(50,50, w, h));
        graphics2D.draw(new Rectangle2D.Double(100, 50, w, h));
    }
}
