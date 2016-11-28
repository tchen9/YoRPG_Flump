// Handles texture updating.
package core;

import graphics.Texture;

import java.awt.image.BufferedImage;

public class TextureHandler {
    
    private Texture texture;
    private int depth; // Draw order, taken care of in Handler.java
    private int xOffset, yOffset;
    private double imageIndex, imageSpeed;
    private boolean visible;

    public TextureHandler(Texture texture) {
        this.texture = texture;
        this.depth = 0;
        this.xOffset = 0;
        this.yOffset = 0;
        this.imageIndex = 0;
        this.imageSpeed = 1;
        this.visible = true;
    }

    public TextureHandler(Texture texture, int depth, int xOffset, int yOffset, int imageSpeed) {
        this(texture);
        this.depth = depth;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.imageSpeed = imageSpeed;
    }

    public void tick() {
        if (visible && texture != null) {
            imageIndex += imageSpeed;
            imageIndex %= texture.getImageNumber();
        }
    }

    // Get current image (based on imageindex
    public BufferedImage getCurrent() {
        return texture.getImage(imageIndex);
    }

    // Get subimage (useful for Windows)
    public BufferedImage getSubimage(double index, int left, int top, int width, int height) {
        return texture.getSubimage(index, left, top, width, height);
    }

    // Get current subimage (useful for Windows)
    public BufferedImage getSubimage(int left, int top, int width, int height) {
        return texture.getSubimage(imageIndex, left, top, width, height);
    }

    // Standard Getters and setters
    
    public Texture getTexture() {
        return texture;
    }

    public int getDepth() {
        return depth;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public double getImageSpeed() {
        return imageSpeed;
    }

    public double getImageIndex() {
        return imageIndex;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setXOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public void setImageSpeed(double imageSpeed) {
        this.imageSpeed = imageSpeed;
    }

    public void setImageIndex(double imageIndex) {
        this.imageIndex = imageIndex;
    }
}
