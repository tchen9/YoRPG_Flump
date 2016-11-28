// Holds and handles sprites, (in the future sounds, text files / saves and more)
package core;

import graphics.Texture;

public class Resources {
    private final String DIR_WINDOW_TEXTURE = "graphicsSrc/sprites/window.png";
    private final String DIR_PLAYER_TEXTURE = "graphicsSrc/sprites/character1.png";
    private final String DIR_NPC1_TEXTURE = "graphicsSrc/sprites/character2.png";
    private final String DIR_NPC2_TEXTURE = "graphicsSrc/sprites/character3.png";
    private final String DIR_TILE_TEXTURE =   "graphicsSrc/sprites/tileset1.png";


    public final Texture TEXTURE_PLAYER_DOWN  = new Texture(DIR_PLAYER_TEXTURE, 32, 48, 4, 0, 0, 4);
    public final Texture TEXTURE_PLAYER_LEFT  = new Texture(DIR_PLAYER_TEXTURE, 32, 48, 4, 0, 1, 4);
    public final Texture TEXTURE_PLAYER_RIGHT = new Texture(DIR_PLAYER_TEXTURE, 32, 48, 4, 0, 2, 4);
    public final Texture TEXTURE_PLAYER_UP    = new Texture(DIR_PLAYER_TEXTURE, 32, 48, 4, 0, 3, 4);

    public final Texture TEXTURE_NPC1_DOWN  = new Texture(DIR_NPC1_TEXTURE, 32, 48, 4, 0, 0, 4);
    public final Texture TEXTURE_NPC1_LEFT  = new Texture(DIR_NPC1_TEXTURE, 32, 48, 4, 0, 1, 4);
    public final Texture TEXTURE_NPC1_RIGHT = new Texture(DIR_NPC1_TEXTURE, 32, 48, 4, 0, 2, 4);
    public final Texture TEXTURE_NPC1_UP    = new Texture(DIR_NPC1_TEXTURE, 32, 48, 4, 0, 3, 4);

    public final Texture TEXTURE_NPC2_DOWN  = new Texture(DIR_NPC2_TEXTURE, 32, 48, 4, 0, 0, 4);
    public final Texture TEXTURE_NPC2_LEFT  = new Texture(DIR_NPC2_TEXTURE, 32, 48, 4, 0, 1, 4);
    public final Texture TEXTURE_NPC2_RIGHT = new Texture(DIR_NPC2_TEXTURE, 32, 48, 4, 0, 2, 4);
    public final Texture TEXTURE_NPC2_UP    = new Texture(DIR_NPC2_TEXTURE, 32, 48, 4, 0, 3, 4);
    
    public final Texture TEXTURE_GROUND = new Texture(DIR_TILE_TEXTURE, 32, 32, 1, 6, 2, 1); 
    public final Texture TEXTURE_WALL = new Texture(DIR_TILE_TEXTURE, 32, 64, 1, 0, 2, 1); 

    public final Texture TEXTURE_WINDOW = new Texture(DIR_WINDOW_TEXTURE); 
}
