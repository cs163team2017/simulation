package Simulation;

import java.util.Iterator;

/**********************************************************************
 * Class to hold and iterate over multiple cashiers 
 * @author Matthew Pische
 *
 *********************************************************************/
public class Cashiers implements ClockListener, ICashiers {
    /** list of cashier instances */
    DList<ICashier> cashiersList;
    /** first empty register */
    ICashier firstEmpty;
    /** the maximum number of people ever being simultaneously served 
     * across all cashiers */
    int maxLength;
    
    /******************************************************************
     * instantiate a new empty cashiers set 
     *****************************************************************/
    public Cashiers() {
        cashiersList = new DList<ICashier>();
        firstEmpty = null;
        maxLength = 0;
    }
    
    /* (non-Javadoc)
     * @see Simulation.ICashiers#add(Simulation.ICashier)
     */
    public void add(ICashier c) {
        if (firstEmpty == null) {
            firstEmpty = c;
        }
        cashiersList.add(c);
    }
    
    /* (non-Javadoc)
     * @see Simulation.ICashiers#add(Simulation.Person)
     */
    @Override
    public void enQ(Person p, int tick) {
        if (firstEmpty == null) {
            throw new RuntimeException("No empty cashier available");
        }
        firstEmpty.enQ(p, tick);
        haveEmpty();
    }
    
    /* (non-Javadoc)
     * @see Simulation.ICashiers#getLeft()
     */
    @Override
    public int getLeft() {
        int left = 0;
        for (ListNode<ICashier> c : cashiersList) {
            left += c.getValue().getLeft();
        }
        return left;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#getMaxQlength()
     */
    @Override
    public int getMaxQueueLength() {

        return maxLength;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#getThroughPut()
     */
    @Override
    public int getThroughput() {
        int t = 0;
        for (ICashier c : this) {
            t += c.getThroughput();
        }
        return t;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#getLost()
     */
    @Override
    public int getLost() {
        int lost = 0;
        for (ICashier c : this) {
            lost += c.getLost();
        }
        return lost;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#iterator()
     */
    @Override
    public Iterator<ICashier> iterator() {
        return new CashierIterator();
    }
    
    /******************************************************************
     * private class to implement java's foreach iteration
     * @author Matthew Pische
     *
     *****************************************************************/
    private class CashierIterator implements Iterator<ICashier> {
        
        private int index = 0;
        
        @Override
        public boolean hasNext() {
            return index < cashiersList.size();
        }

        @Override
        public ICashier next() {
            return cashiersList.getAtIndex(index++);
        }
        
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#event(int)
     */
    @Override
    public void event(int tick) {
        int currMax = 0;
        for (ICashier c : this) {
            c.event(tick);
            currMax += c.getLeft();
        }
        if (currMax > maxLength) {
            maxLength = currMax;
        }
    }
    
    /* (non-Javadoc)
     * @see Simulation.ICashiers#haveEmpty()
     */
    @Override
    public boolean haveEmpty() {
        for (ICashier c : this) {
            if (c.isEmpty()) {
                firstEmpty = c;
                return true;
            }
        }
        firstEmpty = null;
        return false;
    }

}
