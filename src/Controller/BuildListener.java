package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Ball;

//Listener class for BuildView
public class BuildListener implements ActionListener{

    @Override
    public final void actionPerformed(final ActionEvent e) {

        switch(e.getActionCommand()){

            case "Add gizmo":
                System.out.println("Add gizmo button pressed");
                break;

            case "Add flipper":
                System.out.println("Add flipper button pressed");
                break;

            case "Add ball":
                System.out.println("Add ball button pressed");


                break;

            case "Add absorber":
                System.out.println("Add absorber button pressed");
                break;

            case "Move":
                break;

            case "Rotate":
                break;

            case "Delete":
                break;

            case "Clear":
                JOptionPane.showConfirmDialog(null,"Are you sure you want to clear the board?","Clear board",JOptionPane.YES_NO_OPTION);
                break;

            case "Change friction":
                String frictionPopUp = JOptionPane.showInputDialog("Please enter a value for friction: ");
                break;

            case "Change gravity":
                String gravityPopUp = JOptionPane.showInputDialog("Please enter a value for gravity: ");
                break;

            case "Connect":
                break;

            case "Disconnect":
                break;

            case "Key connect":
                break;

            case "Key disconnect":
                break;
        }

    }
}
