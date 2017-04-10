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
    private Eateries eateries;
    private int aveNumOfTicksToNextPerson;
    private int numOfTicksToNextPerson;
    private int averageEateryTime;
    private int aveCashierTime;
    private int aveMaxTime;
    	
    private Random r;
    	
    public PersonProducer(Random r, 
                          Eateries eateries, 
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
            //roll a random number to determine what type of person will be produced
            // 10% will be special needs 
            // 20% will be limited time
            // the rest are regular 
            int personCheck = r.nextInt(100);
            if(r <= 10)
            {
            	person.setEateryTime(Gauss.get(r, averageEateryTime * SpecialNeedsPerson.EATERY_TIME_MULTIPLIER));
            	person.setLeaveTime(Gauss.get(r, aveMaxTime * SpecialNeedsPerson.LEAVE_TIME_MULTIPLIER));
            	person.setCashierTime(Gauss.get(r, aveCashierTime * SpecialNeedsPerson.CASHIER_TIME_MULTIPLIER));
        	}
        	else if( r <=20)
        	{
        		person.setEateryTime(Gauss.get(r, averageEateryTime * LimitedTimePerson.EATERY_TIME_MULTIPLIER));
            	person.setLeaveTime(Gauss.get(r, aveMaxTime * LimitedTimePerson.LEAVE_TIME_MULTIPLIER));
        	}
        	else{
        		person.setEateryTime(Gauss.get(r, averageEateryTime));
            	person.setLeaveTime(Gauss.get(r, aveMaxTime));
            	person.setCashierTime(Gauss.get(r, aveCashierTime));
        	}
            person.setTickTime(tick);
            // enqueue the person to a random eatery's queue 
            eateries.add(person);
            
        //	person.setDestination(theLocationAfterTheEatery);  // You can save off where the person should go.
        }
    }
    
}
