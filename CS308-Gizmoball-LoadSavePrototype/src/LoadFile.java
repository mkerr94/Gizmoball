import javax.swing.*;
import java.io.*;


public class LoadFile {

    private JFileChooser fc;
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents";


    public LoadFile() throws FileNotFoundException {
        fc = new JFileChooser(FILE_PATH);
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            System.out.println("Success!");
            TokenizeFile load = new TokenizeFile(fc.getSelectedFile());
        }
        else if (returnValue == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancelled");
        }
        else
            System.out.println("Failed");




    }
}
