package Simulation;

public class SpecialNeedsPerson extends Person {
    /** modifier to increase time this person spends at cashier */
    private final static double CASHIER_TIME_MODIFIER = 2;
    /** modifier to increase the time until this person exits the sim */
    private final static double LEAVE_TIME_MODIFIER = 3;
    /** modifier to increase time this person spends at the eatery */
    private final static double EATERY_TIME_MODIFIER = 4;
    
    public SpecialNeedsPerson(double eateryTime, double leaveTime,
            double cashierTime, int creationTime) {
        super(eateryTime * EATERY_TIME_MODIFIER, 
              leaveTime * LEAVE_TIME_MODIFIER, 
              cashierTime * CASHIER_TIME_MODIFIER, 
              creationTime);
    }
}
