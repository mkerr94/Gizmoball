package main;

import javax.swing.UIManager;

import model.LoadFile;
import view.RunGui;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}

		LoadFile file = new LoadFile();

		RunGui gui = new RunGui(file);
		gui.createAndShowGUI();

	}
}
