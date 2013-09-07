import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    // internal node class
    private class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;
        private Node(Item item) {
            if (item == null) throw new NullPointerException();
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }

    // internal iterator class
    private class NodeIterator<Item> implements Iterator<Item> {
        private Node<Item> curNode;

        private NodeIterator(Node<Item> head) {
            curNode = head;
        }

        public boolean hasNext() {
            return (curNode != null);
        }
        
        public Item next() {
            if (curNode == null) throw new java.util.NoSuchElementException();
            Item item = curNode.item;
            curNode = curNode.next;
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


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
        if (head == null) {
            tail = newNode;
        }
        else {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }
    
    public void addLast(Item item) {
        // insert the item at the end
        Node<Item> newNode = new Node<Item>(item);
        newNode.prev = tail;
        // this node is the new tail.
        if  (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            // if the list was empty, we're also the new head
            head = newNode;
        }
        size++;
    }
    
    public Item removeFirst() {
        // Check for empty deque
        if (head == null) throw new java.util.NoSuchElementException();
        Item value = head.item;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        else {
            tail = null;
        }
        size--;
        return value;
    }

    public Item removeLast() {
        if (tail == null) throw new java.util.NoSuchElementException();
        Item value = tail.item;
        if (tail.prev != null) {
            tail = tail.prev;
            tail.next = null;
        }
        else {
            // that was the last item
            tail = null;
            head = null;
        }
        
        size--;
        return value;
    }
    
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        Iterator<Item> iter = new NodeIterator<Item>(head);
        return iter;
    }

}
