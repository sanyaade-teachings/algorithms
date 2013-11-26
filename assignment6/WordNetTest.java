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

public class WordNetTest {
    static WordNet wordNet;

    @BeforeClass public static void loadWordNet() {
        wordNet = new WordNet("synsets.txt", "hypernyms.txt");
    }
    
    @Test public void testCreateWordNet() {
        assertNotNull(wordNet);
    }

    @Test public void testNouns() {
        assertNotNull(wordNet.nouns());
        int i = 0;
        for(String s : wordNet.nouns()) {
            i++;
        }
        assertEquals(119188, i);
    }

    @Test public void testIsNoun() {
        assertTrue(wordNet.isNoun("love"));
        assertFalse(wordNet.isNoun("foo"));
    }

    @Test public void testCousins() {
        assertEquals(4, wordNet.distance("administrative_district",
                                         "populated_area"));
        assertEquals("region", wordNet.sap("administrative_district",
                                           "populated_area"));
    }

    @Test public void testBlackThings() {
        assertEquals(33, wordNet.distance("Black_Plague", "black_marlin"));
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("WordNetTest");
    }
}
