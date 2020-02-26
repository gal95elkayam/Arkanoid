package geometry;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collections.Collidable;
import collections.Sprite;
import game.GameLevel;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SIDE_FRAME = 25;
    private KeyboardSensor keyboard;
    private Rectangle rec;
    private GUI gui;
    private int paddleSpeed;
    private int paddleWidth;


    /**
     * Instantiates a new Paddle.
     * //
     *
     * @param gui         the gui
     * @param paddleSpeed the paddle speed
     * @param paddleWidth the paddle width
     */
//    public Paddle(GUI gui) {
//        this.keyboard = gui.getKeyboardSensor();
////        rec = new Rectangle(new Point(WIDTH / 2 - SIDE_FRAME - SIDE_FRAME, HEIGHT - 35), 150, 20);
//    }
    public Paddle(GUI gui, int paddleSpeed, int paddleWidth) {
        this.keyboard = gui.getKeyboardSensor();
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        //(WIDTH - SIDE_FRAME - SIDE_FRAME)/2
        //(WIDTH)/2 -(this.paddleWidth/2)
        rec = new Rectangle(new Point((WIDTH) / 2 - (this.paddleWidth / 2), HEIGHT - 35), this.paddleWidth, 20);

    }


    /**
     * Action preformed.
     * create the new rectangle paddle according to the move
     *
     //     */
//    public void actionPreformed() {
//        Point action = new Point(this.rec.getUpperLeft().getX() + this.paddleSpeed,
//                this.rec.getUpperLeft().getY() );
//        this.rec = new Rectangle(action,  this.paddleWidth, 20);
//    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if ((keyboard.isPressed(KeyboardSensor.LEFT_KEY)) && (rec.getUpperLeft().getX() > 29)) {
            Point action = new Point(this.rec.getUpperLeft().getX() - this.paddleSpeed,
                    this.rec.getUpperLeft().getY());
            this.rec = new Rectangle(action, this.paddleWidth, 20);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {

        if ((keyboard.isPressed(KeyboardSensor.RIGHT_KEY))
                && (rec.getUpperLeft().getX() < 800 - 28 - this.paddleWidth)) {
            Point action = new Point(this.rec.getUpperLeft().getX() + this.paddleSpeed,
                    this.rec.getUpperLeft().getY());
            this.rec = new Rectangle(action, this.paddleWidth, 20);

        }
    }

    /**
     * this method notifies the paddle that a time unit has passed.
     */
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }

    /**
     * this method draws the paddle on given DrawSurface.
     *
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                this.paddleWidth,
                (int) this.getCollisionRectangle().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                this.paddleWidth,
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * returns the rectangle that defines the paddle.
     *
     * @return the rectangle that defines the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }


    /**
     * gets collision point and velocity
     * and returns a new velocity according to the hit location on the block.
     *
     * @param hitter          the hitting ball.
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @return the velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // this variable shows in which part paddle will be hit
        double xValOnRect;
        Velocity newVel = currentVelocity;
        // dividing paddle to 5 equal parts.
        int[] paddleParts = new int[5];
        for (int i = 0; i < paddleParts.length; i++) {
//            paddleParts[i] += this.rec.getWidth() / 5 * (i + 1);
            paddleParts[i] += this.paddleWidth / 5 * (i + 1);
        }
        xValOnRect = collisionPoint.getX() - this.rec.getUpperLeft().getX();
        if (Math.abs(collisionPoint.getY() - this.rec.getUpperLeft().getY()) < 0.001) {
            // paddle is hit in the first part
            if (xValOnRect <= paddleParts[0]) {
                newVel = newVel.fromAngleAndSpeed(60, -currentVelocity.getSpeed());
                // paddle is hit in the second part
            } else if ((xValOnRect > paddleParts[0]) && (xValOnRect <= paddleParts[1])) {
                newVel = newVel.fromAngleAndSpeed(30, -currentVelocity.getSpeed());
                // paddle is hit in the third part
            } else if ((xValOnRect > paddleParts[1]) && (xValOnRect <= paddleParts[2])) {
                //newVel = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                newVel = newVel.fromAngleAndSpeed(0, -currentVelocity.getSpeed());
                // paddle is hit in the forth part
            } else if ((xValOnRect > paddleParts[2]) && (xValOnRect <= paddleParts[3])) {
                newVel = newVel.fromAngleAndSpeed(330, -currentVelocity.getSpeed());
                // paddle is hit in the fifth part
            } else if ((xValOnRect > paddleParts[3]) && (xValOnRect <= paddleParts[4])) {
                newVel = newVel.fromAngleAndSpeed(300, -currentVelocity.getSpeed());
            }
        } else {
            newVel = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return newVel;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
