package graphics;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

// Texture with animated sprite preloaded from an image
public class Texture {
    
    private BufferedImage[] images;

    // Creates from an image
    public Texture(BufferedImage image) {
        images = new BufferedImage[1];
        images[0] = image;
    }

    // Creates from image directory
    public Texture(String directory) {
        images = new BufferedImage[1];
        images[0] = loadImage(directory);
    }

    // Creates from image directory as spritesheet
    public Texture(String directory, int width, int height, int imageCount, int rowStart, int columnStart, int columnsPerRow) {
        images = new BufferedImage[imageCount];
        BufferedImage loadFrom = loadImage(directory);
        for( int i = 0; i < imageCount; i++ ) {
            int xx = width  * (rowStart + i%columnsPerRow);
            int yy = height * (columnStart + (int)(i / columnsPerRow));
            images[i] = getCroppedImage(loadFrom, xx, yy, width, height);
        }
    }

    public static BufferedImage loadImage(String directory) {
        File file = new File(directory);
        //URL url = this.getClass().getResource(directory);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.err.println("NO IMAGE FOUND IN DIRECTORY : " + directory);
            e.printStackTrace();
        }
        return image;
    }

    /// Image Manipulation methods

    public static BufferedImage getCroppedImage(BufferedImage image, int x, int y, int width, int height) { 
        BufferedImage crop = image.getSubimage(x,y,width, height);
        BufferedImage copy = new BufferedImage(crop.getWidth(), crop.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics g = copy.createGraphics();
        g.drawImage(crop, 0, 0, null);
        return copy;
    }

    public static BufferedImage getTiledImage(BufferedImage image, double xtile, double ytile) {
        int w = image.getWidth(), h = image.getHeight();

        BufferedImage result = new BufferedImage((int)(w * xtile), (int)(h * ytile), image.getType());
        
        BufferedImage cropBottom = null, cropRight = null, cropCorner = null;

        int wRemainder = w*(int)(xtile - (int)xtile);
        int hRemainder = h*(int)(ytile - (int)ytile);

        if (hRemainder != 0)
            cropBottom = getCroppedImage(image, 0, 0, w, hRemainder);
        if (wRemainder != 0)
            cropRight  = getCroppedImage(image, 0, 0, wRemainder, h);
        if (hRemainder != 0 && wRemainder != 0)
            cropCorner = getCroppedImage(image, 0, 0, w * (int)(xtile - (int)xtile), h * (int)(ytile - (int)ytile));

        Graphics g = result.getGraphics();
        for(int xx = 0; xx < xtile; xx++) {
            for(int yy = 0; yy < ytile; yy++) {
                g.drawImage(image, xx * w, yy * h, null);
            }
            // Draw sub-images at the bottom
            if (cropBottom != null)
                g.drawImage(cropBottom, xx * w, (int) ytile * h, null);
        }

        // Draw sub-images at the right
        if (cropRight != null)
            for(int yy = 0; yy < ytile; yy++) {
                g.drawImage(cropRight, (int)xtile * w, yy * h, null);
            }

        // Draw lower right corner
        if (cropCorner != null)
            g.drawImage(cropCorner, (int)xtile * w, (int)ytile * w, null);
        g.dispose();

        return result;
    }

    public static BufferedImage getScaledImage(BufferedImage image, double xscale, double yscale) {
        BufferedImage result = new BufferedImage((int) (image.getWidth() * xscale), (int) (image.getHeight() * yscale), image.getType());
        AffineTransform scale = new AffineTransform();
        scale.scale(xscale, yscale);
        AffineTransformOp scaleOp = new AffineTransformOp(scale, AffineTransformOp.TYPE_BILINEAR);

        scaleOp.filter(image,result);
        return result;
    }

    // double argument because imageIndex is double and needs to be cast every time we call this function
    public BufferedImage getImage(double index) {
        return images[(int)index];
    }

    // Returned subimage is not to be manipulated in any way
    public BufferedImage getSubimage(double index, int left, int top, int width, int height) {
        BufferedImage image = getImage(index);
        return image.getSubimage(left, top, width, height);
    }

    public int getImageNumber() {
        return images.length;
    }

}
