package Controller;
import Model.Model;
import View.GameBoard;
import java.awt.event.*;

public class FireBallListener implements KeyListener, ActionListener {

    private final int globalKeyTrigger = KeyEvent.VK_F;

    private Model m;
    private GameBoard gameBoard;

    public FireBallListener(Model m, GameBoard gameBoard){
        this.m = m;
        this.gameBoard = gameBoard;
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
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger) {
            m.fireBall();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m.fireBall();
        System.out.println("F pressed");
    }
}

