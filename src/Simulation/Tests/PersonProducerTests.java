package Simulation.Tests;

import java.util.ArrayList;
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
            System.out.println(r.nextInt(10));
            System.out.println(r.nextGaussian()) ;
            System.out.println(r.nextGaussian()) ;
            System.out.println(r.nextGaussian()) ;
            System.out.println();
            
            /**
             *  1.561581040188955 // next
                4 // regular
                -0.6081826070068602 // cash
                -0.2659156285970381 // eatery
                0.09109207606964836 // leave
                
                0.4729310851145204
                3 // regular
                -0.7793116416355956
                0.63847575097573
                -0.15020446215665365
                
                -0.9730277344207469
                8 // regular
                -1.3141764794994628
                1.1078884748368834
                0.119340882129378
             */
        }
    }
    
    @Test
    public void PersonTypesWorks() {
        // set the thresholds to work with the known "random" values
        // from a Random (1) seed
        PersonProducer pp = new PersonProducer(r, 
                                               eateries, 
                                               1, 20, 20, 40,
                                               4, 1, 5);
        // get how many ticks are needed to spawn a second person
        int neededTicks = (int) Gauss.get(1.561581040188955, 20);
        pp.event(1);
        pp.event(neededTicks);
        // check that the internal method was called expected times
        assert(2 == eateries.addPersonCalled);
        Person p = eateries.people.get(0);
        assert(p instanceof LimitedTimePerson);
        p = eateries.people.get(1);

        assert(p instanceof SpecialNeedsPerson);
        neededTicks += (int) Gauss.get(0.4729310851145204, 20);
        pp.event(neededTicks);
        assert(3 == eateries.addPersonCalled);
        p = eateries.people.get(2);
        assert(p instanceof RegularPerson);
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
         * setCash
         * setEat
         * setLeave 
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
        assert(p instanceof RegularPerson);
        // create duplicate values to those made internally by producer
        int secondCashTime = (int)Gauss.get(-0.7793116416355956, 20);
        int secondEateryTime = (int) Gauss.get(0.63847575097573, 20);
        // check expected values are same as actual internal values
        assert(secondCashTime == (int) p.getCashierTime());
        assert(secondEateryTime == (int) p.getEateryTime());
        neededTicks += (int) Gauss.get(0.4729310851145204, 20);
        pp.event(neededTicks);
        assert(3 == eateries.addPersonCalled);
        p = eateries.people.get(2);
        int thirdCashTime = (int) Gauss.get(-1.3141764794994628, 20);
        int thirdEateryTime = (int) Gauss.get(1.1078884748368834, 20);
        assert(thirdCashTime == (int) p.getCashierTime());
        assert(thirdEateryTime == (int) p.getEateryTime());
        assert(secondCashTime != thirdCashTime);
        assert(secondEateryTime != thirdEateryTime);
        assert(p instanceof RegularPerson);
    }
}
