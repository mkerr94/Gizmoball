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
            if(!model.getBalls().isEmpty()) {
                getSaveFile();
            }
            else{
                JOptionPane.showMessageDialog(null, "Need at least 1 ball to start/save","Nothing to save",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getSaveFile() throws IOException {
        fc.setDialogTitle("Choose a name to save the file");
        int returnValue = fc.showSaveDialog(null);
        File file = fc.getSelectedFile();
        if (returnValue == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "Cancelled, failed to pick an option","Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else if(fc.getSelectedFile().exists() && JOptionPane.showConfirmDialog(null,"Sorry, " + file.getName() + " already exists, overwrite?",
                "File found",JOptionPane.YES_NO_OPTION)  != JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, "Cancelled overwrite","Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            System.out.println("Success, opening file!");
            getGizmos(file); }
    }


    private void getGizmos(File fileToWrite) throws IOException {
        System.out.println("Entered SaveFile");
        FileWriter fstream = null;
        try {
            fstream = new FileWriter(fileToWrite, false);
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
                x = x -1;
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
            double bX = models.getxOrdinate();
            double bY = models.getxOrdinate();
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
        }
        writer.close();
    }

}