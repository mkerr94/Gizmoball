import java.awt.*;

public class TriangleGizmo {

    public void paint(Graphics g) {

        double w = 30; //width
        double h = 30; //height
        int x2Points[] = {200, 250,300};
        int y2Points[] = {200,300,200};

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setPaint(Color.MAGENTA);

        graphics2D.drawPolygon(x2Points,y2Points,3);
        graphics2D.fillPolygon(x2Points,y2Points,3);
    }
}
