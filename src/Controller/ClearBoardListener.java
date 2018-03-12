package Controller;

import Model.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearBoardListener implements ActionListener {
    private Model model;

    public ClearBoardListener(Model model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.clearGizmos();
    }
}
