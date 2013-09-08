import org.junit.*;
import org.junit.Test;;
import static org.junit.Assert.*;
import java.util.*;

public class RandomizedQueueTest {

    @Test
    public void testCreateRandomizedQueue() {
        RandomizedQueue<String> randStrQueue = 
            new RandomizedQueue<String>();
        assertNotNull(randStrQueue);
        assertEquals(randStrQueue.size(), 0);
        assertTrue(randStrQueue.isEmpty());
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("RandomizedQueueTest");
    }
}