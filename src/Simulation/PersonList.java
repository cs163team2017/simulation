package Simulation;

public class PersonList extends DblLList<Person> {

    public int checkLeavers(int tick) {
        int numLeft = 0;
        int index = 0;
        for (ListNode<Person> p : this) {
            if (tick >= p.getValue().leaveTime) {
                this.removeAtIndex(index);
            }
            index++;
            numLeft++;
        }
        return numLeft;
    }    
}
