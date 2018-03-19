package Controller;

import Model.Model;
import View.BuildView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFrictionListener implements ActionListener {

    private Model model;
    private BuildView buildView;

    public ChangeFrictionListener(Model m, BuildView bv){
        this.buildView = bv;
        this.model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double result = buildView.changeFrictionAlert();

        if(result >= 0 && result <=10) {
            model.setFrictionValue(result);
        }else{
            buildView.invalidFrictionAlert();
        }
    }
}
