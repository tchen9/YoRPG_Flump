package objects;

import core.Input;
import core.Game;
import core.Handler;
import core.TextureHandler;
import graphics.Texture;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;

import java.awt.event.KeyEvent;

public class ObjectTest extends Human {

    private double vx, vy;


    public ObjectTest(double x, double y) {
        super(x,y,GameObject.TYPE.PLAYER);
        sprite.setTexture(Game.resources.TEXTURE_PLAYER_RIGHT);
        sprite.setImageSpeed(1.4);

        vx = 0;
        vy = 0;
     
        TEXTURE_LEFT = Game.resources.TEXTURE_PLAYER_LEFT;
        TEXTURE_RIGHT = Game.resources.TEXTURE_PLAYER_RIGHT;
        TEXTURE_UP= Game.resources.TEXTURE_PLAYER_UP;
        TEXTURE_DOWN = Game.resources.TEXTURE_PLAYER_DOWN;
    }

    public void tick() { 
        super.tick();
        Game.setXview( x - Game.getWview() / 2);
        Game.setYview( y - Game.getHview() / 2);
        Game.capView(0,0, 32 * 32, 32 * 32);

        if (!Game.gameEventHandler.isDialog()) {
            if (Input.keyCheckPressed(KeyEvent.VK_Z)) {
                interact();
            }
            move();
            physics();
        } else {
            sprite.setImageSpeed(0);
        }

    }


    // Does movement and updates sprites
    private void move() {
        int xaxis = Input.getKeyAxisHorizontal();
        int yaxis = Input.getKeyAxisVertical();

        //x = 250 + (200.0 * Math.sin(4.0 * System.currentTimeMillis() / 1000.0));
        vx = 2*xaxis;
        vy = 2*yaxis;

        setDirectionByAxis(xaxis, yaxis);
    }

    private void physics() {
        if (placeMeeting(x + vx, y, TYPE.TILE_WALL)) {
            int dx = 0;
            while (!placeMeeting(x + dx, y, TYPE.TILE_WALL)) dx += Math.signum(vx);
            //x += dx - Math.signum(vx);
            vx = 0;
        }
        x += vx;
        
        if (placeMeeting(x, y + vy, TYPE.TILE_WALL)) {
            int dy = 0;
            while (!placeMeeting(x, y + dy, TYPE.TILE_WALL)) dy += Math.signum(vy);
            //y += dy - Math.signum(vy);
            vy = 0;
        }
        y += vy;
    }

    // Interact with objects and npcs
    private void interact() {
        int dx = -16, dy = -16;
        switch(realFacing) {
            case LEFT:
                dx = -32;
                break;
            case RIGHT:
                dx = 0;
                break;
            case UP:
                dy = -32;
                break;
            case DOWN:
                dy = 0;
                break;
        }

        NPC npc = (NPC)Game.handler.collisionRectangle(x + dx,y + dy, 32, 32, TYPE.NPC);
//instancePlace(this,x + dx, y + dy,TYPE.NPC);        
        if (npc != null) {
            npc.setFacing(Human.oppositeFacing(realFacing));
            npc.speak();
        }
    }

}
