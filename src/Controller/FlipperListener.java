package Controller;

import Model.Flipper;
import Model.IGizmo;
import Model.Model;
import View.GameBoard;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FlipperListener implements ActionListener, KeyListener{

    private GameBoard gameBoard;
    private JComboBox gizmoList;
    private Model model;
    private int x, y;
    private final List<Flipper> flippers;
    private final int globalKeyTrigger = KeyEvent.VK_SPACE;
    private List<IGizmo> gizmos;
    private Timer timer;

    public FlipperListener(Model model, GameBoard gameBoard){
        flippers = new ArrayList<>();
        this.model = model;
        this.gameBoard = gameBoard;
        gizmos = model.getGizmos();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (Flipper flipper : flippers) {
                flipper.flip();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (Flipper flipper : flippers) {
                flipper.unflip();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            for (Flipper flipper :
                    flippers) {
                flipper.setAngle(flipper.getAngle() + flipper.getAngularMomentum());
            }
        }
    }

    public void update() {
        gizmos = model.getGizmos();
        for (IGizmo gizmo : gizmos) {
            if (gizmo instanceof Flipper) {
                if (!flippers.contains(gizmo)) {
                    flippers.add((Flipper)gizmo);
                }
            }
        }
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
        timer.addActionListener(this);
    }
}
