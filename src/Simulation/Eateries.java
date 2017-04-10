package Simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Eateries implements ClockListener, 
                                 Iterable<Eatery>, 
                                 QueuePerformance, IEateries {
    
    private DblLList<Eatery> eateryList;
    Random r;
    IMainQ mainQ;
    
    public Eateries(Random r, IMainQ q) {
        this.r = r;
        mainQ = q;
        eateryList = new DblLList<Eatery>();
    }
    
    /**
     * 
     * @param r random instance
     * @param q mainQueue 
     * @param n number of eateries to instantiate
     */
    public Eateries(Random r, IMainQ q, int n) {
        this(r, q);
        for (int i = 0; i < n; i++) {
            Eatery e = new Eatery();
            e.setMainQueue(mainQ);
            eateryList.add(e);
        }
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#setMainQueue(Simulation.IMainQ)
     */
    @Override
    public void setMainQueue(IMainQ q) {
        mainQ = q;
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#add()
     */
    @Override
    public void add() {
        Eatery e = new Eatery();
        e.setMainQueue(mainQ);
        eateryList.add(e);
    }
   
    /* (non-Javadoc)
     * @see Simulation.IEateries#add(int)
     */
    @Override
    public void add(int n) {
        for (int i = 0; i < n; i++) {
            Eatery e = new Eatery();
            e.setMainQueue(mainQ);
            eateryList.add(e);
        }
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#add(Simulation.Person)
     */
    @Override
    public void add(Person p) {
        Eatery e = this.random();
        e.enQ(p);
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#remove()
     */
    @Override
    public int remove() {
        Eatery e = eateryList.pop();
        return e.getLeft();
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#remove(int)
     */
    @Override
    public Eatery remove(int i) {
        return eateryList.removeAtIndex(i);
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#random()
     */
    @Override
    public Eatery random() {
        return eateryList.getAtIndex(r.nextInt(eateryList.size()));
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#event(int)
     */
    @Override
    public void event(int tick) {
        for (Eatery e : this) {
            e.event(tick);
        }
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#iterator()
     */
    @Override
    public Iterator<Eatery> iterator() {
        return new EateryIterator();
    }
    
    private class EateryIterator implements Iterator<Eatery> {

        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < eateryList.size();
        }

        @Override
        public Eatery next() {
            return eateryList.getAtIndex(index++);
        }
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#toArrayList()
     */
    @Override
    public ArrayList<Eatery> toArrayList() {
        return eateryList.toArrayList();
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#getLeft()
     */
    @Override
    public int getLeft() {
        int totalPeopleRemaining = 0;
        for(Eatery e : this) {
            totalPeopleRemaining += e.getLeft();
        }
        return totalPeopleRemaining;
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#getMaxQlength()
     */
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

    /* (non-Javadoc)
     * @see Simulation.IEateries#getThroughPut()
     */
    @Override
    public int getThroughPut() {
        int totalThroughPut = 0;
        for (Eatery e : this) {
            totalThroughPut += e.getThroughPut();
        }
        return totalThroughPut;
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#getLost()
     */
    @Override
    public int getLost() {
        int lost = 0;
        for (Eatery e : this) {
            lost += e.getLost();
        }
        return lost;
    }
}
