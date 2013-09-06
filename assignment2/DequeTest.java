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

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("DequeTest");
    }
}
