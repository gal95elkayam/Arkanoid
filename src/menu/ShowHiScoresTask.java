package menu;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import decorator.KeyPressStoppableAnimation;
import score.HighScoresTable;

public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private HighScoresTable highScoresTable;
    private KeyboardSensor keyboardSensor;

    public ShowHiScoresTask(AnimationRunner runner, HighScoresTable highScoresTable, KeyboardSensor keyboardSensor) {
        this.runner = runner;
        this.highScoresTable = highScoresTable;
        this.keyboardSensor = keyboardSensor;
    }

    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(new HighScoresAnimation(this.highScoresTable),this.keyboardSensor,KeyboardSensor.SPACE_KEY));
        return null;
    }
}