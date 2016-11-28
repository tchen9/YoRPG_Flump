package core;

import java.awt.event.KeyListener;//KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.MouseListener;//MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.MouseInfo;
import java.awt.Point;

public class Input {
    private static KeyboardHandler keyboard;
    private static MouseHandler mouse;

    public Input() {
        keyboard = new KeyboardHandler();
        mouse = new MouseHandler();
    }

    public KeyListener getKeyListener() {
        return keyboard;
    }

    public MouseListener getMouseListener() {
        return mouse;
    }

    // update AFTER the handler is done updating
    public void postUpdate() {
        keyboard.postUpdate();
        mouse.postUpdate();
    }

    public static boolean keyCheck(int keycode) {
        return keyboard.keyCheck(keycode);
    }

    public static boolean keyCheckPressed(int keycode) {
        return keyboard.keyCheckPressed(keycode);
    }

    public static int getKeyAxisHorizontal() {
        return keyboard.getAxisHorizontal();
    }

    public static int getKeyAxisVertical() {
        return keyboard.getAxisVertical();
    }

    public static boolean mouseCheck(int mousecode) {
        return mouse.mouseCheck(mousecode);
    }

    public static boolean mouseCheckPressed(int mousecode) {
        return mouse.mouseCheckPressed(mousecode);
    }

    public static Point getMousePosition() {
        return mouse.getMousePosition();
    }

    // Sets the keycode pressed variables to false for the rest of the frame
    public static void unPressKey(int keycode) {
        keyboard.unpress(keycode);
    }
}

class KeyboardHandler implements KeyListener {//extends KeyAdapter {

    private boolean[] keyHeld, keyHeldLast;

    public KeyboardHandler() {
        keyHeld = new boolean[512]; // ADJUST FOR KEY SIZE
        keyHeldLast = new boolean[keyHeld.length];
        for (int i = 0; i < keyHeld.length; i++) {
            keyHeld[i] = false;
            keyHeldLast[i] = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key < keyHeld.length) {
            keyHeld[key] = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key < keyHeld.length) {
            keyHeld[key] = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public boolean keyCheck(int keycode) {
        if (keycode < keyHeld.length)
            return keyHeld[keycode];
        return false;
    }

    public boolean keyCheckPressed(int keycode) {
        if (keycode < keyHeld.length) {
            return (keyHeld[keycode] && !keyHeldLast[keycode]);
        }
        return false;
    }

    public void unpress(int keycode) {
        if (keycode < keyHeld.length) {
            keyHeld[keycode] = false;
            keyHeldLast[keycode] = false;
        }
    }

    // update input AFTER updating handler. Controls key pressed check
    public void postUpdate() {
        for(int i = 0; i < keyHeldLast.length; i++) {
            keyHeldLast[i] = keyHeld[i];
        }
    }

    public int getAxisHorizontal() {
        return ((keyCheck(KeyEvent.VK_RIGHT)) ? 1 : 0) - ((keyCheck(KeyEvent.VK_LEFT)) ? 1 : 0);
        //WASD: return ((keyCheck(KeyEvent.VK_D)) ? 1 : 0) - ((keyCheck(KeyEvent.VK_A)) ? 1 : 0);
    }

    public int getAxisVertical() {
        return ((keyCheck(KeyEvent.VK_DOWN)) ? 1 : 0) - ((keyCheck(KeyEvent.VK_UP)) ? 1 : 0);
        //WASD: return ((keyCheck(KeyEvent.VK_S)) ? 1 : 0) - ((keyCheck(KeyEvent.VK_W)) ? 1 : 0);
    }

}

class MouseHandler implements MouseListener {

    private boolean[] mouseHeld, mouseHeldLast;

    public MouseHandler() {
        // 0: BUTTON1
        // 1: BUTTON2
        // 2: BUTTON3;
        mouseHeld = new boolean[3]; // ADJUST FOR MOUSE SIZE
        mouseHeldLast = new boolean[3];
        for (int i = 0; i < mouseHeld.length; i++) {
            mouseHeld[i] = false;
            mouseHeldLast[i] = false;
        }
    }

    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        if (button < mouseHeld.length)
            mouseHeld[button] = true;
        else
            System.out.println("Houston, we have a problem");
    }

    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
        if (button < mouseHeld.length)
            mouseHeld[button] = false;
        else
            System.out.println("Houston, we have a problem");
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    public Point getMousePosition() {
        Point mp = MouseInfo.getPointerInfo().getLocation();
        Point wp = Game.getWindowPosition();
        return new Point(mp.x - wp.x, mp.y - wp.y);
    }

    public boolean mouseCheck(int mousecode) {
        if (mousecode < mouseHeld.length)
            return mouseHeld[mousecode];
        return false;
    }

    public boolean mouseCheckPressed(int mousecode) {
        if (mousecode < mouseHeld.length)
            return (mouseHeld[mousecode] && !mouseHeldLast[mousecode]);
        return false;
    }

    // update input AFTER updating handler. Controls key pressed check
    public void postUpdate() {
        for(int i = 0; i < mouseHeldLast.length; i++) {
            mouseHeldLast[i] = mouseHeld[i];
        }
    }
}
