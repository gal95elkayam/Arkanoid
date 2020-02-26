package menu;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.LevelInformation;
import score.HighScoresTable;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StartGameTask implements Task {
    private AnimationRunner runner;
    private GUI gui;
    private File file;
    private List<LevelInformation> levels;
    private HighScoresTable highScoresTable;

    public StartGameTask(AnimationRunner runner, GUI gui, File file, List<LevelInformation> levels, HighScoresTable highScoresTable) {
        this.runner = runner;
        this.gui = gui;
        this.file = file;
        this.levels = levels;
        this.highScoresTable = highScoresTable;
    }
    public Void run() {
        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor(), gui,highScoresTable,file);
        gameFlow.runLevels(levels);
        return null;
    }
}
