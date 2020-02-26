package game;

import animation.*;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import blockremover.Counter;
import decorator.KeyPressStoppableAnimation;
import levels.Level4;
import levels.Level3;
import levels.LevelInformation;
import levels.Level2;
import levels.Level1;
import menu.HighScoresAnimation;
import score.HighScoresTable;
import score.ScoreInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private HighScoresTable highScoresTable;
    private File file;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, HighScoresTable highScoresTable, File file) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.highScoresTable = highScoresTable;
        this.file = file;

    }

//    /**
//     * The entry point of application.
//     *
//     * @param argsg the input arguments
//     */
//    public static void main(String[] argsg) {
//        List<LevelInformation> levels = new ArrayList<>();
//        LevelInformation level1 = new Level1();
//        LevelInformation level2 = new Level2();
//        LevelInformation level3 = new Level3();
//        LevelInformation level4 = new Level4();
//        levels.add(level1);
//        levels.add(level2);
//        levels.add(level3);
//        levels.add(level4);
//        GUI gui = new GUI("GameLevel", 800, 600);
//        AnimationRunner runner = new AnimationRunner(gui);
//        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor(), gui,);
//        gameFlow.runLevels(levels);
//        gui.close();
//
//
//    }

    /**
     * Run levels.
     * This method will be in charge of creating the differnet levels,
     * and runs the appropriate levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {

        Counter counterScore = new Counter(0);
        Counter lives = new Counter(7);
        String st = null;
        int finallyScore = 0;

        for (int i = 0; levels.size() < levels.size(); i++) {

            GameLevel level = new GameLevel(keyboardSensor, gui, levels.get(i), animationRunner, counterScore, lives);

            level.initialize();
            level.playOneTurn();

            while (level.getRemainingBlocks() > 0 && level.getNumberOfLives().getValue() > 0) {
                level.removePaddle();
                level.removeBalls();
                level.playOneTurn();
            }
            if (level.getNumberOfLives().getValue() == 0) {
                st = "run out of lives";
                finallyScore = level.getScore();
                break;
            }
            //end game
            if (i == levels.size()-1 && level.getRemainingBlocks() == 0) {
                st = "clear all the levels";
                finallyScore = level.getScore();
                break;
            }
        }
        if (st.equals("clear all the levels")) {
            Animation win = new YouWinScreen(keyboardSensor, finallyScore);
            this.animationRunner.run(new KeyPressStoppableAnimation(win, this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY));
        }
        if (st.equals("run out of lives")) {
            Animation gameOver = new GameOverScreen(keyboardSensor, finallyScore);
            this.animationRunner.run(new KeyPressStoppableAnimation(gameOver, this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY));
        }
        if (this.highScoresTable.getRank(finallyScore) <= this.highScoresTable.getSize()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo scoreInfo = new ScoreInfo(name, finallyScore);
            this.highScoresTable.add(scoreInfo);
            try {
                this.highScoresTable.save(this.file);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
        Animation highScore=new HighScoresAnimation(this.highScoresTable);
        this.animationRunner.run(new KeyPressStoppableAnimation(highScore, this.keyboardSensor,
                KeyboardSensor.SPACE_KEY));
    }
}
