package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import Model.Model;
import Model.*;
import View.BuildView;
import View.GameBoard;

import javax.swing.event.MouseInputListener;

public class RotateListener implements ActionListener{
    private Model model;
    private GameBoard gameBoard;
    private MouseInputListener mouseInputListener;
    private int x, y;
    private BuildView buildView;

    public RotateListener(Model model, GameBoard gameBoard, BuildView buildView){
        this.model = model;
        this.gameBoard = gameBoard;
        this.buildView = buildView;
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
                if (gizmo != null) {
                    if (gizmo instanceof Flipper) {
                        if (model.checkValidFlipperRotation(gizmo)) {
                            model.rotateGizmo(gizmo);
                        } else {
                            buildView.occupiedSpaceAlert();
                        }
                    } else {
                        model.rotateGizmo(gizmo);
                    }
                }
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
