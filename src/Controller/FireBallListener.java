package Controller;
import Model.Model;
import java.awt.event.*;

public class FireBallListener implements KeyListener {

    private final int globalKeyTrigger = KeyEvent.VK_SPACE;

    private Model m;

    public FireBallListener(Model m){
        this.m = m;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger) {
            m.fireBall();
        }
    }

    @Override
    public void keyReleased(KeyEvent space) {
        m.fireBall();
    }
}

