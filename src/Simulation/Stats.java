package Simulation;

import java.util.ArrayList;

/***************************************************************************
 * Class that holds a plethora of stats
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class Stats {
	//Independent Variables
	/**Average time for a customer to walk in**/
	public static int inflow = 2;
	/**Average time for a cashier to service**/
	public static int cashierTime = 10;
	/**Average time for a eatery to service**/
	public static int avgEateryTime = 10;
	/**Number of eateries**/
	public static int numEaterys = 5;
	/**Number of checkouts**/
	public static int numCheckouts = 2;
	/**Current Tick**/
	public static int currTime = 0;
	/**Total number of ticks to run**/
	public static int runtime = 5000;
	/**Average time for a person to leave the sim**/
	public static int quitTime = 900;
	/**number of people at the mainQ**/
    public static int pplAtMainQ;
    /**Number of people at an Eater(index)**/
    public static  ArrayList<Integer> pplAtEatery = new ArrayList<Integer>();
    /**Number of people at a checkout(index)**/
    public static  ArrayList<Integer> pplAtCheckout = new ArrayList<Integer>();
    
    //Dependent Variables
    /**Number of people that went through all the Eateries**/
    public static int eateriesThru = 0;
    /**Number of people that went through the Main Queue**/
    public static int mainQThru = 0;
    /**Number of people that went through the Cashiers**/
    public static int cashiersThru = 0;
    /**Number of people that are in lines at all the Eateries**/
    public static int inEateryLines = 0;//
    /**Number of people that are in line at the Main Queue**/
    public static int inCashierLines = 0;
    /**Longest length that the mainQ ever was**/
    public static int maxMainQLine = 0;
    /**Longest length any eatery line was**/
    public static int maxEateryLine = 0;
    /**Total number of people that left**/
    public static int totalLost = 0;
    /**Total number of people that left while in line at an Eatery**/
    public static int totalLostAtEatery = 0;
    /**Total number of people that left while in line at the Main Queue**/
    public static int totalLostAtMainQ = 0;
    /**The average time it takes for a person to complete the simulation**/
    public static double avgTimeToSucceed = 0.0;
    /**The Success percent rate (Succeeded/All)**/ 
    public static double successRate = 0.0;
    
    /***********************************************************************
     * Updates all the dependent statistics
     ***********************************************************************/
    public static void updateAll(){
    	calcTotalLost();
    	calcAverageTimeToSucceed();
    	calcSuccessRate();
    }
    
    public static void clear(){
    	pplAtEatery.clear();
    	pplAtCheckout.clear();
    	currTime = 0;
    	avgTimeToSucceed = 0.0;
    	successRate = 0.0;
    } 
    
    /***********************************************************************
     * Calculates the number of people that went through all the eateries
     ***********************************************************************/
    
    public static void calcTotalLost(){
    	totalLost = totalLostAtEatery + totalLostAtMainQ;
    }
    
    
    public static void calcAverageTimeToSucceed() {
    	avgTimeToSucceed = (cashiersThru + 0.0) / currTime;
    }
    
    public static void calcSuccessRate() {
        successRate = (cashiersThru + 0.0) / 
        		(totalLostAtEatery + totalLostAtMainQ);
    }
  

}
