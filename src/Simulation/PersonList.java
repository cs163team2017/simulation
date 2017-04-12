package Simulation;

public class PersonList extends DblLList<Person> {

    /**
     * Checks all people objects in the list to see if their maximum
     * wait time has been exceeded, and removes them if so
     * @param tick the current tick of the simulation's clock
     * @return the number of people removed from the list
     */
    public int checkLeavers(int tick) {
        int numLeft = 0;
        ListNode<Person> curr = head;
        while (curr != null) {
            if (tick >= curr.getValue().getLeaveTime()) {
                numLeft++;
                count--;
                ListNode<Person> next = curr.nextNode();
                ListNode<Person> prev = curr.prevNode();
                if (head == tail) {
                    head = tail = null;
                } else if (curr == head) {
                    head = next;
                } else if (curr == tail) {
                    tail = prev;
                }
                
                if (prev != null) {
                    prev.setNext(next);
                }
                if (next != null) {
                    next.setPrev(prev);
                }

            }
            curr = curr.nextNode();
        }

        return numLeft;
    }    
}
