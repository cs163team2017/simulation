/**
 * 
 */
package Simulation;

/**
 * @author Roger Ferguson
 * @author Matthew Pische
 */
public class Person {
    	
    /** time person stays at eatery counter */
    protected double eateryTime;
    /** max time person will remain in system */
    protected double leaveTime;
    /** time person stays at cashier counter */
    protected double cashierTime;
    /** the tick the person was instantiated */
    protected int creationTime;
    
    public Person() {
        
    }
    
    public Person(double eateryTime, 
                  double leaveTime, 
                  double cashierTime,
                  int creationTime) {
        this.eateryTime = eateryTime;
        this.leaveTime = leaveTime;
        this.cashierTime = cashierTime;
        this.creationTime = creationTime;
    }
    	
    /******************************************************************
     * fetches the time the person will remain at the eatery counter
     * @return the time a person will remain at the kiosk
     ******************************************************************/
    public double getEateryTime() {
    	return eateryTime;
    }
    
    /******************************************************************
     * sets how long the person will stay at the eatery kiosk
     * @param time delay at the eatery kiosk
     ******************************************************************/
    public void setEateryTime(double time) {
    	this.eateryTime = time;
    }
    	
    /******************************************************************
     * sets the maximum amount of time the person 
     * will remain in the system before exiting 
     * @param time maxium duration until exit
     ******************************************************************/
    public void setLeaveTime(double time) {
        leaveTime = time;
    }
    	
    /******************************************************************
     * sets how long the person will take at the cashier register
     * before successfully completing checkout
     * @param time person will stay at cashier before successful exit
     ******************************************************************/
    public void setCashierTime(double time) {
        cashierTime = time;
    }
    	
    /******************************************************************
     * sets the time of the person's creation
     * @param time tick the person was instantiated 
     *****************************************************************/
    public void setCreationTime(double time) {
        creationTime = (int)time;
    }
    	
    /******************************************************************
     *  the maximum time the person will remain in the system 
     * @return time person will remain in the system before exit
     ******************************************************************/
    public double getLeaveTime() {
        return leaveTime + creationTime;
    }
    	
    /******************************************************************
     * fetches how long the person will remain at the cashier counter
     * @return time at the cashier 
     ******************************************************************/
    public double getCashierTime() {
        return cashierTime;
    }
    	
    /******************************************************************
     * fetches the time at which this person was instantiated
     * @return the tick at which this person was instantiated
     ******************************************************************/
    public int getCreationTime() {
        return creationTime;
    }
}
