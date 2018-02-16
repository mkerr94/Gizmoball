package Controller;

import Model.Flipper;
import Physics.LineSegment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class FlipperController implements KeyListener{
    private final List<Flipper> flippers;
    private final int globalKeyTrigger = KeyEvent.VK_SPACE;

    public FlipperController(List<Flipper> flippers){
        this.flippers = flippers;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (Flipper flipper :
                    flippers) {
                flipper.flip();
                System.out.println("flip");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (Flipper flipper :
                    flippers) {
                flipper.unflip();
                System.out.println("unflip");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
