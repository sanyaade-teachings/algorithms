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
        int sums[] = doExperiments(10000, 2);
        verifyExperiments(sums, 10000, 2);
    }

    @Test
    public void testCardShuffle() {
        int sums[] = doExperiments(10000, 52);
        verifyExperiments(sums, 10000, 52);
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

    @Test(expected=NoSuchElementException.class)
    public void testSampleNoEntries() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        intQueue.sample();
    }

    @Test
    public void testSampleOneEntry() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        intQueue.enqueue(127);
        assertEquals(127, intQueue.sample().intValue());
    }

    @Test
    public void testSampleManyEntries() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        for(int i = 0; i < 1000; i++) {
            intQueue.enqueue(i);
            assertTrue( intQueue.sample() != null );
        }
        for(int i = 0; i < 1000; i++) {
            assertTrue(intQueue.sample() != null);
        }
    }

    @Test
    public void testEmptyIteratorHasNext() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        Iterator<Integer> intIter = intQueue.iterator();
        assertNotNull(intIter);
        assertFalse(intIter.hasNext());
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyIteratorNext() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        Iterator<Integer> intIter = intQueue.iterator();
        int i = intIter.next();
        assertFalse(true);
    }

    @Test
    public void testIteratorSingleElement() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        intQueue.enqueue(127);
        Iterator<Integer> intIter = intQueue.iterator();
        assertTrue(intIter.hasNext());
        assertEquals(127, intIter.next().intValue());
        assertFalse(intIter.hasNext());
        assertFalse(intQueue.isEmpty());
    }

    @Test
    public void testIteratorManyElements() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        int[] numTimesSeen = new int[1000];
        for (int i = 0; i < 1000; i++) {
            intQueue.enqueue(i);
            numTimesSeen[i] = 0;
        }
        for(Integer i : intQueue) {
            numTimesSeen[i.intValue()]++;
        }
        for(int i = 0; i < 1000; i++) {
            assertEquals(1, numTimesSeen[i]);
        }
    }

    @Test
    public void testMultiIterator() {
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<Integer>();
        Iterator<Integer>[] iterators = new Iterator[1000];
        for (int i = 0; i < 1000; i++) {
            intQueue.enqueue(i);
        }
        for(int i = 0; i < 1000; i++) {
            iterators[i] = intQueue.iterator();
        }
        for(int i = 0; i < 1000; i++) {
            for(int j = 0; j < 1000; j++) {
                assertTrue(iterators[j].hasNext());
                iterators[j].next();
            }
        }
        for(int i = 0; i < 1000; i++) {
            assertFalse(iterators[i].hasNext());
        }
    }

    
    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("RandomizedQueueTest");
    }
}