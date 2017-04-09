/**
 * 
 */
package Simulation;

/**
 * @author   Roger Ferguson
 * @author Matthew Pische
 */
public class Person {
	private int tickTime;
	private Eatery Destination;
	
	// max time person stays in line
	protected double boothTime;
	protected double leaveTime;
	protected double cashierTime;
	/** the list node that currently holds this person */
	protected ListNode<Person> holdingNode;
	
	public Person() {
	    super();
	    holdingNode = null;
	}
		
	public double getBoothTime() {
		return boothTime;
	}
	
	public Eatery getDestination() {
		return Destination;
	}

	public void setDestination(Eatery destination) {
		Destination = destination;
	}
	
	public int getTickTime() {
		return tickTime;
	}

	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}

	public void setEateryTime(double time) {
		this.boothTime = time;
	}
	
	public void setLeaveTime(double time) {
	    leaveTime = time;
	}
	
	public void setCashierTime(double time) {
	    cashierTime = time;
	}
	
	public void setNode(ListNode<Person> n) {
	    holdingNode = n;
	}
	
	public ListNode<Person> getNode() {
	    return holdingNode;
	}
}
