/*
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class BuildView extends JFrame {

    private JLabel statusbar;

    public BuildView() {
        initUI();
    }

    private void initUI() {

        makeMenuBar();
        makeButtons();

        JPanel box = new JPanel(new GridLayout(20, 20));
        box.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        add(box, BorderLayout.CENTER);

        statusbar = new JLabel("Build Mode");
        statusbar.setBorder(BorderFactory.createEtchedBorder());
        add(statusbar, BorderLayout.SOUTH);

        setTitle("Gizmoball!");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void makeMenuBar() {
        JMenuItem open = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuBar menuBar = new JMenuBar();
        JMenu buildMenu = new JMenu("Mode");

        JMenu file = new JMenu("File");
        menuBar.add(file);
        file.add(open);
        file.add(save);
        file.add(exit);

        exit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        ButtonGroup modeGroup = new ButtonGroup();

        JRadioButtonMenuItem buildMode = new JRadioButtonMenuItem("Build Mode");
        buildMode.setSelected(true);
        buildMenu.add(buildMode);

        buildMode.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusbar.setText("Build Mode");
            }
        });

        JRadioButtonMenuItem runMode = new JRadioButtonMenuItem("Run Mode");
        buildMenu.add(runMode);

        runMode.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusbar.setText("Run Mode");
            }
        });

        modeGroup.add(buildMode);
        modeGroup.add(runMode);
        menuBar.add(buildMenu);
        setJMenuBar(menuBar);
    }

    private void makeButtons() {

        JPanel panel = new JPanel(new GridLayout(10, 1, 10, 10));
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

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            BuildView ex = new BuildView();
            ex.setVisible(true);
        });
    }
}*/
