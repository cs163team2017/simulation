package Simulation;

import java.util.Iterator;

/**********************************************************************
 * The primary queue for the entire system, sits inbetween the eateries
 * and the cashiers 
 * @author Matthew Pische
 *
 *********************************************************************/
public class MainQ implements CQueue<Person>, 
                              ClockListener,
                              QueuePerformance, 
                              IMainQ {
    /** holder of people */
    PersonList q;
    /** total number of people that have passed through the queue */
    private int completed;
    /** number of people lost */
    private int lost;
    /** maximum queue length ever achieved in this run */
    private int maxQLength;
    /** Threshold to trigger serving the next person */
    private ICashiers cashiers;

    /******************************************************************
     * instantiate a new empty main queue
     *****************************************************************/
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
            lost += q.checkLeavers(tick);
            if (0 >= q.size()) {
                return;
            }
            if (cashiers.haveEmpty()) {
                Person p = q.deQ();
                cashiers.enQ(p, tick);
                completed++;
            }
        }
        
    }
    
    /* (non-Javadoc)
     * @see Simulation.IMainQ#setCashiers(Simulation.Cashiers)
     */
    @Override
    public void setCashiers(ICashiers c) {
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
    public int getMaxQueueLength() {
        return maxQLength;
    }

    /* (non-Javadoc)
     * @see Simulation.IMainQ#getThroughPut()
     */
    @Override
    public int getThroughput() {
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
        return new MainQIterator();
    }
    
    /******************************************************************
     * private class to implement java's foreach iteration
     * @author Matthew Pische
     *
     ******************************************************************/
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
