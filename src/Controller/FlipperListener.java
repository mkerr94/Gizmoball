/*package Controller;

import Model.Flipper;
import Model.IGizmo;
import Model.Model;
import View.GameBoard;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FlipperListener implements ActionListener, KeyListener{

    private GameBoard gameBoard;
    private JComboBox gizmoList;
    private String gizmoToAdd;
    private MouseInputListener mouseInputListener;
    private Model model;
    private int x, y;
    private final List<Flipper> flippers;
    private final int globalKeyTrigger = KeyEvent.VK_SPACE;

    public FlipperListener(Model model, GameBoard gameBoard){
        flippers = new ArrayList<>();
        this.model = model;
        this.gameBoard = gameBoard;
        List<IGizmo> gizmos = model.getGizmos();
        for (IGizmo gizmo :
                gizmos) {
            if (gizmo.getClass().getSuperclass().equals(Flipper.class)) {
                flippers.add((Flipper)gizmo);
            }
        }
        mouseInputListener = new MouseInputListener() {
            public void mouseClicked(MouseEvent e) {

            }


            public void mousePressed(MouseEvent e) {

            }


            public void mouseReleased(MouseEvent e) {

                x = e.getX()/30; // L = 30
                y = e.getY()/30; // L = 30
                switch (gizmoToAdd) {
                    case "Circle":
                        Model.Circle circle = new Model.Circle(x, y);
                        if (model.checkGizmoLocation(circle)){
                            model.addGizmo(circle);
                        } else{
                            System.out.println("Gizmo already exists on that location");
                        }
                        break;
                    case "Square":
                        Model.Square square = new Model.Square(x, y);
                        if (model.checkGizmoLocation(square)){
                            model.addGizmo(square);
                        } else{
                            System.out.println("Gizmo already exists on that location");
                        }
                        break;
                    case "Triangle":
                        Model.Triangle triangle = new Model.Triangle(x, y);
                        if (model.checkGizmoLocation(triangle)){
                            model.addGizmo(triangle);
                        } else{
                            System.out.println("Gizmo already exists on that location");
                        }
                        break;
                }
                gameBoard.removeMouseListener(this);
                e.consume();
            }


            public void mouseEntered(MouseEvent e) {

            }


            public void mouseExited(MouseEvent e) {

            }


            public void mouseDragged(MouseEvent e) {

            }


            public void mouseMoved(MouseEvent e) {

            }

        };
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (Flipper flipper :
                    flippers) {
                flipper.flip();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == globalKeyTrigger){
            for (Flipper flipper :
                    flippers) {
                flipper.unflip();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gameBoard.addMouseListener(mouseInputListener);
    }

    }

*/