package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import Model.Model;
import Model.*;
import View.GameBoard;

import javax.swing.event.MouseInputListener;

public class RotateListener implements ActionListener{
    private Model model;
    private GameBoard gameBoard;
    private MouseInputListener mouseInputListener;
    private int x, y;

    public RotateListener(Model model, GameBoard gameBoard){
        this.model = model;
        this.gameBoard = gameBoard;
        mouseInputListener = new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = e.getX() / 30;
                y = e.getY() / 30;
                IGizmo gizmo = model.getGizmo(x, y);
                // todo check for flipper overlaps before rotating
                model.rotateGizmo(gizmo);
                gameBoard.removeMouseListener(this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameBoard.addMouseListener(mouseInputListener);
    }
}
