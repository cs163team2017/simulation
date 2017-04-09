/**
 * 
 */
package Simulation;
/**
 * @author   Roger Ferguson
 * @author   Matthew Pische
 */
public class Eatery implements CisQueue<Person>, ClockListener, QueuePerformance {
    /** internal holder for all people in the eatery's queue */
    private PersonList Q = new PersonList();
    /** Threshold to trigger serving the next person */
    private int ticksToNextPerson = 0;
    /** peak number of people waiting for service */
    private int maxQlength = 0;
    /** total number of people that have passed through the queue */
    private int completed = 0;
    /** number of people who leave the queue due to low speed */
    private int lost = 0;
    	
    /**
     * Add a person to this eatery's queue
     * @param person person to add
     */
    public void add(Person person)
    {


    }
    	
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
                completed++;
            }								
        }	
    }
    	
    	
    @Override
    public int getLeft() {
    	return Q.size();
    }
    	
    @Override
    public int getMaxQlength() {
    	return maxQlength;
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
        return Q.peek();
    }

    @Override
    public Person deQ() {
        lost++;
        return Q.deQ();
    }

    @Override
    public void enQ(Person value) {
        Q.add(value);
        if (Q.size() > maxQlength) {
            maxQlength = Q.size();
        }
        
    }
}
