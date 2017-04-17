package Simulation.Tests;

import java.util.ArrayList;
import java.util.Iterator;

import Simulation.ICashiers;
import Simulation.IMainQ;
import Simulation.Person;

/**********************************************************************
 * Mocked class for testing
 * @author Matthew Pische
 *
 *********************************************************************/
public class MainQMock implements IMainQ {
    public int enQCalled = 0;
    public ArrayList<Person> list = new ArrayList<Person>();

    @Override
    public void event(int tick) {
        
    }

    @Override
    public void setCashiers(ICashiers c) {
        
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
        list.add(value);
    }

    @Override
    public Iterator<Person> iterator() {
        return null;
    }

}
