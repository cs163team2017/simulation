package Simulation;

//import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple generic double linked list implementation 
 * @author Matthew Pische
 *
 * @param <T> type of the values in the list's nodes
 */
public class DList<T> implements CQueue<T>, Iterable<ListNode<T>> {
    /** first node in the list */
    protected ListNode<T> head;
    /** last node in the list */
    protected ListNode<T> tail;
    /** number of nodes in the list */
    protected int count;
        
    /**
     * initializes an empty linked list, with null values for 
     * the head and tail, and an element count of zero
     */
    public DList() {
        head = null;
        tail = null;
        count = 0;
    }
    
    /**
     * Getter for the list's size
     * @return size of the list
     */
    public int size() {
        return count;
    }
    
    /**
     * getter for the value of the list's head node
     * @return
     */
    public T first() {
        if (head == null)
            return null;
        return head.getValue();
    }
    
    /** 
     * evaluates if a node is the head node
     * @param n the node to evaluate
     * @return head
     */
    public boolean isFirstNode(ListNode<T> n) {
        if (n == head) {
            return true;
        }
        return false;
    }
    
    /**
     * evaluates if a given node is the tail node
     * @param n the node to evaluate
     * @return
     */
    public boolean isLastNode(ListNode<T> n) {
        if (n == tail) {
            return true;
        }
        return false;
    }
    
    /**
     * fetch the tail node
     * @return tail
     */
    public ListNode<T> lastNode() {
        return tail;
    }

    /**
     * adds a new node, holding the passed in value, to the head of 
     * the list
     * @param value value to be added to the new head node
     */
    public void addFirst(T value) {
        if (head == null) {
            tail = head = new ListNode<T>(this, value);
            count++;
            return;
        }
        ListNode<T> old = head;
        head = new ListNode<T>(this, value, old);
        old.setPrev(head);
        count++;
    }
    
    /**
     * getter for the value of the tail node
     * @return value of the last node in the list
     */
    public T last() {
        if (tail == null)
            return null;
        return tail.getValue();
    }
        
    /**
     * Inserts a new node holding the passed in value at the end 
     * of the list
     * @param value to insert in a new node at the tail
     */
    public void add(T value) {
        if (tail == null) {
            head = tail = new ListNode<T>(this, value);
        } else {
            ListNode<T> n = new ListNode<T>(this, value, null, tail);
            tail.setNext(n); 
            tail = n;
        }
        count++;
    }
    
    /**
     * Gets the node, not just the value, from the element of the list
     * at the given position. Returns null if index is out of bounds
     * @param index Nth element of the list to retrieve 
     * @return the node at the given position
     */
    protected ListNode<T> getNodeAtIndex(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> n;
        if (index > (count / 2)) {
            n = tail;
            for (int i = count - 1; i > index; i--) {
                n = n.prevNode();
            }
            return n;
        }
        n = head;
        for (int i = 0; i < index; i++) {
            n = n.nextNode();
        }
        return n;
    }
    
    /**
     * Gets the value of the node at the given index
     * @param index location of the node to interrogate
     * @return value of the desired node
     */
    public T getAtIndex(int index) {
        return getNodeAtIndex(index).getValue();
    }
    
    /**
     * Updates the value of the node at the given position 
     * @param value new value for the node
     * @param index location of the node to update
     */
    public void setAtIndex(T value, int index) {
        if (index == 0 && head == null) {
            head = tail = new ListNode<T>(this, value);
            count++;
            return;
        }
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        getNodeAtIndex(index).setValue(value);
    }
    
    /**
     * Adds the given value as a new node at the passed in position 
     * in the list
     * @param value value to insert in the new node
     * @param index location to place the new node
     * @throws IndexOutOfBoundsException if the index exceeds list length
     */
    public void insertAtIndex(T value, int index) {
        if (index == 0 && head == null) {
            head = tail = new ListNode<T>(this, value);
            count++;
            return;
        }
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> curr = getNodeAtIndex(index);
        ListNode<T> prev = curr.prevNode();
        ListNode<T> n = new ListNode<T>(this, value, curr, prev);
        if (prev != null)
            prev.setNext(n);
        curr.setPrev(n);
        count++;
    }
    
    /**
     * Removes the first node, and returns its value
     * @return value of the first, now removed, node
     */
    public T removeFirst() {
        ListNode<T> n = removeFirstNode();
        if (n == null) 
            return null;
        return n.getValue();
    }
    
    protected ListNode<T> removeFirstNode() {
        if (head == null) {
            return null;
        }
        ListNode<T> h = head;
        if (head == tail) {
            head = tail = null;
            count--;
            return h; 
        } 
        head = head.nextNode();
        head.setPrev(null);
        count--;
        return h;
    }
    
    /**
     * Removes the last node in the list and returns its value
     * @return value of the, now removed, final node
     */
    public T removeLast() {
        ListNode<T> n = removeLastNode();
        if (n == null) 
            return null;
        return n.getValue();
    }
    
    /**
     * Removes and returns the last node from the list, returns null
     *  if the list is empty
     * @return the last node on the list
     */
    public ListNode<T> removeLastNode() {
        if (tail == null) {
            return null;
        }
        ListNode<T> t = tail;
        if (head == tail) {
            head = tail = null;
            count--;
            return t;
        }
        tail = tail.prevNode();
        tail.setNext(null);
        count--;
        return t;
    }
    
    /**
     * Removes and returns the node at the given location in the list
     * @param index location of the target node
     * @return node at the given index
     */
    protected ListNode<T> removeNodeAtIndex(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> curr = getNodeAtIndex(index);
        if (count == 1) {
            head = tail = null;
        }
        if (count > 1) {
            ListNode<T> prev = curr.prevNode();
            ListNode<T> next = curr.nextNode();
            if (next != null)
                next.setPrev(prev);
            if (prev != null)
                prev.setNext(next);
        }

        count--;
        return curr;
    }
    
    /**
     * Removes the node at the given location in the list, and returns
     * its held value
     * @param index location of the node to remove 
     * @return
     */
    public T removeAtIndex(int index) {
        return removeNodeAtIndex(index).getValue();
    }
    
    /**
     * wraps adding a new tail value, alias for add()
     * @param value value to add to the new tail node
     */
    public void push(T value) {
        add(value);
    }
    
    /**
     * Removes and returns the value from the last node of the list,
     * alias for removeLast()
     * @return value of the last node, now removed 
     */
    public T pop() {
        return removeLast();       
    }
    
    @Override
    public T peek() {
        return first();
    }
    
    @Override
    public T deQ() {
        return removeFirst();
    }

    @Override
    public void enQ(T value) {
        add(value);
    }
    
    /**
     * Implementing the methods necessary to enable foreach looping
     */
    public Iterator<ListNode<T>> iterator() {
        return new ListIterator();
    }
    
    /**
     * Private class to implement iteration methods enabling foreach
     * looping
     * @author Matthew Pische
     *
     */
    protected class ListIterator implements Iterator<ListNode<T>> {
        private int index = 0;
        
        /**
         * check if there is at least one more element in the set
         */
        public boolean hasNext() {
            return index < count;
        }
        
        /**
         * return the next element of the set, and increment counter
         */
        public ListNode<T> next() {
            return getNodeAtIndex(index++);
        }
    }
}
