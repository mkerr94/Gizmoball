package Controller;

import Model.Model;
import Model.Ball;

import java.awt.event.ActionEvent;

public class AddBallListener {

    private Model model;

    public AddBallListener(Model model){
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Ball added");
       // model.createBall(new Ball(10,10,20,20));
    }
}
