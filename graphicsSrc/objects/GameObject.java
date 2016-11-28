// Game Object blueprint for all objects that draw and tick in the game.
package objects;

import core.Game;
import core.TextureHandler;

import java.awt.Graphics2D;
import graphics.Texture;

import java.awt.Rectangle;

public abstract class GameObject {
   
    protected double x,y;
    public TextureHandler sprite;
    protected Rectangle collisionBox;
    protected TYPE type;

    public GameObject(double x, double y, TYPE type) {
        this.x = x;
        this.y = y;
        this.type = type;
        collisionBox = new Rectangle((int)x,(int)y,0,0);
    }

    public void tick() {}
    public void render(Graphics2D g) {}
    public void renderGUI(Graphics2D g) {}

    public void setTexture(Texture texture) {
        sprite = new TextureHandler(texture);
    }

    public void setCollisionBox(int offsetX, int offsetY, int width, int height) {
        collisionBox.setLocation(offsetX,offsetY);
        collisionBox.setSize(width, height);
    }

    // Invoked by handler and called by ALL objects BEFORE tick method is called
    public void tickUtil() {
        if (sprite != null) {
            sprite.tick();
        }
    }
    
    /*public boolean collidesWith(int xa, int ya, GameObject obj) {
        // 2 new boxes translated appropriately
        Rectangle box1 = new Rectangle(xa + (int)collisionBox.getX() ,ya + (int)collisionBox.getY() ,(int)collisionBox.getWidth(), (int)collisionBox.getHeight());
        Rectangle collisionBox2 = obj.getCollisionBox();
        Rectangle box2 = new Rectangle((int)(obj.getX() + collisionBox2.getX()) ,(int)(obj.getY() + collisionBox2.getY()) ,(int)collisionBox2.getWidth(), (int)collisionBox2.getHeight());
        return box1.intersects(box2);
    }*/

    // Destroy the object.
    public void destroy() {
        Game.handler.removeObject(this);
    }

    // Whether object collides with ANY object of type type
    public boolean placeMeeting(double x, double y, TYPE type) {
        return Game.handler.placeMeeting(this, x, y, type);
    }

    // ACCESSORS AND MODIFIERS
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public TYPE getType() {
        return type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Rectangle getCollisionBox() {
        Rectangle result = new Rectangle((int)(collisionBox.getX() + x), (int)(collisionBox.getY() + y), (int)collisionBox.getWidth(), (int)collisionBox.getHeight());
        return result;
    }


    public static enum TYPE {
        PLAYER,
        NPC,
        TILE_WALL,
        TILE_GROUND,
        WINDOW
        };
}
