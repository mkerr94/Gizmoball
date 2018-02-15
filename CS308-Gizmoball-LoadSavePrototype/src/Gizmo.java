


public class Gizmo
{
    private int x, y, width, height, rotation;

    public Gizmo()
    {

    }

    public Gizmo(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = 0;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getRotation () {
        return rotation;
    }

    public void rotate() {
        if(rotation == 3)
            rotation = 0;
        else
            rotation++;

    }



}
