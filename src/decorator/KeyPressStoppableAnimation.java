package decorator;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation decorated;
    private boolean done;
    private KeyboardSensor keyboardSensor;
    private String key;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param decorated      the decorated
     * @param keyboardSensor the keyboard sensor
     * @param key            the key
     */
    public KeyPressStoppableAnimation(Animation decorated, KeyboardSensor keyboardSensor, String key) {
        this.decorated = decorated;
        this.done = false;
        this.key = key;
        this.keyboardSensor = keyboardSensor;
        this.isAlreadyPressed = true;
    }

    @Override
    public boolean shouldStop() {
        return this.done;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.decorated.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.key)
                && !this.isAlreadyPressed) {
            this.done = true;
        }
        if (!this.keyboardSensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }
}