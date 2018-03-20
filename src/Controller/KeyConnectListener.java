package Controller;

import java.awt.event.*;

import Model.Model;
import Model.IGizmo;
import View.BuildView;
import View.GameBoard;

import javax.swing.event.MouseInputListener;

public class    KeyConnectListener implements ActionListener {
    private Model model;
    private GameBoard gameBoard;
    private KeyListener keyListener;
    private MouseInputListener mouseInputListener;
    private int keyCode;
    private IGizmo gizmoToConnect;
    private BuildView buildView;

    public KeyConnectListener(Model model, GameBoard gameBoard, BuildView buildView){
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

                if(model.checkKeyConnection(e.getKeyCode())){
                    keyCode = e.getKeyCode();
                    gameBoard.removeKeyListener(this);
                    gameBoard.addMouseListener(mouseInputListener);
                }else{
                    buildView.invalidKeyAlert();
                    gameBoard.removeKeyListener(this);
                }

            }
        };
        mouseInputListener = new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX() / 30;
                int y = e.getY() / 30;
                IGizmo iGizmo = model.getGizmo(x, y);
                if (iGizmo != null){
                    gizmoToConnect = iGizmo;
                    buildView.keyConnectAlert();
                }else{
                    throw new NullPointerException("Null gizmo retrieved in mouseReleased()");
                }
                model.addKeyConnection(keyCode, gizmoToConnect);
                System.out.println("key connected: " + keyCode);
                gameBoard.removeMouseListener(this);
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
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameBoard.requestFocus();
        gameBoard.addKeyListener(keyListener);
    }
}
