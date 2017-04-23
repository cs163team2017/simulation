package Simulation;

public class PersonList extends DList<Person> {

    /******************************************************************
     * Checks all people objects in the list to see if their maximum
     * wait time has been exceeded, and removes them if so
     * @param tick the current tick of the simulation's clock
     * @return the number of people removed from the list
     ******************************************************************/
    public int checkLeavers(int tick) {
        // number of people who have exited the system
        int numLeft = 0;
        ListNode<Person> curr = head;
        
        while (curr != null) {
            System.out.println("in leavers, tick: " + tick);
            double lv = curr.getValue().getLeaveTime();
            System.out.println("curr creation: " + curr.getValue().creationTime);
            System.out.println("in leavers, curr leaveTime: " + lv);
            if (tick >= lv) {
                System.out.println("In remove leavers");
                numLeft++;
                count--;
                ListNode<Person> next = curr.nextNode();
                ListNode<Person> prev = curr.prevNode();
                
                //stitch together the mutated linked list
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
