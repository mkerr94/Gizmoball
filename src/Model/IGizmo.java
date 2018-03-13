package Model;


public interface IGizmo {
    double L = 25;

    int getX();

    int getY();

    void rotate();

    void move(int newX, int newY);

    int getRotation();
}
