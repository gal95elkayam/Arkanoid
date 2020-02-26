package menu;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import score.HighScoresTable;

import java.awt.*;

public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    public HighScoresAnimation(HighScoresTable scores) {
        this.scores=scores;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(50, 100, "High Score ", 20);
        d.drawText(50, d.getHeight() / 4, "Player Name " , 20);
        d.drawText(500, d.getHeight() / 4,  "Score ", 20);
        d.drawLine(10, (d.getHeight() / 4 + 20), d.getWidth() - 40, (d.getHeight() / 4 + 20));
        for(int i=0; i<this.scores.getHighScores().size(); i++){
            d.drawText(50, 200 + 32 * i,
            this.scores.getHighScores().get(i).getName(), 30);
            d.drawText(500, 200 + 32 * i,
                    Integer.toString(this.scores.getHighScores().get(i).getScore()), 30);
        }
        d.drawText(150, d.getHeight() - 100, "Press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}