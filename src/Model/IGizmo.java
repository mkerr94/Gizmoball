package Model;


public interface IGizmo {
    int L = 25;

    int getX();

    int getY();

    void rotate();

    void move(int newX, int newY);

    int getRotation();
}
