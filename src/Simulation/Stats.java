package Simulation;

public class Stats {
	public static int inflow;
	public static int cashierTime;
	public static int runtime;
	public static int avgEateryTime;
	public static int quitTime;
	public static int numEaterys = 3; //FIXME remove later RC3
	public static int numCheckouts = 2; //FIXME remove later RC3


	public static int getInflow() {
		return inflow;
	}


	public static void setInflow(int inflow) {
		Stats.inflow = inflow;
	}


	public static int getCashierTime() {
		return cashierTime;
	}


	public static void setCashierTime(int cashierTime) {
		Stats.cashierTime = cashierTime;
	}


	public static int getRuntime() {
		return runtime;
	}


	public static void setRuntime(int runtime) {
		Stats.runtime = runtime;
	}


	public static int getAvgEateryTime() {
		return avgEateryTime;
	}


	public static void setAvgEateryTime(int avgEateryTime) {
		Stats.avgEateryTime = avgEateryTime;
	}


	public static int getQuitTime() {
		return quitTime;
	}


	public static void setQuitTime(int quitTime) {
		Stats.quitTime = quitTime;
	}


	public static int getNumEaterys() {
		return numEaterys;
	}


	public static void setNumEaterys(int numEaterys) {
		Stats.numEaterys = numEaterys;
	}


	public static int getNumCheckouts() {
		return numCheckouts;
	}


	public static void setNumCheckouts(int numCheckouts) {
		Stats.numCheckouts = numCheckouts;
	}

}
