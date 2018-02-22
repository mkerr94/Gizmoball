package Controller;

import Model.Flipper;
import Model.Gizmo;
import Model.Model;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class FlipperListener implements KeyListener{
    private final List<Flipper> flippers;
    private final int globalKeyTrigger = KeyEvent.VK_SPACE;

    public FlipperListener(Model model){
        flippers = new ArrayList<>();
        List<Gizmo> gizmos = model.getGizmos();
        for (Gizmo gizmo :
                gizmos) {
            if (gizmo.getClass().getSuperclass().equals(Flipper.class)) {
                flippers.add((Flipper)gizmo);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (Flipper flipper :
                    flippers) {
                flipper.flip();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (Flipper flipper :
                    flippers) {
                flipper.unflip();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
