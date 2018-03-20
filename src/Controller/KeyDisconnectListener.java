package Controller;

import java.awt.event.*;

import Model.Model;
import Model.IGizmo;
import View.BuildView;
import View.GameBoard;

import javax.swing.event.MouseInputListener;

public class KeyDisconnectListener implements ActionListener {
    private Model model;
    private GameBoard gameBoard;
    private KeyListener keyListener;
    private MouseInputListener mouseInputListener;
    private int keyCode;
    private IGizmo gizmoToConnect;
    private BuildView buildView;

    public KeyDisconnectListener(Model model, GameBoard gameBoard, BuildView buildView) {
        this.model = model;
        this.gameBoard = gameBoard;
        this.buildView = buildView;


        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (!model.checkKeyConnection(e.getKeyCode())) {
                    keyCode = e.getKeyCode();
                    model.removeKeyConnection(keyCode);
                    buildView.keyDisconnectAlert();
                } else {
                    buildView.invalidKeyAlert();

                }
                gameBoard.removeKeyListener(this);
            }
        };

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameBoard.requestFocus();
        gameBoard.addKeyListener(keyListener);
    }
}
