package Controller;

import Model.Model;
import View.GameBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveListener implements ActionListener {

    private Model model;
    private int x, y;
    private GameBoard gameBoard;

    public MoveListener(Model model, GameBoard gameBoard){
        this.model = model;
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
