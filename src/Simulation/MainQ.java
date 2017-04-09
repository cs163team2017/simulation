package Simulation;

public class MainQ implements CisQueue<Person>, 
                              ClockListener,
                              QueuePerformance {
    /** holder of people */
    PersonList q;
    /** total number of people that have passed through the queue */
    private int completed;

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
            q.deQ();
            completed++;
        }
        
    }

    @Override
    public int getLeft() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMaxQlength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getThroughPut() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getLost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Person peek() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Person deQ() {
        
    }

    @Override
    public void enQ(Person value) {
        q.add(value);
    }
    
}
