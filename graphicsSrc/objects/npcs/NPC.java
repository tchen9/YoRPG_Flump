// NPC parent that simply spews dialog and looks at player
package objects;

import core.MessageHandler;
import core.Game;
import core.TextureHandler;
import graphics.Texture;

public class NPC extends Human {

    private MessageHandler messages;

    public NPC(double x, double y, MessageHandler messages) {
        super(x + 16,y + 16,GameObject.TYPE.NPC);
        sprite.setImageSpeed(0);
        sprite.setTexture(Game.resources.TEXTURE_NPC1_DOWN);
        this.messages = messages;
        TEXTURE_LEFT = Game.resources.TEXTURE_NPC1_LEFT;
        TEXTURE_RIGHT = Game.resources.TEXTURE_NPC1_RIGHT;
        TEXTURE_UP= Game.resources.TEXTURE_NPC1_UP;
        TEXTURE_DOWN = Game.resources.TEXTURE_NPC1_DOWN;
    }


    // Start dialog chain
    public void speak() {
        messages.start(); 
    }
}
