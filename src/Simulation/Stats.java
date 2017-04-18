package Simulation;

import java.text.DecimalFormat;
import java.util.ArrayList;

/***************************************************************************
 * Class that holds a plethora of stats
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class Stats {
	public static int inflow = 2;
	public static int cashierTime = 10;
	public static int avgEateryTime = 10;
	public static int numEaterys = 5; 
	public static int numCheckouts = 2;
	public static int currTime = 0;
	public static int runtime = 5000;
	public static int quitTime = 900;
    public static int pplAtMainQ;
    public static  ArrayList<Integer> pplAtEatery = new ArrayList<Integer>();
    public static  ArrayList<Integer> pplAtCheckout = new ArrayList<Integer>();
    // collect:
    // total throughput for system and each 3 components
    // average time per person for system to current tick
    // people remaining at each 3 components
    // total people lost
    public static int eateriesThru = 0;
    public static int mainQThru = 0;
    public static int cashiersThru = 0;
    public static int eateriesLeft = 0;
    public static int mainQLeft = 0;
    public static int cashiersLeft = 0;
    public static int eateriesLost = 0;
    public static int mainQLost = 0;
    
    public static String averageTimePerTotalSuccess() {
        double r = (cashiersThru + 0.0) / currTime;
        return new DecimalFormat("#.##").format(r);
    }
    
    public static String successesOverFailures() {
        double r = (cashiersThru + 0.0) / (eateriesLost + mainQLost);
        return new DecimalFormat("#.##").format(r);
    }
  
    public static void clear(){
    	pplAtEatery.clear();
    	pplAtCheckout.clear();
    	currTime = 0;
    }
}
