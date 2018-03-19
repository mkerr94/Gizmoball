package Controller;

import Model.Model;
import View.GameBoard;
import View.BuildView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import Model.*;

public class AddGizmoListener implements ActionListener {

    private GameBoard gameBoard;
    private JComboBox gizmoList;
    private String gizmoToAdd;
    private MouseInputListener mouseInputListener;
    private Model model;
    private int x, y;
    private BuildView buildView;

    public AddGizmoListener(JComboBox gizmoList, Model model, GameBoard gameBoard, BuildView buildView){
        this.gizmoList = gizmoList;
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
                if (gizmoList.getSelectedItem() != null) {
                    gizmoToAdd = gizmoList.getSelectedItem().toString();
                }
                x = e.getX()/30; // L = 30
                y = e.getY()/30; // L = 30
                switch (gizmoToAdd) {
                    case "Circle":
                        Circle circle = new Circle(x, y);
                        if (model.checkGizmoLocation(circle)){
                            model.addGizmo(circle);
                        } else{
                            buildView.occupiedSpaceAlert();
                        }
                        break;
                    case "Square":
                        Square square = new Square(x, y);
                        if (model.checkGizmoLocation(square)){
                            model.addGizmo(square);
                        } else{
                            buildView.occupiedSpaceAlert();
                        }
                        break;
                    case "Triangle":
                        Triangle triangle = new Triangle(x, y, 0);
                        if (model.checkGizmoLocation(triangle)){
                            model.addGizmo(triangle);
                        } else{
                            buildView.occupiedSpaceAlert();
                        }
                        break;
                }
                gameBoard.removeMouseListener(this);
                e.consume();
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
