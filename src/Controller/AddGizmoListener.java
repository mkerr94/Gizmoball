package Controller;

import Model.Model;
import View.GameBoard;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import Model.*;

public class AddGizmoListener implements ActionListener {

    private GameBoard gameBoard;
    private JComboBox gizmoList;
    private String gizmoToAdd;
    private Model model;
    private int x, y;

    public AddGizmoListener(JComboBox gizmoList, Model model, GameBoard gameBoard){
        this.gizmoList = gizmoList;
        this.model = model;
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameBoard.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.consume();
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
                            System.out.println("Gizmo already exists on that location");
                        }
                        break;
                    case "Square":
                        Square square = new Square(x, y);
                        if (model.checkGizmoLocation(square)){
                            model.addGizmo(square);
                        } else{
                            System.out.println("Gizmo already exists on that location");
                        }
                        break;
                    case "Triangle":
                        Triangle triangle = new Triangle(x, y);
                        if (model.checkGizmoLocation(triangle)){
                            model.addGizmo(triangle);
                        } else{
                            System.out.println("Gizmo already exists on that location");
                        }
                        break;
                }
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
        });
    }
}
