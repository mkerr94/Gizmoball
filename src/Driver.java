import Model.Model;
import View.LoadFile;
import View.MainGUI;

import java.awt.*;

public class Driver {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Model model = new Model();
            LoadFile file = new LoadFile();
            MainGUI view = new MainGUI(file);
            view.setVisible(true);
        });
    }
}
