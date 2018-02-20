package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import model.Ball;
import model.Model;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunListener implements ActionListener {

	private Timer timer;
	private Model model;
	private Ball ball;

	public RunListener(Model m) {
		model = m;
		timer = new Timer(50, this);
		timer.start();
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall(0);
		} else
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
				break;
			case "Stop":
				timer.stop();
				break;
			case "Tick":
				model.moveBall(0);
				model.fireBall();
				break;
			case "Quit":
				System.exit(0);
				break;
				case "Fire":
					model.fireBall();
					break;
			}

	}

	public void keyPressed(KeyEvent space, Model m) {
		System.out.println("PRESSED " + space);
		model.fireBall();
	}
}
