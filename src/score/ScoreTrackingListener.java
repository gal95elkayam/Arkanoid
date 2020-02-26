package score;

import blockremover.Counter;
import collections.HitListener;
import geometry.Ball;
import geometry.Block;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int SIMPLE_HIT = 5;
    private static final int DESTROY = 15;
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    /**
     * This method checks if the hit event
     * is a simple his or that the block
     * that hitted should be destroy.
     *
     * @param beingHit the block that being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.numberOfHitInBlock() == 1) {
            this.currentScore.increase(DESTROY);
        } else {
            this.currentScore.increase(SIMPLE_HIT);
        }

    }
}