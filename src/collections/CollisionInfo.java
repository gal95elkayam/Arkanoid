package collections;

import geometry.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point.
     * the point at which the collision occurs.
     * @return the point
     */

     public Point collisionPoint() {
        return this.collisionPoint;
     }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
     public Collidable collisionObject() {
        return this.collisionObject;
     }
 }

