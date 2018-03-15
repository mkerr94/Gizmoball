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
            buttonPanel.setBackground(Color.white);
            makeButtons(buttonPanel);
            add(buttonPanel, BorderLayout.NORTH);
            JLabel statusBar = new JLabel("Welcome Mode");
            statusBar.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.white));
            setBackground(Color.white);
            statusBar.setForeground( purple = new Color(128,0,128));
            add(statusBar, BorderLayout.SOUTH);
        }

        private void makeButtons(JPanel panel) {
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            JLabel run = new JLabel("Please select a mode from the menu at the top");
            run.setFont(new Font("Arial", Font.PLAIN, 24));
            run.setForeground(purple = new Color(128,0,128));
            run.setPreferredSize(new Dimension(100, 200));
            run.setHorizontalAlignment(SwingConstants.CENTER);
            //logo 3 or 7
            Icon logo = new ImageIcon("logo7.png");
            JLabel log = new JLabel(logo);
            panel.add(log);
            panel.add(run);
           // log.setBorder(BorderFactory.createEtchedBorder( purple = new Color(128,0,128),  purple = new Color(128,0,128)));


    }

}
