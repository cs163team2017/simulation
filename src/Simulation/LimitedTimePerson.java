package Simulation;

public class LimitedTimePerson extends Person {

    /** modifier to increase time this person spends at cashier */
    private static final double CASHIER_TIME_MODIFIER = 1;
    /** modifier to increase the time until this person exits the sim */
    private static final double LEAVE_TIME_MODIFIER = 0.5;
    /** modifier to increase time this person spends at the eatery */
    private static final double EATERY_TIME_MODIFIER = 0.5;
    
    public LimitedTimePerson(double eateryTime, 
                             double leaveTime, 
                             double cashierTime, 
                             int creationTime) {
        super(eateryTime * EATERY_TIME_MODIFIER, 
              leaveTime * LEAVE_TIME_MODIFIER, 
              cashierTime * CASHIER_TIME_MODIFIER, 
              creationTime);
    }

}
