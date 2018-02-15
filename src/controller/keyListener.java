package controller;
import model.Model;
import java.awt.event.*;

public class keyListener implements KeyListener {

    private Model m;

    public keyListener(Model m){
        this.m = m;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent space) {
        //e.getKeyCode();
        System.out.println("PRESSED ");
        m.fireBall();
    }

    @Override
    public void keyReleased(KeyEvent space) {
        //.fireBall();
    }
}