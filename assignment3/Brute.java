/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac Brue.java
 * Execution:
 * Dependencies: Point.java
 *
 * Description: A Brute force version of the colinear point finder
 *
 *************************************************************************/

import java.util.Arrays;

public class Brute {
    private static class Line {
        private Point[] points;

        Line(Point A, Point B, Point C, Point D) {
            points = new Point[4];
            points[0] = A;
            points[1] = B;
            points[2] = C;
            points[3] = D;
            Arrays.sort(points);
        }

        Line(Point[] points) {
            this.points = new Point[points.length];
            for (int i = 0; i < points.length; i++) {
                this.points[i] = points[i];
            }
            Arrays.sort(points);
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
        System.out.println("Read " + numPoints + " points");
        return result;
    }


    private static boolean isEqual(double a, double b) {
        double epsilon = 1E-5;
        return ((a - b) < epsilon) && ((b - a) < epsilon);
    }

    private static void bruteLines(Point[] points) {
        for (int a = 0; a < points.length; a++) {
            Point A = points[a];
            for (int b = a + 1; b < points.length; b++) {
                Point B = points[b];
                double slope = A.slopeTo(B);
                if (Double.NEGATIVE_INFINITY == slope) 
                    continue;
                for (int c = b + 1; c < points.length; c++) {
                    Point C = points[c];
                    // reject points on on the line
                    if (!isEqual(slope, B.slopeTo(C))) 
                        continue;
                    for (int d = c + 1; d < points.length; d++) {
                        Point D = points[d];
                        if (isEqual(slope, C.slopeTo(D))) {
                            Line line = new Line(A, B, C, D);
                            System.out.println(line.toString());
                        }
                    }
                }
            }
        }
    }
    
    // Arg 0 is assumed to be the name of the file
    public static void main(String[] args) {
        In fileInputStream = new In(args[0]);
        Point[] points = readPoints(fileInputStream);
        Arrays.sort(points);
        bruteLines(points);
    }
}