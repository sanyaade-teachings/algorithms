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
    Bag<Integer> bagA;
    Bag<Integer> bagB;

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

        bagA = new Bag<Integer>();
        bagB = new Bag<Integer>();
    }

    @Test public void testCreateSAP() {
        assertNotNull(testSAP);
    }

    @Test public void testParent() {
        assertEquals(1, testSAP.length(0, 2));
        assertEquals(0, testSAP.ancestor(0, 2));
    }

    @Test public void testSameParent() {
        assertEquals(2, testSAP.length(1, 2));
        assertEquals(0, testSAP.ancestor(1, 2));
    }

    @Test public void testUncle() {
        assertEquals(3, testSAP.length(9, 12));
        assertEquals(5, testSAP.ancestor(9, 12));
    }

    @Test public void testDistantRelation() {
        assertEquals(5, testSAP.length(7, 11));
        assertEquals(1, testSAP.ancestor(7, 11));
    }

    @Test public void testReverseDistantRelation() {
        assertEquals(5, testSAP.length(11, 7));
        assertEquals(1, testSAP.ancestor(11, 7));
    }

    /* Multi-source / Sink tests */
    @Test public void testMultiParent() {
        bagA.add(7);
        bagA.add(4);
        bagB.add(11);
        bagB.add(2);
        bagB.add(3);
        bagB.add(8);
        assertEquals(1, testSAP.length(bagA, bagB));
        assertEquals(3, testSAP.ancestor(bagA, bagB));
    }

    @Test public void testMultiUncle() {
        bagA.add(7);
        bagA.add(8);
        bagB.add(5);
        bagB.add(4);
        assertEquals(3, testSAP.length(bagA, bagB));
        assertEquals(1, testSAP.ancestor(bagA, bagB));
    }
    
    @Test public void testNotRelated() {
        bagA.add(15);
        bagA.add(16);
        bagA.add(19);
        bagA.add(18);
        bagB.add(7);
        bagB.add(8);
        bagB.add(11);
        assertEquals(-1, testSAP.length(bagA, bagB));
        assertEquals(-1, testSAP.ancestor(bagA, bagB));
    }
    
    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("SAPTest");
    }
}