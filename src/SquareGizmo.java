import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SquareGizmo {

    public void paint(Graphics g) {

        double w = 30; //width
        double h = 30; //height

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setPaint(Color.BLUE);

        Rectangle2D rect = new Rectangle2D.Double(50,50, w, h); //allows you to fill colour
        graphics2D.fill(rect);

        graphics2D.draw(new Rectangle2D.Double(50,50, w, h));
        //graphics2D.draw(new Rectangle2D.Double(80, 50, w, h));
    }
}
