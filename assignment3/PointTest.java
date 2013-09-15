/****
 *
 * PointTest.java
 * a junit test for the Point class
 *
 ****/

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PointTest {
    
    @Test
    public void testCreateOrigin() {
        Point origin = new Point(0, 0);
        assertNotNull(origin);
    }
    
    @Test
    public void testCompareEqual() {
        Point a = new Point(1, 1);
        Point b = new Point(1, 1);
        assertTrue(a.compareTo(b) == 0);
    }

    @Test
    public void testCompareGreater() {
        Point a = new Point(1, 1);
        Point b = new Point(0, 0);
        assertTrue(a.compareTo(b) > 0);
    }

    @Test
    public void testCompareGreaterX() {
        Point a = new Point(0, 1);
        Point b = new Point(0, 0);
        assertTrue(a.compareTo(b) > 0);
    }

    @Test
    public void testCompareGreaterY() {
        Point a = new Point(1, 0);
        Point b = new Point(0, 0);
        assertTrue(a.compareTo(b) > 0);
    }
        
    @Test
    public void testCompareLess() {
        Point a = new Point(0, 0);
        Point b = new Point(1, 1);
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    public void testCompareLessX() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 1);
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    public void testCompareLessY() {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        assertTrue(a.compareTo(b) < 0);
    }
    
    @Test
    public void testSlopeToPositive() {
        Point a = new Point(0, 0);
        Point b = new Point(1, 1);
        assertEquals(1.0, a.slopeTo(b), 0.00001);
        assertEquals(1.0, b.slopeTo(a), 0.00001);
    }

    @Test
    public void testSlopeToNegative() {
        Point a = new Point(1, 0);
        Point b = new Point(0, 1);
        assertEquals(-1.0, a.slopeTo(b), 0.00001);
        assertEquals(-1.0, b.slopeTo(a), 0.00001);
    }

    @Test
    public void testSlopeHorizontal() {
        Point a = new Point(0, 1);
        Point b = new Point(3, 1);
        assertEquals(0.0, a.slopeTo(b), 0.0);
        assertEquals(0.0, b.slopeTo(a), 0.0);
    }

    @Test
    public void testSlopeVertical() {
        Point a = new Point(0, 1);
        Point b = new Point(0, 0);
        assertEquals(Double.POSITIVE_INFINITY, a.slopeTo(b), 0.0);
        assertEquals(Double.POSITIVE_INFINITY, b.slopeTo(a), 0.0);
    }

    @Test
    public void testSlopeDegenerate() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        assertEquals(Double.NEGATIVE_INFINITY, a.slopeTo(b), 0.0);
        assertEquals(Double.NEGATIVE_INFINITY, b.slopeTo(a), 0.0);
    }
    
    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("PointTest");
    }
}