package geometry;

import biuoop.DrawSurface;
import collections.Collidable;
import collections.HitListener;
import collections.HitNotifier;
import collections.Sprite;
import game.GameLevel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private java.awt.Color color;
    private int numberOfHits;
    private List<HitListener> hitListeners;
    // private Image image;
    private TreeMap<Integer, String> fillsMap;
    private Color stroke;
    private TreeMap<Integer, Image> imageFillMap;
    private TreeMap<Integer, Color> colorsFillMap;

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
//    public Block(Rectangle rectangle, Color color) {
//        this.rectangle = rectangle;
//        this.color = color;
//        this.hitListeners = new ArrayList<HitListener>();
//        this.fillsMap = null;
//        this.colorsFillMap = null;
//        this.imageFillMap = null;
//    }

    /**
     * Instantiates a new Block.
     *
     * @param rectangle    the rectangle
     * @param color        the color
     * @param numberOfHits the number of hits
     */
    public Block(Rectangle rectangle, Color color, int numberOfHits) {
        this.rectangle = rectangle;
        this.color = color;
        this.numberOfHits = numberOfHits;
        this.hitListeners = new ArrayList<>();
        this.fillsMap = null;
        this.colorsFillMap = null;
        this.imageFillMap = null;

    }
    public Block(Rectangle rect, TreeMap<Integer, Image> imageFill, TreeMap<Integer, Color> colorFill,
                 Integer numOfHits, Color stroke) {
        this.rectangle = rect;
        this.imageFillMap = imageFill;
        this.colorsFillMap = colorFill;
        this.numberOfHits = numOfHits;
        this.hitListeners = new ArrayList<>();
        this.stroke = stroke;
    }


    /**
     * this method draws the block on given DrawSurface.
     *
     * @param surface the DrawSurface to draw on.
     */
//    public void drawOn(DrawSurface surface) {
//        surface.setColor(this.color);
//        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
//                (int) this.getCollisionRectangle().getUpperLeft().getY(),
//                (int) this.getCollisionRectangle().getWidth(),
//                (int) this.getCollisionRectangle().getHeight());
//        surface.setColor(Color.BLACK);
//        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
//                (int) this.getCollisionRectangle().getUpperLeft().getY(),
//                (int) this.getCollisionRectangle().getWidth(),
//                (int) this.getCollisionRectangle().getHeight());
//        surface.setColor(Color.WHITE);
////        if (this.numberOfHits == 1 || this.numberOfHits == 2) {
////            surface.drawText(
////                    (int) (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 2),
////                    (int) (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight() / 2),
////                    Integer.toString(this.numberOfHits), 12);
////        }
//
//    }
    public void drawOn(DrawSurface surface) {
        fillForBlock(surface);
        if (this.stroke != null) {
            surface.setColor(this.stroke);
            surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        }
    }

    /**
     * Methdd to draw the inner fill for block - image or color.
     * @param surface surface of GUI
     */
    private void fillForBlock(DrawSurface surface) {
        //no special fill
        if (this.colorsFillMap == null && this.imageFillMap == null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());

            //There is a color fill demand for current block num of hits.
        } else if (this.colorsFillMap != null && this.colorsFillMap.containsKey(this.numberOfHits)) {
            surface.setColor(this.colorsFillMap.get(this.numberOfHits));
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
            //There is an image fill demand for current block num of hits.
        } else if (this.imageFillMap.containsKey(this.numberOfHits)) {
            surface.drawImage((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    this.imageFillMap.get(this.numberOfHits));
        } else {
            //default num of hits fill request - at position '0'.
            if (this.colorsFillMap != null && this.colorsFillMap.containsKey(0)) {
                surface.setColor(this.colorsFillMap.get(0));
                surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                        (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
            } else {
                surface.drawImage((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                        this.imageFillMap.get(0));
            }
        }
    }

    @Override
    public void timePassed() {

    }

    // Return the "collision shape" of the object.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This method notify when the ball hit a block.
     *
     * @param hitter the ball that hit the points
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Method to change velocity when ball hits the block.
     *
     * @param hitter          the hitting ball.
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @return a new velocity based on the hit's properties.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity afterHit = null;
        boolean flipX = false;
        boolean flipY = false;

        //hit in axes y
        if (this.rectangle.getBottomLineAxesY().isPointSectionInRange(collisionPoint)) {
            afterHit = new Velocity(currentVelocity.getDx(), (currentVelocity.getDy()) * -1);
            flipY = true;
        }
        //hit in axes y
        if (this.rectangle.getUpperLineAxesY().isPointSectionInRange(collisionPoint)) {
            afterHit = new Velocity(currentVelocity.getDx(), (currentVelocity.getDy()) * -1);
            flipY = true;
        }
        //hit in axes x
        if (this.rectangle.getLeftLineAxesX().isPointSectionInRange(collisionPoint)) {
            afterHit = new Velocity((currentVelocity.getDx() * -1), currentVelocity.getDy());
            flipX = true;
        }
        //hit in axes x
        if (this.rectangle.getRightLineAxesX().isPointSectionInRange(collisionPoint)) {
            afterHit = new Velocity((currentVelocity.getDx() * -1), currentVelocity.getDy());
            flipX = true;
        }
        this.notifyHit(hitter);
        if (flipX && flipY) {
            return new Velocity((currentVelocity.getDx() * -1), (currentVelocity.getDy() * -1));
        }

        if ((flipX || flipY) && this.numberOfHits > 0) {
            this.numberOfHits--;
        }
        if (flipX || flipY) {
            return afterHit;
        }

        return currentVelocity;
    }

    /**
     * Remove from game.
     * remove the block from collidable and sprite list
     *
     * @param game the game
     */

    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);


    }

    /**
     * Number of hit in block int.
     *
     * @return the int
     */
    public int numberOfHitInBlock() {
        return this.numberOfHits;
    }


}
