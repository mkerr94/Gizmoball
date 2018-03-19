package Controller;

import Model.Model;
import Model.*;
import View.GameBoard;
import View.BuildView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class AddAbsorberListener implements ActionListener {

    private GameBoard gameBoard;
    private Model model;
    private MouseInputListener mouseInputListener;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private BuildView buildView;

    public AddAbsorberListener(Model model, GameBoard gameBoard, BuildView buildView){
        this.model = model;
        this.gameBoard = gameBoard;
        this.buildView = buildView;
        mouseInputListener = new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX()/30;
                y1 = e.getY()/30;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = (e.getX()/30) + 1; // L = 30. +1 so that you don't need to bypass a square completely to add the absorber to it
                y2 = (e.getY()/30) + 1; // L = 30
                int dx = x2 - x1;
                int dy = y2 - y1;
                Absorber absorber = new Absorber(x1,y1,dx,dy);

                //doesn't give error message - not sure how to fix
                if (model.checkGizmoLocation(absorber)) {
                    model.addGizmo(absorber);
                }else{
                    buildView.occupiedSpaceAlert();
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
