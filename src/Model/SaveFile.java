package Model;

import Model.Model;
import Model.IGizmo;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.io.*;

public class SaveFile {

    private Model model;
    private int x;
    private int y;
    int i = 0;
    private String gType;
    private int rotate;
    private JFileChooser fc;
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents";

    public SaveFile(Model model) {
        fc = new JFileChooser(FILE_PATH);
        this.model = model;
        try {
            getSaveFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void getSaveFile() throws IOException {
        int returnValue = fc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION){
            System.out.println("Success!");
            File file = fc.getSelectedFile();
            getGizmos(file); }
        else if (returnValue == JFileChooser.CANCEL_OPTION){
            System.out.println("Cancelled");
        }
        else {
            System.out.println("Failed");
        }
    }

    void getGizmos (File fileToWrite) throws IOException {
        System.out.println("Entered SaveFile");
        FileWriter fstream = null;
        try {
            fstream = new FileWriter(fileToWrite, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter writer = new BufferedWriter(fstream);
        for (IGizmo models : model.getGizmos()) {
            x = models.getX1();
            y = models.getY1();
            gType = models.getClass().getTypeName().substring(6);
            System.out.println("Gizmo: " + gType + " X: " + x + " Y:" + y);
            //writer.write(gType + " name " + x + " " + y);
            if(gType.equals("Absorber")){
                writer.write(gType + " " +gType.substring(0, 1) + " " + x + " " + (x+19) + " " + y + " " + y);
                continue;
            }
            if(gType.equals("Ball")){
                writer.write(gType + " " +gType.substring(0, 1) + " " + x + " " + y + " toDo gravity and velocity" );
                continue;
            }
            if(gType.equals("Triangle") && models.getRotation() != 0){
                writer.write(gType + " " +gType.substring(0, 1) + x + + y + " " + x + " " + y);
                writer.newLine();
                writer.write("Rotate " +gType.substring(0, 1) + x + + y);
                writer.newLine();
                continue;
            }
            writer.write(gType + " " +gType.substring(0, 1) + x + + y + " " + x + " " + y);
            writer.newLine();
        }
        writer.close();
    }

}
