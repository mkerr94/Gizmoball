
public class Square extends Gizmo {

    public Square (int x, int y)
    {
        super( 1, 1, x, y);
    }

    public GizmoType getType()
    {
        return GizmoType.Square;
    }
}
