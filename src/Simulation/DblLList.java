package Simulation;

public class DblLList<T> implements CisQueue<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int count;
        
    public DblLList() {
        head = null;
        tail = null;
        count = 0;
    }
    
    public int size() {
        return count;
    }
    
    public T first() {
        return head.getValue();
    }
    
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
    
    public T last() {
        return tail.getValue();
    }
        
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
    
    public void push(T value) {
        add(value);
    }
    
    public T pop() {
        if (tail == null) {
            return tail.getValue();
        }
        ListNode<T> last = tail;
        tail.prevNode().setNext(null);
        count--;
        return last.getValue();        
    }
    
    private ListNode<T> getNodeAtIndex(int index) {
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
    
    public void setAtIndex(T value, int index) {
        getNodeAtIndex(index).setValue(value);
    }
    
    public void insertAtIndex(T value, int index) {
        ListNode<T> curr = getNodeAtIndex(index);
        ListNode<T> prev = curr.prevNode();
        ListNode<T> n = new ListNode<T>(value, curr, prev);
        
        prev.setNext(n);
        curr.setPrev(n);
        count++;
    }
    
    private ListNode<T> removeNodeAtIndex(int index) {
        ListNode<T> curr = getNodeAtIndex(index);
        ListNode<T> prev = curr.prevNode();
        ListNode<T> next = curr.nextNode();
        
        next.setPrev(prev);
        prev.setNext(next);
        count--;
        return curr;
    }
    
    public T removeAtIndex(int index) {
        return removeNodeAtIndex(index).getValue();
    }
    
    @Override
    public T peek() {
        return first();
    }
    
    @Override
    public T deQ() {
        return pop();
    }

    @Override
    public void enQ(T value) {
        add(value);
    }
}
