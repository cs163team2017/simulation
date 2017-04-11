package Simulation.Tests;
import org.junit.Test;

import Simulation.Person;

public class PersonTests {
    @Test
    public void PersonWorksWhenGivenValidInput() {
        Person p = new Person();
        Double t = 20.0;
        p.setCashierTime(t);
        p.setEateryTime(t + 1);
        p.setLeaveTime(t + 2);
        
        assert(20.0 == p.getCashierTime());
        assert(21.0 == p.getEateryTime());
        assert(22.0 == p.getLeaveTime());
    }

}
