import Model.*;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class GizmoTests {

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
        Triangle triangle1 = new Triangle(100,100,0);
        Triangle triangle2 = new Triangle(200,200,0);

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
        Triangle triangle = new Triangle(100,100,0);
        Circle circle = new Circle(200,200);

        assertNotEquals(triangle,circle);
    }

    @Test
    public void rightFlipperTest(){
        RightFlipper flipper1 = new RightFlipper(10,10);
        RightFlipper flipper2 = new RightFlipper(20,20);

        assertNotEquals(flipper1,flipper2);
    }

    @Test
    public void leftFlipperTest(){
        LeftFlipper flipper1 = new LeftFlipper(10,10);
        LeftFlipper flipper2 = new LeftFlipper(20,20);

        assertNotEquals(flipper1,flipper2);
    }

    @Test
    public void ballTest(){
        Ball ball1 = new Ball(10,10,10,10);
        Ball ball2 = new Ball(20,20,20,20);

        assertNotEquals(ball1,ball2);
    }
}
