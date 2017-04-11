package Simulation.Tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Simulation.Gauss;
import Simulation.IMainQ;
import Simulation.Person;
import Simulation.PersonProducer;

public class PersonProducerTests {
    private Random r;
    private EateriesMock eateries;
    private IMainQ mainQ;
    private ArrayList<Double> gaussians;
     
    @Before
    public void setUp() {
        r = new Random(1);
        mainQ = new MainQMock();
        eateries = new EateriesMock();
        gaussians = new ArrayList<Double>();
        gaussians.add(  1.561581040188955);
        gaussians.add(-0.6081826070068602);
        gaussians.add(-1.0912278829447088);
        gaussians.add(-0.6245401364066232);
        gaussians.add(-1.1182832102556484);
        gaussians.add(-1.6583217791337177);
        gaussians.add(-1.8821643777572246);
        gaussians.add(0.059255494425680996);
        gaussians.add(-0.4084113286637019);
        gaussians.add(0.28770950299107917);
        gaussians.add(0.4452322425599013);
        gaussians.add(-0.9558118873968129);
    }
    
    @Test
    public void LetsSeeTheFirst12ResultsFromRandomSeed1() {
        for (int i = 0; i < 12; i++) {
            System.out.println(r.nextGaussian()) ;
        }
    }
    
    @Test
    public void PersonProducerWorksGivenValidInput() {
        PersonProducer pp = new PersonProducer(r, eateries, 1, 20, 20);
        
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
        PersonProducer pp = new PersonProducer(r, eateries, 1, 20, 20);
        // get how many ticks are needed to spawn a second person
        int neededTicks = (int) Gauss.get(gaussians.get(4), 20);
        pp.event(1);
        pp.event(neededTicks);
        // cheeck that the internal method was called expected times
        assert(2 == eateries.addPersonCalled);
        Person p = eateries.people.get(1);
        // create duplicate values to those made internally by producer
        int secondCashTime = (int)Gauss.get(gaussians.get(5), 20);
        int secondEateryTime = (int) Gauss.get(gaussians.get(6), 20);
        // check expected values are same as actual internal values
        assert(secondCashTime == (int) p.getCashierTime());
        assert(secondEateryTime == (int) p.getEateryTime());
        neededTicks = (int) Gauss.get(gaussians.get(8), 20);
        pp.event(neededTicks);
        assert(3 == eateries.addPersonCalled);
        p = eateries.people.get(2);
        int thirdCashTime = (int) Gauss.get(gaussians.get(9), 20);
        int thirdEateryTime = (int) Gauss.get(gaussians.get(10), 20);
        assert(thirdCashTime == (int) p.getCashierTime());
        assert(thirdEateryTime == (int) p.getEateryTime());
        assert(secondCashTime != thirdCashTime);
        assert(secondEateryTime != thirdEateryTime);
    }
}
