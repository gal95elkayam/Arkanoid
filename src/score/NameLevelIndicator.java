package score;

import biuoop.DrawSurface;
import collections.Sprite;
import game.GameLevel;
import levels.LevelInformation;

import java.awt.Color;

/**
 * The type Name level indicator.
 */
public class NameLevelIndicator implements Sprite {
    private LevelInformation info;

    /**
     * Instantiates a new Name level indicator.
     *
     * @param info the info
     */
    public NameLevelIndicator(LevelInformation info) {
        this.info = info;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 15, "Level Name: " + this.info.levelName(), 15);
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
