package Simulation;

import java.util.Iterator;

public class MainQ implements CisQueue<Person>, 
                              ClockListener,
                              QueuePerformance, 
                              IMainQ,
                              Iterable<Person> {
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
    
    /* (non-Javadoc)
     * @see Simulation.IMainQ#event(int)
     */
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
    
    /* (non-Javadoc)
     * @see Simulation.IMainQ#setCashiers(Simulation.Cashiers)
     */
    @Override
    public void setCashiers(Cashiers c) {
        cashiers = c;
    }

    /* (non-Javadoc)
     * @see Simulation.IMainQ#getLeft()
     */
    @Override
    public int getLeft() {
        return q.size();
    }

    /* (non-Javadoc)
     * @see Simulation.IMainQ#getMaxQlength()
     */
    @Override
    public int getMaxQlength() {
        return maxQLength;
    }

    /* (non-Javadoc)
     * @see Simulation.IMainQ#getThroughPut()
     */
    @Override
    public int getThroughPut() {
        return completed;
    }

    /* (non-Javadoc)
     * @see Simulation.IMainQ#getLost()
     */
    @Override
    public int getLost() {
        return lost;
    }

    /* (non-Javadoc)
     * @see Simulation.IMainQ#peek()
     */
    @Override
    public Person peek() {
        return q.peek();
    }

    /* (non-Javadoc)
     * @see Simulation.IMainQ#deQ()
     */
    @Override
    public Person deQ() {
        return q.deQ();
    }

    /* (non-Javadoc)
     * @see Simulation.IMainQ#enQ(Simulation.Person)
     */
    @Override
    public void enQ(Person value) {
        q.add(value);
        if (q.size() > maxQLength) {
            maxQLength = q.size();
        }
    }

    @Override
    public Iterator<Person> iterator() {
        // TODO Auto-generated method stub
        return new MainQIterator();
    }
    
    private class MainQIterator implements Iterator<Person> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < q.size();
        }

        @Override
        public Person next() {
            return q.getAtIndex(index++);
        }   
    }
}
