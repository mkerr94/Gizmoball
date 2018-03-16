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

    RunView(Model model) {
        this.model = model;
        runListener = new RunListener(model,this);
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
        add(new GameBoard(600, 600, model, Mode.RUN), BorderLayout.CENTER);
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
        JButton resetB = new JButton("Fire");
        resetB.setFont(new Font("Arial", Font.PLAIN, 24));
        resetB.setBackground(Color.white);
        resetB.setPreferredSize(new Dimension(247, 100));
        resetB.addActionListener(runListener);
        panel.add(startB);
        panel.add(stopB);
        panel.add(tickB);
        panel.add(resetB);

    }

    public void noBallAlert(){
        JOptionPane.showMessageDialog(null,"Your board must contain at least one ball!");
    }
}
