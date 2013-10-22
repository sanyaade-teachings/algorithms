/****                                                                         
 *                                                                            
 * KdTreeTest.java
 * a junit test for the KdTree class
 *                                                                            
****/

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class KdTreeTest {

    private KdTree testTree;

    @Before public void startUp() {
        testTree = new KdTree();
    }

    @Test public void testMakeKdTree() {
        Point2D testPoint = new Point2D(0, 0);
        assertNotNull(testTree);
        assertTrue(testTree.isEmpty());
        assertEquals(0, testTree.size());
        assertFalse(testTree.contains(testPoint));
    }

    @Test public void testInsertOnePoint() {
        Point2D testPoint = new Point2D(0, 0);
        testTree.insert(testPoint);
        assertFalse(testTree.isEmpty());
        assertEquals(1, testTree.size());
        assertTrue(testTree.contains(testPoint));
    }

    @Test public void testInsertManyPoints() {
        Point2D testPoint = new Point2D(0.5, 0.5);
        testTree.insert(testPoint);
        testPoint = new Point2D(0.25, 0.5);
        testTree.insert(testPoint);
        assertEquals(2, testTree.size());
        assertTrue(testTree.contains(testPoint));

        testPoint = new Point2D(0.25, 0.25);
        testTree.insert(testPoint);
        testPoint = new Point2D(0.75, 0.25);
        testTree.insert(testPoint);
        assertFalse(testTree.contains(new Point2D(0.75, 0.5)));
        assertTrue(testTree.contains(new Point2D(0.25, 0.25)));
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("PointSETTest");
    }
}