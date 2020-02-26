package collections;
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     *  draw the sprite to the screen
     * @param d the d
     */

    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
    void timePassed();
}
