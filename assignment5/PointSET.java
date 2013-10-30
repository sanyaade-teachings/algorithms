/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac PointSET.java
 * Execution:
 * Dependencies: 
 *
 * Description: A set of points in 2d space (brute force)
 *
 *************************************************************************/

import java.util.SortedSet;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> set;

    public PointSET() {
        // construct an empty set of points
        set = new TreeSet<Point2D>();
    }
     
    public boolean isEmpty() {
        // is the set empty?
        return set.isEmpty();
    }
     
    public int size() {
        // number of points in the set
        return set.size();
    }
     
    public void insert(Point2D p) {
        if (p == null) 
            throw new NullPointerException("called insert() with a null point");
        // add the point p to the set (if it is not already in the set)
        set.add(p);
    }
    
    public boolean contains(Point2D p) {
        // does the set contain the point p?
        return set.contains(p);
    }
     
    public void draw() {
        // draw all of the points to standard draw
        for (Point2D point : set) {
            point.draw();
        }
    }
     
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) 
            throw new NullPointerException("called range() with null rect");
        // all points in the set that are inside the rectangle
        Point2D beginPoint = new Point2D(rect.xmin(), rect.ymin());
        Point2D endPoint = new Point2D(rect.xmax(), rect.ymax());

        SortedSet<Point2D> tail = set.tailSet(beginPoint);
        SortedSet<Point2D> range = tail.headSet(endPoint);
        TreeSet<Point2D> result = new TreeSet<Point2D>();
        for (Point2D point : range) {
            if (point.x() >= rect.xmin() && point.x() <= rect.xmax() 
                && point.y() >= rect.ymin() && point.y() <= rect.ymax()) {
                result.add(point);
            }
        }

        if (set.contains(endPoint)) result.add(endPoint);
        
        return result;
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to p; null if set is empty
        Point2D nearest = set.first();
        double nearestDistanceSquared = p.distanceTo(nearest);
        for (Point2D q : set) {
            if (p.distanceSquaredTo(q) < nearestDistanceSquared) {
                nearest = q;
                nearestDistanceSquared = p.distanceSquaredTo(q);
            }
        }

        return nearest;
    }
}