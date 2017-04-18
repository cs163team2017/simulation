/**
 * 
 */
package Simulation;

import java.util.Iterator;

/**
 * Class to implement a single eatery 
 * @author   Roger Ferguson
 * @author   Matthew Pische
 */
public class Eatery implements IEatery {
    
    /** internal holder for all people in the eatery's queue */
    private PersonList Q;
    /** peak number of people waiting for service */
    private int maxQlength;
    /** total number of people that have passed through the queue */
    private int completed;
    /** number of people who leave the queue due to low speed */
    private int lost;
    /** the main queue for the simulation */
    private IMainQ mainQ;
    /** the person currently being served by this eatery */
    private Person atDesk;

    /****************************************************************** 
     * Instantiate a new empty eatery 
     ******************************************************************/
    public Eatery() {
        Q = new PersonList();
        maxQlength = 0;
        completed = 0;
        lost = 0;
        atDesk = null;
    }
    	
    /* (non-Javadoc)
     * @see Simulation.IEatery#setMainQueue(Simulation.IMainQ)
     */
    @Override
    public void setMainQueue(IMainQ q) {
        mainQ = q;
    }
    	
    /* (non-Javadoc)
     * @see Simulation.IEatery#event(int)
     */
    @Override
    public void event (int tick){
        
        int s = Q.size();
        
        if (s >= 1) {

            // remove all people who won't wait anymore
            lost += Q.checkLeavers(tick);
            if (0 >= Q.size()) {
                return;
            }
            
            if (atDesk == null || atDesk != Q.peek()) {
                atDesk = Q.peek();
                atDesk.setEateryTime(atDesk.getEateryTime() + tick);
            }

            if (tick >= atDesk.getEateryTime()) {
                Person person = Q.deQ();
                mainQ.enQ(person);
                atDesk = Q.peek();
                if (atDesk != null) {
                    atDesk.setEateryTime(atDesk.getEateryTime() + tick);
                }

                completed++;
            }								
        }
    }
    	
    	
    /* (non-Javadoc)
     * @see Simulation.IEatery#getLeft()
     */
    @Override
    public int getLeft() {
    	return Q.size();
    }
    	
    /* (non-Javadoc)
     * @see Simulation.IEatery#getMaxQlength()
     */
    @Override
    public int getMaxQueueLength() {
    	return maxQlength;
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEatery#getThroughPut()
     */
    @Override
    public int getThroughput() {
    	return completed;
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEatery#getLost()
     */
    @Override
    public int getLost() {
        return lost;
    }

    /* (non-Javadoc)
     * @see Simulation.IEatery#peek()
     */
    @Override
    public Person peek() {
        return Q.peek();
    }

    /* (non-Javadoc)
     * @see Simulation.IEatery#deQ()
     */
    @Override
    public Person deQ() {
        lost++;
        return Q.deQ();
    }

    /* (non-Javadoc)
     * @see Simulation.IEatery#enQ(Simulation.Person)
     */
    @Override
    public void enQ(Person value) {
        Q.add(value);
        if (Q.size() > maxQlength) {
            maxQlength = Q.size();
        }   

    }

    /* (non-Javadoc)
     * @see Simulation.IEatery#iterator()
     */
    @Override
    public Iterator<Person> iterator() {
        return new EateryIterator();
    }
    
    /******************************************************************
     * Private class to implement java's foreach looping
     * @author Matthew Pische
     *
     ******************************************************************/
    private class EateryIterator implements Iterator<Person> {
        private int index = 0;
        
        @Override
        public boolean hasNext() {
            return index < Q.size();
        }

        @Override
        public Person next() {
            return Q.getAtIndex(index++);
        } 
    }
}
