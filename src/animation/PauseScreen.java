package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}