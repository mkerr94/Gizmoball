import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class RunView extends JFrame {

    private JLabel statusbar;

    public RunView() {
        runGUI();
    }

    private void runGUI() {

        makeMenuBar();
        makeButtons();

        JPanel box = new JPanel(new GridLayout(20, 20));
        box.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        add(box, BorderLayout.CENTER);

        statusbar = new JLabel("Run Mode");
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

    private void makeButtons(){

        JPanel panel = new JPanel(new GridLayout(10,1, 5, 5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton startB = new JButton("Start");
        JButton stopB = new JButton("Stop");
        JButton tickB = new JButton("Tick");

        panel.add(startB);
        panel.add(stopB);
        panel.add(tickB);
        add(panel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            RunView ex = new RunView();
            ex.setVisible(true);
        });
    }
}

