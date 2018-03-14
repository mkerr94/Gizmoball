package JUnit;

import Model.Model;
import org.junit.Before;
import org.junit.Test;
import Model.Circle;
import Model.Triangle;

import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void addGizmoTest() {
        Model model = new Model();
        Circle circle = new Circle(100, 100);

        model.addGizmo(circle);
        assertEquals(model.getGizmos().size(), 1);
    }

    @Test
    public void multipleGizmoTest(){
        Model model = new Model();
        Circle circle = new Circle(100,100);
        Triangle triangle = new Triangle(200,200);
        model.addGizmo(circle);
        model.addGizmo(triangle);

        assertEquals(model.getGizmos().size(),2);
    }

    @Test
    public void deleteGizmoTest(){
        Model model = new Model();
        Circle circle = new Circle(100,100);
        model.addGizmo(circle);

        model.deleteGizmo(100,100);
        assertEquals(model.getGizmos().size(),0);
    }

    @Test
    public void clearBoardTest(){
        Model model = new Model();
        Circle circle = new Circle(100,100);
        model.addGizmo(circle);

        model.clearGizmos();
        assertEquals(model.getGizmos().size(),0);
    }
}
