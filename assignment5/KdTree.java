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

        public Node(Point2D point) {
            p = point;
        }

        public static Node insertPoint(Node tree, Point2D point, 
                                       boolean horizontal) {
            if (tree == null) {
                return new Node(point);
            }
            
            double pointCoord = 0.0;
            double myCoord = 0.0;
            if (horizontal) {
                pointCoord = point.x();
                myCoord = tree.p.x();
            }
            else {
                pointCoord = point.y();
                myCoord = tree.p.y();
            }
            
            if (pointCoord < myCoord) {
                tree.lb = Node.insertPoint(tree.lb, point, !horizontal);
                return tree;
            }
            else {
                tree.rt = Node.insertPoint(tree.rt, point, !horizontal);
                return tree;
            }
        }

        public boolean containsPoint(Point2D point,
                                     boolean horizontal) {
            if (p.equals(point)) {
                return true;
            }

            double pointCoord = 0.0;
            double myCoord = 0.0;
            if (horizontal) {
                System.out.println("Searching Horizontal (" 
                                   + point.x() + ", " + point.y() + ")");
                pointCoord = point.x();
                myCoord = p.x();
            }
            else {
                System.out.println("Searching Vertical (" 
                                   + point.x() + ", " + point.y() + ")");
                pointCoord = point.y();
                myCoord = p.y();
            }
            
            if (pointCoord < myCoord) {
                if (lb != null) 
                    return lb.containsPoint(point, !horizontal);
                else
                    return false;
            }
            else {
                if (rt != null)
                    return rt.containsPoint(point, !horizontal);
                else
                    return false;
            }
        }
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
        root = Node.insertPoint(root, p, true);
        size++;
    }
    
    public boolean contains(Point2D p) {
        // does the set contain the point p?
        if (root != null) {
            return root.containsPoint(p, true);
        }
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