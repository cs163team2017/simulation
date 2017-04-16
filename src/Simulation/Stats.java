package Simulation;

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
	public static int runtime = 100;
	public static int quitTime = 100;
    public static int pplAtMainQ;
    public static int[] pplAtEatery = new int [numEaterys];
    public static int[] pplAtCheckout = new int[numCheckouts];
}
