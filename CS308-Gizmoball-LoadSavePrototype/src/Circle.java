
public class Circle extends Gizmo {
    public Circle (int x, int y)
    {
        super( 1, 1, x, y);
    }

    public GizmoType getType()
    {
        return GizmoType.Circle;
    }
}

