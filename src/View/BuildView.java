package View;

import Controller.AddAbsorberListener;
import Controller.AddGizmoListener;
import Controller.ClearBoardListener;
import Model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.BuildListener;



class BuildView extends JPanel {
    private Model model;
    private JLabel statusbar;
    private ActionListener listener;

    BuildView(Model model) {
        this.model = model;
        listener = new BuildListener();
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        makeButtons(buttonPanel);
        add(buttonPanel, BorderLayout.EAST);
        add(new GameBoard(600, 600, model, 'b'), BorderLayout.CENTER);
        statusbar = new JLabel("Build Mode");
        statusbar.setBorder(BorderFactory.createEtchedBorder());
        add(statusbar, BorderLayout.SOUTH);
    }

    private void makeButtons(JPanel panel) {

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] bumpers = {"Circle", "Square", "Triangle"};
        JComboBox bumperList = new JComboBox(bumpers);
        ((JLabel) bumperList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        String[] flippers = {"Right flipper", "Left flipper"};
        JComboBox flipperList = new JComboBox(flippers);
        ((JLabel) flipperList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        //Create buttons
        JButton addGizmoButton = new JButton("Add gizmo");
        JButton flipperB = new JButton("Add flipper");
        JButton addBall = new JButton("Add ball");
        JButton addAbsorber = new JButton("Add absorber");
        JButton moveB = new JButton("Move");
        JButton rotateB = new JButton("Rotate");
        JButton deleteB = new JButton("Delete");
        JButton clearB = new JButton("Clear");
        JButton frictionB = new JButton("Change friction");
        JButton gravityB = new JButton("Change gravity");
        JButton connectB = new JButton("Connect");
        JButton disconnectB = new JButton("Disconnect");
        JButton keyConnectB = new JButton("Key connect");
        JButton keyDisconnectB = new JButton("Key disconnect");

        //Add buttons to screen
        panel.add(bumperList);
        panel.add(addGizmoButton);
        panel.add(flipperList);
        panel.add(flipperB);
        panel.add(addBall);
        panel.add(addAbsorber);
        panel.add(moveB);
        panel.add(rotateB);
        panel.add(deleteB);
        panel.add(clearB);
        panel.add(frictionB);
        panel.add(gravityB);
        panel.add(connectB);
        panel.add(disconnectB);
        panel.add(keyConnectB);
        panel.add(keyDisconnectB);
        add(panel, BorderLayout.EAST);

        //Action listeners
        addGizmoButton.addActionListener(new AddGizmoListener(bumperList, model));
        flipperB.addActionListener(listener);
        addBall.addActionListener(listener);
        addAbsorber.addActionListener(new AddAbsorberListener(model));
        moveB.addActionListener(listener);
        rotateB.addActionListener(listener);
        deleteB.addActionListener(listener);
        clearB.addActionListener(new ClearBoardListener(model));
        frictionB.addActionListener(listener);
        gravityB.addActionListener(listener);
        connectB.addActionListener(listener);
        disconnectB.addActionListener(listener);
        keyConnectB.addActionListener(listener);
        keyDisconnectB.addActionListener(listener);
    }
}
