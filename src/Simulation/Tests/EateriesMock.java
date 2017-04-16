package Simulation.Tests;

import java.util.ArrayList;
import java.util.Iterator;

import Simulation.IEateries;
import Simulation.IEatery;
import Simulation.IMainQ;
import Simulation.Person;

/**********************************************************************
 * Mocked class for testing
 * @author Matthew Pische
 *
 **********************************************************************/
public class EateriesMock implements IEateries {
    public ArrayList<Person> people;
    public int addPersonCalled;
    
    public EateriesMock() {
        people = new ArrayList<Person>();
        addPersonCalled = 0;
    }

    @Override
    public void setMainQueue(IMainQ q) {
        
    }

    @Override
    public void add() {
        
    }

    @Override
    public void add(int n) {
        
    }

    @Override
    public void enQ(Person p) {
        addPersonCalled++;
        people.add(p);
    }

    @Override
    public int remove() {
        return 0;
    }

    @Override
    public IEatery remove(int i) {
        return null;
    }

    @Override
    public IEatery random() {
        return null;
    }

    @Override
    public void event(int tick) {
        
    }

    @Override
    public Iterator<IEatery> iterator() {
        return null;
    }

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public int getMaxQueueLength() {
        return 0;
    }

    @Override
    public int getThroughput() {
        return 0;
    }

    @Override
    public int getLost() {
        return 0;
    }

    @Override
    public void add(IEatery e) {
        
    }

}
