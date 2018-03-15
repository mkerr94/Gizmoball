package View;

import Controller.RunListener;
import Model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

public class Welcome  extends JPanel {

        private Model model;
        private Color purple;
        private Color pink;

        Welcome() {
            init();
            purple = new Color(128,0,128);
        }

        private void init() {
            setLayout(new BorderLayout());
            JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
            buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            buttonPanel.setBackground(purple = new Color(128,0,128));
            makeButtons(buttonPanel);
            add(buttonPanel, BorderLayout.NORTH);
            JLabel statusBar = new JLabel("Welcome Mode");
            statusBar.setBorder(BorderFactory.createEtchedBorder(purple, purple));
            setBackground(purple);
            statusBar.setForeground(Color.white);
            add(statusBar, BorderLayout.SOUTH);
        }

        private void makeButtons(JPanel panel) {
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            JLabel run = new JLabel("Please select mode from menu at top");
            run.setFont(new Font("Arial", Font.PLAIN, 24));
            run.setForeground(Color.white);
            run.setPreferredSize(new Dimension(400, 200));
            panel.add(run);

    }

}
