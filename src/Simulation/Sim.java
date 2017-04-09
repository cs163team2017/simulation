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
    MainQ main = new MainQ();
    Eateries eateries = new Eateries(rand);
    Cashiers cashiers = new Cashiers(rand);
    eateries.add();
    int numTicks2Person = 20; 
    int aveEateryTime = 20;
    int aveCashierTime = 20;
    	
    PersonProducer producer = new PersonProducer(
                                    rand, 
                                    eateries, 
                                    numTicks2Person, 
                                    aveEateryTime);	
    clk.add(producer);
    clk.add(eateries);
    clk.add(cashiers);
    	
    clk.run(10000);
    	
    System.out.println("Eateries total throughput is: " + 
                        eateries.getThroughPut() + 
                        " people.");
    System.out.println("Eateries Ppl still Queued: " + 
                        eateries.getLeft() + ".");
    System.out.println ("Eateries Maximum people Queued: " + 
                        eateries.getMaxQlength() + 
                        ".");
    System.out.println("Cashiers total throughput is: " +
                       cashiers.getThroughPut() +
                       " people.");
    System.out.println("Cashiers Ppl still Queued: " +
                       cashiers.getLeft() + 
                       ".");
    System.out.println("Cashiers maximum people queued: " +
                       cashiers.getMaxQlength() + 
                       ".");
    }
}
