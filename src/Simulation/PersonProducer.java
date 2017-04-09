/**
 * 
 */
package Simulation;

import java.util.Random;

/**
 * @author Roger Ferguson
 * @author Matthew Pische
 */
public class PersonProducer implements ClockListener {
	
	private int nextPerson = 0;
	private Eateries eateries;
	private int numOfTicksNextPerson;
	private int averageEateryTime;
	
	private Random r;
	
	public PersonProducer(Random r, Eateries eateries, 
			int numOfTicksNextPerson, 
			int averageEateryTime) {
		
	        this.r = r;
		this.eateries = eateries;
		this.numOfTicksNextPerson = numOfTicksNextPerson;
		this.averageEateryTime = averageEateryTime;
	}
	
	public void event(int tick) {
		if (nextPerson <= tick) {
			nextPerson = tick + numOfTicksNextPerson;
			
			Person person = new Person();

			person.setEateryTime(averageEateryTime*0.5*r.nextGaussian() + averageEateryTime +.5);
			person.setTickTime(tick);
			eateries.add(person);
			
		//	person.setDestination(theLocationAfterTheEatery);  // You can save off where the person should go.
		}
	}

}
