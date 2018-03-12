import Model.Model;
//import View.LoadFile;
import View.MainGUI;
import Model.VerticalLine;

import java.awt.*;

public class Driver {
    public static void main(String[] args) {


        Model Model = new Model();

        Model.setBallSpeed(200, 200);


        EventQueue.invokeLater(() -> {
            Model model = new Model();
            MainGUI view = new MainGUI(model);
            view.setVisible(true);
        });
    }
}
