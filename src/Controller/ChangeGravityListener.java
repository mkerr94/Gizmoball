package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.BuildView;
import Model.Model;

public class ChangeGravityListener implements ActionListener {

    private Model model;
    private BuildView buildView;

    public ChangeGravityListener(Model m, BuildView bv){
        this.buildView = bv;
        this.model = m;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int result = buildView.changeGravityAlert();
        model.setGravityValue(result);
    }
}
