package geometry;
/**
 * The type Velocity.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Apply to point point.
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        Point changePosition = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return changePosition;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}
