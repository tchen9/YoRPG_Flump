// Figure out how to do this without having a new object each and every time u make a new tile.......
package objects;

import core.Game;

public class TileGround extends Tile {
        
    public TileGround(int x, int y) {
        super(GameObject.TYPE.TILE_GROUND, Game.resources.TEXTURE_GROUND, x, y, 0, 32, true);
        sprite.setDepth(-999);
    }
}
