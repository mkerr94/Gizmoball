package Controller;

import Model.Model;
import Model.*;
import View.GameBoard;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class AddAbsorberListener implements ActionListener {

    private GameBoard gameBoard;
    private Model model;
    private int x;
    private int y;
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public AddAbsorberListener(Model model, GameBoard gameBoard){
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
                x1 = e.getX()/30;
                y1 = e.getY()/30;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX()/30;
                y2 = e.getY()/30;
                int dx = x2 - x1;
                int dy = y2 - y1;
                System.out.println("dx = " + dx);
                System.out.println("dy = " + dy);
                model.addGizmo(new Absorber(x1, y1, dx, dy));
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
        //model.addGizmo(new Absorber(0, 19, 20, 1));
    }
}
