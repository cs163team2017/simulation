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
    	
    private double ticksToNextPerson = 0;
    private IEateries eateries;
    private int aveNumOfTicksToNextPerson;
    private int numOfTicksToNextPerson;
    private int averageEateryTime;
    private int aveCashierTime;
    private int aveMaxTime;
    	
    private Random r;
    	
    public PersonProducer(Random r, 
                          IEateries eateries, 
                          int aveNumOfTicksToNextPerson, 
                          int averageEateryTime, 
                          int aveCashierTime) {
    		
        this.r = r;
        this.eateries = eateries;
        this.aveNumOfTicksToNextPerson = aveNumOfTicksToNextPerson;
        this.averageEateryTime = averageEateryTime;
        this.aveCashierTime = aveCashierTime;
    }
    	
    public void event(int tick) {
        if (tick >= ticksToNextPerson) {
            ticksToNextPerson = tick + 
                                Gauss.get(r, aveNumOfTicksToNextPerson);
            
            Person person = new Person();
            
            // set how long the person will remain 
            // at the counter of the eatery 
            // when he reaches it
            person.setCashierTime(Gauss.get(r, aveCashierTime));
            person.setEateryTime(Gauss.get(r, averageEateryTime));
            person.setLeaveTime(Gauss.get(r, aveMaxTime));

          //  person.setTickTime(tick);
            // enqueue the person to a random eatery's queue 
            eateries.add(person);
            
        //	person.setDestination(theLocationAfterTheEatery);  // You can save off where the person should go.
        }
    }
    
}
