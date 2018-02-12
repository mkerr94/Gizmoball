package Model;

import java.awt.*;

public class TriangleGizmo {

    public void paint(Graphics g) {

        int x2Points[] = {100, 110,120};
        int y2Points[] = {140,120,140};

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setPaint(Color.MAGENTA);

        graphics2D.drawPolygon(x2Points,y2Points,3);
        graphics2D.fillPolygon(x2Points,y2Points,3);
    }
}
