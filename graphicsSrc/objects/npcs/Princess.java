package objects;

import core.Game;
import core.MessageHandler;

public class Princess extends NPC {
    
    public Princess(double x, double y, MessageHandler messages) {
        super(x,y,messages);

        TEXTURE_LEFT = Game.resources.TEXTURE_NPC2_LEFT;
        TEXTURE_RIGHT = Game.resources.TEXTURE_NPC2_RIGHT;
        TEXTURE_UP= Game.resources.TEXTURE_NPC2_UP;
        TEXTURE_DOWN = Game.resources.TEXTURE_NPC2_DOWN;
        
        sprite.setTexture(Game.resources.TEXTURE_NPC2_DOWN);
    }
}
