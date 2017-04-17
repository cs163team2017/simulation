package Simulation;

import java.util.ArrayList;

/***************************************************************************
 * Class that holds a plethora of stats
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class Stats {
	public static int inflow = 2;
	public static int cashierTime = 15;
	public static int avgEateryTime = 10;
	public static int numEaterys = 3; 
	public static int numCheckouts = 2;
	public static int currTime = 0;
	public static int runtime = 300;
	public static int quitTime = 50;
    public static int pplAtMainQ;
    public static  ArrayList<Integer> pplAtEatery = new ArrayList<Integer>();
    public static  ArrayList<Integer> pplAtCheckout = new ArrayList<Integer>();
  
    public static void clear(){
    	pplAtEatery.clear();
    	pplAtCheckout.clear();
    	currTime = 0;
    }
}
