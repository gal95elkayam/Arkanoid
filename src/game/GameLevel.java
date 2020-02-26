package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import ballremover.BallRemover;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import blockremover.BlockRemover;
import blockremover.Counter;
import collections.Collidable;
import collections.Sprite;
import collections.SpriteCollection;
import decorator.KeyPressStoppableAnimation;
import geometry.Rectangle;
import geometry.Block;
import geometry.Ball;
import geometry.Paddle;
import geometry.Point;
import levels.LevelInformation;
import score.LivesIndicator;
import score.NameLevelIndicator;
import score.ScoreIndicator;
import score.ScoreTrackingListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    // static variable for maintain the game screen width
    private static final int WITH = 800;
    // static variable for maintain the game screen height
    private static final int HEIGHT = 600;
    // static variable for maintain the width of the screen's borders
    private static final int SIZE = 25;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter numberOfLives;
    private Paddle paddle;
    private List<Ball> ballsList;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation info;
//    private ScoreIndicator scoreIndicator;
//    private LivesIndicator livesIndicator;

    /**
     * Instantiates a new GameLevel.
     *
     * @param keyboard the keyboard
     * @param gui      the gui
     * @param info     the info
     * @param runner   the runner
     * @param score    the score
     * @param lives    the lives
     */
    public GameLevel(KeyboardSensor keyboard, GUI gui, LevelInformation info, AnimationRunner runner, Counter score,
                     Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.score = score;
        this.numberOfLives = lives;
        this.ballsList = new ArrayList<>();
        this.runner = runner;
        this.keyboard = keyboard;
        this.gui = gui;
        this.info = info;
        this.remainingBlocks = new Counter(info.numberOfBlocksToRemove());

    }


    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }

    /**
     * Gets number of lives.
     *
     * @return the number of lives
     */
    public Counter getNumberOfLives() {
        return this.numberOfLives;
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score.getValue();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);

    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * Initialize.
     * Initialize a new game: create the Blocks, Ball and Paddle,
     * and add them to the game.
     */
    public void initialize() {
        addSprite(this.info.getBackground());
        createBorders();
        defineDeathBlock();
        addIndicators();
        BlockRemover removeBlock = new BlockRemover(this, this.remainingBlocks);
//        this.remainingBlocks=removeBlock.getRemainingBlocks();
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (int i = 0; i < info.blocks().size(); i++) {
            info.blocks().get(i).addHitListener(removeBlock);
            info.blocks().get(i).addHitListener(scoreTrackingListener);
            info.blocks().get(i).addToGame(this);
        }
    }


    /**
     * This method define the border that if the ball cross this border
     * the ball remove from the game.
     */
    private void defineDeathBlock() {
        BallRemover removeBall = new BallRemover(this);
        Rectangle death = new Rectangle(new Point(0, HEIGHT), WITH, 10);
        Block deathRegion = new Block(death, Color.black,0);
        deathRegion.addHitListener(removeBall);
        deathRegion.addToGame(this);
    }


    /**
     * Create balls.
     */
    private void createBalls() {
        for (int i = 0; i < info.numberOfBalls(); i++) {
            Point p = new Point(400, 500);
            Ball ball = new Ball(p, 3, Color.WHITE, info.initialBallVelocities().get(i),
                    this.environment, this);
            ball.addToGame(this);
            this.ballsList.add(ball);
        }
        this.remainingBalls = new Counter(this.ballsList.size());
    }

    /**
     * Create paddle.
     */

    private void createPaddle() {
        int paddleSpeed = info.paddleSpeed();
        int paddleWidth = info.paddleWidth();
        this.paddle = new Paddle(this.gui, paddleSpeed, paddleWidth);
        this.paddle.addToGame(this);
    }

    /**
     * Method to determine borders.
     */

    private void createBorders() {
        // Upper border rectangle
        Rectangle topRecFrame = new Rectangle(new Point(0, 0), WITH, SIZE + 20);
        // Left border rectangle
        Rectangle leftRecFrame = new Rectangle(new Point(0, SIZE + 21), SIZE, HEIGHT);
        // Right border rectangle
        Rectangle rightRecFrame = new Rectangle(new Point(WITH - SIZE, SIZE + 21), SIZE, HEIGHT);
        // Rectangle for block on top of upper block for the score
        Rectangle topRecScore = new Rectangle(new Point(0, 0), WITH, 20);
        Block topScore = new Block(topRecScore, Color.LIGHT_GRAY, 0);
        Block topFrame = new Block(topRecFrame, Color.GRAY, 0);
        Block leftFrame = new Block(leftRecFrame, Color.gray, 0);
        Block rightFrame = new Block(rightRecFrame, Color.gray, 0);
        topFrame.addToGame(this);
        leftFrame.addToGame(this);
        rightFrame.addToGame(this);
        topScore.addToGame(this);

    }

    /**
     * This method adds the indicator.
     */

    private void addIndicators() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.numberOfLives);
        livesIndicator.addToGame(this);
        NameLevelIndicator nameLevel = new NameLevelIndicator(info);
        nameLevel.addToGame(this);
    }


    /**
     * This method removes the paddle from the game.
     */
    public void removePaddle() {
        this.removeSprite(this.paddle);
        this.removeCollidable(this.paddle);
    }

    /**
     * This method removes the balls from the game.
     */
    public void removeBalls() {
        for (Ball ball : this.ballsList) {
            ball.removeFromGame(this);
        }
        this.ballsList.clear();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            Animation puseScreen = new PauseScreen(this.keyboard);
            this.runner.run(new KeyPressStoppableAnimation(puseScreen, this.keyboard, KeyboardSensor.SPACE_KEY));
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;

        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * Play one turn.
     */
    public void playOneTurn() {
        createBalls();
        createPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }
}




