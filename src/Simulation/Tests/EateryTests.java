package Simulation.Tests;

import org.junit.Before;
import org.junit.Test;

import Simulation.Eatery;
import Simulation.Person;

public class EateryTests {
    MainQMock mQ;
    Person p1,p2,p3,p4,p5;
    Eatery e;
    
    @Before
    public void setUp() {
        mQ = new MainQMock();
        e = new Eatery();
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
    public void EaterWorkGivenValidInput() {
        e.enQ(p1);
        e.enQ(p2);
        e.enQ(p3); // [1,2,3]
        
        e.setMainQueue(mQ);
        assert(3 == e.getLeft());
        assert(3 == e.getMaxQlength());
        assert(p1 == e.peek());
        e.event(1); // [2,3]
        System.out.println(e.getLeft());
        assert(2 == e.getLeft());
        assert(1 == mQ.enQCalled);
        assert(1 == mQ.list.size());
        assert(p1 == mQ.list.get(0));
        assert(p2 == e.peek());
        assert(2 == e.getLeft());
        e.enQ(p4); // [2,3,4]
        e.enQ(p5); // [2.3,4,5]
        e.event(25); // mQ <- 2, [5], 3 & 4 left
        
        assert(2 == mQ.enQCalled);
        assert(p2 == mQ.list.get(1));
        assert(1 == e.getLeft()); // [5]
        assert(p5 == e.peek());
        assert(2 == e.getThroughPut());
        assert(2 == e.getLost());
        
    }

}
