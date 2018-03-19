package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import Model.Model;
import View.GameBoard;
import View.MainGUI;

public class RunListener implements ActionListener {

    private Timer timer;
    private Model model;
    private MainGUI mainGUI;
    private GameBoard gameBoard;

    public RunListener(Model m, MainGUI mainGUI, GameBoard gameBoard) {
        model = m;
        this.timer = new Timer(50, this);
        this.mainGUI = mainGUI;
        this.gameBoard = gameBoard;
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        if (e.getSource() == timer) {
            model.moveBall();
        } else
            switch (e.getActionCommand()) {
                case "Start":
                    if(model.getBalls().size() != 0){
                        timer.start();
                        gameBoard.requestFocus();
                        gameBoard.initFlipperListeners(timer);
                    }else{
                         mainGUI.noBallAlert();
                    }
                    break;
                case "Stop":
                    timer.stop();
                    break;
                case "Tick":
                    if(model.getBalls().size() != 0) {
                        model.moveBall();
                    }else{
                        mainGUI.noBallAlert();
                    }
                    break;
                case "Fire":
                    model.fireBall();
                    break;
            }
    }
    public void killTimer(){
        this.timer.stop();
    }

}
