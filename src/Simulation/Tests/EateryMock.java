package Simulation.Tests;

import java.util.ArrayList;
import java.util.Iterator;

import Simulation.IEatery;
import Simulation.IMainQ;
import Simulation.Person;

public class EateryMock implements IEatery {
    public ArrayList<Person> l = new ArrayList<Person>();
    public int eventCalled = 0;
    public int enQCalled = 0;
    public int lost = 0;

    @Override
    public Person peek() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Person deQ() {
        // TODO Auto-generated method stub
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
    public int getMaxQlength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getThroughPut() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getLost() {
        return lost;
    }

    @Override
    public Iterator<Person> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMainQueue(IMainQ q) {
        // TODO Auto-generated method stub
        
    }
    
}
