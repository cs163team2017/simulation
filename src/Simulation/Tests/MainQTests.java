package Simulation.Tests;

import org.junit.Before;
import org.junit.Test;

import Simulation.MainQ;
import Simulation.Person;

/**********************************************************************
 * Tests for the MainQ class
 * @author Matthew Pische
 *
 **********************************************************************/
public class MainQTests {
    public CashiersMock c;
    public MainQ q;
    public Person p1,p2,p3,p4,p5;
    
    @Before
    public void setUp() {
        c = new CashiersMock();
        q = new MainQ();
        p1 = new Person();
        p1.setEateryTime(1);
        p1.setLeaveTime(30);
        p2 = new Person();
        p2.setEateryTime(2);
        p2.setLeaveTime(60);
        p3 = new Person();
        p3.setEateryTime(3);
        p3.setLeaveTime(17);
        p4 = new Person();
        p4.setEateryTime(4);
        p4.setLeaveTime(15);
        p5 = new Person();
        p5.setEateryTime(5);
        p5.setLeaveTime(50);
    }
    
    @Test
    public void MainQWorkWhenGivenValidInput() {
        q.setCashiers(c);
        q.enQ(p1);
        q.enQ(p2);
        q.enQ(p3);
        q.event(1);
        assert(1 == c.haveEmptyCalled);
        assert(p1 == c.l.get(0));
        assert(1 == c.givenTick);
        q.event(2);
        assert(2 == c.haveEmptyCalled);
        assert(p2 == c.l.get(1));
        assert(2 == c.givenTick);
        assert(1 == q.getLeft());
        c.haveEmptyVal = false;
        q.enQ(p4);
        q.event(3);
        q.event(4);
        assert(4 == c.haveEmptyCalled);
        assert(2 == c.l.size());
        assert(2 == c.givenTick);
        assert(2 == q.getLeft());
        c.haveEmptyVal = true;
        q.event(10);
        assert(5 == c.haveEmptyCalled);
        assert(p3 == c.l.get(2));
        System.out.println(c.givenTick);
        assert(10 == c.givenTick);
        q.event(15);
        assert(5 == c.haveEmptyCalled);
        assert(3 == c.l.size());
        assert(10 == c.givenTick);
        assert(1 == q.getLost());
        q.event(16);
        System.out.println(c.haveEmptyCalled);
        assert(5 == c.haveEmptyCalled);
        assert(3 == c.l.size());
        assert(10 == c.givenTick);
        assert(0 == q.getLeft());
        assert(3 == q.getThroughput());
        assert(3 == q.getMaxQueueLength());
        q.enQ(p5);
        assert(1 == q.getLeft());
    }
}
