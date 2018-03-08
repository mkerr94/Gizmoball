package Controller;

import Model.Model;
import View.GameBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Model.*;

public class AddGizmoListener implements ActionListener {

    private JComboBox gizmoList;
    private String gizmoToAdd;
    private Model model;

    public AddGizmoListener(JComboBox gizmoList, Model model){
        this.gizmoList = gizmoList;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gizmoList.getSelectedItem() != null) {
            gizmoToAdd = gizmoList.getSelectedItem().toString();
        }
        int x = (int) (Math.random() * (20 - 1)) + 1;
        int y = (int) (Math.random() * (20 - 1)) + 1;
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
