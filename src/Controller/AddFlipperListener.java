package Controller;

import Model.Model;
import Model.RightFlipper;
import Model.LeftFlipper;
import View.GameBoard;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.*;

import View.BuildView;

public class AddFlipperListener implements ActionListener {
    private Model model;
    private GameBoard gameBoard;
    private JComboBox flipperList;
    private String flipperType;
    private MouseInputListener mouseInputListener;
    private int x, y;
    private BuildView buildView;
    public static List<RightFlipper> rightflippers;
    public static List<LeftFlipper> leftflippers;

    public AddFlipperListener(JComboBox flipperList, Model model, GameBoard gameBoard, BuildView buildView) {
        this.flipperList = flipperList;
        this.model = model;
        this.gameBoard = gameBoard;
        this.buildView = buildView;
        rightflippers = new ArrayList<>();
       leftflippers = new ArrayList<>();
        mouseInputListener = new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (flipperList.getSelectedItem() != null){
                    flipperType = flipperList.getSelectedItem().toString();
                }
                x = e.getX()/30; // L = 30
                y = e.getY()/30; // L = 30

                switch (flipperType){
                    case "Right flipper":
                        RightFlipper rightFlipper = new RightFlipper(x, y);
                        if (model.checkGizmoLocation(rightFlipper)){
                            model.addGizmo(rightFlipper);
                            System.out.println("rightflipper: x - " + x + "; y - " + y);
                            rightflippers.add(rightFlipper);
                        }else{
                            buildView.occupiedSpaceAlert();
                        }
                        break;
                    case "Left flipper":
                        LeftFlipper leftFlipper = new LeftFlipper(x, y);
                        if (model.checkGizmoLocation(leftFlipper)){
                            model.addGizmo(leftFlipper);
                            System.out.println("leftflipper: x - " + x + "; y - " + y);
                            leftflippers.add(leftFlipper);
                        }else{
                            buildView.occupiedSpaceAlert();
                        }
                        break;
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
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameBoard.addMouseListener(mouseInputListener);
    }
}
