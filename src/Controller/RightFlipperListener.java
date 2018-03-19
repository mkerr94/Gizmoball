package Controller;

import Model.Flipper;
import Model.RightFlipper;
import Physics.LineSegment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class RightFlipperListener implements KeyListener{
   // private final List<RightFlipper> rightflippers;
   public static List<RightFlipper> rightflippers;
    private final int globalKeyTrigger = KeyEvent.VK_SHIFT;

    public RightFlipperListener(List<RightFlipper> rightflippers){
        AddFlipperListener.rightflippers = rightflippers;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (RightFlipper flipper : rightflippers) {
                flipper.flip();
                System.out.println("flip");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (RightFlipper flipper : rightflippers) {
                flipper.unflip();
                System.out.println("unflip");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
