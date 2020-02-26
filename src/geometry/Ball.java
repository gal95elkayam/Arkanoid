package geometry;

import biuoop.DrawSurface;
import collections.CollisionInfo;
import collections.Sprite;
import game.GameLevel;
import game.GameEnvironment;

import java.awt.Color;


/**
 * The type Ball.
 */
public class Ball implements Sprite {

    private int radius;
    private java.awt.Color color;
    private Point center;
    private Velocity velocity;
    private GameEnvironment environment;
    private GameLevel game;

    /**
     * Instantiates a new Ball.
     * constructor
     *
     * @param center      the center
     * @param r           the r
     * @param color       the color
     * @param velocity    the velocity
     * @param environment the environment
     * @param game        the game
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity velocity, GameEnvironment environment,
                GameLevel game) {
        this.radius = r;
        this.color = color;
        this.center = center;
        this.velocity = velocity;
        this.environment = environment;
        this.game = game;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     * draw the ball on the given DrawSurface
     *
     * @param surface the surface
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Move one step.
     * this method moves the ball's center according to it's velocity.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        /*
        compute the ball trajectory (the trajectory is "how the ball will move
        without any obstacles" -- its a line starting at current location, and
        ending where the velocity will take the ball if no collisions will occur).
        */
        Point nextLocation = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(Math.floor(this.center.getX()), Math.floor(this.center.getY()),
                Math.floor(nextLocation.getX()), Math.floor(nextLocation.getY()));

        /*Check if moving on this trajectory will hit anything
          If no, then move the ball to the end of the trajectory.
        */
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info != null) {
            this.setVelocity(this.environment.getClosestCollision(trajectory).
                    collisionObject().hit(this, info.collisionPoint(), this.velocity));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);

    }

}





