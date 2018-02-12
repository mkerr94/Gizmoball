import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleGizmo {

    public void paint(Graphics g) {

        double w = 30; //width
        double h = 30; //height

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setPaint(Color.GREEN);

        Ellipse2D circle = new Ellipse2D.Double(150,150, w, h); //allows you to fill colour
        graphics2D.fill(circle);

        graphics2D.draw(new Ellipse2D.Double(150,150, w, h));

    }
}
