package animation;

import biuoop.DrawSurface;
import collections.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private int countFrom;
    private double numOfSeconds;
    private boolean running;
    private long numOfMillis;
    private long initiationTime;
    private int initialCount;


    /**
     * Instantiates a new Countdown animation.
     * Construct a count down animation object
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.countFrom = countFrom;
        this.initialCount = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.running = true;
        this.numOfMillis = (long) (numOfSeconds * 1000);
        this.initiationTime = System.currentTimeMillis();
    }

    /**
     * this method draws each frame of the animation.
     *
     * @param d the DrawSurface to draw on.
     */

    public void doOneFrame(DrawSurface d) {
        if (this.countFrom == 0) {
            this.running = false;
        }
        gameScreen.drawAllOn(d);
        d.setColor(Color.magenta);
        d.drawText(390, 90, "" + countFrom, 55);
        if (System.currentTimeMillis() - this.initiationTime
                > this.numOfMillis / this.initialCount) {
            this.initiationTime = System.currentTimeMillis();
            this.countFrom--;
        }
    }
    /**
     * this method returns true if count down animation has to stop,
     * false otherwise.
     * @return true if count down animation has to stop, false otherwise.
     */

    public boolean shouldStop() {
        return !this.running;
    }
}