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
        size = 0;
    }

    public boolean isEmpty() {
        // is the queue empty?
        return (size == 0);
    }

    public int size() {
        // return the number of items on the queue
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        // resize the queue to fit the new item if necessary
        resize(size + 1);
        // add the new item
        items[size] = item;
        size++;
        // shuffle the item into the queue to preserve randomness
        shuffleLastItem();
    }

    private void resize(int newSize) {
        Item[] newItems = null;
        // don't do anything when the array goes to 0
        // to avoid thrashing on the empty->nonempty transition.
        if (newSize == 0) return;
        
        if ((items == null) // don't have an array currently
            || (items.length < newSize) // need to grow the array
            || (items.length >= (newSize * 4))) { // need to shrink the array
            int newLength = size * 2;
            // clip the capacity below at 2 to prevent thrashing.
            if (newLength < 2) newLength = 2;
            newItems = (Item[]) new Object[newLength];
        }
        
        // copy the array over if necessary
        if (newItems != null) {
            if (items != null) {
                // items could be up to size in length
                for (int i = 0; i < size; i++) {
                    newItems[i] = items[i];
                }
            }
            items = newItems;
        }
        
    }

    private void shuffleLastItem() {
        // no shuffling necessary for < 2 items
        if (size <= 1) return;

        // Choose a destination with equal distribution
        //  accross all of the possible sites
        int sourceIndex = size - 1;
        int targetIndex = StdRandom.uniform(size);
        Item temp = items[sourceIndex];
        items[sourceIndex] = items[targetIndex];
        items[targetIndex] = temp;
    }
    
    public Item dequeue() {
        if (size == 0) throw new java.util.NoSuchElementException();
        // delete and return a random item
        // the array is already shuffled,
        // so picking the last element will yield
        // a random element.
        Item randomItem = items[size - 1];
        items[size - 1] = null;
        size--;
        resize(size);
        return randomItem;
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