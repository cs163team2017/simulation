package Simulation;


/**
 * A generic node class to be used in a double linked list
 * @author Matthew Pische
 *
 * @param <T> The type of the value held in the node
 */
public class ListNode<T> {
    private ListNode<T> next;
    private ListNode<T> prev;
    private T value;
    private DblLList<T> itsList = null;
    
    /**
     * constructor taking only the value for the node, links are null
     * @param value
     */
    public ListNode(DblLList<T> l, T value) {
        itsList = l;
        this.value = value;
        next = null;
        prev = null;
    }
    
    /**
     * Takes a value and the next node, previous node is null, 
     * could make a single linked list with this
     * @param value content of the node
     * @param next the next node in the list
     */
    public ListNode(DblLList<T> l, T value, ListNode<T> next) {
        itsList = l;
        this.value = value;
        this.next = next;
        prev = null;
    }
    
    /**
     * Full constructor of the node for a double linked list
     * @param value value of the node
     * @param next next node in the list
     * @param prev prior node in the list
     */
    public ListNode(DblLList<T> l, 
                    T value, 
                    ListNode<T> next, 
                    ListNode<T> prev) {
        itsList = l;
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
    
    /**
     * getter for the node content
     * @return returns the content of the node
     */
    public T getValue() {
        return value;
    }
    
    /**
     * setter for the node content
     * @param value the new value for the node
     */
    public void setValue(T value) {
        this.value = value;
    }
    
    /**
     * setter for the node's next link
     * @param next the next node to point to in the list
     */
    public void setNext(ListNode<T> next) {
        this.next = next;
    }
    
    /**
     * getter for the next node this links to
     * @return the next node
     */
    public ListNode<T> nextNode() {
        return next;
    }
    
    /**
     * setter for the node's prior relation
     * @param prev the node before this one in the list
     */
    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }
    
    /**
     * getter for the previous node in the list
     * @return the prior node
     */
    public ListNode<T> prevNode() {
        return prev;
    }
    
    public void removeSelf() {
        if (itsList == null) {
            throw new RuntimeException();
        }
        if (itsList.isFirstNode(this)) {
            if (itsList.isLastNode(this)) {
                itsList.removeLast();
                return;
            }
            itsList.removeFirst();
            return;
        }
        if (itsList.isLastNode(this)) {
            itsList.removeLast();
            return;
        }
        ListNode<T> n = this.next;
        ListNode<T> p = this.prev;
        if (p != null) {
            p.setNext(n);
        }
        if (n != null) {
            n.setNext(p);
        }
    }

}
