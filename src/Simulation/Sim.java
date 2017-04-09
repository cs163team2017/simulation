/**
 * 
 */
package Simulation;

import java.util.Random;

/**
 * @author Roger Ferguson
 * @author Matthew Pische
 */
public class Sim {
	    
	public static void main (String[] args) {
	                // make a single random instance, so that
	                // you can pass it down into everything
	                // & thus you can give it a single seed value
	                // causing the entire sim to be deterministic
			Random rand = new Random();
			Clock clk = new Clock();
			Eateries eateries = new Eateries(rand);
			eateries.add();
			int numTicks2Person = 20; 
			int aveEateryTime = 20;
			
			PersonProducer producer = new PersonProducer(
			                                rand, 
			                                eateries, 
			                                numTicks2Person, 
			                                aveEateryTime);	
			clk.add(producer);
			clk.add(eateries);
			
			clk.run(10000);
			
			System.out.println("Through put is: " + 
			                    eateries.getThroughPut() + 
			                    " people.");
			System.out.println("People still Queued: " + 
			                    eateries.getLeft() + ".");
			System.out.println ("Maximum people Queued: " + 
			                    eateries.getMaxQlength() + 
			                    ".");
		}
	}
