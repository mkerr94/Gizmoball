package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import Model.Model;
import View.GameBoard;
import View.MainGUI;
import View.Mode;
import View.RunView;

public class RunListener implements ActionListener {

    private Timer timer;
    private Model model;
    private MainGUI mainGUI;

    public RunListener(Model m, MainGUI mainGUI) {
        model = m;
        this.timer = new Timer(50, this);
        this.mainGUI = mainGUI;
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
                    }else{
                         mainGUI.noBallAlert();
                    }
                    break;
                case "Stop":
                    timer.stop();
                    break;
                case "Tick":
                    model.moveBall();
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
