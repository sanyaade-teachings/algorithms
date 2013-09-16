/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER;

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private class SlopeComparator implements Comparator<Point> {
        private Point origin;
        SlopeComparator(Point origin) {
            this.origin = origin;
        }

        private boolean isEqualSlope(double a, double b) {
            if (a == Double.POSITIVE_INFINITY
                && b == Double.POSITIVE_INFINITY) {
                return true;
            }
            double epsilon = 1E-5;
            return ((a - b) < epsilon) && ((b - a) < epsilon);
        }

        public int compare(Point a, Point b) {
            double slopeA = origin.slopeTo(a);
            double slopeB = origin.slopeTo(b);

            if (isEqualSlope(slopeA, slopeB)) return 0;
            if (slopeA > slopeB) return 1;
            return -1;
        }
    }

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        this.SLOPE_ORDER = new SlopeComparator(this);
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        
        if (this.x == that.x) {
            if (this.y == that.y) return Double.NEGATIVE_INFINITY;
            else return Double.POSITIVE_INFINITY;
        }
        if (this.y == that.y) return 0.0;

        return (that.y - this.y) / ((double) (that.x - this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        // Y-major comparison
        if (this.y < that.y) {
            return -1;
        }
        else if (this.y > that.y) {
            return 1;
        }
        else { // Y is equal, break the tie with X.
            if (this.x < that.x) {
                return -1;
            }
            else if (this.x > that.x) {
                return 1;
            }
        }
        // they are equal
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        // unit tests live in PointTest.java (which uses junit)
    }
}
