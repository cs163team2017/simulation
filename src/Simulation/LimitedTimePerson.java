package Simulation;

/*********************************************************
*@author Caitlin Heyn
*A class that defines a person in a hurry who needs 
*to be helped quickly or they will leave to make it through 
*each phase of the eatery and its queues.
*
*
***********************************************************/

public class LimitedTimePerson extends Person{
	FINAL STATIC int LEAVE_TIME_MULTIPLIER = 0.5;
	FINAL STATIC int EATERY_TIME_MULTIPLIER = 0.5;

	public LimitedTimePerson(){
	super();
	}
}