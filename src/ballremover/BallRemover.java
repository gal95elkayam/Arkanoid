package ballremover;

import collections.HitListener;
import game.GameLevel;
import geometry.Ball;
import geometry.Block;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;


    /**
     * Instantiates a new Ball remover.
     *
     * @param game the game
     */
    public BallRemover(GameLevel game) {
        this.game = game;

    }

    @Override
    /**
     * This method check if the ball hits the point the take him out of the game.
     *
     * @param beingHit the point of "death"
     * @param hitter   the ball the hit the "death" point on the block
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.game.getRemainingBalls().decrease(1);
        if (this.game.getRemainingBalls().getValue() == 0) {
            this.game.getNumberOfLives().decrease(1);

        }
    }
}

