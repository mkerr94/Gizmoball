package Controller;

import Model.Model;
import View.GameBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import Model.*;

public class AddGizmoListener implements ActionListener, MouseListener {

    private JComboBox gizmoList;
    private String gizmoToAdd;
    private Model model;
    private int x, y;

    public AddGizmoListener(JComboBox gizmoList, Model model){
        this.gizmoList = gizmoList;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gizmoList.getSelectedItem() != null) {
            gizmoToAdd = gizmoList.getSelectedItem().toString();
        }

        x = (int) (Math.random() * (20 - 1)) + 1;
        y = (int) (Math.random() * (20 - 1)) + 1;

        switch (gizmoToAdd) {
            case "Circle":
                model.addGizmo(new Circle(x, y));
                break;
            case "Square":
                model.addGizmo(new Square(x, y));
                break;
            case "Triangle":
                model.addGizmo(new Triangle(x, y));
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x1 = e.getX();
        int y1 = e.getY();
        System.out.println(x1 + "," + y1);//these co-ords are relative to the component

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
