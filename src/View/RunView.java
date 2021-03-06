package View;

import Model.Model;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Controller.RunListener;

public class RunView extends JPanel {
    private Model model;
    private RunListener runListener;
    private Color purple;
    private GameBoard gameBoard;

    RunView(Model model, RunListener runListener, GameBoard gameBoard) {
        this.model = model;
        this.gameBoard = gameBoard;
        this.gameBoard.setMode(Mode.RUN);
        this.runListener = runListener;
        init();
        purple = new Color(128,0,128);
    }


    private void init() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(purple = new Color(128,0,128));
        makeButtons(buttonPanel);
        add(buttonPanel, BorderLayout.EAST);
        add(gameBoard, BorderLayout.CENTER);
        JLabel statusBar = new JLabel("Run Mode");
        statusBar.setBorder(BorderFactory.createEtchedBorder(purple, purple));
        setBackground(purple);
        statusBar.setForeground(Color.white);
        add(statusBar, BorderLayout.SOUTH);
    }

    private void makeButtons(JPanel panel) {
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton startB = new JButton("Start");
        startB.setFont(new Font("Arial", Font.PLAIN, 24));
        startB.setBackground(Color.white);
        startB.setPreferredSize(new Dimension(247, 200));
        startB.addActionListener(runListener);
        JButton stopB = new JButton("Stop");
        stopB.setFont(new Font("Arial", Font.PLAIN, 24));
        stopB.setBackground(Color.white);
        stopB.addActionListener(runListener);
        JButton tickB = new JButton("Tick");
        tickB.setFont(new Font("Arial", Font.PLAIN, 24));
        tickB.setBackground(Color.white);
        tickB.addActionListener(runListener);
        JButton fireB = new JButton("Fire");
        fireB.setFont(new Font("Arial", Font.PLAIN, 24));
        fireB.setBackground(Color.white);
        fireB.setPreferredSize(new Dimension(247, 100));
        fireB.addActionListener(runListener);

        panel.add(startB);
        panel.add(stopB);
        panel.add(tickB);
        panel.add(fireB);

    }

    public void noBallAlert(){
        JOptionPane.showMessageDialog(null,"Your board must contain at least one ball!");
    }
}
