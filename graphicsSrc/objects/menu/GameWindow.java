package objects;

import core.Game;

import java.io.File;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import graphics.Texture;

public class GameWindow extends GameObject {
    
    public static final Font MAIN_FONT = loadFont("graphicsSrc/fonts/Abel-Regular.ttf");
    
    protected double width, height;

    public GameWindow(double x, double y, double width, double height) {
        super(x, y, TYPE.WINDOW);
        this.width = width;
        this.height = height;
        this.setTexture(Game.resources.TEXTURE_WINDOW);
    }

    //public void tick() {} // do nothing

    //public void render() {} // do nothing

    public void renderGUI(Graphics2D g) {
        // Different parts of the window
        boolean heightPositive = ((int)height - 32 > 0);
        boolean widthPositive = ((int)width - 32 > 0);

        BufferedImage topleft, topright, bottomleft, bottomright, middleleft = null, middleright = null, topmiddle = null, bottommiddle = null, middle = null; 
        topleft      = sprite.getSubimage(64,0,16,16);
        bottomleft   = sprite.getSubimage(64,64 - 16,16,16);
        topright     = sprite.getSubimage(128 - 16,0,16,16);
        bottomright  = sprite.getSubimage(128 - 16,64 - 16,16,16);
        if (heightPositive) {
            middleleft   = Texture.getTiledImage(sprite.getSubimage(64,16,16,32), 1, (height - 32) / 32);
            middleright  = Texture.getTiledImage(sprite.getSubimage(128 - 16,16,16,32), 1, (height - 32) / 32);
        }
        if (widthPositive) {
            topmiddle    = Texture.getTiledImage(sprite.getSubimage(64 + 16,0,32,16), (width - 32) / 32, 1);
            bottommiddle = Texture.getTiledImage(sprite.getSubimage(64 + 16,64 - 16,32,16), (width - 32) / 32, 1);
        }

        int d = 5; // border offset so that we can have rounded borders without the middle jutting out
        boolean midPositive = (width - 2*d > 0 && height - 2*d > 0);
        if (midPositive)
            middle = Texture.getScaledImage(sprite.getSubimage(0,0,64,64), (width - 2*d) / 64, (height - 2*d) / 64);
        
        // TODO: Make separate image for middle and border and apply effects (color and maybe other stuff)
        
        // because I am NOT type casting 16 times.
        int xa = (int)x, ya = (int)y, h = (int) height, w = (int) width;

        if (midPositive)
            g.drawImage(middle, xa + d, ya + d, null);

        g.drawImage(topleft, xa, ya, null);
        g.drawImage(bottomleft, xa, ya + h - 16, null);
        g.drawImage(topright, xa + w - 16, ya, null);
        g.drawImage(bottomright, xa + w - 16, ya + h - 16, null);
        if (heightPositive) {
            g.drawImage(middleleft, xa, ya + 16, null);
            g.drawImage(middleright, xa + w - 16, ya + 16, null);
        }
        if (widthPositive) {
            g.drawImage(topmiddle, xa + 16, ya, null);
            g.drawImage(bottommiddle, xa + 16, ya + h - 16, null);
        }    
    }

    public static Font loadFont(String dir) {
        Font result;
        try {
            // Create font from directory and set size to 8
            result = Font.createFont( Font.TRUETYPE_FONT, new File(dir) );
            result = result.deriveFont(16f);
        } catch ( Exception e ) {
            System.err.println("Font in " + dir + " not found! Using default font");
            result = new Font("Arial", Font.BOLD, 16);
        }
        return result;
    }
}
