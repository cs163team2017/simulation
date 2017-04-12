package Simulation;

import java.util.Iterator;
import java.util.Random;

public class Cashiers implements ClockListener, ICashiers {

    DblLList<Cashier> cashiersList;
    Random r;
    Cashier firstEmpty;
    int maxLength;
    
    public Cashiers(Random r) {
        this.r = r;
        cashiersList = new DblLList<Cashier>();
        cashiersList.add(new Cashier());
        cashiersList.add(new Cashier());
        firstEmpty = cashiersList.first();
    }
    
    public Cashiers(Random r, int n) {
        this(r);
        for (int i = 0; i < n; i++) {
            cashiersList.add(new Cashier());
        }
    }
    
    /* (non-Javadoc)
     * @see Simulation.ICashiers#random()
     */
    @Override
    public Cashier random() {
        return cashiersList.getAtIndex(r.nextInt(cashiersList.size()));
    }
    
    /* (non-Javadoc)
     * @see Simulation.ICashiers#add(Simulation.Person)
     */
    @Override
    public void add(Person p, int tick) {
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
        for (ListNode<Cashier> c : cashiersList) {
            left += c.getValue().getLeft();
        }
        return left;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#getMaxQlength()
     */
    @Override
    public int getMaxQlength() {
        int max = 0;
        for (Cashier c : this) {
            if (c.getMaxQlength() > max) {
                max = c.getMaxQlength();
            }
        }
        return max;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#getThroughPut()
     */
    @Override
    public int getThroughPut() {
        int t = 0;
        for (Cashier c : this) {
            t += c.getThroughPut();
        }
        return t;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#getLost()
     */
    @Override
    public int getLost() {
        int lost = 0;
        for (Cashier c : this) {
            lost += c.getLost();
        }
        return lost;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#iterator()
     */
    @Override
    public Iterator<Cashier> iterator() {
        return new CashierIterator();
    }
    
    private class CashierIterator implements Iterator<Cashier> {
        
        private int index = 0;
        
        @Override
        public boolean hasNext() {
            return index < cashiersList.size();
        }

        @Override
        public Cashier next() {
            return cashiersList.getAtIndex(index++);
        }
        
    }

    /* (non-Javadoc)
     * @see Simulation.ICashiers#event(int)
     */
    @Override
    public void event(int tick) {
        for (Cashier c : this) {
            c.event(tick);
        }
        
    }
    
    @Override
    public boolean haveEmpty() {
        for (Cashier c : this) {
            if (c.isEmpty()) {
                firstEmpty = c;
                return true;
            }
        }
        firstEmpty = null;
        return false;
    }

}
