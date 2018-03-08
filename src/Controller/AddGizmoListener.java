package Controller;

import Model.Model;
import View.GameBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        switch (gizmoToAdd) {
            case "Circle":
                model.addGizmo(new Circle(5, 5));
                break;
            case "Square":
                model.addGizmo(new Square(5, 5));
                break;
            case "Triangle":
                model.addGizmo(new Triangle(5, 5));
                break;
        }
    }
}
