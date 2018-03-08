package View;

import Model.*;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;


public class LoadFile {

    private JFileChooser fc;
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents";
    private Model model;

    public LoadFile(Model model) {
        fc = new JFileChooser(FILE_PATH);
        this.model = model;
        try {
            getLoadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    void getLoadFile() throws FileNotFoundException {
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            System.out.println("Success!");
            tokenizeFile(); }
        else if (returnValue == JFileChooser.CANCEL_OPTION){
            System.out.println("Cancelled");
        }
        else {
            System.out.println("Failed");
        }
    }

    private HashMap<String, IGizmo> loadedGizmos = new HashMap<>();

    private void tokenizeFile(){

        int x, y, x1, y1, x2, y2;
        String lineRead, gName;

        try {
            FileReader fileReader = new FileReader(fc.getSelectedFile());
            BufferedReader buffReader = new BufferedReader(fileReader);

            lineRead = buffReader.readLine();
            while (lineRead != null) {
                String[] gizmoGroup = lineRead.split(" ");
                switch (gizmoGroup[0]) {
                    case "Square":
                       // System.out.println("found");
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Square(x, y));
                        model.addGizmo(new Square(x, y));
                        break;
                    case "Circle":
                        //System.out.println("found");
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Circle(x, y));
                        model.addGizmo(new Circle(x, y));
                        break;
                    case "Triangle":
                        //System.out.println("found");
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Triangle(x, y));
                        model.addGizmo(new Triangle(x, y));
                        break;
                    case "RightFlipper":
                        //System.out.println("found");
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new RightFlipper(x, y));
                        break;
                    case "LeftFlipper":
                        //System.out.println("found");
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new LeftFlipper(x, y));
                        break;
                    case "Absorber":
                       //System.out.println("found");
                        gName = gizmoGroup[1];
                        x1 = Integer.parseInt(gizmoGroup[2]);
                        y1 = Integer.parseInt(gizmoGroup[3]);
                        x2 = Integer.parseInt(gizmoGroup[4]);
                        y2 = Integer.parseInt(gizmoGroup[5]);
                        loadedGizmos.put(gName, new Absorber(x1, y1, x2, y2));
                        model.addGizmo(new Absorber(x1, y1, x2, y2));
                        break;
                    case "Balle":
                        //System.out.println("found");
                        gName = gizmoGroup[1];
                        x1 = Integer.parseInt(gizmoGroup[2]);
                        y1 = Integer.parseInt(gizmoGroup[3]);
                        x2 = Integer.parseInt(gizmoGroup[4]);
                        y2 = Integer.parseInt(gizmoGroup[5]);
                        //loadedGizmos.put(gName, new Ball(x1, y1, x2));
                        break;
                    case "Rotate":
                        //System.out.println("found");
                        gName = gizmoGroup[1];
                        loadedGizmos.get(gName).rotate();
                        break;
                }
                lineRead = buffReader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    HashMap<String, IGizmo> getLoadedGizmos() {
        return loadedGizmos;
    }

}

