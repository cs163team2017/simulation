/**
 * 
 */
package Simulation;

/**
 * @author   Roger Ferguson
 */
public class Clock {

	private ClockListener[] myListeners;
	private int numListeners;
	private int MAX = 100;
	private int currTick = 0;

	public Clock() {
		numListeners = 0;
		myListeners = new ClockListener[MAX];
	}

	public void run(int endingTime) {
		for (int currentTime = 0; currentTime <= endingTime; currentTime++) {
			for (int j = 0; j < numListeners; j++)
				myListeners[j].event(currentTime);
		}
	}
	
	public void tock(){
		for (int j = 0; j < numListeners; j++){
			myListeners[j].event(currTick++);
			if (myListeners[j] instanceof Eateries){		
				int i = 0;
				for (IEatery E: (IEateries) myListeners[j]){
					Stats.pplAtEatery[i] = E.getLeft();
					i++;
				}
			}	
			if (myListeners[j] instanceof MainQ){		
					Stats.pplAtMainQ = ((MainQ) myListeners[j]).getLeft();
			}
//			if (myListeners[j] instanceof Cashiers){		
//				int i = 0;
//				for (ICashier C: (ICashiers) myListeners[j]){
//					Stats.pplAtCheckout[i] = C.getLeft();
//					i++;
//				}
//			}
		}
		Stats.currTime = currTick;
	}

	public void add(ClockListener cl) {
		myListeners[numListeners] = cl;
		numListeners++;
	}

	public ClockListener[] getMyListeners() {
		return myListeners;
	}

	public void setMyListeners(ClockListener[] myListeners) {
		this.myListeners = myListeners;
	}

	public int getNumListeners() {
		return numListeners;
	}

	public void setNumListeners(int numListeners) {
		this.numListeners = numListeners;
	}

	public int getMAX() {
		return MAX;
	}

}
