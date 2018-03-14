package JUnit;

import Model.Circle;
import Model.Model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelTest {

    private Model model;

    @Test
    void addGizmo() throws Exception {
        model = new Model();
        Circle circle = new Circle(0,0);
        model.addGizmo(circle);
        System.out.println(model.getGizmos().size());
        assertEquals(model.getGizmos().size(),1);
    }
}
