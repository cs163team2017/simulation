package Simulation.Tests;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;

import Simulation.Eateries;
import Simulation.Eatery;
import Simulation.IEatery;
import Simulation.Person;
import Simulation.PersonProducer;

/**********************************************************************
 * Tests for the eatery class
 * @author Matthew Pische
 *
 **********************************************************************/
public class EateryTests {
    MainQMock mQ;
    Person p1,p2,p3,p4,p5;
    Eatery e;
    Eateries es;
    Random r;
    @Before
    public void setUp() {
        r = new Random(1);
        mQ = new MainQMock();
        e = new Eatery();
        es = new Eateries(r, mQ);
        es.add(e);
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
        assert(3 == e.getMaxQueueLength());
        assert(p1 == e.peek());
        e.event(0); // [1,2,3] tick 0 enqueues the person at the desk
        e.event(1); // [2,3] tick 1 completes p1's eatery time
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
        assert(2 == e.getThroughput());
        assert(2 == e.getLost());
        
    }

    @Test
    public void LeaversWorks() {
        // ticksToNextP, aveCashier, aveEat, aveLeave
        PersonProducer pp = new PersonProducer(new Random(1), 
                                es,
                                1, 2, 2, 10
                                );
        pp.event(0);
        System.out.println(es.getLeft());

        pp.event(10);
        System.out.println(es.getLeft());
        pp.event(20);
        System.out.println(es.getLeft());
        pp.event(30);
        System.out.println(es.getLeft());
        pp.event(40);
        System.out.println(es.getLeft());
        pp.event(50);
        System.out.println(es.getLeft());
        for (IEatery e : es) {
            for (Person p : e) {
                System.out.println("curr person leave: " + p.getLeaveTime());

            }
        }
        es.event(6);
        System.out.println("left: " + es.getLeft());
        System.out.println("lost: " + es.getLost());
        assert(5 == es.getLeft());
        assert(1 == es.getLost());
        es.event(13);
        System.out.println("left: " + es.getLeft());
        System.out.println("lost: " + es.getLost());
        assert(4 == es.getLeft());
        assert(2 == es.getLost());
        es.event(54);
        System.out.println("left: " + es.getLeft());
        System.out.println("lost: " + es.getLost());
        assert(1 == es.getLeft());
        assert(5 == es.getLost());
    }
}
