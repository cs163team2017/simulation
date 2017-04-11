/**
 * 
 */
package Simulation;

import java.util.Iterator;

/**
 * @author   Roger Ferguson
 * @author   Matthew Pische
 */
public class Eatery implements IEatery {
    
    /** internal holder for all people in the eatery's queue */
    private PersonList Q;
    /** Threshold to trigger serving the next person */
    private int ticksToNextPerson;
    /** peak number of people waiting for service */
    private int maxQlength;
    /** total number of people that have passed through the queue */
    private int completed;
    /** number of people who leave the queue due to low speed */
    private int lost;
    /** the main queue for the simulation */
    IMainQ mainQ;
    
    public Eatery() {
        Q = new PersonList();
        ticksToNextPerson = 0;
        maxQlength = 0;
        completed = 0;
        lost = 0;
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
        
        //if (tick >= ticksToNextPerson) {
        // Notice the delay that takes place here
        //if (person != null) {  
        // take this person to the next station. 
        //	person.getDestination().add(person);  
        // I have send the person on. 
        //person = null;				
        //}
        
        int s = Q.size();
        
        // TODO 1: this logic is not actually correct
        // need to check if the one at the counter is leaving,
        // if so, reset ticks to next to the _next_ person in the queue
        if (s >= 1) {
            // remove all people who won't wait anymore
            lost += Q.checkLeavers(tick);
            if (0 >= Q.size()) {
                return;
            }
            if (tick >= ticksToNextPerson) {
                Person person = Q.deQ();

                ticksToNextPerson = tick + 
                                    (int)(person.getEateryTime() + 1);
                mainQ.enQ(person);
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
    public int getMaxQlength() {
    	return maxQlength;
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEatery#getThroughPut()
     */
    @Override
    public int getThroughPut() {
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
