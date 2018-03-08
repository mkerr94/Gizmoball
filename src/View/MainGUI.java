package View;

import Controller.MainGUIListener;
import Model.Model;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;

public class MainGUI extends JFrame {
    private Model model;
    private MainGUIListener controller;
    private JPanel viewMode;
    private LoadFile loadedFile;

    public MainGUI(LoadFile lf){
        this.loadedFile = lf;
        controller = new MainGUIListener(this);
        init();
    }

    private void init() {
        makeMenuBar();
        viewMode = new RunView(loadedFile);
        setContentPane(viewMode);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void makeMenuBar() {
        LoadFile lf = new LoadFile();
        setTitle("GizmoBall - Run Mode");
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        file.add(open);
        file.add(save);
        file.add(exit);
        menuBar.add(file);
        open.addActionListener(evt -> {
            try {
                lf.getLoadFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        exit.addActionListener((ActionEvent event) -> System.exit(0));


        ButtonGroup modeGroup = new ButtonGroup();
        JMenu modeMenu = new JMenu("Mode");
        JRadioButtonMenuItem buildMode = new JRadioButtonMenuItem("Build Mode");
        JRadioButtonMenuItem runMode = new JRadioButtonMenuItem("Run Mode");
        modeMenu.add(buildMode);
        modeMenu.add(runMode);
        runMode.setSelected(true);

        buildMode.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                setTitle("GizmoBall - Build Mode");
                viewMode.setVisible(false);
                viewMode = new BuildView(loadedFile);
                viewMode.setVisible(true);
                setContentPane(viewMode);
            }
        });
        runMode.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                setTitle("GizmoBall - Run Mode");
                viewMode.setVisible(false);
                viewMode = new RunView(loadedFile);
                viewMode.setVisible(true);
                setContentPane(viewMode);
            }
        });
        modeGroup.add(buildMode);
        modeGroup.add(runMode);
        menuBar.add(modeMenu);
        setJMenuBar(menuBar);
    }
}
