package Controller;

import Model.Model;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAbsorberListener implements ActionListener {

    private Model model;
    private JComboBox gizmoList;

    public AddAbsorberListener(JComboBox gizmoList, Model model){
        this.model = model;
        this.gizmoList = gizmoList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            gizmoList.getSelectedItem();
            model.addGizmo(new Absorber(0, 20, 20, 1));
        }
}
