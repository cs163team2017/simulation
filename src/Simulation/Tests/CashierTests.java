package Simulation.Tests;
import org.junit.Before;
import org.junit.Test;

import Simulation.Cashier;
import Simulation.Person;

public class CashierTests {
    public Person p1,p2,p3,p4,p5;
    
    @Before
    public void setUp() {            
        p1 = new Person();
        p1.setEateryTime(1);
        p1.setLeaveTime(30);
        p1.setCashierTime(11);
        p2 = new Person();
        p2.setEateryTime(2);
        p2.setLeaveTime(60);
        p2.setCashierTime(22);
        p3 = new Person();
        p3.setEateryTime(3);
        p3.setLeaveTime(17);
        p3.setCashierTime(33);
        p4 = new Person();
        p4.setEateryTime(4);
        p4.setLeaveTime(15);
        p5 = new Person();
        p5.setEateryTime(5);
        p5.setLeaveTime(50);
    }
    
    @Test
    public void CashierWorksGivenValidInput() {
        Cashier c = new Cashier();
        assert(c.isEmpty());
        c.enQ(p1, 1);
        assert(false == c.isEmpty());
        assert(1 == c.getLeft());
        assert(1 == c.getMaxQlength());
        assert(0 == c.getThroughPut());
        c.event(12);
        assert(c.isEmpty());
        assert(0 == c.getLeft());
        assert(1 == c.getThroughPut());
        c.enQ(p2, 20);
        assert(false == c.isEmpty());
        assert(1 == c.getLeft());
        c.event(41);
        assert(false == c.isEmpty());
        assert(1 == c.getLeft());
        c.event(42);
        assert(c.isEmpty());
        assert(0 == c.getLeft());
        assert(2 == c.getThroughPut());
        
    }
}
