package Controller;

import Model.Flipper;
import Model.RightFlipper;
import Model.LeftFlipper;
import Model.IGizmo;
import Model.Model;
import View.GameBoard;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FlipperListener implements ActionListener, KeyListener {

    private GameBoard gameBoard;
    private JComboBox gizmoList;
    private Model model;
    private final List<Flipper> flippers;
    private final List<Flipper> leftflippers;
    private final List<Flipper> rightflippers;
    private final int leftFlipperTrigger = KeyEvent.VK_Z;
    private final int rightFlipperTrigger = KeyEvent.VK_M;
    private final int globalFlipperTrigger = KeyEvent.VK_SPACE;
    private List<IGizmo> gizmos;
    private Timer timer;

    public FlipperListener(Model model, GameBoard gameBoard) {
        flippers = new ArrayList<>();
        rightflippers = new ArrayList<>();
        leftflippers = new ArrayList<>();
        this.model = model;
        this.gameBoard = gameBoard;
        gizmos = model.getGizmos();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == globalFlipperTrigger) {
            for (Flipper flipper : flippers) {
                flipper.flip();
            }
        }
        if (e.getKeyCode() == leftFlipperTrigger) {
            for (Flipper flipper : leftflippers) {
                flipper.flip();
            }
        }
        if (e.getKeyCode() == rightFlipperTrigger) {
            for (Flipper flipper : rightflippers) {
                flipper.flip();
            }
        }

        // flipper connections
        if (!model.checkKeyConnection(e.getKeyCode())) {
            for (Flipper flipper : rightflippers) {
                flipper.flip();
            }
        }
        if (!model.checkKeyConnection(e.getKeyCode())) {
            for (Flipper flipper : leftflippers) {
                flipper.flip();
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == globalFlipperTrigger) {
            for (Flipper flipper : flippers) {
                flipper.unflip();
            }
        }
        if (e.getKeyCode() == leftFlipperTrigger) {
            for (Flipper flipper : leftflippers) {
                flipper.unflip();
            }
        }
        if (e.getKeyCode() == rightFlipperTrigger) {
            for (Flipper flipper : rightflippers) {
                flipper.unflip();
            }
        }

        // flipper connections
        if (!model.checkKeyConnection(e.getKeyCode())) {
            for (Flipper flipper : rightflippers) {
                flipper.unflip();
            }
        }
        if (!model.checkKeyConnection(e.getKeyCode())) {
            for (Flipper flipper : leftflippers) {
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
            for (Flipper flipper : flippers) {
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
            if (gizmo instanceof RightFlipper) {
                if (!rightflippers.contains(gizmo)) {
                    rightflippers.add((Flipper)gizmo);
                }
            }
            if (gizmo instanceof LeftFlipper) {
                if (!leftflippers.contains(gizmo)) {
                    leftflippers.add((Flipper)gizmo);
                }
            }
        }
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
        timer.addActionListener(this);
    }
}
