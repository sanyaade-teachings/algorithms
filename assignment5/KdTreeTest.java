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
        assertNotNull(testTree);
        assertTrue(testTree.isEmpty());
        assertEquals(0, testTree.size());
    }

    @Test public void testInsertOnePoint() {
        Point2D testPoint = new Point2D(0, 0);
        testTree.insert(testPoint);
        assertFalse(testTree.isEmpty());
        assertEquals(1, testTree.size());
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("PointSETTest");
    }
}