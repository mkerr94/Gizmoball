import java.util.List;

public class Board {

    private int width, height;
    private List<Gizmo> gizmos;

    public Board(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void addGizmo (Gizmo gizmo){
        gizmos.add(gizmo);
    }

    public void removeGizmo(Gizmo gizmo){
        gizmos.remove(gizmo);
    }

    public void clearGizmos(){
        gizmos.clear();
    }

}
