package Simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Eateries implements IEateries {
    
    private DblLList<IEatery> eateryList;
    Random r;
    IMainQ mainQ;
    
    public Eateries(Random r, IMainQ q) {
        this.r = r;
        mainQ = q;
        eateryList = new DblLList<IEatery>();
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
    public void add(Person p) {
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
        for (IEatery e : this) {
            e.event(tick);
        }
    }

    /* (non-Javadoc)
     * @see Simulation.IEateries#iterator()
     */
    @Override
    public Iterator<IEatery> iterator() {
        return new EateryIterator();
    }
    
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
     * @see Simulation.IEateries#toArrayList()
     */
    @Override
    public ArrayList<IEatery> toArrayList() {
        return eateryList.toArrayList();
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
    public int getMaxQlength() {
        int maxQ = 0;
        for (IEatery e : this) {
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
        for (IEatery e : this) {
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
        for (IEatery e : this) {
            lost += e.getLost();
        }
        return lost;
    }
}
