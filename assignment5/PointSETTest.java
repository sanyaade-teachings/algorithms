/****                                                                           
 *                                                                              
 * BoardTest.java                                                               
 * a junit test for the Board class                                             
 *                                                                              
 ****/

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PointSETTest {
    private PointSET testSet;

    @Before public void startUp() {
        testSet = new PointSET();
    }

    @Test public void testMakePointSet() {
        assertNotNull(testSet);
        assertTrue(testSet.isEmpty());
        assertEquals(0, testSet.size());
    }

    @Test public void testInsertPoint() {
        Point2D testPoint = new Point2D(0, 0);
        testSet.insert(testPoint);
        assertFalse(testSet.isEmpty());
        assertEquals(1, testSet.size());
    }

    @Test public void testInsertManyPoints() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Point2D testPoint = new Point2D(i, j);
                testSet.insert(testPoint);
            }
        }
        
        assertEquals(100 * 100, testSet.size());
    }

    @Test public void testContainsPoint() {
        Point2D testPoint = new Point2D(0, 0);
        testSet.insert(testPoint);
        assertTrue(testSet.contains(testPoint));
        Point2D fakePoint = new Point2D(1, 0);
        assertFalse(testSet.contains(fakePoint));
    }
    
    @Test public void testContainsManyPoints() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Point2D testPoint = new Point2D(i, j);
                testSet.insert(testPoint);
            }
        }
        
        Point2D testPoint = new Point2D(50, 55);
        assertTrue(testSet.contains(testPoint));
        Point2D fakePoint = new Point2D(-500, 255);
        assertFalse(testSet.contains(fakePoint));
    }

    public void testDraw() {
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.clear();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Point2D testPoint = new Point2D(i, j);
                testSet.insert(testPoint);
            }
        }
        testSet.draw();
        StdDraw.show(3);
    }

    @Test public void testRange() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Point2D testPoint = new Point2D(i, j);
                testSet.insert(testPoint);
            }
        }
        RectHV intersectRect = new RectHV(25, 25, 50, 50);
        int numPoints = 0;

        for(Point2D point : testSet.range(intersectRect)) {
            numPoints++;
        }
        // range is inclusive
        assertEquals(26 * 26, numPoints);
    }

    @Test public void testNearest() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Point2D testPoint = new Point2D(i, j);
                testSet.insert(testPoint);
            }
        }

        Point2D testPoint = new Point2D(-1, -1);

        Point2D nearestPoint = testSet.nearest(testPoint);
        assertEquals(0, nearestPoint.x(), 0.0);
        assertEquals(0, nearestPoint.y(), 0.0);
    }
        

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("PointSETTest");
    }
}