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

public class AddGizmoListener implements ActionListener {

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
}
