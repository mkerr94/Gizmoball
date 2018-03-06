package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Listener class for RunView
public class RunListener implements ActionListener {

    @Override
    public final void actionPerformed(final ActionEvent e) {
        switch(e.getActionCommand()){
            case "Start":
                System.out.println("Start button pressed");
                break;

            case "Stop":
                System.out.println("Stop button pressed");
                break;

            case "Tick":
                System.out.println("Tick button pressed");
                break;
        }
    }
}
