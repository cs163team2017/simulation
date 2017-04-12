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
    	
    private double ticksToNextPerson;
    private IEateries eateries;
    private int aveNumOfTicksToNextPerson;
    private int averageEateryTime;
    private int aveCashierTime;
    private int aveLeaveTime;
    	
    private Random r;
    	
    public PersonProducer(Random r, 
                          IEateries eateries, 
                          int aveNumOfTicksToNextPerson, 
                          int aveCashierTime,
                          int averageEateryTime, 
                          int aveLeaveTime) {
    		
        this.r = r;
        this.eateries = eateries;
        this.aveNumOfTicksToNextPerson = aveNumOfTicksToNextPerson;
        this.aveCashierTime = aveCashierTime;
        this.averageEateryTime = averageEateryTime;
        this.aveLeaveTime = aveLeaveTime;
        ticksToNextPerson = 0;
    }
    	
    public void event(int tick) {
        if (tick >= ticksToNextPerson) {
            ticksToNextPerson = tick + 
                                Gauss.get(r, aveNumOfTicksToNextPerson);
            
            Person person = new Person();
            
            // set how long the person will remain 
            // at the counter of the eatery 
            // when he reaches it
            person.setCreationTime(tick);
            person.setCashierTime(Gauss.get(r, aveCashierTime));
            person.setEateryTime(Gauss.get(r, averageEateryTime));
            person.setLeaveTime(Gauss.get(r, aveLeaveTime));

          //  person.setTickTime(tick);
            // enqueue the person to a random eatery's queue 
            eateries.add(person);
            
        //	person.setDestination(theLocationAfterTheEatery);  // You can save off where the person should go.
        }
    }
    
}
