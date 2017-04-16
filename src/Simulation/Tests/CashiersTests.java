package Simulation.Tests;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Simulation.Cashiers;
import Simulation.Person;

public class CashiersTests {
    public CashierMock cm;
    
    public Person p1,p2,p3,p4,p5;
    
    @Before
    public void setUp() {
        cm = new CashierMock();
        
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
    public void CashiersWorksGivenValidInput() {
        Cashiers c = new Cashiers();
        c.add(cm);
        assert(0 == cm.l.size());
        assert(true == cm.isEmpty());
        assert(true == c.haveEmpty());
        c.add(p1, 1);
        assert(false == c.haveEmpty());
        c.event(2);
        assert(2 == cm.currTick);
        assert(p1 == cm.l.get(0));
        assert(false == cm.isEmpty());
        assert(1 == c.getMaxQlength());
    }
    
    @Test(expected=RuntimeException.class) 
    public void CashiersThrowsWhenItAttempsToAddPersonToFullCashier() {
        Cashiers c = new Cashiers();
        c.add(cm);
        assert(true == cm.isEmpty());
        c.add(p1, 1);
        assert(false == cm.isEmpty());
        c.add(p2, 2);
    }
}
