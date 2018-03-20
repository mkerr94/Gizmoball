import Model.Model;
import Model.Circle;
import Model.Triangle;
import Model.Square;
import Model.Absorber;
import Model.Flipper;
import View.Mode;
import org.junit.Test;
import Model.RightFlipper;
import Model.LeftFlipper;
import Model.Ball;

import java.awt.event.KeyEvent;

import static org.junit.Assert.*;


public class ModelTest {

    @Test
    public void addGizmoTest() {
        Model model = new Model();
        Circle circle = new Circle(100, 100);

        model.addGizmo(circle);
        assertEquals(model.getGizmos().size(), 1);
    }

    @Test(expected = NullPointerException.class)
    public void addNullGizmoTest() {
        Model model = new Model();
        Circle circle = null;

        model.addGizmo(circle);
        assertEquals(model.getGizmos().size(),0);
    }

    @Test
    public void multipleGizmoTest() {
        Model model = new Model();
        Circle circle = new Circle(100, 100);
        Triangle triangle = new Triangle(200, 200, 0);
        model.addGizmo(circle);
        model.addGizmo(triangle);

        assertEquals(model.getGizmos().size(), 2);
    }

    @Test
    public void deleteCircleGizmoTest() {
        Model model = new Model();
        Circle circle = new Circle(100, 100);
        model.addGizmo(circle);

        model.deleteGizmoOrBall(100, 100);
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    public void deleteTriangleGizmoTest() {
        Model model = new Model();
        Triangle triangle = new Triangle(100, 100,0);
        model.addGizmo(triangle);

        model.deleteGizmoOrBall(100, 100);
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    public void deleteSquareGizmoTest() {
        Model model = new Model();
        Square square = new Square(100, 100);
        model.addGizmo(square);

        model.deleteGizmoOrBall(100, 100);
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    public void clearBoardTest() {
        Model model = new Model();
        Circle circle = new Circle(100, 100);
        model.addGizmo(circle);

        model.clearGizmos();
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    public void addBall() {
        Model model = new Model();

        model.addBall(10, 10, 50, 50);
        assertEquals(model.getBalls().size(), 1);
    }

    @Test
    public void deleteBall(){
        Model model = new Model();

        model.addBall(10,10,50,50);
        model.deleteGizmoOrBall(10/30,10/30);

        assertEquals(model.getBalls().size(),0);
    }

    @Test
    public void addMultipleBalls() {
        Model model = new Model();

        model.addBall(10, 10, 50, 50);
        model.addBall(20, 20, 50, 50);

        assertEquals(model.getBalls().size(), 2);
    }

    @Test
    public void deleteRightFlipper(){
        Model model = new Model();

        RightFlipper flipper = new RightFlipper(10,10);
        model.addGizmo(flipper);
        model.deleteGizmoOrBall(10,10);

        assertEquals(model.getFlippers().size(),0);
    }

    @Test
    public void deleteLeftFlipper(){
        Model model = new Model();

        LeftFlipper flipper = new LeftFlipper(10,10);
        model.addGizmo(flipper);
        model.deleteGizmoOrBall(10,10);

        assertEquals(model.getFlippers().size(),0);
    }

    @Test
    public void rotateTriangle(){
        Model model = new Model();
        Triangle triangle = new Triangle(10,10,0);

        model.rotateGizmo(triangle);
        assertEquals(triangle.getRotation(),1);
    }

    @Test
    public void rotateRightFlipper(){
        Model model = new Model();
        RightFlipper flipper = new RightFlipper(10,10);

        model.rotateGizmo(flipper);
        assertEquals(flipper.getRotation(),1);
    }

    @Test
    public void rotateLeftFlipper(){
        Model model = new Model();
        LeftFlipper flipper = new LeftFlipper(10,10);

        model.rotateGizmo(flipper);
        assertEquals(flipper.getRotation(),1);
    }

    @Test
    public void moveCircle(){
        Model model = new Model();
        Circle circle = new Circle(10,10);

        model.moveGizmo(circle,20,20);
        assertEquals(circle.getX1(),20);
    }

    @Test
    public void moveSquare(){
        Model model = new Model();
        Square square = new Square(10,10);

        model.moveGizmo(square,20,20);
        assertEquals(square.getX1(),20);
    }

    @Test
    public void moveTriangle(){
        Model model = new Model();
        Triangle triangle = new Triangle(10,10,0);

        model.moveGizmo(triangle,20,20);
        assertEquals(triangle.getX1(),20);
    }

    @Test
    public void addKeyConnectionGizmoTest(){
        Model model = new Model();
        int keyCode = KeyEvent.VK_SPACE;
        Circle circle = new Circle(10,10);

        model.addKeyConnection(keyCode,circle);
        assertFalse(model.checkKeyConnection(keyCode));
    }

    @Test
    public void addKeyConnectionFlipperTest(){
        Model model = new Model();
        int keyCode = KeyEvent.VK_SPACE;
        RightFlipper flipper = new RightFlipper(10,10);

        model.addKeyConnection(keyCode,flipper);
        assertFalse(model.checkKeyConnection(keyCode));
    }

    @Test
    public void deleteKeyConnectionGizmoTest(){
        Model model = new Model();
        int keyCode = KeyEvent.VK_SPACE;
        Circle circle = new Circle(10,10);

        model.addKeyConnection(keyCode,circle);
        model.removeKeyConnection(keyCode);
        assertTrue(model.checkKeyConnection(keyCode));
    }

    @Test
    public void deleteKeyConnectionFlipperTest(){
        Model model = new Model();
        int keyCode = KeyEvent.VK_SPACE;
        RightFlipper flipper = new RightFlipper(10,10);

        model.addKeyConnection(keyCode,flipper);
        model.removeKeyConnection(keyCode);
        assertTrue(model.checkKeyConnection(keyCode));
    }
}


