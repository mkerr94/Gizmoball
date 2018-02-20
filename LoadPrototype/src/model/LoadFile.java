package model;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Observable;

public class LoadFile extends Observable{

    private JFileChooser fc;
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents";


    public LoadFile() {
        fc = new JFileChooser(FILE_PATH);
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println("Success!");
            tokenizeFile();
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancelled");
            System.exit(1);
        } else{
            System.out.println("Failed");
            System.exit(1);
        }


    }
    HashMap<String, IGizmo> loadedGizmos = new HashMap<>();

    public void tokenizeFile(){

        int x, y, x1, y1, x2, y2;
        float fx, fy, fvx, fvy;
        String lineRead, gName;

        try {
            FileReader fileReader = new FileReader(fc.getSelectedFile());
            BufferedReader buffReader = new BufferedReader(fileReader);

            lineRead = buffReader.readLine();
            while (lineRead != null) {
                String[] gizmoGroup = lineRead.split(" ");
                switch (gizmoGroup[0]) {
                    case "Square":
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Square(x, y));
                        break;
                    case "Circle":
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Circle(x, y));
                        break;
                    case "Triangle":
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Triangle(x, y));
                        break;
                    case "RightFlipper":
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new RightFlipper(x, y));
                        break;
                    case "LeftFlipper":
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new LeftFlipper(x, y));
                        break;
                    case "Absorber":
                        gName = gizmoGroup[1];
                        x1 = Integer.parseInt(gizmoGroup[2]);
                        y1 = Integer.parseInt(gizmoGroup[3]);
                        x2 = Integer.parseInt(gizmoGroup[4]);
                        y2 = Integer.parseInt(gizmoGroup[5]);
                        loadedGizmos.put(gName, new Absorber(x1, y1, x2, y2));
                        break;
                    case "Ball":
                        gName = gizmoGroup[1];
                        fx = Float.parseFloat(gizmoGroup[2]);
                        fy = Float.parseFloat(gizmoGroup[3]);
                        fvx = Float.parseFloat(gizmoGroup[4]);
                        fvy = Float.parseFloat(gizmoGroup[5]);
                        loadedGizmos.put(gName, new Ball(fx, fy, fvx, fvy));
                        break;
                    case "Rotate":
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

    public HashMap<String, IGizmo> getLoadedGizmos() {
        return loadedGizmos;
    }

}






