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
    public void checkValidLocationTest(){
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
    public void checkInvalidLocationTest() {
        Square square = new Square(400, 400);
        Triangle triangle = new Triangle(400, 400, 1);

        model.addGizmo(square);
        assertFalse(model.checkValidGizmoLocation(triangle));
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

    @Test
    public void leftFlipperBoardEdgeTest(){
        LeftFlipper flipper = new LeftFlipper(19,10);
        model.addGizmo(flipper);
        assertFalse(model.checkValidGizmoLocation(flipper));
    }

    @Test
    public void rightFlipperBoardEdgeTest(){
        RightFlipper flipper = new RightFlipper(0,10);
        model.addGizmo(flipper);
        assertFalse(model.checkValidGizmoLocation(flipper));
    }

    @Test
    public void circleOutsideGridTest(){
        Circle circle = new Circle(30,30);
        assertFalse(model.checkValidGizmoLocation(circle));
    }

    @Test
    public void absorberOutsideGridTest(){
        Absorber absorber = new Absorber(30,30,30,30);
        assertFalse(model.checkValidGizmoLocation(absorber));
    }

    @Test
    public void checkInvalidBallSpawnTest(){
        Square square = new Square(10,10);

        model.addGizmo(square);
        assertFalse(model.checkIfValidBallSpawn(10,10));
    }

    @Test
    public void checkValidBallSpawnTest(){
        Square square = new Square(10,10);

        model.addGizmo(square);
        assertTrue(model.checkIfValidBallSpawn(20,20));
    }

    @Test
    public void checkValidFlipperRotationTest(){
        LeftFlipper flipper = new LeftFlipper(10,10);
        Square square = new Square(9,10);
        model.addGizmo(square);
        assertFalse(model.checkValidFlipperRotation(flipper));

        RightFlipper flipper2 = new RightFlipper(50,50);
        Square square2 = new Square(49,50);
        model.addGizmo(square2);
        assertFalse(model.checkValidFlipperRotation(flipper2));

        RightFlipper flipper3 = new RightFlipper(20,20);
        Square square3 = new Square(20,21);
        model.addGizmo(square3);
        flipper3.rotate();
        flipper3.rotate();
        flipper3.rotate();
        assertFalse(model.checkValidFlipperRotation(flipper3));

        LeftFlipper flipper4 = new LeftFlipper(100,90);
        Square square4 = new Square(100,91);
        model.addGizmo(square4);
        flipper4.rotate();
        flipper4.rotate();
        flipper4.rotate();
        assertFalse(model.checkValidFlipperRotation(flipper4));

        RightFlipper flipper5 = new RightFlipper(5,5);
        Square square5 = new Square(5,4);
        model.addGizmo(square5);
        flipper5.rotate();
        assertFalse(model.checkValidFlipperRotation(flipper5));

        LeftFlipper flipper6 = new LeftFlipper(8,8);
        Square square6 = new Square(8,7);
        model.addGizmo(square6);
        flipper6.rotate();
        assertFalse(model.checkValidFlipperRotation(flipper6));

        RightFlipper flipper7 = new RightFlipper(300,290);
        Square square7 = new Square(301,290);
        model.addGizmo(square7);
        flipper7.rotate();
        flipper7.rotate();
        assertFalse(model.checkValidFlipperRotation(flipper7));

        LeftFlipper flipper8 = new LeftFlipper(500,490);
        Square square8 = new Square(501,490);
        model.addGizmo(square8);
        flipper8.rotate();
        flipper8.rotate();
        assertFalse(model.checkValidFlipperRotation(flipper8));

        LeftFlipper flipper9 = new LeftFlipper(1,1);
        assertTrue(model.checkValidFlipperRotation(flipper9));
    }

    @Test
    public void clearFlipperTest(){
        RightFlipper flipper = new RightFlipper(10,10);
        model.addGizmo(flipper);
        model.clearFlippers();
        assertEquals(model.getFlippers().size(),0);
    }
}


