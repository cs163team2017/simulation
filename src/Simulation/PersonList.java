package Simulation;

public class PersonList extends DblLList<Person> {

    public int checkLeavers(int tick) {
        int numLeft = 0;
        // TODO 1: this loop doesn't work, removal iterates the 
        // index, and also shifts forward remaining cells by one
        // so the next cell after the one removed is never checked
        // put it in a where, reverse it length => 0, then 
        // remove at index on the cell to eliminate should work
        ListNode<Person> curr = head;
        while (curr != null) {
            if (curr.getValue().leaveTime >= tick) {
                ListNode<Person> next = curr.nextNode();
                ListNode<Person> prev = curr.prevNode();
                if (prev != null) {
                    prev.setNext(next);
                }
                if (next != null) {
                    next.setPrev(prev);
                }
                numLeft++;
            }
            curr = curr.nextNode();
        }
        
//        int index = 0;
//        for (ListNode<Person> p : this) {
//            if (tick >= p.getValue().leaveTime) {
//                this.removeAtIndex(index);
//                numLeft++;
//            }
//            index++;
//        }
        return numLeft;
    }    
}
