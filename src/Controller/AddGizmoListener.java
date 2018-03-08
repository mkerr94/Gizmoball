package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGizmoListener implements ActionListener {

    private JComboBox gizmoList;
    private String gizmoToAdd;

    public AddGizmoListener(JComboBox gizmoList){
        this.gizmoList = gizmoList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gizmoList.getSelectedItem() != null) {
            gizmoToAdd = gizmoList.getSelectedItem().toString();
        }
        System.out.println("add gizmo: " + gizmoToAdd);
    }
}
