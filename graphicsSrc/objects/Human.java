// Human with simple human properties including direction and walk cycle
package objects;

import core.Game;
import core.TextureHandler;
import graphics.Texture;

import java.awt.Color;
import java.awt.Graphics2D;

public class Human extends GameObject {

    // Direction and realfacing (realFacing never is NONE)
    protected Direction facing, realFacing;
    
    protected Texture TEXTURE_UP, TEXTURE_DOWN, TEXTURE_LEFT, TEXTURE_RIGHT;
    

    public static enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        NONE
    };

    public Human(double x, double y, TYPE type) {
        super(x,y,type);
        facing = Direction.NONE;
        realFacing = Direction.RIGHT;
        setCollisionBox(-8,-12,16,12);        
        sprite = new TextureHandler(Game.resources.TEXTURE_PLAYER_RIGHT); // Default
        sprite.setXOffset(16);
        sprite.setYOffset(48);
    }

    public void tick() {
        sprite.setDepth((int)y);
        if (facing != Direction.NONE) {
            realFacing = facing;
            sprite.setTexture(directionToTexture(facing));
        }
    }

    public void render(Graphics2D g) {
        g.drawImage(sprite.getCurrent(), (int) x - sprite.getXOffset(), (int) y - sprite.getYOffset(), null);
    }

    protected void setDirectionByAxis(int xaxis, int yaxis) {

        if (xaxis > 0 && (yaxis == 0 || facing == Direction.LEFT)) {
            facing = Direction.RIGHT;
        } else if (xaxis < 0 && (yaxis == 0 || facing == Direction.RIGHT)) {
            facing = Direction.LEFT;
        } else if (yaxis > 0 && (xaxis == 0 || facing == Direction.UP)) {
            facing = Direction.DOWN;
        } else if (yaxis < 0 && (xaxis == 0 || facing == Direction.DOWN)) {
            facing = Direction.UP;
        }
        if (facing == Direction.NONE) {
            sprite.setImageIndex(0);
            sprite.setImageSpeed(0);
        } else {
            // Spend the first frame with leg stretched and not staying still
            if (sprite.getImageSpeed() == 0) {
                sprite.setImageIndex(1);
            }
            sprite.setImageSpeed(3.5 / 60.0);
        }
        if ((xaxis == 0) && (yaxis == 0))
            facing = Direction.NONE;
    }

    // REDEFINED IN EVERY CHARACTER
    private Texture directionToTexture(Direction dir) {
        switch (dir) {
            case LEFT:
                return TEXTURE_LEFT;
            case RIGHT:
                return TEXTURE_RIGHT;
            case UP:
                return TEXTURE_UP;
            case DOWN:
                return TEXTURE_DOWN;
        }
        return null;
    }

    public static Direction oppositeFacing(Direction dir) {
        switch(dir) {
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
        }
        return dir;
    }

    /// Accessors and modifiers
    
    public void setFacing(Direction facing) {
        this.facing = facing;
    }
    
    public Direction getFacing() {
        return facing;
    }



}
