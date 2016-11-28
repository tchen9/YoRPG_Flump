// This handler deals with pausing, fighting, and everything in between.
package core;

public class GameEventHandler {
    private boolean dialog   = false;
    private boolean fighting = false;

    public void setDialog(boolean dialog) {
        this.dialog = dialog;
    }

    public void setFighting(boolean fighting) {
        this.fighting = fighting;
    }

    public boolean isDialog() {
        return dialog;
    }

    public boolean isFighting() {
        return fighting;
    }
}
