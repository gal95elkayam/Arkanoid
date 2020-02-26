package score;

import biuoop.DrawSurface;
import blockremover.Counter;
import collections.Sprite;
import game.GameLevel;
import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;


    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(390, 15, "Score: " + this.score.getValue(), 15);
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
}
