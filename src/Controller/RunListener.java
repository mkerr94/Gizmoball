package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import Model.Model;
import View.RunView;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunListener implements ActionListener {

    private Timer timer;
    private Model model;
    private RunView runView;

    public RunListener(Model m, RunView rv) {
        model = m;
        timer = new Timer(50, this);
        runView = rv;
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
                         runView.noBallAlert();
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
}
