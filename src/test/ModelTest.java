import Model.Model;
import Model.Absorber;
import Model.Circle;
import Model.Triangle;
import Model.Square;
import Model.Flipper;
import View.Mode;
import org.junit.Test;
import Model.RightFlipper;
import Model.LeftFlipper;
import Model.Ball;

import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.Assert.*;


public class ModelTest {

    Model model = new Model();

    @Test
    public void addGizmoTest() {
        Circle circle = new Circle(100, 100);

        model.addGizmo(circle);
        assertEquals(model.getGizmos().size(), 1);
    }

    @Test(expected = NullPointerException.class)
    public void addNullGizmoTest() {
        Circle circle = null;

        model.addGizmo(circle);
        assertEquals(model.getGizmos().size(),0);
    }

    @Test
    public void multipleGizmoTest() {
        Circle circle = new Circle(100, 100);
        Triangle triangle = new Triangle(200, 200, 0);
        model.addGizmo(circle);
        model.addGizmo(triangle);

        assertEquals(model.getGizmos().size(), 2);
    }

    @Test
    public void deleteCircleGizmoTest() {
        Circle circle = new Circle(100, 100);
        model.addGizmo(circle);

        model.deleteGizmoOrBall(100, 100);
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    public void deleteTriangleGizmoTest() {
        Triangle triangle = new Triangle(100, 100,0);
        model.addGizmo(triangle);

        model.deleteGizmoOrBall(100, 100);
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    public void deleteSquareGizmoTest() {
        Square square = new Square(100, 100);
        model.addGizmo(square);

        model.deleteGizmoOrBall(100, 100);
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    public void clearBoardTest() {
        Circle circle = new Circle(100, 100);
        model.addGizmo(circle);

        model.clearGizmos();
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    public void addBall() {

        model.addBall(10, 10, 50, 50);
        assertEquals(model.getBalls().size(), 1);
    }

    @Test
    public void deleteBall(){

        model.addBall(10,10,50,50);
        model.deleteGizmoOrBall(10/30,10/30);

        assertEquals(model.getBalls().size(),0);
    }

    @Test
    public void addMultipleBalls() {

        model.addBall(10, 10, 50, 50);
        model.addBall(20, 20, 50, 50);

        assertEquals(model.getBalls().size(), 2);
    }

    @Test
    public void deleteRightFlipper(){

        RightFlipper flipper = new RightFlipper(10,10);
        model.addGizmo(flipper);
        model.deleteGizmoOrBall(10,10);

        assertEquals(model.getFlippers().size(),0);
    }

    @Test
    public void deleteLeftFlipper(){

        LeftFlipper flipper = new LeftFlipper(10,10);
        model.addGizmo(flipper);
        model.deleteGizmoOrBall(10,10);

        assertEquals(model.getFlippers().size(),0);
    }

    @Test
    public void rotateTriangle(){
        Triangle triangle = new Triangle(10,10,0);

        model.rotateGizmo(triangle);
        assertEquals(triangle.getRotation(),1);
    }

    @Test
    public void rotateRightFlipper(){
        RightFlipper flipper = new RightFlipper(10,10);

        model.rotateGizmo(flipper);
        assertEquals(flipper.getRotation(),1);
    }

    @Test
    public void rotateLeftFlipper(){
        LeftFlipper flipper = new LeftFlipper(10,10);

        model.rotateGizmo(flipper);
        assertEquals(flipper.getRotation(),1);
    }

    @Test
    public void moveCircle(){
        Circle circle = new Circle(10,10);

        model.moveGizmo(circle,20,20);
        assertEquals(circle.getX1(),20);
    }

    @Test
    public void moveSquare(){
        Square square = new Square(10,10);

        model.moveGizmo(square,20,20);
        assertEquals(square.getX1(),20);
    }

    @Test
    public void moveTriangle(){
        Triangle triangle = new Triangle(10,10,0);

        model.moveGizmo(triangle,20,20);
        assertEquals(triangle.getX1(),20);
    }

    @Test
    public void addKeyConnectionGizmoTest(){
        int keyCode = KeyEvent.VK_SPACE;
        Circle circle = new Circle(10,10);

        model.addKeyConnection(keyCode,circle);
        assertFalse(model.checkKeyConnection(keyCode));
    }

    @Test
    public void addKeyConnectionFlipperTest(){
        int keyCode = KeyEvent.VK_SPACE;
        RightFlipper flipper = new RightFlipper(10,10);

        model.addKeyConnection(keyCode,flipper);
        assertFalse(model.checkKeyConnection(keyCode));
    }

    @Test
    public void deleteKeyConnectionGizmoTest(){
        int keyCode = KeyEvent.VK_SPACE;
        Circle circle = new Circle(10,10);

        model.addKeyConnection(keyCode,circle);
        model.removeKeyConnection(keyCode);
        assertTrue(model.checkKeyConnection(keyCode));
    }

    @Test
    public void deleteKeyConnectionFlipperTest(){
        int keyCode = KeyEvent.VK_SPACE;
        RightFlipper flipper = new RightFlipper(10,10);

        model.addKeyConnection(keyCode,flipper);
        model.removeKeyConnection(keyCode);
        assertTrue(model.checkKeyConnection(keyCode));
    }

    @Test
    public void addAbsorberTest(){
        Absorber absorber = new Absorber(10, 10, 20, 1);

        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    public void getWallsTest() {
        assertEquals(model.getWalls(), model.getWalls());
    }

    @Test
    public void checkValidLocationTest() {
        Square square = new Square(400, 400);
        Triangle triangle = new Triangle(400, 400, 1);
        Absorber abs = new Absorber(20, 20, 19, 10);
        Square square1 = new Square(20, 20);

        model.addGizmo(square1);
        model.checkValidGizmoLocation(square1);
        model.addGizmo(abs);
        model.checkValidGizmoLocation(abs);
        model.addGizmo(square);
        model.checkValidGizmoLocation(square);
        model.checkValidGizmoLocation(triangle);
        model.addGizmo(triangle);

    }

    @Test
    public void checkValidLocationFlipperTest() {
        Square square = new Square(10, 10);
        Square square1 = new Square(6, 6);
        RightFlipper rig2 = new RightFlipper(11, 10);
        LeftFlipper lef2 = new LeftFlipper(5, 6);
        LeftFlipper lef = new LeftFlipper(40, 40);
        RightFlipper rig = new RightFlipper(11, 9);
        LeftFlipper lef1 = new LeftFlipper(1, 1);
        RightFlipper rig1 = new RightFlipper(19, 19);

        model.addGizmo(lef);
        model.addGizmo(rig);
        model.addGizmo(lef1);
        model.addGizmo(rig1);
        model.addGizmo(square);
        model.addGizmo(rig2);
        model.addGizmo(lef2);
        model.checkValidGizmoLocation(lef2);
        model.checkValidGizmoLocation(rig2);
        model.checkValidGizmoLocation(lef);
        model.checkValidGizmoLocation(lef1);
        model.checkValidGizmoLocation(rig);
        model.checkValidGizmoLocation(rig1);

    }

}


