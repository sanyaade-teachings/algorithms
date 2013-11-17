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

    @Test public void testInsertSeveralPoints() {
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
    
    public void testRangeXIncreasing() {
        SET<Point2D> expectedPoints = new SET<Point2D>();
        for(double xCoord = 0.75; xCoord < 1.0; xCoord += 0.01) {
            Point2D testPoint = new Point2D(xCoord, 0.5);
            expectedPoints.add(testPoint);
            testTree.insert(testPoint);
        }
        RectHV testRect = new RectHV(0.75, 0.0, 1.0, 1.0);
        SET<Point2D> actualPoints = new SET<Point2D>();
        for(Point2D point : testTree.range(testRect)) {
            actualPoints.add(point);
        }
        assertTrue(expectedPoints.equals(actualPoints));
    }

    public void testRangeXDecreasing() {
        SET<Point2D> expectedPoints = new SET<Point2D>();
        for(double xCoord = 1.0; xCoord >= 0.75; xCoord -= 0.01) {
            Point2D testPoint = new Point2D(xCoord, 0.5);
            expectedPoints.add(testPoint);
            testTree.insert(testPoint);
        }
        RectHV testRect = new RectHV(0.75, 0.0, 1.0, 1.0);
        SET<Point2D> actualPoints = new SET<Point2D>();
        for(Point2D point : testTree.range(testRect)) {
            actualPoints.add(point);
        }
        assertTrue(expectedPoints.equals(actualPoints));
    }


    @Test public void testRangeXYGridIncreasing() {
        SET<Point2D> expectedPoints = new SET<Point2D>();
        RectHV testRect = new RectHV(0.45, 0.25, 0.60, 0.75);
        for(double xCoord = 0.0; xCoord < 1.0; xCoord += 0.01) {
            for(double yCoord = 0.0; yCoord < 1.0; yCoord += 0.01) {
                Point2D testPoint = new Point2D(xCoord, yCoord);
                if (testRect.contains(testPoint)) 
                    expectedPoints.add(testPoint);
                testTree.insert(testPoint);
            }
        }
        SET<Point2D> actualPoints = new SET<Point2D>();
        for(Point2D point : testTree.range(testRect)) {
            actualPoints.add(point);
        }
        assertTrue(expectedPoints.equals(actualPoints));
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("KdTreeTest");
    }
}