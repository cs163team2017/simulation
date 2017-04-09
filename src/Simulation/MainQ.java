package Simulation;

public class MainQ implements CisQueue<Person>, 
                              ClockListener,
                              QueuePerformance {
    /** holder of people */
    PersonList q;
    /** total number of people that have passed through the queue */
    private int completed;
    /** number of people lost */
    private int lost;
    /** maximum queue length ever achieved in this run */
    private int maxQLength;
    /** Threshold to trigger serving the next person */
    private Cashiers cashiers;

    public MainQ() {
       q = new PersonList();
       completed = 0;
    }
    
    @Override
    public void event(int tick) {
        if (q.size() > 0) {
            q.checkLeavers(tick);
            if (0 >= q.size()) {
                return;
            }
            Person p = q.deQ();
            
            completed++;
            cashiers.random().enQ(p);
        }
        
    }
    
    public void setCashiers(Cashiers c) {
        cashiers = c;
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

    @Override
    public Person peek() {
        return q.peek();
    }

    @Override
    public Person deQ() {
        return q.deQ();
    }

    @Override
    public void enQ(Person value) {
        q.add(value);
        if (q.size() > maxQLength) {
            maxQLength = q.size();
        }
    }
    
}
