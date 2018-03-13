package Controller;

import View.GameBoard;
import Model.Model;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class DeleteListener implements ActionListener{

    private Model model;
    private GameBoard gameBoard;
    private int x, y;

    public DeleteListener(Model model, GameBoard gameBoard) {
        this.model = model;
        this.gameBoard = gameBoard;
    }

    public void actionPerformed(ActionEvent e) {
        gameBoard.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                x = e.getX() / 30; // L = 30
                y = e.getY() / 30; // L = 30

               model.deleteGizmo(x,y);
               e.consume();
            }

            @Override
            public void mousePressed(MouseEvent e) {

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
