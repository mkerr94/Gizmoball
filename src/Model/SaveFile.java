package Model;

import Model.Model;
import Model.IGizmo;

import Physics.Vect;

import com.sun.org.apache.xpath.internal.SourceTree;


import javax.swing.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveFile {

    private Model model;
    private int x;
    private int y;
    private int x2;
    private int y2;
    private double bX;
    private double bY;
    private Vect velocity;
    private String bV1 = "";
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
                Absorber newA = (Absorber) models;
                x2 = newA.getX2();
                y2 = newA.getY2();
                writer.write(gType + " " +gType.substring(0, 1) + " " + x + " " + y + " " + x2 + " " + y2);
                writer.newLine();
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
        for (Ball models : model.getBalls()) {
            bX = models.getxOrdinate();
            bY = models.getyOrdinate();
            velocity = models.getVelo();
            String sVelocity = velocity.toString();
            Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
            Matcher m = p.matcher(sVelocity);
            while(m.find()) {
                String d = m.group(1);
                bV1 = bV1.concat(d);
                bV1 = bV1.concat(" ");

            }
            gType = models.getClass().getTypeName().substring(6);
            writer.write(gType + " " +gType.substring(0, 1) + " " + bX + " " + bY + " " + bV1);
            writer.newLine();
            System.out.println("Ball: " + gType + " X: " + x + " Y:" + y + " Velocity = " + bV1);
        }
        writer.close();
    }

}
