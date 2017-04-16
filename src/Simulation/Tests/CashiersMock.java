package Simulation.Tests;

import java.util.ArrayList;
import java.util.Iterator;

import Simulation.Cashier;
import Simulation.ICashier;
import Simulation.ICashiers;
import Simulation.Person;

public class CashiersMock implements ICashiers {
    public int haveEmptyCalled;
    public boolean haveEmptyVal;
    public int addCalled;
    public int getLeftCalled;
    public int givenTick;
    public ArrayList<Person> l;
    
    public CashiersMock() {
        haveEmptyCalled = 0;
        haveEmptyVal = true;
        addCalled = 0;
        givenTick = 0;
        l = new ArrayList<Person>();
    }

    @Override
    public void enQ(Person p, int tick) {
        l.add(p);
        addCalled++;
        givenTick = tick;
    }

    @Override
    public int getLeft() {
        return 1;
    }

    @Override
    public int getMaxQueueLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getThroughput() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getLost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Iterator<ICashier> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void event(int tick) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean haveEmpty() {
        haveEmptyCalled++;
        return haveEmptyVal;
    }

    @Override
    public void add(ICashier c) {
        // TODO Auto-generated method stub
        
    }

}
