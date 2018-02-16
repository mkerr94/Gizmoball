package View;

import Controller.FlipperController;
import Model.Flipper;
import Model.LeftFlipper;
import Model.RightFlipper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private JLabel statusbar;
    private List<Flipper> model;
    private FlipperController controller;

    public GUI(){
        model = new ArrayList<>();
        model.add(new LeftFlipper(100, 100));
        model.add(new RightFlipper(277, 100));
        controller = new FlipperController(model);
        init();
    }

    private void init() {
        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        makeMenuBar();
        makeButtons(buttonPanel);
        //Put it in a scrollpane (makes a border)
        JScrollPane scrollPane = new JScrollPane(new FlipperPanel(model, controller));
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(800, 800));
        contentPane.add(buttonPanel, BorderLayout.EAST);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        setContentPane(contentPane);

        statusbar = new JLabel("Run Mode");
        statusbar.setBorder(BorderFactory.createEtchedBorder());
        add(statusbar, BorderLayout.SOUTH);

        setTitle("Gizmoball");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        exit.addActionListener((ActionEvent event) -> System.exit(0));

        ButtonGroup modeGroup = new ButtonGroup();

        JRadioButtonMenuItem buildMode = new JRadioButtonMenuItem("Build Mode");
        buildMenu.add(buildMode);

        buildMode.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusbar.setText("Build Mode");
            }
        });

        JRadioButtonMenuItem runMode = new JRadioButtonMenuItem("Run Mode");
        runMode.setSelected(true);
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
    private void makeButtons(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton startB = new JButton("Start");
        JButton stopB = new JButton("Stop");
        panel.add(startB);
        panel.add(stopB);
    }
}
