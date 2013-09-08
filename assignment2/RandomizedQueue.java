/*
 * RandomizedQueue
 *
 * A randomized collection API
 *
 * Author: Mark Pauley
 * Date: 9-8-2013
 *
 */

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] items;
    private int size;
    

    public RandomizedQueue() {
        // construct an empty randomized queue
        items = null;
    }

    public boolean isEmpty() {
        // is the queue empty?
        return (size == 0);
    }

    public int size() {
        // return the number of items on the queue
        return 0;
    }

    public void enqueue(Item item) {
        // add the item
        
    }

    private void resize(int newSize) {

    }
    
    public Item dequeue() {
        // delete and return a random item
        return null;
    }
    
    public Item sample() {
        // return (but do not delete) a random item
        return null;
    }
    
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return null;
    }

}