package Model;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Model implements Observer {
    private static final int L = 25;
    private List<Gizmo> gizmos;

    @Override
    public void update(Observable o, Object arg) {

    }
}
