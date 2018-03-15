package Controller;

import Model.Model;
import View.GameBoard;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MoveListener implements ActionListener {

    private Model model;
    private int x, y;
    private GameBoard gameBoard;

    public MoveListener(Model model, GameBoard gameBoard) {
        this.model = model;
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameBoard.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }
}
