package View;

import Controller.*;
import Model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BuildView extends JPanel {
    private Model model;
    private ActionListener listener;
    private GameBoard gameBoard;
    private Color purple;
    JLabel statusbar;
    String result1;
    String result2;
    double xv;
    double xy;

    BuildView(Model model, GameBoard gameBoard) {
        this.model = model;
        listener = new BuildListener();
        this.gameBoard = gameBoard;
        this.gameBoard.setMode(Mode.BUILD);
        init();
        purple = new Color(128, 0, 128);
    }

    private void init() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        buttonPanel.setBackground(purple = new Color(128, 0, 128));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(buttonPanel, BorderLayout.EAST);
        add(gameBoard, BorderLayout.CENTER);
        setBackground(purple);
        statusbar = new JLabel("Build Mode");
        statusbar.setBorder(BorderFactory.createEtchedBorder(purple, purple));
        statusbar.setForeground(Color.white);
        add(statusbar, BorderLayout.SOUTH);
        makeButtons(buttonPanel);
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

        //Action listeners
        addGizmoButton.addActionListener(e -> statusbar.setText("Add Gizmo"));
        addGizmoButton.addActionListener(new AddGizmoListener(bumperList, model, gameBoard, this));
        flipperB.addActionListener(e -> statusbar.setText("Add Flipper"));
        flipperB.addActionListener(new AddFlipperListener(flipperList, model, gameBoard, this));
        addBall.addActionListener(e -> statusbar.setText("Add Ball"));
        addBall.addActionListener(new AddBallListener(model, gameBoard, this));
        addAbsorber.addActionListener(e -> statusbar.setText("Add Absorber"));
        addAbsorber.addActionListener(new AddAbsorberListener(model, gameBoard, this));
        moveB.addActionListener(e -> statusbar.setText("Move Gizmo"));
        moveB.addActionListener(new MoveListener(model, gameBoard, this));
        rotateB.addActionListener(e -> statusbar.setText("Rotate Gizmo"));
        rotateB.addActionListener(new RotateListener(model, gameBoard));
        deleteB.addActionListener(e -> statusbar.setText("Delete Gizmo"));
        deleteB.addActionListener(new DeleteListener(model, gameBoard));
        clearB.addActionListener(e -> statusbar.setText("Clear Board"));
        clearB.addActionListener(new ClearBoardListener(model, this));
        frictionB.addActionListener(e -> statusbar.setText("Change Friction"));
        frictionB.addActionListener(new ChangeFrictionListener(model, this));
        gravityB.addActionListener(e -> statusbar.setText("Change Gravity"));
        gravityB.addActionListener(new ChangeGravityListener(model, this));
        connectB.addActionListener(listener);
        connectB.addActionListener(e -> statusbar.setText("Add Connection"));
        disconnectB.addActionListener(listener);
        disconnectB.addActionListener(e -> statusbar.setText("Disconnect Gizmo"));
        keyConnectB.addActionListener(new KeyConnectListener(model, gameBoard, this));
        keyConnectB.addActionListener(e -> statusbar.setText("Add Key Connection"));
        keyDisconnectB.addActionListener(new KeyDisconnectListener(model,gameBoard,this));
        keyDisconnectB.addActionListener(e -> statusbar.setText("Disconnect Key"));
    }

    public void ballVelocityAlert() {
        JTextField vxField = new JTextField(5);
        JTextField vyField = new JTextField(5);
        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("Velocity of x: "));
        myPanel.add(vxField);

        myPanel.add(new JLabel("Velocity of y: "));
        myPanel.add(vyField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, "Add ball", JOptionPane.OK_CANCEL_OPTION);

        if (result == 0) {
            xv = Double.parseDouble(vxField.getText());
            xy = Double.parseDouble(vyField.getText());
        }
    }

    public double getBallxv() {
        return xv;
    }

    public double getBallxy() {
        return xy;
    }

    public void invalidVelocityAlert(){
        JOptionPane.showMessageDialog(null,"Your velocity values must be within -1000 and 1000");
    }

    public int clearBoardAlert() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the board?", "Clear board", JOptionPane.YES_NO_OPTION);
        return result;
    }

    public int changeGravityAlert() {
        String result = JOptionPane.showInputDialog("Please enter a value for gravity: ");
        if (result != null) {
            int gravity = Integer.parseInt(result);
            return gravity;
        }
        return 25;
    }

    public double changeFrictionAlert() {
        String result = JOptionPane.showInputDialog("Please enter a value for friction: ");
        if (result != null) {
            double friction = Double.parseDouble(result);
            return friction;
        }
        return 0.025;
    }

    public void invalidGravityAlert(){
        JOptionPane.showMessageDialog(null,"Your value for gravity must be greater than 0.");
    }

    public void invalidFrictionAlert(){
        JOptionPane.showMessageDialog(null,"Your value for friction must be greater than 0.");
    }

    public void occupiedSpaceAlert() {
        JOptionPane.showMessageDialog(null, "This space is already occupied! Please choose an empty space.");
    }

    public void keyConnectAlert(){
        JOptionPane.showMessageDialog(null,"Connect successful!");
    }

    public void keyDisconnectAlert(){
        JOptionPane.showMessageDialog(null,"Disconnect successful!");
    }

    public void invalidKeyAlert(){JOptionPane.showMessageDialog(null,"This key is already being used for another connection. Pleases try again."); }

}
