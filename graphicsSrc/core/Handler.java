// Handles loops and drawing
package core;

import objects.GameObject;
import objects.ObjectTest;
import objects.TileWall;
import objects.DialogWindow;
import objects.GameWindow;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Comparator;
import java.util.Collections;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.geom.AffineTransform;

public class Handler {  

    private ArrayList<GameObject> gameObjects;
    private DrawDepthComparator depthComparator;

    private ListIterator<GameObject> tickIterator;

    private MapHandler mapHandler;

    public Handler() {
        gameObjects = new ArrayList<GameObject>();
        depthComparator = new DrawDepthComparator();
        
        mapHandler = new MapHandler();
    }

    public void initialize() {
        MapHandler.loadMap("graphicsSrc/maps/map0.png");
        (new MessageHandler("Greetings Traveler! (press Z to continue and X to speed text)", 
                            "Welcome to your journey!", 
                            "Move around with arrow keys",
                            "Interact with Z (face NPC's and press Z to talk)",
                            "Your adventure awaits!")).start();
        
        /*addObject(new ObjectTest(200,200));
        for(int i = 0; i < 5; i++)
            addObject(new TileWall(i * 32, 0));
        */
    }
 
    public void tick() {
        tickIterator = gameObjects.listIterator();
        while ( tickIterator.hasNext() ) {
            GameObject object = tickIterator.next();
            object.tickUtil();
            object.tick();
        }
        /*for( GameObject object : gameObjects ) {
            object.tickUtil();
            object.tick();
        }*/
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,Game.getWview(), Game.getHview());
       
        // Store original transformation
        AffineTransform previousTransform = g2d.getTransform();
        g2d.translate(-1 * (int)Game.getXview(), -1 * (int)Game.getYview());
        
        // Sort by depth for drawing
        Collections.sort(gameObjects, depthComparator);
        for( GameObject object : gameObjects ) {
            object.render(g2d);
        }

        
        // Reset transformations
        g2d.setTransform(previousTransform);

        // Draw gui
        for( GameObject object : gameObjects ) {
            object.renderGUI(g2d);
        }

	g2d.dispose();
    }

    public void addObject(GameObject object) {
        if (tickIterator != null)
            tickIterator.add(object);
        else 
            gameObjects.add(object);
    }

    public void removeObject(GameObject object) {
        //gameObjects.remove(object);
        tickIterator.remove();
        //object = null; // Delete the object
    }

    /// COLLISIONS
    
    public boolean placeMeeting(GameObject object, double x, double y, GameObject.TYPE type) {
        return (instancePlace(object, x, y, type) != null);
    }

    public GameObject instancePlace(GameObject object, double x, double y, GameObject.TYPE type) {
        Rectangle rect = object.getCollisionBox();
        double dx = x - object.getX();
        double dy = y - object.getY();
        return collisionRectangle(rect.getX() + (int)dx, rect.getY() + (int)dy, rect.getWidth(), rect.getHeight(), type);
    }

    public GameObject collisionRectangle(double x, double y, double width, double height, GameObject.TYPE type) {
        GameObject result = null;
        Rectangle rect = new Rectangle((int)x,(int)y,(int)width,(int)height);
        for( GameObject object2 : gameObjects ) {
            Rectangle o2Rect = object2.getCollisionBox();
            if ( type == object2.getType() && rect.intersects(o2Rect) ) {
                result = object2;
                break;
            }
        }
        return result;
    }

}

// Depth comparator so that objects are drawn based on their depth.
class DrawDepthComparator implements Comparator<GameObject> {
    public int compare(GameObject obj1, GameObject obj2) {
        int depth1 = obj1.sprite.getDepth();
        int depth2 = obj2.sprite.getDepth();
        if (depth2 > depth1) return -1;
        if (depth2 < depth1) return 1;
        return 0;
    }
}
