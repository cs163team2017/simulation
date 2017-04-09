/**
 * 
 */
package Simulation;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Roger Ferguson
 *
 */
public class Sim {
	
        private static Eatery randEatery(ArrayList<Eatery> a) {
            Random r = new Random();
            return a.get(r.nextInt(a.size()));
        }
    
	public static void main (String[] args) {
			
			Clock clk = new Clock();
			Eatery e = new Eatery();
			ArrayList<Eatery> booths = new ArrayList<Eatery>();
			booths.add(e);
			Eatery booth = randEatery(booths);
			// 		int numOfTicksNextPerson = 20 
			//      int averageBoothTime = 20
			
			PersonProducer produce = new PersonProducer(booth, 20, 18);	
			clk.add(produce);
			clk.add(booth);
			
			clk.run(10000);
			
			System.out.println("Through put is: " + booth.getThroughPut() + " people.");
			System.out.println("People that are still in the Q:" + booth.getLeft() + " people.");
			System.out.println ("Max Q length:" + booth.getMaxQlength() + " people.");
		}
	}
