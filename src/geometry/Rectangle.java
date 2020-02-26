package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets upper line axes y.
     *
     * @return the upper line axes y
     */
    public Line getUpperLineAxesY() {
        Point start = this.upperLeft;
        Point end = new Point(start.getX() + this.width, start.getY());
        return new Line(start, end);
    }

    /**
     * Gets bottom line axes y.
     *
     * @return the bottom line axes y
     */
    public Line getBottomLineAxesY() {
        Point start = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point end = new Point(start.getX() + this.width, start.getY());
        return new Line(start, end);
    }

    /**
     * Gets left line axes x.
     *
     * @return the left line axes x
     */
    public Line getLeftLineAxesX() {
        Point start = this.upperLeft;
        Point end = new Point(start.getX(), start.getY() + this.height);
        return new Line(start, end);
    }

    /**
     * Gets right line axes x.
     *
     * @return the right line axes x
     */
    public Line getRightLineAxesX() {
        Point start = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point end = new Point(start.getX(), start.getY() + this.height);
        return new Line(start, end);
    }

    /**
     * Intersection points java . util . list.
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line the line
     * @return the java . util . list
     */

    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();

        //check if we have intersection between the line and Bottom Line Axes y
        if (getBottomLineAxesY().isIntersecting(line)) {
            list.add(getBottomLineAxesY().intersectionWith(line));
        }
        //check if we have intersection between the line and Upper Line axes y
        if (getUpperLineAxesY().isIntersecting(line)) {
            list.add(getUpperLineAxesY().intersectionWith(line));
        }
        if (getRightLineAxesX().isIntersecting(line)) {
            list.add(getRightLineAxesX().intersectionWith(line));
        }
        if (getLeftLineAxesX().isIntersecting(line)) {
            list.add((getLeftLineAxesX().intersectionWith(line)));
        }
        return list;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     * Returns the upper-left point of the rectangle
     * @return the upper left
     */

    public Point getUpperLeft() {
        return this.upperLeft;
    }
}


