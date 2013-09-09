import org.junit.*;
import org.junit.Test;;
import static org.junit.Assert.*;
import java.util.*;

public class RandomizedQueueTest {

    @Test
    public void testCreate() {
        RandomizedQueue<String> randStrQueue = 
            new RandomizedQueue<String>();
        assertNotNull(randStrQueue);
        assertEquals(randStrQueue.size(), 0);
        assertTrue(randStrQueue.isEmpty());
    }

    @Test
    public void testEnqueueOne() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        intQueue.enqueue(127);
        assertFalse(intQueue.isEmpty());
        assertEquals(1, intQueue.size());
    }

    @Test
    public void testEnqueueMany() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 1000; i++) {
            intQueue.enqueue(i);
        }
        assertFalse(intQueue.isEmpty());
        assertEquals(1000, intQueue.size());
    }

    @Test(expected=NullPointerException.class)
    public void testEnqueueNull() {
        RandomizedQueue<String> strQueue = new RandomizedQueue<String>();
        strQueue.enqueue(null);
    }
       
    @Test
    public void testEnqueDequeOne() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 100; i++) {
            intQueue.enqueue(127);
            assertEquals(127, intQueue.dequeue().intValue());
            assertTrue(intQueue.isEmpty());
            assertEquals(0, intQueue.size());
        }
    }

    @Test
    public void testEnqueueDequeueMany() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 1000; i++) {
            intQueue.enqueue(i);
        }
        assertEquals(1000, intQueue.size());
        for (int i = 1; i <= 1000; i++) {
            int j = intQueue.dequeue();
            assertNotEquals(0, j);
        }
        assertTrue(intQueue.isEmpty());
        assertEquals(0, intQueue.size());
    }

    @Test
    public void testRandomCoinFlip() {
        int sums[] = doExperiments(100000, 2);
        verifyExperiments(sums, 100000, 2);
    }

    @Test
    public void testCardShuffle() {
        int sums[] = doExperiments(100000, 52);
        verifyExperiments(sums, 100000, 52);
    }
    
    // returns an array of sums
    private int[] doExperiments(int numExperiments, int setSize) {
        int sums[] = new int[setSize];
        for(int i = 0; i < sums.length; i++) {
            sums[i] = 0;
        }
        for(int i = 0; i < numExperiments; i++) {
            doOneExperiment(sums);
        }
        return sums;
    }

    private void doOneExperiment(int[] accumulator) {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        for(int i = 0; i < accumulator.length; i++) {
            intQueue.enqueue(i);
        }
        for(int i = 0; i < accumulator.length; i++) {
            accumulator[i] += intQueue.dequeue();
        }
    }

    private void verifyExperiments(int[] sums, 
                                   int numExperiments, 
                                   int setSize) {
        double ideal = (setSize - 1) / 2.0;
        double epsilon = (100.0 / numExperiments) * setSize * 4.0;
        double upperLimit = ideal + epsilon;
        double lowerLimit = ideal - epsilon;
        for(int i = 0; i < sums.length; i++) {
            double mean = sums[i] / ((double)numExperiments);
            assertTrue(mean <= upperLimit);
            assertTrue(mean >= lowerLimit);
        }
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("RandomizedQueueTest");
    }
}