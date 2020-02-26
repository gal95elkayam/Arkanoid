package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Game over screen.
 */
public class GameOverScreen implements Animation {
    private KeyboardSensor keyboard;
    private int score;


    /**
     * Instantiates a new Game over screen.
     *
     * @param keyboard the keyboard
     * @param score    the score
     */
    public GameOverScreen(KeyboardSensor keyboard, int score) {
        this.keyboard = keyboard;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(150, d.getHeight() / 2, "Game Over. Your score is " + this.score, 20);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
