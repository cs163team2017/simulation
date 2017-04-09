/**
 * 
 */
package Simulation;
/**
 * @author   Roger Ferguson
 * @author   Matthew Pische
 */
public class Eatery implements ClockListener, QueuePerformance {
/** internal holder for all people in the eatery's queue */
	private DblLList<Person> Q = new DblLList<Person>();
	/** Threshold to trigger serving the next person */
	private int timeOfNextEvent = 0;
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
		Q.add(person);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}
	
	@Override
	public void event (int tick){
		if (tick >= timeOfNextEvent) {
		 // Notice the delay that takes place here
			//if (person != null) {  
		 // take this person to the next station. 
			//	person.getDestination().add(person);  
		 // I have send the person on. 
			//person = null;				
			}
			
			if (Q.size() >= 1) {
				Person person = Q.deQ();
				// do not send this person as of yet, 
				// make them wait. 
				timeOfNextEvent = tick + 
				        (int)(person.getBoothTime() + 1);
				completed++;										
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
}
