package Model;

import Physics.Vect;


import javax.swing.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveFile {

    private Model model;
    private JFileChooser fc;
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents/load";

    public SaveFile(Model model) {
        fc = new JFileChooser(FILE_PATH);
        this.model = model;
        try {
            getSaveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getSaveFile() throws IOException {
        int returnValue = fc.showSaveDialog(null);
        fc.setDialogTitle("Save as...");
        if(fc.getSelectedFile().exists()){
            System.out.println("Cancelled, file exists");
        }
        else if (returnValue == JFileChooser.APPROVE_OPTION){
            System.out.println("Success, opening file!");
            File file = fc.getSelectedFile();
            getGizmos(file); }
        else if (returnValue == JFileChooser.CANCEL_OPTION){
            System.out.println("Cancelled, failed to pick an option");
        }
    }


    private void getGizmos(File fileToWrite) throws IOException {
        System.out.println("Entered SaveFile");
        FileWriter fstream = null;
        try {
            fstream = new FileWriter(fileToWrite, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fstream != null;
        BufferedWriter writer = new BufferedWriter(fstream);
        String gType;
        for (IGizmo models : model.getGizmos()) {
            int x = models.getX1();
            int y = models.getY1();
            gType = models.getClass().getTypeName().substring(6);
            String gName = gType.substring(0, 1);
            System.out.println("Gizmo: " + gType + " X: " + x + " Y:" + y + " and the models is " + models);
            //writer.write(gType + " name " + x + " " + y);
            if(gType.equals("Absorber")){
                Absorber newA = (Absorber) models;
                int x2 = newA.getX2();
                int y2 = newA.getY2();
                writer.write(gType + " " + gName + " " + x + " " + y + " " + x2 + " " + y2);
                writer.newLine();
                continue;
            }
            if(gType.equals("Triangle") && models.getRotation() != 0){
                writer.write(gType + " " + gName + x + +y + " " + x + " " + y);
                writer.newLine();
                if(models.getRotation() == 2) {
                    writer.write("Rotate " + gName + x + +y);
                    writer.newLine();
                }
                writer.write("Rotate " + gName + x + +y);
                writer.newLine();
                continue;
            }
            if (models instanceof RightFlipper){
                System.out.println("hjghgfghjghfhgfjhfjhghgjhghgghhghghgghhghg");
                x = x -1;
                writer.write(gType + " " + gName + x + +y + " " + x + " " + y);
                writer.newLine();
            }

            writer.write(gType + " " + gName + x + +y + " " + x + " " + y);
            writer.newLine();
            if(model.stringKeyConnection(models) != null){
                System.out.println("keyConnection found " + (model.stringKeyConnection(models)));
                writer.write(("keyConnection " + model.stringKeyConnection(models)));
                writer.newLine();
            }
        }
        int i = 0;
        for (Ball models : model.getBalls()) {
            double bX = models.getExactX();
            double bY = models.getExactY();
            String bV1 = "";
            Vect velocity = models.getVelo();
            String sVelocity = velocity.toString();
            Pattern p = Pattern.compile("(\\d+(?:\\.\\d))");
            Matcher m = p.matcher(sVelocity);
            while(m.find()) {
                String d = m.group(1);
                bV1 = bV1.concat(d);
                bV1 = bV1.concat(" ");

            }
            gType = models.getClass().getTypeName().substring(6);
            writer.write(gType + " B" + i + " " + bX + " " + bY + " " + bV1);
            writer.newLine();
            i++;
            model.printKeyConnections();
        }
        writer.close();
    }

}