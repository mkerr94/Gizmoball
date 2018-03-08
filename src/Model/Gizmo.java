package Model;

public class Gizmo implements IGizmo{

    private int x;
    private int y;
    private int rotation;


    public Gizmo(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.rotation = 0;
    }


    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void rotate() {

    }

    @Override
    public void move(int newX, int newY) {

    }

    @Override
    public int getRotation() {
        return 0;
    }
}
