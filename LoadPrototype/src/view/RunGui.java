package view;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import model.LoadFile;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunGui {

	private LoadFile file;

	public RunGui(LoadFile m) {
		file = m;
	}

	public void createAndShowGUI() {

		JFrame frame = new JFrame("Load Prototype");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Board board = new Board(400, 400, file);
		Container cp = frame.getContentPane();

		cp.add(board, BorderLayout.CENTER);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
