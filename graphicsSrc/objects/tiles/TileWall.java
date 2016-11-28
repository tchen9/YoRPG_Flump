// Figure out how to do this without having a new object each and every time u make a new tile.......
package objects;

import core.Game;

public class TileWall extends Tile {
        
    public TileWall(int x, int y) {
        super(GameObject.TYPE.TILE_WALL, Game.resources.TEXTURE_WALL, x, y, 0, 32, true);
        sprite.setDepth(y);
    }
}
