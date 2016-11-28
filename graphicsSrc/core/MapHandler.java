// Simple map handler that converts an image
// to a map.
// TODO: Make it convert a Tiled editor file into a map,
//       or from Ogmo editor (or any other game editor out there whatever)
package core;

import objects.*;//GameObject;
import graphics.Texture;

import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class MapHandler {
    private static HashMap<Integer, Integer> rgbToHexInt;

    private static final MessageHandler NPC1_MESSAGE = new MessageHandler("Hello there!",
                                                                          "I heard that in this oddly shaped wooden structure lies adventure!",
                                                                          "Indeed, it is rumored that monsters and other sorts of creatures lie in the bowels of said poorly-constructed low-budget fortress",
                                                                          "Why don't you go check around?"
                                                                          );

    private static final MessageHandler NPC2_MESSAGE = new MessageHandler("Ah I see you are interested in entering.... this thing",
                                                                          "Ah and you are looking for adventure?",
                                                                          "Hmmmmm..... There appears to be just one problem here",
                                                                          "You see, the person who constructed that palace wasn't a very bright chap",
                                                                          "He left it wide open and all of the monsters escaped",
                                                                          "So yeah, they're all gone now",
                                                                          "Have fun navigating this... maze"
                                                                           );
    private static final MessageHandler NPC3_MESSAGE = new MessageHandler("Why am I here?",
                                                                          "Why AM I here?!",
                                                                          "WHY AM I HERE?!?!");
    private static final MessageHandler NPC4_MESSAGE = new MessageHandler("The princess is right around the corner!",
                                                                          "I think she needs to be saved!");

    private static final MessageHandler NPCSECRET_MESSAGE = new MessageHandler("...",
                                                                               "...",
                                                                               "(the designer forgot to give this character text)",
                                                                               "(or a personality)",
                                                                               "Let us give a moment of silence for this characterless character",
                                                                               "...",
                                                                               "............",
                                                                               ".............................................................................................................................");

    private static final MessageHandler NPCSHOP_MESSAGE = new MessageHandler("Hello there!",
                                                                             "I'm the blacksmith!",
                                                                             "If you ever need to buy or repair weapons or armour, you can talk to me!",
                                                                             "Oh what's that?",
                                                                             "Oh, you don't have any weapons",
                                                                             "Or armour",
                                                                             "Or any money for the matter",
                                                                             "...",
                                                                             "Why are you here?");

    private static final MessageHandler NPCEND_MESSAGE = new MessageHandler("Hi",
                                                                            "I'm the princess",
                                                                            "...",
                                                                            "You mean I don't look like a princess?",
                                                                            "Just because I'm a man with a beard and armour doesn't mean I can't be a princess!",
                                                                            "You better check your monarchial privilege!");

    public static void loadMap(String src) {

        // Initialize if map is empty
        if (rgbToHexInt == null) {
            /// This is very janky. This should be done automatically.....
            rgbToHexInt = new HashMap<Integer, Integer>();
            rgbToHexInt.put(new Color(0,0,0).getRGB(), 0x000000);
            rgbToHexInt.put(new Color(0,255,0).getRGB(), 0x00ff00);
            rgbToHexInt.put(new Color(255,255,0).getRGB(), 0xffff00);
            rgbToHexInt.put(new Color(255,255,1).getRGB(), 0xffff01);
            rgbToHexInt.put(new Color(255,255,2).getRGB(), 0xffff02);
            rgbToHexInt.put(new Color(255,255,3).getRGB(), 0xffff03);
            rgbToHexInt.put(new Color(255,255,153).getRGB(), 0xffff99);
            rgbToHexInt.put(new Color(0,0,255).getRGB(), 0x0000ff);
            rgbToHexInt.put(new Color(255,0,0).getRGB(), 0xff0000);
            System.out.println("aight");
        }

        
        BufferedImage image = Texture.loadImage(src);
        for(int xx = 0; xx < image.getWidth(); xx++) {
            for(int yy = 0; yy < image.getHeight(); yy++) {
                Game.handler.addObject(new TileGround(xx * 32, yy * 32));
                int rgb = image.getRGB(xx,yy);
                if (rgbToHexInt.containsKey(rgb)) {
                    int hex = rgbToHexInt.get(rgb); 
                    switch (hex) {
                        case 0x000000:
                            Game.handler.addObject(new TileWall(xx * 32, yy * 32));
                            break;
                        case 0x00ff00:
                            Game.handler.addObject(new ObjectTest(xx * 32, yy * 32));
                            break;
                        case 0xffff00:
                            Game.handler.addObject(new NPC(xx * 32, yy * 32, NPC1_MESSAGE));
                            break;
                        case 0xffff01:
                            Game.handler.addObject(new NPC(xx * 32, yy * 32, NPC2_MESSAGE));
                            break;
                        case 0xffff02:
                            Game.handler.addObject(new NPC(xx * 32, yy * 32, NPC3_MESSAGE));
                            break;
                        case 0xffff03:
                            Game.handler.addObject(new NPC(xx * 32, yy * 32, NPC4_MESSAGE));
                            break;
                        case 0xffff99:
                            Game.handler.addObject(new NPC(xx * 32, yy * 32, NPCSECRET_MESSAGE));
                            break;
                        case 0x0000ff:
                            Game.handler.addObject(new NPC(xx * 32, yy * 32, NPCSHOP_MESSAGE));
                            break;
                        case 0xff0000:
                            Game.handler.addObject(new Princess(xx * 32, yy * 32, NPCEND_MESSAGE));
                    }
                }
            }
        }
    }

}
