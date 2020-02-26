package score;

import biuoop.DrawSurface;
import blockremover.Counter;
import collections.Sprite;
import game.GameLevel;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    /**
     * The Live.
     */
    private Counter live;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param live the live
     */
    public LivesIndicator(Counter live) {
        this.live = live;
    }

    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.black);
        d.drawText(190, 15, "Live: " + this.live.getValue(), 15);

    }

    @Override
    public void timePassed() {

    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Gets num of live.
     *
     * @return the num of live
     */
    public Counter getNumOFLive() {
        return this.live;
    }
}
