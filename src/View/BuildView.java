package View;

import Controller.*;
import Model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class BuildView extends JPanel {
    private Model model;
    private ActionListener listener;
    private GameBoard gameBoard;
    private Color purple;
    String result1;
    String result2;
    double xv;
    double xy;

    BuildView(Model model) {
        this.model = model;
        listener = new BuildListener();
        gameBoard = new GameBoard(600, 600, model, Mode.BUILD);
        init();
        purple = new Color(128,0,128);
    }

    private void init() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        buttonPanel.setBackground(purple = new Color(128,0,128));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        makeButtons(buttonPanel);
        add(buttonPanel, BorderLayout.EAST);
        add(gameBoard, BorderLayout.CENTER);
        JLabel statusbar = new JLabel("Build Mode");
        statusbar.setBorder(BorderFactory.createEtchedBorder(purple, purple));
        setBackground(purple);
        statusbar.setForeground(Color.white);
        add(statusbar, BorderLayout.SOUTH);
    }

    private void makeButtons(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        Icon circle = new ImageIcon((new ImageIcon("ball.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
        Icon square = new ImageIcon((new ImageIcon("square.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
        Icon triangle = new ImageIcon((new ImageIcon("triangle.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
        String[] bumpers = {"Circle", "Square", "Triangle"};
        Icon[] images = {circle, square, triangle};
        JComboBox bumperList = new JComboBox(bumpers);
        bumperList.setBackground(Color.white);
        ((JLabel) bumperList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        String[] flippers = {"Right flipper", "Left flipper"};
        JComboBox flipperList = new JComboBox(flippers);
        flipperList.setBackground(Color.white);
        ((JLabel) flipperList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        //Create buttons
        JButton addGizmoButton = new JButton("Add gizmo");
        addGizmoButton.setBackground(Color.white);
        JButton flipperB = new JButton("Add flipper");
        flipperB.setBackground(Color.white);
        JButton addBall = new JButton("Add ball");
        addBall.setBackground(Color.white);
        JButton addAbsorber = new JButton("Add absorber");
        addAbsorber.setBackground(Color.white);
        JButton moveB = new JButton("Move");
        moveB.setBackground(Color.white);
        JButton rotateB = new JButton("Rotate");
        rotateB.setBackground(Color.white);
        JButton deleteB = new JButton("Delete");
        deleteB.setBackground(Color.white);
        JButton clearB = new JButton("Clear");
        clearB.setBackground(Color.white);
        JButton frictionB = new JButton("Change friction");
        frictionB.setBackground(Color.white);
        JButton gravityB = new JButton("Change gravity");
        gravityB.setBackground(Color.white);
        JButton connectB = new JButton("Connect");
        connectB.setBackground(Color.white);
        JButton disconnectB = new JButton("Disconnect");
        disconnectB.setBackground(Color.white);
        JButton keyConnectB = new JButton("Key connect");
        keyConnectB.setBackground(Color.white);
        JButton keyDisconnectB = new JButton("Key disconnect");
        keyDisconnectB.setBackground(Color.white);

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

        //Action listenersw
        addGizmoButton.addActionListener(new AddGizmoListener(bumperList, model, gameBoard));
        flipperB.addActionListener(listener);
        addBall.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e){
                JTextField vxField = new JTextField(1);
                JTextField vyField = new JTextField(1);
                JPanel myPanel = new JPanel();

                myPanel.add(new JLabel("Velocity of x: "));
                myPanel.add(vxField);

                myPanel.add(new JLabel("Velocity of y: "));
                myPanel.add(vyField);

                int result = JOptionPane.showConfirmDialog(null, myPanel, "Add ball", JOptionPane.OK_CANCEL_OPTION);

                if(result == 0) {
                    xv = Double.parseDouble(vxField.getText());
                    xy = Double.parseDouble(vyField.getText());
                }

                System.out.println(xv);
                System.out.println(xy);
            }
        });
        addBall.addActionListener(new AddBallListener(model,gameBoard, xv, xy));
        addAbsorber.addActionListener(new AddAbsorberListener(model, gameBoard));
        moveB.addActionListener(listener);
        rotateB.addActionListener(listener);
        deleteB.addActionListener(new DeleteListener(model,gameBoard));
        clearB.addActionListener(new ClearBoardListener(model));
        frictionB.addActionListener(listener);
        gravityB.addActionListener(listener);
        connectB.addActionListener(listener);
        disconnectB.addActionListener(listener);
        keyConnectB.addActionListener(listener);
        keyDisconnectB.addActionListener(listener);
    }
}
