package Simulation.Tests;

import java.util.ArrayList;
import java.util.Iterator;

import Simulation.IEatery;
import Simulation.IMainQ;
import Simulation.Person;

/**********************************************************************
 * Mocked class for testing
 * @author Matthew Pische
 *
 **********************************************************************/
public class EateryMock implements IEatery {
    public ArrayList<Person> l = new ArrayList<Person>();
    public int eventCalled = 0;
    public int enQCalled = 0;
    public int lost = 0;
    public int thruPut = 1;
    public int maxLength = 2;

    @Override
    public Person peek() {
        return null;
    }

    @Override
    public Person deQ() {
        return null;
    }

    @Override
    public void enQ(Person value) {
        enQCalled++;
        l.add(value);
    }

    @Override
    public void event(int tick) {
        eventCalled++;
        for (int i = l.size() - 1; i >= 0; i--) {
            if (tick >= l.get(i).getLeaveTime()) {
                lost++;
                l.remove(i);
            } 
        }
        
    }

    @Override
    public int getLeft() {
        return l.size();
    }

    @Override
    public int getMaxQueueLength() {

        return maxLength;
    }

    @Override
    public int getThroughput() {

        return thruPut;
    }

    @Override
    public int getLost() {
        return lost;
    }

    @Override
    public Iterator<Person> iterator() {
        return null;
    }

    @Override
    public void setMainQueue(IMainQ q) {
        
    }
    
}
