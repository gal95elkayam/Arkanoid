package menu;

import biuoop.GUI;

public class EndGameTask implements Task{

    private GUI gui;

    public EndGameTask(GUI gui) {
        this.gui = gui;
    }

    public Void run() {
        this.gui.close();
        System.exit(0);
        return null;
    }
}
