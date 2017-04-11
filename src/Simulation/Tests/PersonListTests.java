package Simulation.Tests;

import org.junit.Before;
import org.junit.Test;

import Simulation.Person;
import Simulation.PersonList;

public class PersonListTests {
    Person p1,p2,p3,p4,p5;
    PersonList l;
    
    @Before
    public void setUp() {
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
        
        l = new PersonList();
        l.enQ(p1);
        l.enQ(p2);
        l.enQ(p3);
    }
    
    @Test
    public void PersonListLeaversWorksGivenValidInput() {
        assert(3 == l.size());
        int n = l.checkLeavers(1); // [1,2,3]
        System.out.println(n);
        assert(3 == l.size());
        assert(p1 == l.peek());
        n = l.checkLeavers(18); // [1,2]
        assert(2 == l.size());
        assert(p1 == l.peek());
        assert(p2 == l.last());
        assert(1 == n);
        l.enQ(p4); // [1,2,4]
        l.enQ(p5); // [1,2,4,5]
        n = l.checkLeavers(30); // [2,5]
        assert(2 == n);
        assert(p2 == l.peek());
        assert(2 == l.size());
        assert(p5 == l.last());
        n = l.checkLeavers(1000000); // []
        assert(2 == n);
        assert(0 == l.size());
        assert(null == l.first());
    }
}
