/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac KdTree.java
 * Execution:
 * Dependencies: 
 *
 * Description: A sorted set of points in 2d space (KdTree algorithm)
 *
 *************************************************************************/


public class KdTree {


    private static class Node {
        private Point2D p; // the point
        private RectHV rect; // the axis-aligned rectangle for this node
        private Node lb; // the left/bottom subtree
        private Node rt; // the right/top subtree
    }

    private int size;
    private Node root;

    public KdTree() {
        // construct an empty tree
        size = 0;
        root = null;
    }
    
    public boolean isEmpty() {
        // is the set empty?
        return (size == 0);
    }

    public int size() {
        // number of points in the set
        return size;
    }
    
    public void insert(Point2D p) {
        // add the point p to the set (if it is not already in the set)
    }
    
    public boolean contains(Point2D p) {
        // does the set contain the point p?
        return false;
    }
    
    public void draw() {
        // draw all of the points to standard draw
    }
     
    public Iterable<Point2D> range(RectHV rect) {
        // all points in the set that are inside the rectangle
        return null;
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to p; null if set is empty
        return null;
    }
}