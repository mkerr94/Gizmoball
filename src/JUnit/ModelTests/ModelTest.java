/*package ModelTests;

import Model.Model;
import Model.Circle;
import Model.Triangle;
import Model.Square;
import Model.Absorber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Test
    public void circleTest(){
        Circle circle1 = new Circle(100,100);
        Circle circle2 = new Circle(200,200);

        assertNotEquals(circle1,circle2);
    }


    @Test
    public void squareTest(){
        Square square1 = new Square(100,100);
        Square square2 = new Square(200,200);

        assertNotEquals(square1,square2);
    }


    @Test
    public void triangleTest(){
        Triangle triangle1 = new Triangle(100,100);
        Triangle triangle2 = new Triangle(200,200);

        assertNotEquals(triangle1,triangle2);
    }


    @Test
    public void absorberTest(){
        Absorber absorber1 = new Absorber(100,100,10,10);
        Absorber absorber2 = new Absorber(200,200,10,10);

        assertNotEquals(absorber1,absorber2);
    }

    @Test
    public void differentGizmoTest(){
        Triangle triangle = new Triangle(100,100);
        Circle cirlcle = new Circle(200,200);

        assertNotEquals(triangle,cirlcle);
    }
}
*/