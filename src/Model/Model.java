package Model;

import java.util.*;

public class Model extends Observable {
    private List<IGizmo> gizmos;

    public Model(){
        gizmos = new ArrayList<>();
    }

    public List<IGizmo> getGizmos(){
        return gizmos;
    }

    /***
     * Adds anything that implements the Gizmo interface to Model's collection of gizmos
     * @param gizmo Gizmo to add to the model's collection
     */
    public void addGizmo(IGizmo gizmo) {
        if (gizmo != null){
            gizmos.add(gizmo);
            setChanged();
            notifyObservers();
        }else{
            System.out.println("null gizmo");
        }
    }

    /***
     * Empties the collection of gizmos and notifies the view,
     * so that when it repaints there is nothing to paint.
     */
    public void clearGizmos() {
        gizmos.clear();
        setChanged();
        notifyObservers();
    }
}
