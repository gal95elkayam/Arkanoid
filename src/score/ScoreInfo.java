package score;

import java.io.Serializable;

public class ScoreInfo implements Serializable {
    private String name;
    private  int score;
    public ScoreInfo(String name, int score) {
        this.name=name;
        this.score=score;
    }
    public String getName() {
        return this.name;
    }
    public int getScore() {
        return this.score;
    }
}
