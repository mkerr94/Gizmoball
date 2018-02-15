import javax.swing.*;
import java.io.*;

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



public class TokenizeFile {




    public TokenizeFile(File file) throws FileNotFoundException {

        // IDENTIFIER {'0'..'9','A'..'Z','a..z','_'}. The identifier"OuterWalls" is a special
        // reserved word which refers to the outer walls; no other item may use this identifier.


        int x, y, x1, y1, x2, y2, keyNum;
        float fx, fy, fvx, fvy, g, mu, mu2;
        String lineRead, gizmoOp, gName, cName;


        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);

            lineRead = buffReader.readLine();
            while (lineRead != null) {
                System.out.println(lineRead + "test1");
                String[] gizmoGroup = lineRead.split(" ");
                for(int i=0; !gizmoGroup[i].isEmpty(); i++){

                /*System.out.println(gizmoGroup.length);
                //matcher = loadGizmoTest.matcher(lineRead);
                if (matcher.matches()){
                    gizmoOp = matcher.group(0);
                    System.out.println(gizmoOp + "test2");*/
                    switch (gizmoGroup[i]) {
                        case "Square":
                            System.out.println("Found Square: " + lineRead);
                            //lineRead = buffReader.readLine();
                            break;
                        case "Circle":
                            System.out.println("Found Circle: " + lineRead);
                            break;
                        case "Triangle":
                            System.out.println("Found Triangle: " + lineRead);
                            break;
                        case "RightFlipper":
                            System.out.println("Found RightFlipper: " + lineRead);
                            break;
                        case "LeftFlipper":
                            System.out.println("Found LeftFlipper: " + lineRead);
                            break;
                        case "Absorber":
                            System.out.println("Found Absorber: " + lineRead);
                            break;
                        case "Ball":
                            System.out.println("Found Ball: " + lineRead);
                            break;
                        case "Rotate":
                            System.out.println("Found Rotate: " + lineRead);
                            break;
                        case "Connect":
                            System.out.println("Found Connect: " + lineRead);
                            break;
                        case "KeyConnect":
                            System.out.println("Found KeyConnect: " + lineRead);
                            break;
                    }


                }

                lineRead = buffReader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }


    }
}
