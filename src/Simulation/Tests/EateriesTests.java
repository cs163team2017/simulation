package Simulation.Tests;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Simulation.Eateries;
import Simulation.Person;

public class EateriesTests {
    Random r;
    MainQMock mQ;
    Person p1,p2,p3,p4,p5;
    EateryMock e1,e2,e3;
    
    @Before
    public void setUp() {
        r = new Random(1);
        mQ = new MainQMock();
        e1 = new EateryMock();
        e2 = new EateryMock();
        e3 = new EateryMock();
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
    
    public void RunRandom() {
        for (int i = 0; i < 12; i++) {
            System.out.println(r.nextInt(3));
        }
        /* first 12 nextInt(3)
         *  0
            1
            1
            0
            2
            1
            2
            1
            1
            1
            1
            1
         */
        
    }
    
    @Test
    public void EateriesWorksGivenValidInput() {
        Eateries e = new Eateries(r, mQ);
        e.add(e1);
        e.enQ(p1);
        e.enQ(p2);
        assert(2 == e1.enQCalled);
        assert(2 == e1.getLeft());
        assert(0 == e1.getLost());
        e.event(1);
        assert(1 == e1.eventCalled);
        assert(2 == e1.getLeft());
        assert(0 == e1.getLost());
        e.add(e2);
        e.add(e3);
        EateryMock randE = (EateryMock)e.random();
        assert(e1 == randE);
        e.enQ(p3);
        e.event(2);
        assert(2 == e1.eventCalled);
        assert(3 == e.getLeft());
        assert(0 == e.getLost());
        assert(1 == e2.l.size());
        assert(2 == e1.l.size());
        randE = (EateryMock)e.random();
        assert(e2 == randE);
        e.enQ(p4);
        assert(4 == e.getLeft());
        assert(3 == e1.l.size());
        e.event(18);
        assert(3 == e1.eventCalled);
        assert(2 == e.getLeft());
        assert(2 == e.getLost());
        assert(2 == e1.l.size());
        assert(0 == e2.l.size());
        e.enQ(p5);
        assert(3 == e.getLeft());
        assert(2 == e.getLost());
        assert(1 == e2.enQCalled);
        assert(3 == e1.enQCalled);
        assert(1 == e3.l.size());
        
    }
    
    @Test
    public void EateriesGetThroughputGivesTotalFromSubEateryObjs() {
        Eateries e = new Eateries(r, mQ);
        e2.thruPut = 3;
        e.add(e1);
        e.add(e2);
        e.add(e3);
        assert(5 == e.getThroughput());
    }
    
    @Test
    public void EateriesMaxSubQLengthGivesTotalFromSubEateryObjs() {
        Eateries e = new Eateries(r, mQ);
        e3.maxLength = 16;
        e.add(e1);
        e.add(e2);
        e.add(e3);
        assert(16 == e.getMaxSubQLength());
    }
    
    @Test
    public void EateriesMaxQLengthGivesTotalFromSubEateryObjs() {
        Eateries e = new Eateries(r, mQ);
        e1.enQ(p1);
        e1.enQ(p2);
        e1.enQ(p3);
        e3.enQ(p4);
        e3.enQ(p5);
        e.add(e1);
        e.add(e2);
        e.add(e3);
        assert(0 == e.getMaxQueueLength());
        e.event(1);
        assert(5 == e.getMaxQueueLength());
    }
}
