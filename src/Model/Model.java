package Model;

import Physics.LineSegment;

import java.util.*;

public class Model extends Observable {
    private static final int L = 25;
    private List<Gizmo> gizmos;
    private List<LineSegment> lineSegments;

    public Model(){
        gizmos = new ArrayList<>();
    }

    public List<Gizmo> getGizmos(){
        return gizmos;
    }

    /***
     * Adds anything that implements the Gizmo interface to Model's collection of gizmos
     * @param gizmo Gizmo to add to the model's collection
     */
    public void addGizmo(Gizmo gizmo) {
        if (gizmo != null){
            gizmos.add(gizmo);
        }else{
            System.out.println("null gizmo");
        }
    }
}
