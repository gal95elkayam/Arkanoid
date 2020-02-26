package geometry;
/**
 * The type Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     * distance - return the distance of this point to the other point
     *
     * @param other the other
     * @return the double
     */
    public double distance(Point other) {
        //((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))
        double distance = ((this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
        return Math.sqrt(distance);
    }

    /**
     * Equals boolean.
     * equals --return true is the points are equal, false otherwise
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Point other) {
        if (this == null || other == null) {
            return false;
        }
        return (this.x == other.getX()) && (this.y == other.getY());
    }

    /**
     * Gets x.
     * return the x value of this point
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     * return the y value of this point
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}
