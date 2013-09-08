import org.junit.*;
import org.junit.Test;;
import static org.junit.Assert.*;
import java.util.*;

public class DequeTest {

    @Test
    public void testEmptyDeque() {
        Deque<Object> empty = new Deque<Object>();
        assertTrue(empty.size() == 0);
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testAddRemoveFrontSingle() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addFirst(i);
            int j = intDeque.removeFirst();
            assertTrue(i == j);
        }
        assertTrue(intDeque.isEmpty());
    }

    @Test
    public void testAddRemoveFrontMany() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addFirst(i);
        }
        assertTrue(intDeque.size() == 100);
        for(int i = 99; i >= 0; i--) {
            int j = intDeque.removeFirst();
            assertEquals(i, j);
        }
        assertEquals(0, intDeque.size());
        assertTrue(intDeque.isEmpty());
    }
    
    @Test(expected=NullPointerException.class)
    public void testAddFirstNull() {
        Deque<String> strDeque = new Deque<String>();
        strDeque.addFirst(null);
    }

    @Test(expected=NullPointerException.class)
    public void testAddLastNull() {
        Deque<String> strDeque = new Deque<String>();
        strDeque.addLast(null);
    }

    @Test
    public void testAddRemoveLastSingle() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addLast(i);
            int j = intDeque.removeLast();
            assertEquals(i, j);
        }
        assertTrue(intDeque.isEmpty());
        assertEquals(intDeque.size(), 0);
    }

    @Test
    public void testAddRemoveLastMany() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addLast(i);
        }
        assertEquals(intDeque.size(), 100);
        for(int i = 99; i >= 0; i--) {
            int j = intDeque.removeLast();
            assertEquals(i, j);
        }
        assertTrue(intDeque.isEmpty());
        assertEquals(intDeque.size(), 0);
    }

    @Test
    public void testAddFirstRemoveLastSingle() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addFirst(i);
            int j = intDeque.removeLast();
            assertEquals(i, j);
        }
        assertTrue(intDeque.isEmpty());
        assertEquals(intDeque.size(), 0);
    }

    @Test
    public void testAddFirstRemoveLastMany() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addFirst(i);
        }
        assertEquals(intDeque.size(), 100);
        for(int i = 0; i < 100; i++) {
            int j = intDeque.removeLast();
            assertEquals(i, j);
        }
        assertTrue(intDeque.isEmpty());
        assertEquals(intDeque.size(), 0);
    }

    @Test
    public void testAddLastRemoveFirstSingle() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addLast(i);
            int j = intDeque.removeFirst();
            assertEquals(i, j);
        }
        assertTrue(intDeque.isEmpty());
        assertEquals(intDeque.size(), 0);
    }

    @Test
    public void testAddLastRemoveFirstMany() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addLast(i);
        }
        assertEquals(intDeque.size(), 100);
        for(int i = 0; i < 100; i++) {
            int j = intDeque.removeFirst();
            assertEquals(i, j);
        }
        assertTrue(intDeque.isEmpty());
        assertEquals(intDeque.size(), 0);
    }
    

    @Test(expected=NoSuchElementException.class)
    public void testRemoveFirstEmpty() {
        Deque<String> strDeque = new Deque<String>();
        String str = strDeque.removeFirst();
    }

    @Test(expected=NoSuchElementException.class)
    public void testRemoveFirstEmptyAddFirst() {
        Deque<String> strDeque = null;
        try {
            strDeque = new Deque<String>();
            strDeque.addFirst("Test");
            strDeque.removeFirst();
        } 
        catch (Exception e) {
            assertTrue(false);
        }

        strDeque.removeFirst();
    }

    @Test(expected=NoSuchElementException.class)
    public void testRemoveFirstEmptyAddLast() {
        Deque<String> strDeque = null;
        try {
            strDeque = new Deque<String>();
            strDeque.addLast("Test");
            strDeque.removeLast();
        } 
        catch (Exception e) {
            assertTrue(false);
        }
        strDeque.removeFirst();
    }

    @Test(expected=NoSuchElementException.class)
    public void testRemoveLastEmpty() {
        Deque<String> strDeque = new Deque<String>();
        strDeque.removeLast();
    }

    @Test(expected=NoSuchElementException.class)
    public void testRemoveLastEmptyAddFirst() {
        Deque<String> strDeque = null;
        try {
            strDeque = new Deque<String>();
            strDeque.addFirst("Test");
            strDeque.removeFirst();
        }
        catch (Exception e) {
            assertTrue(false);
        }
        strDeque.removeLast();
    }

    @Test(expected=NoSuchElementException.class)
    public void testRemoveLastEmptyAddLast() {
        Deque<String> strDeque = null;
        try {
            strDeque = new Deque<String>();
            strDeque.addLast("Test");
            strDeque.removeLast();
        }
        catch (Exception e) {
            assertTrue(false);
        }
        strDeque.removeLast();
    }
    
    @Test(expected=NoSuchElementException.class)
    public void testCreateIteratorEmpty() {
        Deque<Integer> intDeque = null;
        Iterator<Integer> intIter = null;
        try {
            intDeque = new Deque<Integer>();
            intIter = intDeque.iterator();
            assertNotNull(intIter);
            assertFalse(intIter.hasNext());
        }
        catch (Exception e) {
            assertTrue(false);
        }
        intIter.next();
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testIteratorRemoveUnsupported() {
        Deque<Integer> intDeque = new Deque<Integer>();
        Iterator<Integer> intIter = intDeque.iterator();
        intIter.remove();
    }

    @Test
    public void testIteratorNext() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addLast(i);
        }
        Iterator<Integer> intIter = intDeque.iterator();
        int i = 0;
        while (intIter.hasNext()) {
            int value = intIter.next();
            assertEquals(i, value);
            i++;
        }
        assertEquals(i, 100);
    }

    @Test
    public void testMultiIterator() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for (int i = 0; i < 100; i++) {
            intDeque.addLast(i);
        }
        Iterator<Integer> intIters[] = new Iterator[10];
        for (int i = 0; i < intIters.length; i++) {
            intIters[i] = intDeque.iterator();
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < intIters.length; j++) {
                int value = intIters[j].next();
                assertEquals(i, value);
            }
        }
        for (int i = 0; i < intIters.length; i++) {
            assertFalse(intIters[i].hasNext());
        }
    }
    
    @Test
    public void testIteratorForEach() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addFirst(i);
        }
        int i = 99;
        for(Integer j : intDeque) {
            assertEquals(i, j.intValue());
            i--;
        }
        assertEquals(i, -1);
    }
    
    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("DequeTest");
    }
}
