package Simulation;
/*********************************************************
*@author Caitlin Heyn
*A class that defines a special needs person who needs 
*more time to make it through each phase of the eatery
*and its queues.
*
*
***********************************************************/

public class SpecialNeedsPerson extends Person{
	final static int CASHIER_TIME_MULTIPLIER = 2;
	final static int LEAVE_TIME_MULTIPLIER = 3;
	final static int EATERY_TIME_MULTIPLIER = 4;
	public SpecialNeedsPerson(){
		super();
		
	}
}