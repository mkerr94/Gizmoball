package View;

import Model.Model;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Controller.RunListener;

class RunView extends JPanel {
    private Model model;
    private RunListener runListener;

    RunView(Model model) {
        this.model = model;
        runListener = new RunListener(model);
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        makeButtons(buttonPanel);
        add(buttonPanel, BorderLayout.EAST);
        add(new GameBoard(600, 600, model, Mode.RUN), BorderLayout.CENTER);
        JLabel statusBar = new JLabel("Run Mode");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);
    }

    private void makeButtons(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton startB = new JButton("Start");
        startB.setPreferredSize(new Dimension(245, 100));
        startB.addActionListener(runListener);
        JButton stopB = new JButton("Stop");
        stopB.addActionListener(runListener);
        JButton tickB = new JButton("Tick");
        tickB.addActionListener(runListener);
        panel.add(startB);
        panel.add(stopB);
        panel.add(tickB);
    }
}
