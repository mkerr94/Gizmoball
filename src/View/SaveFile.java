package View;

import Model.Model;
import Model.IGizmo;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SaveFile {

    private Model model;
    private int x;
    private int y;
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
        int returnValue = fc.showOpenDialog(null);
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
            int i = 0;
            x = models.getX();
            y = models.getY();
            gType = models.getClass().getTypeName().substring(6);
            System.out.println("Gizmo: " + gType + " X: " + x + " Y:" + y);
            //writer.write(gType + " name " + x + " " + y);
            if(gType.equals("Absorber") || gType.equals("Ball")){
                System.out.println("found ab or ball");
                continue;}
            writer.write(gType + " " +gType.substring(0, 0) + i + " " + x + " " + y);
            i++;
            writer.newLine();
        }
        writer.close();
    }

}
