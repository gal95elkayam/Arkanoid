package geometry;
import java.util.List;

/**
 * The type Line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Middle point.
     * returns the middle point of the line
     *
     * @return the point
     */
    public Point middle() {
        double middleX = (this.end.getX() + this.start.getX()) / 2;
        double middleY = (this.end.getY() + this.start.getY()) / 2;
        Point middle = new Point(middleX, middleY);
        return middle;
    }

    /**
     * Start point.
     * returns the start point of the line
     *
     * @return the point
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     * returns the end point of the line
     *
     * @return the point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean.
     * returns true if the lines intersect, false otherwise
     * our lines will be in the form Ax+By=C.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {

        //calculate this point A = y2-y1
        double a1 = this.end.getY() - this.start.getY();
        //calculate this B = x1-x2
        double b1 = this.start.getX() - this.end.getX();
        //calculate this C = Ax1+By1
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();
        //calculate other point A = y2-y1
        double a2 = other.end().getY() - other.start().getY();
        //calculate other B = x1-x2
        double b2 = other.start().getX() - other.end().getX();
        //calculate other C = Ax1+By1
        double c2 = a2 * other.start().getX() + b2 * other.start().getY();
        // calculate the determinate of the lines.
        double det = a1 * b2 - a2 * b1;
        if (det == 0) {
            // lines are parallel
            return false;
        } else {
            Point checkRange = new Point((b2 * c1 - b1 * c2) / det, (a1 * c2 - a2 * c1) / det);
            //check if the point is in the range of two the segment line  and return the answer appropriate
            return (this.isPointSectionInRange(checkRange) && other.isPointSectionInRange((checkRange)));
        }
    }

    /**
     * Intersection with point.
     * Returns the intersection point if the lines intersect,and null otherwise.
     * our lines will be in the form Ax+By=C
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        //calculate this point A = y2-y1
        double a1 = this.end.getY() - this.start.getY();
        //calculate this B = x1-x2
        double b1 = this.start.getX() - this.end.getX();
        //calculate this C = Ax1+By1
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();
        //calculate other point A = y2-y1
        double a2 = other.end().getY() - other.start().getY();
        //calculate other B = x1-x2
        double b2 = other.start().getX() - other.end().getX();
        //calculate other C = Ax1+By1
        double c2 = a2 * other.start().getX() + b2 * other.start().getY();
        // calculate the determinate of the lines.
        double det = a1 * b2 - a2 * b1;
        if (det == 0) {
            // lines are parallel
            return null;
        } else {
            Point checkRange = new Point((b2 * c1 - b1 * c2) / det, (a1 * c2 - a2 * c1) / det);
            //if the point is in the range of two the segment line
            if (this.isPointSectionInRange(checkRange) && other.isPointSectionInRange((checkRange))) {
                return checkRange;
            } else {
                return null;
            }
        }
    }

    /**
     * Is point section in range boolean.
     *
     * @param checkRange the check range
     * @return the boolean
     */
    public boolean isPointSectionInRange(Point checkRange) {
        //check if the point in the rage in axis x- exist between the min and max of the two line segments
        if ((checkRange.getX() <= Math.max(this.start.getX(), this.end.getX()))
                && (checkRange.getX() >= Math.min(this.start.getX(), this.end.getX()))) {
            //check if the point in the rage in axis y-exist between the min and max of the two line segments
            if (checkRange.getY() <= Math.max(this.start.getY(), this.end.getY())
                    && checkRange.getY() >= Math.min(this.start.getY(), this.end.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Equals boolean.
     * equals - return true is the lines are equal, false otherwise
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start)));
    }

    /**
     * Closest intersection to start of line point.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        //Line l = new Line(this.start, this.end);
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        List<Point> list;
        list = rect.intersectionPoints(this);
        //one intersection
        if (list.size() == 1) {
            return list.get(0);

            //two point intersection
        } else {
            //check the closest intersection point by find the min distance between this point
            // and the two intersection point
            if (list.get(0).distance(this.start) < list.get(1).distance(this.start)) {
                return list.get(0);
            } else {
                return list.get(1);
            }
        }
    }
}
