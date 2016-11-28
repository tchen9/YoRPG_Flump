// Tiles for the game.
package objects;

import core.TextureHandler;
import graphics.Texture;

import java.awt.Graphics2D;
import java.awt.Color;

public abstract class Tile extends GameObject {

    private boolean isCollidable;

    public Tile(TYPE type, Texture texture, double x, double y, int xOffset, int yOffset, boolean isCollidable) {
        super(x,y,type);
        sprite = new TextureHandler(texture, -1, xOffset, yOffset, 0);
        this.isCollidable = isCollidable;

        if (isCollidable) {
            setCollisionBox(0,0,32, 32);
        }
    }

    public void tick() {
       // Do nothing 
    }

    public void render(Graphics2D g) {
        g.drawImage(sprite.getCurrent(), (int)x - sprite.getXOffset(), (int)y - sprite.getYOffset(), null);
    }


    public boolean isCollidable() {
        return isCollidable;
    }
}
