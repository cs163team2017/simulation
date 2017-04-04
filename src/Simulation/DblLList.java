package Simulation;

/**
 * A simple generic double linked list implementation 
 * @author Matthew Pische
 *
 * @param <T> type of the values in the list's nodes
 */
public class DblLList<T> implements CisQueue<T> {
    /** first node in the list */
    private ListNode<T> head;
    /** last node in the list */
    private ListNode<T> tail;
    /** number of nodes in the list */
    private int count;
        
    /**
     * initializes an empty linked list, with null values for 
     * the head and tail, and an element count of zero
     */
    public DblLList() {
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
        return head.getValue();
    }

    /**
     * adds a new node, holding the passed in value, to the head of 
     * the list
     * @param value value to be added to the new head node
     */
    public void addFirst(T value) {
        if (head == null) {
            tail = head = new ListNode<T>(value);
            count++;
            return;
        }
        ListNode<T> old = head;
        head = new ListNode<T>(value, old);
        old.setPrev(head);
        count++;
    }
    
    /**
     * getter for the value of the tail node
     * @return value of the last node in the list
     */
    public T last() {
        return tail.getValue();
    }
        
    /**
     * Inserts a new node holding the passed in value at the end 
     * of the list
     * @param value to insert in a new node at the tail
     */
    public void add(T value) {
        if (head == null) {
            head = tail = new ListNode<T>(value);
        } else {
            ListNode<T> n = new ListNode<T>(value, null, tail);
            tail.setNext(n); 
            tail = n;
        }
        count++;
    }
    
    /**
     * wraps adding a new tail value
     * @param value value to add to the new tail node
     */
    public void push(T value) {
        add(value);
    }
    
    /**
     * Removes and returns the value from the last node of the list
     * @return value of the last node, now removed 
     */
    public T pop() {
        if (tail == null) {
            return null;
        }
        ListNode<T> last = tail;
        if (count > 1) {
            tail.prevNode().setNext(null);  
        } else {
            head = tail = null;
        }

        count--;
        return last.getValue();        
    }
    
    /**
     * Gets the node, not just the value, from the element of the list
     * at the given position. Returns null if index is out of bounds
     * @param index Nth element of the list to retrieve 
     * @return the node at the given position
     */
    private ListNode<T> getNodeAtIndex(int index) {
        if (index > count - 1) {
            return null;
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
     * @param value
     * @param index
     */
    public void setAtIndex(T value, int index) 
            throws IndexOutOfBoundsException {
        if (index > count - 1) {
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
    public void insertAtIndex(T value, int index) 
            throws IndexOutOfBoundsException {
        if (index > count - 1) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> curr = getNodeAtIndex(index);
        ListNode<T> prev = curr.prevNode();
        ListNode<T> n = new ListNode<T>(value, curr, prev);
        
        prev.setNext(n);
        curr.setPrev(n);
        count++;
    }
    
    /**
     * Removes the first node, and returns its value
     * @return value of the first, now removed, node
     */
    public T removeFirst() {
        if (head == null) {
            return null;
        } else if (head == tail) {
            T v = first();
            head = tail = null;
            count--;
            return v; 
        } 
        ListNode<T> h = head;
        head = head.nextNode();
        head.setPrev(null);
        count--;
        return h.getValue();
    }
    
    /**
     * Removes the last node in the list and returns its value
     * @return value of the, now removed, final node
     */
    public T removeLast() {
        if (tail == null) {
            return null;
        } else if (head == tail) {
            T v = last();
            head = tail = null;
            count--;
            return v;
        }
        ListNode<T> t = tail;
        tail = tail.prevNode();
        tail.setNext(null);
        count--;
        return t.getValue();
    }
    
    /**
     * Removes and returns the node at the given location in the list
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    private ListNode<T> removeNodeAtIndex(int index) 
            throws IndexOutOfBoundsException {
        if ((index > count - 1) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> curr = getNodeAtIndex(index);
        if (count > 1) {
            ListNode<T> prev = curr.prevNode();
            ListNode<T> next = curr.nextNode();
            
            next.setPrev(prev);
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
}
