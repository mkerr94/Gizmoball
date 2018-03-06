package Model;

import Physics.LineSegment;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Model implements Observer {
    private static final int L = 25;
    private List<Gizmo> gizmos;
    private List<LineSegment> lineSegments;

    public List<Gizmo> getGizmos(){
        return gizmos;
    }

    public Model(){
    }

    public void addGizmo(Gizmo gizmo) {
        if (gizmo != null){
            gizmos.add(gizmo);
        }else{
            System.out.println("null gizmo");
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
