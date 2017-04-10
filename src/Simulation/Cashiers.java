package Simulation;

import java.util.Iterator;
import java.util.Random;

public class Cashiers implements ClockListener, 
                                 Iterable<Cashier>, 
                                 QueuePerformance {

    DblLList<Cashier> cashiersList;
    Random r;
    
    public Cashiers(Random r) {
        this.r = r;
        cashiersList = new DblLList<Cashier>();
        cashiersList.add(new Cashier());
        cashiersList.add(new Cashier());
    }
    
    public Cashiers(Random r, int n) {
        this(r);
        for (int i = 0; i < n; i++) {
            cashiersList.add(new Cashier());
        }
    }
    
    /**
     * get a random cashier from the set 
     * @return
     */
    public Cashier random() {
        return cashiersList.getAtIndex(r.nextInt(cashiersList.size()));
    }
    
    public void add(Person p) {
        Cashier c = random();
        c.enQ(p);
    }
    
    @Override
    public int getLeft() {
        int left = 0;
        for (ListNode<Cashier> c : cashiersList) {
            left += c.getValue().getLeft();
        }
        return left;
    }

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

    @Override
    public int getThroughPut() {
        int t = 0;
        for (Cashier c : this) {
            t += c.getThroughPut();
        }
        return t;
    }

    @Override
    public int getLost() {
        int lost = 0;
        for (Cashier c : this) {
            lost += c.getLost();
        }
        return lost;
    }

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

    @Override
    public void event(int tick) {
        for (Cashier c : this) {
            c.event(tick);
        }
        
    }

}
