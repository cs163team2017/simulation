package Simulation;

public class SpecialNeedsPerson extends Person {
    /** modifier to increase time this person spends at cashier */
    private final double CASHIER_TIME_MODIFIER = 2;
    /** modifier to increase the time until this person exits the sim */
    private final double LEAVE_TIME_MODIFIER = 3;
    /** modifier to increase time this person spends at the eatery */
    private final double EATERY_TIME_MODIFIER = 4;
    
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
