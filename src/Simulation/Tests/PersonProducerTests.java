package Simulation.Tests;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Simulation.Gauss;
import Simulation.LimitedTimePerson;
import Simulation.Person;
import Simulation.PersonProducer;
import Simulation.RegularPerson;
import Simulation.SpecialNeedsPerson;

/**********************************************************************
 * Tests for the person producer class
 * @author Matthew Pische
 *
 *********************************************************************/
public class PersonProducerTests {
    private Random r;
    private EateriesMock eateries;
     
    @Before
    public void setUp() {
        r = new Random(1);
        eateries = new EateriesMock();
    }

    public void GetTheFirst15ResultsFromRandomSeed1AsGaussAndInt() {
        for (int i = 0; i < 3; i++) {
            System.out.println(r.nextGaussian()) ;
            System.out.println(r.nextGaussian()) ;
            System.out.println(r.nextGaussian()) ;
            System.out.println(r.nextGaussian()) ;
            System.out.println(r.nextInt(10));
            System.out.println();
            
            /**
             *  1.561581040188955
                -0.6081826070068602
                -1.0912278829447088
                -0.6245401364066232
                8 // regular
                
                0.4729310851145204
                -0.7793116416355956
                -1.2046744957116344
                0.19134942752208015
                2 // special
                
                0.63847575097573
                -0.15020446215665365
                -0.9730277344207469
                -1.3141764794994628
                8 // regular
                
                Gauss.get(r, avgEateryTime), 
                Gauss.get(r, avgLeaveTime), 
                Gauss.get(r, avgCashierTime)
             */
        }
    }
    
    @Test
    public void PersonTypesWorks() {

        PersonProducer pp = new PersonProducer(r, 
                                               eateries, 
                                               1, 20, 20, 40,
                                               4, 2, 4);
        // get how many ticks are needed to spawn a second person
        int neededTicks = (int) Gauss.get(1.561581040188955, 20);
        pp.event(1);
        Person p1 = eateries.people.get(0);
        pp.event(neededTicks);
        // check that the internal method was called expected times
        assert(2 == eateries.addPersonCalled);
        p1 = eateries.people.get(0);
        assert(Gauss.get(-0.6081826070068602, 20) == 
                                                    p1.getEateryTime());
        assert(p1 instanceof RegularPerson);
        Person p2 = eateries.people.get(1);
        assert(Gauss.get(-0.7793116416355956, 20) != 
                                                    p2.getEateryTime());
        assert(Gauss.get(-0.7793116416355956, 20) * 4 == 
                                                    p2.getEateryTime());
        assert(p2 instanceof SpecialNeedsPerson);
        neededTicks += (int) Gauss.get(0.4729310851145204, 20);
        pp.event(neededTicks);
        assert(3 == eateries.addPersonCalled);
        Person p3 = eateries.people.get(2);
        assert(p3 instanceof RegularPerson);
    }
    
    @Test
    public void PersonProducerWorksGivenValidInput() {
        PersonProducer pp = new PersonProducer(r, 
                                               eateries, 
                                               1, 20, 20, 40);
        
        pp.event(1);
        
        assert(1 == eateries.addPersonCalled);
        Person p = eateries.people.get(0);
        assert(0 < p.getCashierTime());
        assert(0 < p.getEateryTime());
        assert(0 < p.getLeaveTime());
        
    }
    
    @Test
    public void PersonProducerMakesMultiplePeopleGivenTicks() {
        /*
         * order: 
         * ticks
         * 
         * setEat
         * setLeave 
         * cashier
         */
        
        // make the producer under test
        PersonProducer pp = new PersonProducer(r, eateries, 
                                               1, 20, 20, 40);
        // get how many ticks are needed to spawn a second person
        int neededTicks = (int) Gauss.get(1.561581040188955, 20);
        pp.event(1);
        pp.event(neededTicks);
        // check that the internal method was called expected times
        assert(2 == eateries.addPersonCalled);
        Person p = eateries.people.get(1);
        assert(p instanceof LimitedTimePerson);
        // create duplicate values to those made internally by producer
        int secondCashTime = (int)(Gauss.get(0.19134942752208015, 20));
        int secondEateryTime = (int) (Gauss.get(-0.7793116416355956, 20) * 0.5);
        // check expected values are same as actual internal values
        assert(secondCashTime == (int) p.getCashierTime());
        assert(secondEateryTime == (int) p.getEateryTime());
        neededTicks += (int) Gauss.get(0.4729310851145204, 20);
        pp.event(neededTicks);
        assert(3 == eateries.addPersonCalled);
        p = eateries.people.get(2);
        int thirdCashTime = (int) Gauss.get(-1.3141764794994628, 20);
        int thirdEateryTime = (int) Gauss.get(-0.15020446215665365, 20);
        assert(thirdCashTime == (int) p.getCashierTime());
        assert(thirdEateryTime == (int) p.getEateryTime());
        assert(secondCashTime != thirdCashTime);
        assert(secondEateryTime != thirdEateryTime);
        assert(p instanceof RegularPerson);
    }
}
