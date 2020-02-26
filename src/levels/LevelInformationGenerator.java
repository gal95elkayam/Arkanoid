package levels;

import collections.Sprite;
import geometry.Block;
import geometry.Velocity;

import java.awt.*;
import java.util.List;

public class LevelInformationGenerator implements LevelInformation {
    private int numOfBalls;
    private List<Velocity> intiallBallVeloc;
    private String levelName;
    private int paddleWidth;
    private int blocksToRemove;
    private int paddleSpeed;
    private List<Block> blockList;
    private Image image;
    private Color color;

    public LevelInformationGenerator(int numOfBalls, List<Velocity> intiallBallVeloc, String levelName, int paddleWidth,
                                     int blocksToRemove, int paddleSpeed, List<Block> blockList) {
        this.numOfBalls = numOfBalls;
        this.intiallBallVeloc = intiallBallVeloc;
        this.levelName = levelName;
        this.paddleWidth = paddleWidth;
        this.blocksToRemove = blocksToRemove;
        this.paddleSpeed = paddleSpeed;
        this.blockList = blockList;
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.intiallBallVeloc;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        if (this.color == null) {
            return new BackgroundForImage(this.image);
        } else {
            return new BackgroundForImage(this.color);
        }
    }

    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }

    public void setLevelBackground(Image img) {
        this.image = img;
        this.color = null;
    }

    /**
     * Set the GUI background to be a color.
     * @param clr Background color
     */
    public void setLevelBackground(Color clr) {
        this.color = clr;
        this.image = null;
    }
}
