package Controller;

import Model.Model;
import View.BuildView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearBoardListener implements ActionListener {
    private Model model;
    private BuildView buildView;

    public ClearBoardListener(Model model, BuildView buildView) {
        this.buildView = buildView;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int result = buildView.clearBoardAlert();
        //if user clicks yes
        if (result == 0) {
            model.clearGizmos();
            model.clearFlippers();
        }
    }
}
