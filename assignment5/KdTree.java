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
        
        // Horizontal is true we are spliting the X-axis
        //  meaning that the dividing line is parallel to the Y-axis
        public boolean containsPoint(Point2D point,
                                     boolean horizontal) {
            if (p.equals(point)) {
                return true;
            }

            double pointCoord = 0.0;
            double myCoord = 0.0;
            if (horizontal) {
                pointCoord = point.x();
                myCoord = p.x();
            }
            else {
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

        private RectHV lbRect(RectHV rect,
                              boolean horizontal) {
            if (horizontal) {
                // bisect to the left
                double newMaxX = Math.min(p.x(), rect.xmax());
                return new RectHV(rect.xmin(), rect.ymin(), 
                                  newMaxX, rect.ymax());
            }
            else {
                // bisect to the bottom
                double newMaxY = Math.min(p.y(), rect.ymax());
                return new RectHV(rect.xmin(), rect.ymin(),
                                  rect.xmax(), newMaxY);
            }
        }

        private RectHV rtRect(RectHV rect,
                              boolean horizontal) {
            if (horizontal) {
                // bisect to the right
                double newMinX = Math.max(p.x(), rect.xmin());
                return new RectHV(newMinX,     rect.ymin(),
                                  rect.xmax(), rect.ymax());
            }
            else {
                // bisect to the top
                double newMinY = Math.max(p.y(), rect.ymin());
                return new RectHV(rect.xmin(), newMinY,
                                  rect.xmax(), rect.ymax());
            }
        }
        
        // returns -1 for lb, 0 for intersecting and 1 for rt
        private int compareToRect(RectHV rect, boolean horizontal) {
            double dividingCoord;
            double minCoord;
            double maxCoord;
            if (horizontal) {
                dividingCoord = p.x();
                minCoord = rect.xmin();
                maxCoord = rect.xmax();
            }
            else {
                dividingCoord = p.y();
                minCoord = rect.ymin();
                maxCoord = rect.ymax();
            }
            
            if (maxCoord < dividingCoord) {
                return -1;
            }
            else if (minCoord >= dividingCoord) {
                return 1;
            }
            else {
                return 0;
            }
        }
        
        public SET<Point2D> pointsInRange(RectHV rect, 
                                 boolean horizontal) {
            SET<Point2D> result = new SET<Point2D>();
            if (rect.contains(p)) {
                result.add(p);
            }
            
            int comparison = compareToRect(rect, horizontal);
            boolean needToCheckRT = (comparison >= 0);
            boolean needToCheckLB = (comparison <= 0);

            RectHV subRect;
            // Left/Bottom
            if (needToCheckLB && lb != null) {
                subRect = lbRect(rect, horizontal);
                SET<Point2D> lbPoints = lb.pointsInRange(subRect, !horizontal);
                if (lbPoints != null) {
                    result = result.union(lbPoints);
                }
            }

            // Right/Top
            if (needToCheckRT && rt != null) {
                subRect = rtRect(rect, horizontal);
                SET<Point2D> rtPoints = rt.pointsInRange(subRect, !horizontal);
                if (rtPoints != null) {
                    result = result.union(rtPoints);
                }
            }
            return result;
        }
        
        public void draw(RectHV rect, boolean horizontal) {
            if (horizontal) {
                drawHorizontalSplit(rect);
            }
            else {
                drawVerticalSplit(rect);
            }

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            p.draw();
            
            if (lb != null) {
                lb.draw(lbRect(rect, horizontal), !horizontal);
            }
            if (rt != null) {
                rt.draw(rtRect(rect, horizontal), !horizontal);
            }
        }

        private void drawHorizontalSplit(RectHV rect) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(p.x(), rect.ymin(), p.x(), rect.ymax());
        }
        private void drawVerticalSplit(RectHV rect) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(rect.xmin(), p.y(), rect.xmax(), p.y());
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
        if (root == null || !root.containsPoint(p, true)) {
            root = Node.insertPoint(root, p, true);
            size++;
        }
    }
    
    public boolean contains(Point2D p) {
        // does the set contain the point p?
        if (root != null) {
            return root.containsPoint(p, true);
        }
        return false;
    }
    
    public void draw() {
        if (root != null) {
            RectHV rootRect = new RectHV(0.0, 0.0, 1.0, 1.0);
            root.draw(rootRect, true);
        }
    }
     
    public Iterable<Point2D> range(RectHV rect) {
        if (root != null) {
            // this falls over when root is not in rect.
            return root.pointsInRange(rect, true);
        }
        else {
            return new SET<Point2D>();
        }
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to p; null if set is empty
        return null;
    }
}