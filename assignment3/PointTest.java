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
    
    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("PointTest");
    }
}