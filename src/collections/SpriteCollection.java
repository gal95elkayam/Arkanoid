package collections;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> sprite;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprite = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprite.add(s);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprite.size(); ++i) {
            this.sprite.get(i).timePassed();
        }
    }

    /**
     * Draw all on.
     * call drawOn(d) on all sprites
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprite.size(); ++i) {
            this.sprite.get(i).drawOn(d);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprite.remove(s);
    }
}