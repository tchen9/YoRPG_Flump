// Controls flow of multiple messages. Used for dialogs.
package core;

import objects.DialogWindow;
import java.awt.event.KeyEvent;

public class MessageHandler {

    private String[] messages;
    private int counter;

    private DialogWindow currentMessage;

    public MessageHandler(String... args) {
        messages = new String[args.length];
        for( int i = 0; i < args.length; i++ ) {
            messages[i] = args[i];
        }
    }

    // Create first message and begin the chain
    public void start() {
        Game.gameEventHandler.setDialog(true);
        currentMessage = new DialogWindow(messages[0], this); 
        Game.handler.addObject(currentMessage); 
        counter = 0;
    }

    public void nextMessage() {
        counter++;
        if (counter < messages.length) {
            currentMessage = new DialogWindow(messages[counter], this);
            Game.handler.addObject(currentMessage);
        } else {
            // We're done with dialog and now we can move!
            Game.gameEventHandler.setDialog(false);
        }
    }
}
