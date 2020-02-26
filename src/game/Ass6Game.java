package game;

import animation.AnimationRunner;
import biuoop.GUI;
import levels.*;
import menu.*;
import score.HighScoresTable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * Run the game.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);
        File file = new File("highscores.txt");
        HighScoresTable highScoresTable;
        if (file.exists()) {
            highScoresTable = HighScoresTable.loadFromFile(file);
        } else {
            highScoresTable = new HighScoresTable(5);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Menu<Task<Void>> subMenu = new MenuAnimation<>("Level-Sets", gui.getKeyboardSensor(), runner);
        try {
            List<LevelSet> levelsSets;

            if (args.length != 0) {
                InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
                levelsSets = LevelSetsReader.fromReader(new InputStreamReader(inputStream));

            } else {
                InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
                levelsSets = LevelSetsReader.fromReader(new InputStreamReader(inputStream));
            }
            for (LevelSet selection : levelsSets) {
                subMenu.addSelection(selection.getKey(),
                        selection.getMessage(),
                        new StartGameTask(runner, gui, file,
                                selection.getLevelsForSet(), highScoresTable));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                    "Arkanoid", gui.getKeyboardSensor(), runner);
            menu.addSubMenu("s", "Start Game", subMenu);
            menu.addSelection("h", "High Score", new ShowHiScoresTask(runner, highScoresTable, gui.getKeyboardSensor()));
            menu.addSelection("e", "Exit", new EndGameTask(gui));
            while (true) {
                runner.run(menu);
                Task<Void> task = menu.getStatus();
                task.run();
                ((MenuAnimation<Task<Void>>) menu).setStop(false);
            }
        }
    }


