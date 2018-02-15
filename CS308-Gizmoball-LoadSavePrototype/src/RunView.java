import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;

public class RunView extends JFrame {

    private JLabel statusbar;
    private AnimationWindow animationWindow;

    public RunView() {
        runGUI();
    }

    private void runGUI() {


        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        makeMenuBar();
        makeButtons(buttonPanel);

        //Create animation area used for input
        animationWindow = new AnimationWindow();

        //Put it in a scrollpane (makes a border)
        JScrollPane scrollPane = new JScrollPane(animationWindow);

        //Lay out the content pane
       /* JPanel contentPane = new JPanel(new GridLayout(20, 20));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        add(contentPane, BorderLayout.CENTER);
        setContentPane(contentPane);*/

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(500, 500));
        contentPane.add(buttonPanel, BorderLayout.EAST);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        setContentPane(contentPane);

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

        open.addActionListener((ActionEvent event) -> {
            //JFileChooser chosenFile = new JFileChooser();
            //chosenFile.setCurrentDirectory(new File(System.getProperty("user.home")));
            //int result = chosenFile.showOpenDialog(menuBar);
            try {
                LoadFile fc = new LoadFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        });

        save.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

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

    protected void makeButtons(JPanel panel) {

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton startB = new JButton("Start");
        JButton stopB = new JButton("Stop");

        startB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationWindow.setMode(true);
            }
        });

        stopB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationWindow.setMode(false);
            }
        });

        panel.add(startB);
        panel.add(stopB);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            RunView ex = new RunView();
            ex.setVisible(true);
        });
    }
}

