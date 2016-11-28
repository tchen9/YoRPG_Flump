package core;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.BufferStrategy;

import java.awt.Point;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    private static int view_wview = 280;
    private static int view_hview = view_wview / 12 * 9;
    private static int view_wport = view_wview * 2;
    private static int view_hport = view_hview * 2;

    private static double view_xview = 0;
    private static double view_yview = 0;

    private static String title = "Game Test 1";

    private static JFrame frame;

    private boolean running;

    private int tickCount = 0; // Utility

    // Main image that is drawn every frame.
    // Maybe set to ARGB later on so we can do transparency....
    private BufferedImage image = new BufferedImage(view_wview, view_hview, BufferedImage.TYPE_INT_RGB);

    public static Handler handler;
    public static GameEventHandler gameEventHandler;
    private Input input;
    public static Resources resources;

    public Game() {
        running = false;
        handler = new Handler();
        gameEventHandler = new GameEventHandler();
        input = new Input();
        resources = new Resources();
        addKeyListener(input.getKeyListener());
        addMouseListener(input.getMouseListener());

        // Initialize Window. Perhaps use another class?
        Dimension screen = new Dimension(view_wport, view_hport);
        setMinimumSize(screen);
        setMaximumSize(screen);
        setPreferredSize(screen);

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setAlwaysOnTop(true);
        frame.toFront();
        frame.requestFocus();
        frame.setAlwaysOnTop(false);
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
        // Thread.join?
    }

    public void run() {
        handler.initialize();
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / 60.0; // billion divided by target ups
        double delta = 0;
        
        long secondTimer = System.currentTimeMillis();
        int ticks = 0;
        int frames = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            boolean shouldRender = true;
            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;

                shouldRender = true; // Only render after ticking
            }

            if (shouldRender) {
                frames++;
                render();
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace(); // this is bad, but eh what can you do
            }
            

            // Print number of ticks and frames per second. Debug only.
            if (System.currentTimeMillis() - secondTimer >= 1000) {
                secondTimer += 1000; // ummm why not just set to current time?
                System.out.println("frames: " + frames + ", ticks: " + ticks);
                frames = 0;
                ticks = 0;
            }

        }
        System.out.println("ok den");
    }

    private void tick() {
        tickCount++;
        handler.tick();
        input.postUpdate();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // Pass the image graphics to handler 
        handler.render(image.getGraphics());

        // HUZZAAHHH! This fixes the "game lags when there is no input" issue
        //            that seemingly had no solution until this magically
        //            fixed it! YAY!
        g.drawImage(image, 0,0,getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }


    public static void capView(int x, int y, int width, int height) {
        view_xview = Math.max( Math.min(view_xview, width - view_wview), x);
        view_yview = Math.max( Math.min(view_yview, height - view_hview), y);
    }

    /// ACCESSORS AND MODIFIERS

    public static Point getWindowPosition() {
        return frame.getLocation();
    }

    public static int getWview() {
        return view_wview;
    }

    public static int getHview() {
        return view_hview;
    }

    public static double getXview() {
        return view_xview;
    }

    public static double getYview() {
        return view_yview;
    }

    public static void setWview(int view_wview) {
        Game.view_wview = view_wview;
    }

    public static void setHview(int view_Hview) {
        Game.view_hview = view_hview;
    }

    public static void setXview(double view_xview) {
        Game.view_xview = view_xview;
    }

    public static void setYview(double view_yview) {
        Game.view_yview = view_yview;
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
