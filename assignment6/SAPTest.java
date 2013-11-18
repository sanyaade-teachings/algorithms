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

public class SAPTest {
    Digraph testG;
    SAP testSAP;

    @Before public void createGraph() {
        Digraph testG = new Digraph(20);
        testG.addEdge(11, 10);
        testG.addEdge(12, 10);
        testG.addEdge(10, 5);
        testG.addEdge(9, 5);
        testG.addEdge(7, 3);
        testG.addEdge(8, 3);
        testG.addEdge(3, 1);
        testG.addEdge(4, 1);
        testG.addEdge(5, 1);
        testG.addEdge(1, 0);
        testG.addEdge(2, 0);
        
        // make a cycle (for testing)
        testG.addEdge(18, 19);
        testG.addEdge(19, 16);
        testG.addEdge(16, 15);
        testG.addEdge(15, 19);
        testG.addEdge(15, 14);
        testSAP = new SAP(testG);
    }

    @Test public void testCreateSAP() {
        assertNotNull(testSAP);
    }

    @Test public void testParent() {
        assertEquals(1, testSAP.length(0, 2));
    }

    @Test public void testSameParent() {
        assertEquals(2, testSAP.length(1, 2));
    }

    @Test public void testUncle() {
        assertEquals(3, testSAP.length(9, 12));
    }

    @Test public void testDistantRelation() {
        assertEquals(5, testSAP.length(7, 11));
    }

    @Test public void testReverseDistantRelation() {
        assertEquals(5, testSAP.length(11, 7));
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("SAPTest");
    }
}