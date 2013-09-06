import org.junit.*;
import org.junit.Test;;
import static org.junit.Assert.*;
import java.util.*;

public class DequeTest {

    @Test
    public void testEmptyDeque() {
        Deque<Object> empty = new Deque<Object>();
        assertTrue(empty.size() == 0);
    }

    @Test
    public void testAddRemoveFront() {
        Deque<Integer> intDeque = new Deque<Integer>();
        for(int i = 0; i < 100; i++) {
            intDeque.addFirst(i);
            int j = intDeque.removeFirst();
            assertTrue(i == j);
        }
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("DequeTest");
    }
}
