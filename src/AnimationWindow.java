// Note the very indirect way control flow works during an animation:
//
// (1) We set up an eventListener with a reference to the animationWindow.
// (2) We set up a timer with a reference to the eventListener.
// (3) We call timer.start().
// (4) Every 20 milliseconds the timer calls eventListener.actionPerformed()
// (5) eventListener.actionPerformed() modifies the logical
//     datastructure (e.g. changes the coordinates of the ball).
// (6) eventListener.actionPerformed() calls myWindow.repaint.
// (7) Swing schedules, at some point in the future, a call to
//      myWindow.paint()
// (8) myWindow.paint() tells various objects to paint
//     themselves on the provided Graphics context.
//
// This may seem very complicated, but it makes the coordination of
// all the various different kinds of user input much easier. For
// example here is how control flow works when the user presses the
// mouse button:
//
// (1) We set up an eventListener (actually we just use the same
//     eventListener that is being used by the timer.)
// (2) We register the eventListener with the window using the
//     addMouseListener() method.
// (3) Every time the mouse button is pressed inside the window the
//     window calls eventListener.mouseClicked().
// (4) eventListener.mouseClicked() modifies the logical
//     datastructures. (In this example it calls ball.randomBump(), but
//     in other programs it might do something else, including request a
//     repaint operation).
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AnimationWindow extends JPanel {
    // overview: an AnimationWindow is an area on the screen in which a
    // bouncing ball animation occurs. AnimationWindows have two modes:
    // on and off. During the on mode the ball moves, during the off
    // mode the ball doesn't move.

    private AnimationEventListener eventListener;
    private BouncingBall ball;
    private Absorber absorber;
    private Timer timer;
    private boolean mode;

    public AnimationWindow() {
        // effects: initializes this to be in the off mode.

        super(); // do the standard JPanel setup stuff
        ball = new BouncingBall();
        absorber = new Absorber();

        // this only initializes the timer, we actually start and stop the
        // timer in the setMode() method
        eventListener = new AnimationEventListener();
        // The first parameter is how often (in milliseconds) the timer
        // should call us back. 50 milliseconds = 20 frames/second
        timer = new Timer(50, eventListener);

        mode = false;
    }

    // This is just here so that we can accept the keyboard focus
    public boolean isFocusTraversable() {
        return true;
    }

    public void paint(Graphics g) {
        // modifies: <g>
        // effects: Repaints the Graphics area <g>. Swing will then send the
        //          newly painted g to the screen.

        // first repaint the proper background color (controlled by
        // the windowing system)
        super.paint(g);
        absorber.paint(g);
        ball.paint(g);
    }

    public void setMode(boolean m) {
        // modifies: this
        // effects: changes the mode to <m>.

        if (mode == true) {
            // we're about to change mode: turn off all the old listeners
            removeMouseListener(eventListener);
            removeMouseMotionListener(eventListener);
            removeKeyListener(eventListener);
        }

        mode = m;

        if (mode == true) {
            // the mode is true: turn on the listeners
            addMouseListener(eventListener);
            addMouseMotionListener(eventListener);
            addKeyListener(eventListener);
            requestFocus(); // make sure keyboard is directed to us
            timer.start();
        } else {
            timer.stop();
        }
    }

    class AnimationEventListener extends MouseAdapter implements
            MouseMotionListener, KeyListener, ActionListener {
        // overview: AnimationEventListener is an inner class that
        // responds to all sorts of external events, and provides the
        // required semantic operations for our particular program. It
        // owns, and sends semantic actions to the ball and window of the
        // outer class

        // MouseAdapter gives us empty methods for the MouseListener
        // interface: mouseClicked, mouseEntered, mouseExited, mousePressed,
        // and mouseReleased.

        // for this example we only need to override mouseClicked
        public void mouseClicked(MouseEvent e) {
            // modifes: the ball that this listener owns
            // effects: causes the ball to be bumped in a random direction
            ball.randomBump();
        }

        // Here's the MouseMotionListener interface
        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
        }

        // Here's the KeyListener interface
        public void keyPressed(KeyEvent e) {
            // modifes: the ball that this listener owns
            // effects: causes the ball to be bumped in a random direction but
            //          only if one of the keys A-J is pressed.
            int keynum = e.getKeyCode();

            if ((keynum >= 65) && (keynum <= 74)) {
                System.out.println("keypress " + e.getKeyCode());
                ball.randomBump();
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }

        // this is the callback for the timer
        public void actionPerformed(ActionEvent e) {
            // modifes: both the ball and the window that this listener owns
            // effects: causes the ball to move and the window to be updated
            //          to show the new position of the ball.

            Rectangle oldPos = ball.boundingBox();

            ball.move(); // make changes to the logical animation state

            Rectangle repaintArea = oldPos.union(ball.boundingBox());

            // Have Swing tell the AnimationWindow to run its paint()
            // method. One could also call repaint(), but this would
            // repaint the entire window as opposed to only the portion that
            // has changed.
            repaint(repaintArea.x, repaintArea.y, repaintArea.width,
                    repaintArea.height);
        }
    }
}
