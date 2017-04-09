package Simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Eateries implements ClockListener, 
                                 Iterable<Eatery>, 
                                 QueuePerformance {
    
    private DblLList<Eatery> EateryList;
    Random r;
    
    public Eateries(Random r) {
        this.r = r;
        EateryList = new DblLList<Eatery>();
        
    }
    
    public Eateries(Random r, int n) {
        this.r = r;
        for (int i = 0; i < n; i++) {
            EateryList.add(new Eatery());
        }
    }
    
    /** 
     * add one eatery
     */
    public void add() {
        EateryList.add(new Eatery());
    }
    
    /**
     * Add multple eateries 
     * @param n number of eateries to add
     */
    public void add(int n) {
        for (int i = 0; i < n; i++) {
            EateryList.add(new Eatery());
        }
    }
    
    /** 
     * Add a person to a random eatery
     */
    public void add(Person p) {
        Eatery e = this.random();
        e.add(p);
    }
    
    /**
     * removes the last eatery 
     * @return returns the number of customers lost from its queue
     */
    public int remove() {
        Eatery e = EateryList.pop();
        return e.getLeft();
    }
    
    /** removes the eatery at the given index
     * 
     * @param i index of the eatery to remove
     * @return the removed Eatery
     */
    public Eatery remove(int i) {
        return EateryList.removeAtIndex(i);
    }

    /**
     * get a random eatery from the set of eateries 
     * @return
     */
    public Eatery random() {
        return EateryList.getAtIndex(r.nextInt(EateryList.size()));
    }

    @Override
    public void event(int tick) {
        for (ListNode<Eatery> n : EateryList) {
            n.getValue().event(tick);
        }
    }

    @Override
    public Iterator<Eatery> iterator() {
        return new EateryIterator();
    }
    
    private class EateryIterator implements Iterator<Eatery> {

        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < EateryList.size();
        }

        @Override
        public Eatery next() {
            return EateryList.getAtIndex(index);
        }
    }
    
    /**
     * create an ArrayList of the held Eateries
     * @return
     */
    public ArrayList<Eatery> toArrayList() {
        return EateryList.toArrayList();
    }

    @Override
    public int getLeft() {
        int totalPeopleRemaining = 0;
        for(Eatery e : this) {
            totalPeopleRemaining += e.getLeft();
        }
        return totalPeopleRemaining;
    }

    @Override
    public int getMaxQlength() {
        int maxQ = 0;
        for (Eatery e : this) {
            if (e.getMaxQlength() > maxQ) {
                maxQ = e.getMaxQlength();
            }
        }
        return maxQ;
    }

    @Override
    public int getThroughPut() {
        int totalThroughPut = 0;
        for (Eatery e : this) {
            totalThroughPut += e.getThroughPut();
        }
        return totalThroughPut;
    }

    @Override
    public int getLost() {
        int lost = 0;
        for (Eatery e : this) {
            lost += e.getLost();
        }
        return lost;
    }
}
