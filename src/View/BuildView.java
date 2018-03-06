package View;

import Model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

class BuildView extends JPanel {
    private Model model;

    BuildView(Model model) {
        this.model = model;
        System.out.println("now in the buildview content pane");
    }

    private class GameBoard extends JPanel implements Observer, ActionListener{
        @Override
        public void update(Observable o, Object arg) {

        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
