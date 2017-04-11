package Simulation.Tests;

import java.util.ArrayList;

import Simulation.Cashiers;
import Simulation.IMainQ;
import Simulation.Person;

public class MainQMock implements IMainQ {
    public int enQCalled = 0;
    public ArrayList<Person> list = new ArrayList<Person>();

    @Override
    public void event(int tick) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setCashiers(Cashiers c) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getLeft() {
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
        return 0;
    }

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
        list.add(value);
    }

}
