package Controller;

import Model.Model;
import Model.IGizmo;
import View.GameBoard;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import Model.Square;
import View.BuildView;

public class MoveListener implements ActionListener {

    private Model model;
    private int x, y;
    private int newX, newY;
    private GameBoard gameBoard;
    private BuildView buildView;

    public MoveListener(Model model, GameBoard gameBoard, BuildView buildView) {
        this.model = model;
        this.gameBoard = gameBoard;
        this.buildView = buildView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameBoard.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = e.getX() / 30;
                y = e.getY() / 30;
                IGizmo gizmo = model.getGizmo(x, y);
                if (gizmo == null) System.out.println("null gizmo");
                gameBoard.addMouseListener(new MouseInputListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        newX = e.getX() / 30;
                        newY = e.getY() / 30;

                        Square gizmoCheck = new Square(newX, newY);
                       // try{
                        if(model.checkGizmoLocation(gizmoCheck)){
                            model.moveGizmo(gizmo, newX, newY);
                        //}catch (IllegalStateException exception){
                            //System.out.println("Gizmo already exists at the target location");
                        }
                        else{
                            buildView.occupiedSpaceAlert();
                        }
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
                });
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
        });
    }
}
