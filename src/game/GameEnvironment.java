package game;

import collections.Collidable;
import collections.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    // add the given collidable to the environment.

    /**
     * Instantiates a new GameLevel environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets closest collision.
     * object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidables.isEmpty()) {
            return null;
        }
        int i = 0;
        while (i < this.collidables.size() && trajectory.closestIntersectionToStartOfLine(
                this.collidables.get(i).getCollisionRectangle()) == null) {
            i++;
        }
        if (i >= this.collidables.size()) {
            return null;
        }

        Point closest = trajectory.closestIntersectionToStartOfLine(
                this.collidables.get(i).getCollisionRectangle());
        int collision = i;
        for (int j = i; j < this.collidables.size(); ++j) {
            if (trajectory.closestIntersectionToStartOfLine(
                    this.collidables.get(j).getCollisionRectangle()) != null) {
                if (closest.distance(trajectory.start())
                        > trajectory.closestIntersectionToStartOfLine(
                        this.collidables.get(j).getCollisionRectangle()).
                        distance(trajectory.start())) {
                    closest = trajectory.closestIntersectionToStartOfLine(
                            this.collidables.get(j).getCollisionRectangle());
                    collision = j;
                }
            }
        }
        return new CollisionInfo(closest,
                this.collidables.get(collision));
    }
}
