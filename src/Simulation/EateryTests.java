package Simulation;

import org.junit.Before;
import org.junit.Test;

public class EateryTests {
    MainQMock mQ;
    
    @Before
    public void setUp() {
        mQ = new MainQMock();
    }
    
    @Test
    public void EaterWorkGivenValidInput() {
        Eatery e = new Eatery();
        Person p1 = new Person();
        p1.setEateryTime(10);
        p1.setLeaveTime(30);
        Person p2 = new Person();
        p2.setEateryTime(20);
        p2.setLeaveTime(60);
        Person p3 = new Person();
        p3.setEateryTime(30);
        p3.setLeaveTime(17);
        Person p4 = new Person();
        p4.setEateryTime(41);
        p4.setLeaveTime(15);
        e.enQ(p1);
        e.enQ(p2);
        e.enQ(p3);
        
        e.setMainQueue(mQ);
        assert(3 == e.getLeft());
        assert(3 == e.getMaxQlength());
        assert(p1 == e.peek());
        e.event(1);
        assert(2 == e.getLeft());
        assert(1 == mQ.enQCalled);
        assert(1 == mQ.list.size());
        assert(p1 == mQ.list.get(0));
        assert(p2 == e.peek());
        assert(2 == e.getLeft());
        e.enQ(p4);
        e.event(25);
        
        // TODO 1: bad tests
        
        assert(2 == mQ.enQCalled);
        assert(p2 == mQ.list.get(1));
        assert(1 == e.getLeft());
        assert(p4 == e.peek());
        assert(2 == e.getThroughPut());
        assert(1 == e.getLost());
        
    }

}
