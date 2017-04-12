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
    MainQ mainQ = new MainQ();
    Eateries eateries = new Eateries(rand, mainQ);
    Cashiers cashiers = new Cashiers(rand);
    eateries.add();
    eateries.add();
    int numTicks2Person = 20; 
    int aveEateryTime = 20;
    int aveCashierTime = 60;
    int aveLeaveTime = 100;
    	
    PersonProducer producer = new PersonProducer(
                                    rand, 
                                    eateries, 
                                    numTicks2Person, 
                                    aveCashierTime,
                                    aveEateryTime,
                                    aveLeaveTime);	
    mainQ.setCashiers(cashiers);
    clk.add(producer);
    clk.add(eateries);
    clk.add(mainQ);
    clk.add(cashiers);
    
    clk.run(10000);
    
    System.out.println("___EATERIES___\n");
    System.out.println("Eateries total throughput is: " + 
                        eateries.getThroughPut() + 
                        " people.");
    System.out.println("Eateries Ppl still Queued: " + 
                        eateries.getLeft() + ".");
    System.out.println ("Eateries Maximum people Queued: " + 
                        eateries.getMaxQlength() + 
                        ".");
    System.out.println("Eateries people lost: " + 
                        eateries.getLost() + ".\n");
    System.out.println("___MAIN QUEUE___\n");
    System.out.println("MainQ throughput is: "  + 
                        mainQ.getThroughPut() + ".");
    System.out.println("MainQ people still Queued: " + 
                        mainQ.getLeft() + ".");
    System.out.println("MainQ maximum queue size: " + 
                        mainQ.getMaxQlength() + ".");
    System.out.println("MainQ people lost: " + 
                        mainQ.getLost() + ".\n");
    System.out.println("___CASHIERS___\n");
    System.out.println("Cashiers total throughput is: " +
                       cashiers.getThroughPut() +
                       " people.");
    System.out.println("Cashiers Ppl still Queued: " +
                       cashiers.getLeft() + 
                       ".");
    System.out.println("Cashiers maximum people queued: " +
                       cashiers.getMaxQlength() + 
                       ".");
    System.out.println("Cashiers people lost: " + 
                       cashiers.getLost() + ".");
    }
}
