package View;

import Model.LeftFlipper;
import Model.Model;
import Model.RightFlipper;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.RunListener;

class RunView extends JPanel {
    private Model model;
    private JLabel statusbar;
    private ActionListener listener;

    RunView(Model model) {
        this.model = model;
        model.addGizmo(new LeftFlipper(100, 100)); // hard-coding for testing
        model.addGizmo(new RightFlipper(277, 100));
        listener = new RunListener();
        init();
    }

     private void init() {

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        makeButtons(buttonPanel);
        add(buttonPanel, BorderLayout.EAST);

        //Generic board area
        /*JPanel board = new JPanel(new GridLayout(20,20));
        board.setBorder(BorderFactory.createLineBorder(Color.black,1));
        add(board, BorderLayout.CENTER);*/

        //Board panel from GameBoard
        add(new GameBoard(model), BorderLayout.CENTER);

        statusbar = new JLabel("Run Mode");
        statusbar.setBorder(BorderFactory.createEtchedBorder());
        add(statusbar, BorderLayout.SOUTH);

    }

    private void makeButtons(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton startB = new JButton("Start");
        startB.addActionListener(listener);

        JButton stopB = new JButton("Stop");
        stopB.addActionListener(listener);

        JButton tickB = new JButton("Tick");
        tickB.addActionListener(listener);

        panel.add(startB);
        panel.add(stopB);
        panel.add(tickB);
    }

}
