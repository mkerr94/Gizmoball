package View;

import Model.LeftFlipper;
import Model.Model;
import Model.RightFlipper;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

class RunView extends JPanel {
    private Model model;

    RunView(Model model) {
        this.model = model;
        model.addGizmo(new LeftFlipper(100, 100)); // hard-coding for testing
        model.addGizmo(new RightFlipper(277, 100));
        init();
    }

    private void init() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 20, 5));
        makeButtons(buttonPanel);
        add(buttonPanel);
        add(new GameBoard(model));
    }

    private void makeButtons(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton startB = new JButton("Start");
        JButton stopB = new JButton("Stop");
        JButton tickB = new JButton("Tick");
        panel.add(startB);
        panel.add(stopB);
        panel.add(tickB);
    }

}
