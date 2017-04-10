package Simulation;

import java.util.ArrayList;
import java.util.Iterator;

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
    public void add(Person p) {
        addPersonCalled++;
        people.add(p);
    }

    @Override
    public int remove() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Eatery remove(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Eatery random() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void event(int tick) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<Eatery> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Eatery> toArrayList() {
        // TODO Auto-generated method stub
        return null;
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

}
