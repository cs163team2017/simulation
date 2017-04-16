package Simulation.Tests;

import java.util.ArrayList;
import java.util.Iterator;

import Simulation.Eatery;
import Simulation.IEateries;
import Simulation.IEatery;
import Simulation.IMainQ;
import Simulation.Person;

public class EateriesMock implements IEateries {
    public ArrayList<Person> people;
    public int addPersonCalled;
    
    public EateriesMock() {
        people = new ArrayList<Person>();
        addPersonCalled = 0;
    }

    @Override
    public void setMainQueue(IMainQ q) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void add() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void add(int n) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enQ(Person p) {
        addPersonCalled++;
        people.add(p);
    }

    @Override
    public int remove() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public IEatery remove(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IEatery random() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void event(int tick) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<IEatery> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLeft() {
        // TODO Auto-generated method stub
        return 0;
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
    public void add(IEatery e) {
        // TODO Auto-generated method stub
        
    }

}
