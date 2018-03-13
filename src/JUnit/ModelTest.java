package JUnit;

import Model.Circle;
import org.junit.Before;
import org.junit.Test;
import Model.Model;
import Model.Triangle;

import static org.junit.Assert.*;

public class ModelTest {

    private Model model;

    //doesn't work? idk why
    @Test
    public void addGizmo() throws Exception {
        Circle circle = new Circle(0,0);
        model.addGizmo(circle);
        System.out.println(model.getGizmos().size());
        assertEquals(model.getGizmos().size(),1);
    }
}
