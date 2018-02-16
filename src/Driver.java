import View.GUI;
import java.awt.*;

public class Driver {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI ex = new GUI();
            ex.setVisible(true);
        });
    }
}
