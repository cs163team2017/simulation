package Simulation;

public class LimitedTimePerson extends Person {

    /** modifier to increase time this person spends at cashier */
    private final double CASHIER_TIME_MODIFIER = 1;
    /** modifier to increase the time until this person exits the sim */
    private final double LEAVE_TIME_MODIFIER = 0.5;
    /** modifier to increase time this person spends at the eatery */
    private final double EATERY_TIME_MODIFIER = 0.5;
    
    public LimitedTimePerson(double eateryTime, double leaveTime,
            double cashierTime, int creationTime) {
        super(eateryTime, leaveTime, cashierTime, creationTime);
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public void setEateryTime(double time) {
        eateryTime = time * EATERY_TIME_MODIFIER;
    }
        
    @Override
    public void setLeaveTime(double time) {
        leaveTime = time * LEAVE_TIME_MODIFIER;
    }
        
    @Override
    public void setCashierTime(double time) {
        cashierTime = time * CASHIER_TIME_MODIFIER;
    }
}
