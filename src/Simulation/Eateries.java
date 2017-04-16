package Simulation;

import java.util.Iterator;
import java.util.Random;

public class Eateries implements IEateries {
    /** container for all eateries in this object */
    private DList<IEatery> eateryList;
    /** the random number generator */
    Random r;
    /** the main queue where this eatery set will pass people once 
     * they have completed their time at the various kiosks
     */
    IMainQ mainQ;
    /** the maximum number of people ever queued across all eateries 
     * within this set 
     */
    int maxLength;
    
    /** ***************************************************************
     * instantiate a new eatery set
     * @param r the random instance that drives the simulation
     * @param q the main queue where people go after service at these 
     * eateries
     ******************************************************************/
    public Eateries(Random r, IMainQ q) {
        this.r = r;
        mainQ = q;
        maxLength = 0;
        eateryList = new DList<IEatery>();
    }
    
    /******************************************************************
     * overloaded constructor that instantiates the passed in number of
     * eateries
     * @param r random instance
     * @param q mainQueue to pass people to
     * @param n number of eateries to instantiate
     ******************************************************************/
    public Eateries(Random r, IMainQ q, int n) {
        this(r, q);
        for (int i = 0; i < n; i++) {
            IEatery e = new Eatery();
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
        IEatery e = new Eatery();
        e.setMainQueue(mainQ);
        eateryList.add(e);
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#add()
     */
    @Override
    public void add(IEatery e) {
        e.setMainQueue(mainQ);
        eateryList.add(e);
    }
   
    /* (non-Javadoc)
     * @see Simulation.IEateries#add(int)
     */
    @Override
    public void add(int n) {
        for (int i = 0; i < n; i++) {
            IEatery e = new Eatery();
            e.setMainQueue(mainQ);
            eateryList.add(e);
        }
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#add(Simulation.Person)
     */
    @Override
    public void enQ(Person p) {
        IEatery e = this.random();
        e.enQ(p);
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#remove()
     */
    @Override
    public int remove() {
        IEatery e = eateryList.pop();
        return e.getLeft();
    }
    
    /* (non-Javadoc)
     * @see Simulation.IEateries#remove(int)
     */
    @Override
    public IEatery remove(int i) {
        return eateryList.removeAtIndex(i);
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#random()
     */
    @Override
    public IEatery random() {
        if (eateryList.size() == 0) {
            return null;
        }
        if (eateryList.size() == 1) {
            return eateryList.first();
        }
        return eateryList.getAtIndex(r.nextInt(eateryList.size()));
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#event(int)
     */
    @Override
    public void event(int tick) {
        int currMax = 0;
        for (IEatery e : this) {
            e.event(tick);
            currMax += e.getLeft();
        }
        if (currMax > maxLength) {
            maxLength = currMax;
        }
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#iterator()
     */
    @Override
    public Iterator<IEatery> iterator() {
        return new EateryIterator();
    }
    
    /** 
     * class to implement java foreach looping
     * @author Matthew Pische
     *
     */
    private class EateryIterator implements Iterator<IEatery> {

        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < eateryList.size();
        }

        @Override
        public IEatery next() {
            return eateryList.getAtIndex(index++);
        }
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#getLeft()
     */
    @Override
    public int getLeft() {
        int totalPeopleRemaining = 0;
        for(IEatery e : this) {
            totalPeopleRemaining += e.getLeft();
        }
        return totalPeopleRemaining;
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#getMaxQlength()
     */
    @Override
    public int getMaxQueueLength() {
        return maxLength;
    }
    
    /**
     * the longest queue any of the eateries in this set has seen
     * @return the maximum queue length for any child eatery
     */
    public int getMaxSubQLength() {
        int maxQ = 0;
        for (IEatery e : this) {
            if (e.getMaxQueueLength() > maxQ) {
                maxQ = e.getMaxQueueLength();
            }
        }
        return maxQ;
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#getThroughPut()
     */
    @Override
    public int getThroughput() {
        int totalThroughPut = 0;
        for (IEatery e : this) {
            totalThroughPut += e.getThroughput();
        }
        return totalThroughPut;
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#getLost()
     */
    @Override
    public int getLost() {
        int lost = 0;
        for (IEatery e : this) {
            lost += e.getLost();
        }
        return lost;
    }
}
