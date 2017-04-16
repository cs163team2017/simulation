package Simulation;

import java.util.Random;

/**
 * Class that creates new people and begins their journeys through 
 * the simulation 
 * @author Roger Ferguson
 * @author Matthew Pische
 */
public class PersonProducer implements ClockListener {
    /** when the next person should be instantiated */
    private double ticksToNextPerson;
    /** the set of eateries to pass new people off to */
    private IEateries eateries;
    /** how often should new people be produced */
    private int avgNumOfTicksToNextPerson;
    /** how long should people take to clear an eatery */
    private int avgEateryTime;
    /** how long should people take to clear the cashier */
    private int avgCashierTime;
    /** how long should people remain in the system */
    private int avgLeaveTime;
    /** the random instance for the simulation */	
    private Random r;
    	
    /** 
     * instantiate a new person producer
     * @param r the random instance for the entire simulation
     * @param eateries eateries this producer should push new people to
     * @param aveNumOfTicksToNextPerson time to produce another person
     * @param aveCashierTime time for a person to clear the register
     * @param averageEateryTime time for a person to clear an eatery
     * @param aveLeaveTime time before a person exits the system
     */
    public PersonProducer(Random r, 
                          IEateries eateries, 
                          int aveNumOfTicksToNextPerson, 
                          int aveCashierTime,
                          int averageEateryTime, 
                          int aveLeaveTime) {
    		
        this.r = r;
        this.eateries = eateries;
        this.avgNumOfTicksToNextPerson = aveNumOfTicksToNextPerson;
        this.avgCashierTime = aveCashierTime;
        this.avgEateryTime = averageEateryTime;
        this.avgLeaveTime = aveLeaveTime;
        ticksToNextPerson = 0;
    }
    	
    public void event(int tick) {
        if (tick >= ticksToNextPerson) {
            ticksToNextPerson = tick + 
                                Gauss.get(r, avgNumOfTicksToNextPerson);
            
            Person person = new Person();
            
            // record the current moment of instantiation 
            person.setCreationTime(tick);
            person.setCashierTime(Gauss.get(r, avgCashierTime));
            // set how long the person will remain 
            // at the counter of the eatery 
            // when he reaches it
            person.setEateryTime(Gauss.get(r, avgEateryTime));
            person.setLeaveTime(Gauss.get(r, avgLeaveTime));

            // enqueue the person to a random eatery's queue 
            eateries.enQ(person);
        }
    }
    
}
