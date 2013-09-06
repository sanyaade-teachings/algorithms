import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    // internal node class
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node(Item item) {
            if (item == null) throw new NullPointerException();
            this.item = item;
        }
    }

    // internal iterator class


    private Node<Item> head;
    private Node<Item> tail;
    private int size;
    
    public Deque() {
        // construct an empty deque
        size = 0;
        head = null;
        tail = null;
    }
    
    public boolean isEmpty() {
        // is the deque empty?
        return (size == 0);
    }

    public int size() {
        // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {
        // insert the item at the front
        Node<Item> newNode = new Node<Item>(item);
        // this node is the new head
        newNode.next = head;
        head = newNode;
        if (tail == null) {
            // if the list was empty, we're also the new tail
            tail = newNode;
        }
    }
    
    public void addLast(Item item) {
        // insert the item at the end
        Node<Item> newNode = new Node<Item>(item);
        newNode.next = null;
        // this node is the new tail.
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            // if the list was empty, we're also the new head
            head = newNode;
        }
    }
    
    public Item removeFirst() {
        Item value = head.item;
        head = head.next;
        return value;
    }

    public Item removeLast() {
        // delete and return the item at the end
        return null;
    }
    
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return null;
    }

}
