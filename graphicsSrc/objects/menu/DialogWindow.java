package objects;

import core.Game;
import core.Input;
import core.MessageHandler;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.event.KeyEvent;

public class DialogWindow extends GameWindow {

    private final int LINE_MAX_WIDTH = Game.getWview() - 26 - 16;
    private Font font = GameWindow.MAIN_FONT;
    private FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true); // useless, really.

    private String fullText;
    private String[] text;

    private MessageHandler parentHandler;

    private double scrollIndex;
    private double scrollSpeed;
    private double scrollSpeedFast;

    private int lastScrollIndex;

    private int lineIndex; // updates every time a new line is reached

    private boolean finishedScrolling;

    public DialogWindow(String text, MessageHandler parentHandler) {
        super(8, Game.getHview() - 64, Game.getWview() - 16, 64 - 8);
        fullText = text;
        this.parentHandler = parentHandler;
        this.text = new String[]{ "", "" };
        scrollIndex = 0;
        lastScrollIndex = 0; // -1 because we want to start printing immediately.
        scrollSpeed = 2.5 / 4.0;
        scrollSpeedFast = scrollSpeed*2.5;
        lineIndex = (int)scrollSpeed;
        finishedScrolling = false;
    }

    public void tick() {        
        if ((int)scrollIndex > fullText.length() - 1) finishedScrolling = true;
        if (!finishedScrolling) {
            
            if (Input.keyCheck(KeyEvent.VK_X))
                scrollIndex += scrollSpeedFast;
            else
                scrollIndex += scrollSpeed;
            
            // Stop when we've reached the end

            
            // Append this line with the next character, if we have reached a new scroll index.
            if ((int) scrollIndex != lastScrollIndex) {
                int delta = (int) (scrollIndex - lastScrollIndex);
                for(int i = 0; i < delta; i++) {
                    text[lineIndex] += fullText.charAt((int)lastScrollIndex);
                    lastScrollIndex++;
                    if ((int) lastScrollIndex > fullText.length() - 1 ) break;
                }
            }
            // Find this line's width to check if we move on to the next line
            int width = text[lineIndex].length() * 4;
            
            
            //(int)(font.getStringBounds(text[lineIndex], frc).getWidth());

            // Move on to the next line or shift text down
            if (width >= LINE_MAX_WIDTH) {
                lineIndex++;
                // If there is overlap, shift the text down.
                if (lineIndex >= 2) {
                    lineIndex = 1;
                    text[0] = text[1];
                    text[1] = "";
                }
            }
        } else {
            if (Input.keyCheckPressed(KeyEvent.VK_Z)) {
                destroy();
 
                Input.unPressKey(KeyEvent.VK_Z); // SO we don't get weird dialog conflicts.
                if (parentHandler != null)
                    parentHandler.nextMessage();
            }
        }
    }

    public void renderGUI(Graphics2D g) {
        super.renderGUI(g); // render window
        g.setFont(GameWindow.MAIN_FONT);
        g.setColor(Color.WHITE);
        System.out.println("IF THE PROGRAM CRASHES with a fatal jre error RIGHT after this message, then you can't draw text for some reason.");
        g.drawString(text[0], (int) x + 6, (int) y + 4 + 16);
        g.drawString(text[1], (int) x + 6, (int) y + 4 + 16 + 16);
       
        // If done scrolling, draw an arrow to signify that we can continue.
        if (finishedScrolling) {
            // dx: make it bob up and down
            int dy = (int) (3D * Math.sin( 5*System.currentTimeMillis() / 1000D));
            g.drawImage(sprite.getSubimage(96, 64, 16, 16), (int) (x + width) - 22, (int) (y + height) - 24 + dy, null );
        }
    }
}
