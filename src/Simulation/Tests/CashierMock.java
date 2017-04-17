package Simulation.Tests;

import java.util.ArrayList;

import Simulation.ICashier;
import Simulation.Person;

/**********************************************************************
 * Mocked class for testing
 * @author Matthew Pische
 *
 **********************************************************************/
public class CashierMock implements ICashier {
    public int eventCalled = 0;
    public boolean empty = true;
    public int currTick = 0;
    public int max = 0;
    public ArrayList<Person> l = new ArrayList<Person>();
    
    @Override
    public void event(int tick) {
        eventCalled++;
        currTick = tick;
    }

    @Override
    public boolean isEmpty() {
        return empty;
    }

    @Override
    public void enQ(Person value, int tick) {
        max = 1;
        currTick = tick;
        empty = false;
        l.add(value);
    }

    @Override
    public int getLeft() {
        return empty ? 0 : 1;
    }

    @Override
    public int getMaxQueueLength() {
        return max;
    }

    @Override
    public int getThroughput() {
        return l.size();
    }

    @Override
    public int getLost() {
        return 0;
    }

    @Override
    public Person peek() {
        return null;
    }

    
}
