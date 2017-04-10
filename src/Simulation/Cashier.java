package Simulation;

public class Cashier implements CisQueue<Person>, 
                                ClockListener,
                                QueuePerformance {
    /** internal holder for all people in the cashier's queue */
    private PersonList q;
    /** number of customers that leave before completion */
    private int lost;
    /** maximum queue length ever achieved in this run */
    private int maxQLength;
    /** Threshold to trigger serving the next person */
    private int ticksToNextPerson = 0;
    /** total number of people that have passed through the queue */
    private int completed = 0;
    
    public Cashier() {
        q = new PersonList();
    }
    
    @Override
    public void event(int tick) {
        if (q.size() > 0) {
            lost += q.checkLeavers(tick);
            if (0 >= q.size()) {
                return;
            }
            // TODO 1: this logic is not correct, check if person at 
            // head dumped out due to delay
            if (tick >= ticksToNextPerson) {
                Person p = q.deQ();
                ticksToNextPerson = tick + 
                                    (int)(p.getCashierTime() + 1);
                completed++;
            }
        }
        
    }

    @Override
    public Person peek() {
        return q.peek();
    }

    @Override
    public Person deQ() {
        lost++;
        return q.deQ();
    }

    @Override
    public void enQ(Person value) {
        q.add(value);
        if (q.size() > maxQLength) {
            maxQLength = q.size();
        }
        
    }

    @Override
    public int getLeft() {
        return q.size();
    }

    @Override
    public int getMaxQlength() {
        return maxQLength;
    }

    @Override
    public int getThroughPut() {
        return completed;
    }

    @Override
    public int getLost() {
        return lost;
    }

}
