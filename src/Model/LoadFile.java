package Model;

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

        int x = 0, y = 0, x1, y1, x2, y2, kNo;
        double d, d1, d2, d3;
        String lineRead, gName, gConnect1, gConnect2, upDown;

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
                        loadedGizmos.put(gName, new Triangle(x, y, 0));
                        model.addGizmo(new Triangle(x, y, 0));
                        break;
                    case "RightFlipper":
                        //System.out.println("found");
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new RightFlipper(x, y));
                        model.addGizmo(new RightFlipper(x + 1, y ));
                        break;
                    case "LeftFlipper":
                        //System.out.println("found");
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new LeftFlipper(x, y));
                        model.addGizmo(new LeftFlipper(x, y));
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
                    case "Ball":
                        //System.out.println("-----------found ball----------");
                        gName = gizmoGroup[1];
                        d = Double.parseDouble(gizmoGroup[2]);
                        d1 = Double.parseDouble(gizmoGroup[3]);
                        d2 = Double.parseDouble(gizmoGroup[4]);
                        d3 = Double.parseDouble(gizmoGroup[5]);
                        //loadedGizmos.put(gName, new Circle(x2, y2));
                        model.addBall(d, d1, d2, d3);
                        break;
                    case "Rotate":
                        System.out.println("found Rotate");
                        gName = gizmoGroup[1];
                        loadedGizmos.get(gName).rotate();
                        model.rotateGizmo(model.getGizmo(x, y));
                        break;
                    case "KeyConnect":
                        System.out.println("found KeyConnect");
                        kNo = Integer.parseInt(gizmoGroup[2]);
                        upDown = gizmoGroup[3];
                        gConnect1 = gizmoGroup[4];
                        break;
                    case "Connect":
                        System.out.println("found Connect");
                        gConnect1 = gizmoGroup[1];
                        gConnect2 = gizmoGroup[2];
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
