/**
 * 
 */
package Simulation;

/**
 * @author   Roger Ferguson
 * @author Matthew Pische
 */
public class Person {
//	private int tickTime;
//	private Eatery Destination;
	
	/** time person stays at eatery counter */
	protected double eateryTime;
	/** max time person will remain in system */
	protected double leaveTime;
	/** time person stays at cashier counter */
	protected double cashierTime;
	/** the tick the person was instantiated */
	protected int creationTime;
	/** the list node that currently holds this person */
	protected ListNode<Person> holdingNode;
	
	public Person() {
//	    super();
//	    holdingNode = null;
	}
		
	/**
	 * the time the person will remain at the eatery counter
	 * @return
	 */
	public double getEateryTime() {
		return eateryTime;
	}
	
//	public Eatery getDestination() {
//		return Destination;
//	}
//
//	public void setDestination(Eatery destination) {
//		Destination = destination;
//	}
	
//	public int getTickTime() {
//		return tickTime;
//	}
//
//	public void setTickTime(int tickTime) {
//		this.tickTime = tickTime;
//	}

	public void setEateryTime(double time) {
		this.eateryTime = time;
	}
	
	public void setLeaveTime(double time) {
	    leaveTime = time;
	}
	
	public void setCashierTime(double time) {
	    cashierTime = time;
	}
	
	public void setCreationTime(double time) {
	    creationTime = (int)time;
	}
	
	/**
	 *  the maximum time the person will remain in the system 
	 * @return
	 */
	public double getLeaveTime() {
	    return leaveTime + creationTime;
	}
	
	/**
	 * how long the person will remain at the cashier counter
	 * @return
	 */
	public double getCashierTime() {
	    return cashierTime;
	}
	
	public int getCreationTime() {
	    return creationTime;
	}
	
//	public void setNode(ListNode<Person> n) {
//	    holdingNode = n;
//	}
//	
//	public ListNode<Person> getNode() {
//	    return holdingNode;
//	}
}
