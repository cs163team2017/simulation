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
    /** weight of normal person type */
    private int regular;
    /** weight of special person type */
    private int special;
    /** weight of limited person type */
    private int limited;
    	
    /****************************************************************** 
     * instantiate a new person producer
     * @param r the random instance for the entire simulation
     * @param eateries eateries this producer should push new people to
     * @param aveNumOfTicksToNextPerson time to produce another person
     * @param aveCashierTime time for a person to clear the register
     * @param averageEateryTime time for a person to clear an eatery
     * @param aveLeaveTime time before a person exits the system
     ******************************************************************/
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
        regular = 7;
        special = 1;
        limited = 2;
    }
    
    public PersonProducer(Random r, 
            IEateries eateries, 
            int aveNumOfTicksToNextPerson, 
            int aveCashierTime,
            int averageEateryTime, 
            int aveLeaveTime, 
            int special,
            int limited,
            int regular) {
  
        this.r = r;
        this.eateries = eateries;
        this.avgNumOfTicksToNextPerson = aveNumOfTicksToNextPerson;
        this.avgCashierTime = aveCashierTime;
        this.avgEateryTime = averageEateryTime;
        this.avgLeaveTime = aveLeaveTime;
        ticksToNextPerson = 0;
        this.special = special;
        this.limited = limited;
        this.regular = regular;
    }
    
    /******************************************************************
     * Instantiates a new random person of different types based on
     * the default, or custom weights set in the class constructor
     * @return the new person 
     *****************************************************************/
    public Person randomPerson(double eateryTime, 
                               double leaveTime, 
                               double cashierTime,
                               int creationTime) {
        // you can get the class with "X instance of Y" comparison 
        // or X.getClass() method
        int next = r.nextInt(special + limited + regular);

        if (next < special) {
            return new SpecialNeedsPerson(eateryTime, 
                                          leaveTime, 
                                          cashierTime, 
                                          creationTime);
        }
        if (next < (special + limited)) {
            return new LimitedTimePerson(eateryTime, 
                                         leaveTime, 
                                         cashierTime, 
                                         creationTime);
        }
        return new RegularPerson(eateryTime, 
                                 leaveTime, 
                                 cashierTime, 
                                 creationTime);
    }
    	
    /*
     * (non-Javadoc)
     * @see Simulation.ClockListener#event(int)
     */
    @Override
    public void event(int tick) {
        if (tick >= ticksToNextPerson) {
            ticksToNextPerson = tick + 
                                Gauss.get(r, avgNumOfTicksToNextPerson);
            // set how long the person will remain 
            // at the counter of the eatery, or cashier, 
            // when he reaches it, or stay in the system
            Person person = randomPerson(Gauss.get(r, avgEateryTime), 
                                         Gauss.get(r, avgLeaveTime), 
                                         Gauss.get(r, avgCashierTime), 
                                         tick);
            
            // enqueue the person to a random eatery's queue 
            eateries.enQ(person);
        }
    }
}
