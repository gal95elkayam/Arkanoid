package blockremover;

import collections.HitListener;
import game.GameLevel;
import geometry.Ball;
import geometry.Block;

/**
 * The type Block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
    // of the number of blocks that remain.
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }


    /**
     * Blocks that are hit and reach 1 hit-points should be removed
     * from the game.
     *
     * @param beingHit the block that being hit
     * @param hitter   the ball that hit the block
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.numberOfHitInBlock() == 1) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }

    /**
     * Get remaining blocks counter.
     *
     * @return the counter
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }
}