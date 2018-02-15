
public class Triangle extends Gizmo {
    public Triangle (int x, int y)
    {
        super( 1, 1, x, y);
    }

    public GizmoType getType()
    {
        return GizmoType.Triangle;
    }
}

