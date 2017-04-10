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
        Person p2 = new Person();
        p2.setEateryTime(20);
        p2.setLeaveTime(60);
        Person p3 = new Person();
        p3.setLeaveTime(17);
        p3.setEateryTime(30);
        Person p4 = new Person();
        p4.setEateryTime(40);
        e.enQ(p1);
        e.enQ(p2);
        e.enQ(p3);
        e.setMainQueue(mQ);
        assert(3 == e.getLeft());
        assert(3 == e.getMaxQlength());
        assert(p1 == e.peek());
        
        e.event(3);
        assert(1 == mQ.enQCalled);
        assert(p1 == mQ.list.get(0));
        System.out.println(e.peek().getEateryTime());
        System.out.println();
        assert(2 == e.getLeft());
        
        e.event(15);
        assert(2 == mQ.enQCalled);
        assert(p2 == mQ.list.get(1));
        assert(1 == e.getLeft());
        assert(2 == e.getThroughPut());
        
    }

}
