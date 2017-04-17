package Simulation.Tests;
import java.util.Random;

import org.junit.Test;

import Simulation.Cashier;
import Simulation.Cashiers;
import Simulation.Clock;
import Simulation.Eateries;
import Simulation.MainQ;
import Simulation.Person;
import Simulation.PersonProducer;

/**********************************************************************
 * Test of the simulation
 * @author Matthew Pische
 *
 *********************************************************************/
public class IntegrationTests {
    public Person p1,p2,p3,p4,p5;
    public Random r;
    public Clock clk;
    public MainQ mainQ;
    public Eateries eateries;
    public Cashiers cashiers;
    public PersonProducer producer;
    public int ticksToPerson;
    public int avgEateryTime;
    public int avgCashierTime;
    public int avgLeaveTime;
    
    @Test
    public void ItWorks() {
        for (int i = 0; i < 500; i++) {
            r = new Random();
            clk = new Clock();
            mainQ = new MainQ();
            eateries = new Eateries(r, mainQ);
            cashiers = new Cashiers();
            cashiers.add(new Cashier());
            cashiers.add(new Cashier());
            eateries.add();
            eateries.add();
            eateries.add();
            mainQ.setCashiers(cashiers);
            
            ticksToPerson = 5;
            avgCashierTime = 15; 
            avgEateryTime = 15;  
            avgLeaveTime = 25;  
            
            producer = new PersonProducer(r,
                                          eateries, 
                                          ticksToPerson,
                                          avgCashierTime,
                                          avgEateryTime,
                                          avgLeaveTime);
                     
            mainQ.setCashiers(cashiers);
            clk.add(producer);
            clk.add(eateries);
            clk.add(mainQ);
            clk.add(cashiers);
              
            clk.run(800);
            
            int totalThroughEateries = eateries.getThroughput() + 
                                  eateries.getLeft() +
                                  eateries.getLost();
            int totalThroughMainQ = mainQ.getThroughput() +
                                    mainQ.getLeft() +
                                    mainQ.getLost();
            int totalThroughCashiers = cashiers.getThroughput() +
                                       cashiers.getLeft() + 
                                       cashiers.getLost();
            
            System.out.println("total through eateries on run: " + 
                               i + " = " + totalThroughEateries);
            System.out.println("total through cashiers on run: " + 
                               i + " = " + totalThroughCashiers);
            assert(totalThroughEateries > totalThroughCashiers);
            assert(totalThroughEateries > totalThroughMainQ);
            /** due to the random starting value, and the large number 
             * of iterations, on some occasions this assertion as >
             * will fail, simply because occasionally no one will wind
             * up getting lost, or remaining in the mainQ.
             * setting > to >= should ensure it always passes
             */
            assert(totalThroughMainQ >= totalThroughCashiers);
            assert(eateries.getThroughput() == totalThroughMainQ);
            assert(mainQ.getThroughput() == totalThroughCashiers);
        }
    }
}
