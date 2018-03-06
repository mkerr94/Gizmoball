package View;

import Model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

class BuildView extends JPanel {
    private Model model;
    private JLabel statusbar;

    BuildView(Model model) {
        this.model = model;
        init();
    }

    private void init() {

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        makeButtons(buttonPanel);
        add(buttonPanel, BorderLayout.EAST);

        //Generic board area
       /* JPanel board = new JPanel(new GridLayout(20,20));
        board.setBorder(BorderFactory.createLineBorder(Color.black,1));
        add(board, BorderLayout.CENTER);*/

       //Board panel from GameBoard
       add(new GameBoard(model), BorderLayout.CENTER);

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

        JButton addB = new JButton("Add gizmo");
        JButton flipperB = new JButton("Add flipper");
        JButton addBall = new JButton("Add Ball");
        JButton addAbsorber = new JButton("Add Absorber");
        JButton moveB = new JButton("Move");
        JButton rotateB = new JButton("Rotate");
        JButton deleteB = new JButton("Delete");
        JButton clearB = new JButton("Clear");
        JButton frictionB = new JButton("Change Friction");
        JButton gravityB = new JButton("Change Gravity");
        JButton connectB = new JButton("Connect");
        JButton disconnectB = new JButton("Disconnect");
        JButton keyConnectB = new JButton("Key Connect");
        JButton keyDisconnectB = new JButton("Key Disconnect");

        panel.add(bumperList);
        panel.add(addB);
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

        clearB.addActionListener((ActionEvent event) -> {
            JOptionPane.showConfirmDialog(panel,"Are you sure you want to clear the board?","Clear board",JOptionPane.YES_NO_OPTION);
        });

        addBall.addActionListener((ActionEvent event) -> {
            JTextField xField = new JTextField(2);
            JTextField yField = new JTextField(2);
            JTextField vField = new JTextField(2);
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("X co-ordinate: "));
            myPanel.add(xField);
            myPanel.add(new JLabel("Y co-ordinate: "));
            myPanel.add(yField);
            myPanel.add(new JLabel("Velocity: "));
            myPanel.add(vField);

            int result = JOptionPane.showConfirmDialog(null, myPanel, "Add ball", JOptionPane.OK_CANCEL_OPTION);
        });

        frictionB.addActionListener((ActionEvent event) -> {
            String frictionPopUp = JOptionPane.showInputDialog("Please enter a value for friction: ");
        });

        gravityB.addActionListener((ActionEvent event) -> {
            String gravityPopUp = JOptionPane.showInputDialog("Please enter a value for gravity: ");
        });
    }

}
