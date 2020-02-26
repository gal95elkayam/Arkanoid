package levels;

import biuoop.DrawSurface;
import collections.Sprite;

import java.awt.*;

public class BackgroundForImage implements Sprite {
    private Image image;
    private Color color;

    /**
     * Constructor.
     * @param img image for background
     */
    public BackgroundForImage(Image img) {
        this.image = img;
        this.color = null;
    }
    public BackgroundForImage(Color clr) {
        this.color = clr;
        this.image = null;
    }
    @Override
    public void drawOn(DrawSurface d) {
        //draw image
        if (this.image != null) {
            d.drawImage(0, 0, this.image);
            //draw color
        } else {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800,600);
        }
    }

    @Override
    public void timePassed() {

    }
}
