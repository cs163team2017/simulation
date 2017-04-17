package Simulation;

import java.util.ArrayList;
import java.util.Arrays;

/**********************************************************************
 * @author Roger Ferguson
 * @author Richard Critchlow
 * @author Matthew Pische
 **********************************************************************/
public class Clock { 
    /** array of elements that listen for the clock's ticks */
    private ClockListener[] myListeners;
    /** the number of listening elements */
    private int numListeners;
    /** arbitrary limit on the amount of elements that can listen for 
     * clock ticks
     */
    private int MAX = 100;
    /** the current tick of the clock */
    private int currTick;
    
    /******************************************************************
     * instantiate a default empty clock
     ******************************************************************/
    public Clock() {
        currTick = 0;
        numListeners = 0;
        myListeners = new ClockListener[MAX];
    }
    
    /****************************************************************** 
     * run a set number of clock ticks
     * @param endingTime total number of ticks 
     ******************************************************************/
    public void run(int endingTime) {
        for (int currentTime = 0; 
                currentTime <= endingTime; currentTime++) {
            for (int j = 0; j < numListeners; j++)
                myListeners[j].event(currentTime);
        }
    }
    	
    /**
     * run a single tick and increment the clock by one
     */
    public void tock(){
        for (int j = 0; j < numListeners; j++){
            myListeners[j].event(currTick);
            if (myListeners[j] instanceof Eateries){		
                int i = 0;
                try{
                	for (IEatery E: (IEateries) myListeners[j]){
                			Stats.pplAtEatery.set(i, E.getLeft());
                			i++;
                	}
                }
                catch(IndexOutOfBoundsException e){
                	for (Integer I: Stats.pplAtEatery)
                		I = 0;
                }
            }	
            if (myListeners[j] instanceof MainQ){		
                Stats.pplAtMainQ = ((MainQ) myListeners[j]).getLeft();
            }
            if (myListeners[j] instanceof Cashiers){		
                int i = 0;
                try{
                	for (ICashier C: (ICashiers) myListeners[j]){
                		Stats.pplAtCheckout.set(i,C.getLeft());
                		i++;
                	}
                }
                catch(IndexOutOfBoundsException e){
                	for (Integer I: Stats.pplAtCheckout)
                		I = 0;
                }
            }
        }
        currTick++;
        Stats.currTime = currTick;
    }
    
    /******************************************************************
     * add a listener to the current clock
     * @param cl
     ******************************************************************/
    public void add(ClockListener cl) {
        myListeners[numListeners] = cl;
        numListeners++;
    }
    
    /******************************************************************
     * returns the array of all elements currently listening 
     * to the clock
     * @return array of listeners
     *****************************************************************/
    public ClockListener[] getMyListeners() {
        return myListeners;
    }
    
    /******************************************************************
     * set all clock listeners to those contained in an existing array
     * @param myListeners array of listeners
     ******************************************************************/
    public void setMyListeners(ClockListener[] myListeners) {
        this.myListeners = myListeners;
    }
    
    /******************************************************************
     * fetch the number of elements listening to the current clock
     * @return current number of listeners
     ******************************************************************/
    public int getNumListeners() {
        return numListeners;
    }
    
    /******************************************************************
     * sets the number of listening elements
     * @param numListeners
     ******************************************************************/
    public void setNumListeners(int numListeners) {
        this.numListeners = numListeners;
    }
    
    /******************************************************************
     * fetch the maximum number of elements that can listen to the clock
     * @return maximum listenes
     ******************************************************************/
    public int getMAX() {
        return MAX;
    }
    	
    /**************************************************************
     * removes all current listeners form this clock & resets it
     **************************************************************/
    public void clear() {
        currTick = 0;
        numListeners = 0;
        Arrays.fill(myListeners, null);
    }
    
    /******************************************************************
     * fetch the total set of all people enqueued at all eateries
     * @return arraylist of arraylist of all people, the outer list
     * holds a list of its corresponding eatery's people
     *****************************************************************/
    public ArrayList<ArrayList<Person>> getEateryPeople() {
        if (myListeners[1] == null) {
           throw new RuntimeException("no eateries at index 1");
        }
        ArrayList<ArrayList<Person>> output = 
                                    new ArrayList<ArrayList<Person>>();
        IEateries es = (IEateries) myListeners[1];
        int i = 0;
        for (IEatery e : es) {
            output.add(new ArrayList<Person>());
            for (Person p : e) {
                output.get(i).add(p);
            }
            i++;
        }
        return output;
    }
    
    /******************************************************************
     * fetches an arraylist of all people currently being served by 
     * the cashiers. Null represents an array that's emtpy
     * @return an arraylist of people currently being served, null 
     * values indicate an empty cashier at that position
     *****************************************************************/
    public ArrayList<Person> getCashierPeople() {
        if (myListeners[3] == null) {
            throw new RuntimeException("no cashiers at index 3");
        }
        ArrayList<Person> output = new ArrayList<Person>();
        ICashiers cs = (ICashiers) myListeners[3];
        for (ICashier c : cs) {
            output.add(c.peek());
        }
        return output;
    }
    
    /******************************************************************
     * fetches an arraylist of all people currently in the mainQ
     * @return all people in the mainQ
     *****************************************************************/
    public ArrayList<Person> getMainQPeople() {
        if (myListeners[2] == null) {
            throw new RuntimeException("no mainQ at index 2");
        }
        ArrayList<Person> output = new ArrayList<Person>();
        IMainQ q = (IMainQ) myListeners[2];
        for (Person p : q) {
            output.add(p);
        }
        return output;
    }
}
