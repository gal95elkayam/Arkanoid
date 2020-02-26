package collections;

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     * Return the "collision shape" of the object.
     * @return the collision rectangle
     */

    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter the hitter ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
