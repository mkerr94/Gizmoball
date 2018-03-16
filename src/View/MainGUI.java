package View;

import Model.Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class MainGUI extends JFrame {
    private Model model;
    private JPanel viewMode;

    public MainGUI(Model model){
        this.model = model;
        makeMenuBar();
        viewMode = new Welcome();
        setContentPane(viewMode);
        setSize(885, 684);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /***
     * Constructs the menu bar of the application.
     */
    private void makeMenuBar() {
        setTitle("GizmoBall - Welcome Mode");
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        file.add(open);
        file.add(save);
        file.add(exit);
        menuBar.add(file);

        open.addActionListener(evt -> new LoadFile(model));
        save.addActionListener(evt -> new SaveFile(model));
        exit.addActionListener((ActionEvent event) -> System.exit(0));

        ButtonGroup modeGroup = new ButtonGroup();
        JMenu modeMenu = new JMenu("Mode");
        JRadioButtonMenuItem buildMode = new JRadioButtonMenuItem("Build Mode");
        JRadioButtonMenuItem runMode = new JRadioButtonMenuItem("Run Mode");
        modeMenu.add(buildMode);
        modeMenu.add(runMode);
        buildMode.addItemListener((ItemEvent e) -> { // if build mode has been selected
            if (e.getStateChange() == ItemEvent.SELECTED) {
                setTitle("GizmoBall - Build Mode");
                viewMode.setVisible(false);
                viewMode = new BuildView(model);
                viewMode.setVisible(true);
                setContentPane(viewMode);
            }
        });
        runMode.addItemListener((ItemEvent e) -> { // if run mode has been selected
            if (e.getStateChange() == ItemEvent.SELECTED) {
                setTitle("GizmoBall - Run Mode");
                viewMode.setVisible(false);
                viewMode = new RunView(model);
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
