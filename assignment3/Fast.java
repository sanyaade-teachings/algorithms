/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac Fast.java
 * Execution:
 * Dependencies: Point.java
 *
 * Description: A fast version of the colinear point finder
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Fast {
    private static class Line {
        private Point[] points;
        private double slope;
        
        Line(Point A, Point B, Point C, Point D) {
            points = new Point[4];
            points[0] = A;
            points[1] = B;
            points[2] = C;
            points[3] = D;
            slope = A.slopeTo(D);
            Arrays.sort(points);
        }

        Line(Point[] points) {
            this.points = new Point[points.length];
            for (int i = 0; i < points.length; i++) {
                this.points[i] = points[i];
            }
            Arrays.sort(points);
            slope = points[0].slopeTo(points[points.length - 1]);
        }

        public void draw() {
            Point a = points[0];
            Point b = points[points.length -1];
            a.drawTo(b);
            StdDraw.show(0);
        }

        public String toString() {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < points.length - 1; i++) {
                result.append(points[i].toString());
                result.append(" -> ");
            }
            result.append(points[points.length - 1].toString());
            return result.toString();
        }
    }

    private static Point[] readPoints(In inputStream) {
        int numPoints = inputStream.readInt();
        Point[] result = new Point[numPoints];
        
        for (int i = 0; i < numPoints; i++) {
            int x = inputStream.readInt();
            int y = inputStream.readInt();
            result[i] = new Point(x, y);
        }
        return result;
    }


    private static boolean isEqual(double a, double b) {
        if (a == Double.POSITIVE_INFINITY 
            && b == Double.POSITIVE_INFINITY) {
            return true;
        }
        
        double epsilon = 1E-5;
        return ((a - b) < epsilon) && ((b - a) < epsilon);
    }

    private static void fastLines(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            Point curPoint = points[i];
            Comparator<Point> slopeTest = curPoint.SLOPE_ORDER;
            Point[] otherPoints = Arrays.copyOf(points, points.length);
                                                    
            Arrays.sort(otherPoints, slopeTest);
            int j = 0;
            while (j < otherPoints.length) {
                // gobble up all of the collinear points.
                // they will be in a row, since we're sorted by slope.
                int k = 1;
                boolean alreadyFoundLine = false;
                if (0 < curPoint.compareTo(otherPoints[j])) {
                    alreadyFoundLine = true;
                }
                while ((j + k < otherPoints.length)
                       && (0 == (slopeTest.compare(otherPoints[j],
                                                   otherPoints[j + k])))) {
                    if (0 < curPoint.compareTo(otherPoints[j + k])) {
                        alreadyFoundLine = true;
                    }
                    k++;
                }
                if (!alreadyFoundLine && k >= 3) {
                    Point[] foundPoints = new Point[k + 1];
                    foundPoints[0] = curPoint;
                    for (int l = 0; l < k; l++) {
                        foundPoints[l + 1] = otherPoints[j + l];
                    }
                    Line foundLine = new Line(foundPoints);
                    System.out.println(foundLine.toString());
                    foundLine.draw();
                }
                j += k;
            }
        }
    }
    
    // Arg 0 is assumed to be the name of the file
    public static void main(String[] args) {
        In fileInputStream = new In(args[0]);
        Point[] points = readPoints(fileInputStream);
        Arrays.sort(points);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        fastLines(points);
        StdDraw.show(0);
    }
}