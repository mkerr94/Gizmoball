package Controller;

import Model.Model;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAbsorberListener implements ActionListener {

    private Model model;

    public AddAbsorberListener(Model model){
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Absorber added");
        model.addGizmo(new Absorber(0, 20, 20, 1));
    }
}
