package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.*;

import model.Model;
import controller.RunListener;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunGui {

	private Model model;
	private JFrame frame;
	private ActionListener listener;
	private Board board;
	private JLabel statusbar;

	public RunGui(Model m) {
		model = m;
		listener = new RunListener(m);
	}

	public void createAndShowGUI() {

		frame = new JFrame("Gizmoball!");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// Board is passed the Model so it can act as Observer
		board = new Board(500, 500, model);

		Container cp = frame.getContentPane();

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(4, 1));
		//buttons.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));

		statusbar = new JLabel("Run Mode");
		statusbar.setBorder(BorderFactory.createEtchedBorder());
		cp.add(statusbar, BorderLayout.SOUTH);

		JButton button1 = new JButton("Start");
		button1.addActionListener(listener);
		buttons.add(button1);

		JButton button2 = new JButton("Stop");
		button2.addActionListener(listener);
		buttons.add(button2);

		JButton button4 = new JButton("Tick");
		button4.addActionListener(listener);
		buttons.add(button4);

		cp.add(buttons, BorderLayout.EAST);
		cp.add(board, BorderLayout.CENTER);

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
		frame.setJMenuBar(menuBar);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
