package model;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Observable;
/*
* Formal Syntax
<file> ::= <commandline>*

<commandline> ::= <command>"\n" | "\n"

<command> ::= <gizmoOp> <name> <int-pair> |
Absorber <name> <int-pair> <int-pair> |
Ball <name> <float-pair> <float-pair> |
Rotate <name> |
Delete <name> |
Move <name> <number-pair> |
Connect <name> <name> |
KeyConnect <keyid> <name> |
Gravity FLOAT |
Friction FLOAT FLOAT

<name> ::= IDENTIFIER

<gizmoOp> ::= Square | Circle | Triangle | RightFlipper | LeftFlipper

<number-pair> ::= <int-pair> | <float-pair>

<int-pair> ::= INTEGER INTEGER

<float-pair> ::= FLOAT FLOAT

<keyid> ::= "key" KEYNUM "down" |
"key" KEYNUM "up"

IDENTIFIER    represents any string composed only from the characters {'0'..'9','A'..'Z','a..z','_'}. The identifier "OuterWalls" is a special reserved word which refers to the outer walls; no other item may use this identifier.

INTEGER        represents any integer number
FLOAT          represents any floating point number
KEYNUM         represents any numeric key identifier (which are integers)
* */


public class LoadFile extends Observable{

    private JFileChooser fc;
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents";


    public LoadFile() throws FileNotFoundException {
        fc = new JFileChooser(FILE_PATH);
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println("Success!");
            tokenizeFile();
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancelled");
        } else
            System.out.println("Failed");

    }

    HashMap<String, Gizmo> loadedGizmos = new HashMap<String, Gizmo>();

    public void tokenizeFile() throws FileNotFoundException {

        // IDENTIFIER {'0'..'9','A'..'Z','a..z','_'}. The identifier"OuterWalls" is a special
        // reserved word which refers to the outer walls; no other item may use this identifier.


        int x, y, x1, y1, x2, y2, keyNum;
        float fx, fy, fvx, fvy, g, mu, mu2;
        String lineRead, gizmoOp, gName, cName, key;


        try {
            FileReader fileReader = new FileReader(fc.getSelectedFile());
            BufferedReader buffReader = new BufferedReader(fileReader);

            lineRead = buffReader.readLine();
            while (lineRead != null) {
                String[] gizmoGroup = lineRead.split(" ");
                switch (gizmoGroup[0]) {
                    case "Square":
                        System.out.println("Found Square: " + lineRead);
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Square(x, y));
                        break;
                    case "Circle":
                        System.out.println("Found Circle: " + lineRead);
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Circle(x, y));
                        break;
                    case "Triangle":
                        System.out.println("Found Triangle: " + lineRead);
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Triangle(x, y));
                        break;
                    case "RightFlipper":
                        System.out.println("Found RightFlipper: " + lineRead);
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new RightFlipper(x, y));
                        break;
                    case "LeftFlipper":
                        System.out.println("Found LeftFlipper: " + lineRead);
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new LeftFlipper(x, y));
                        break;
                    case "Absorber":
                        System.out.println("Found Absorber: " + lineRead);
                        gName = gizmoGroup[1];
                        x = Integer.parseInt(gizmoGroup[2]);
                        y = Integer.parseInt(gizmoGroup[3]);
                        loadedGizmos.put(gName, new Absorber(x, y));
                        break;
                    case "Ball":
                        System.out.println("Found Ball: " + lineRead);
                        gName = gizmoGroup[1];
                        fx = Float.parseFloat(gizmoGroup[2]);
                        fy = Float.parseFloat(gizmoGroup[3]);
                        fvx = Float.parseFloat(gizmoGroup[4]);
                        fvy = Float.parseFloat(gizmoGroup[5]);
                        loadedGizmos.put(gName, new Ball(fx, fy, fvx, fvy));
                        break;
                    case "Rotate":
                        System.out.println("Found Rotate: " + lineRead);
                        gName = gizmoGroup[1];
                        loadedGizmos.get(gName).rotate();
                        break;
                    case "Connect":
                        System.out.println("Found Connect: " + lineRead);
                        gName = gizmoGroup[1];
                        cName = gizmoGroup[2];
                        loadedGizmos.get(gName);
                        break;
                    case "KeyConnect":
                        System.out.println("Found KeyConnect: " + lineRead);
                        key = gizmoGroup[2];
                        keyNum = Integer.parseInt(gizmoGroup[2]);
                        cName = gizmoGroup[4];
                        loadedGizmos.get(cName);
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

    public HashMap<String, Gizmo> getLoadedGizmos() {
        return loadedGizmos;
    }


    public GizmoType getType() {
        return loadedGizmos.get(0).getType();
    }

}






