package Simulation;

import java.util.Iterator;

public class Cashier implements // CisQueue<Person>, 
                                ClockListener,
                                QueuePerformance
                                // Iterable<Person> 
                                {
    /** internal holder for all people in the cashier's queue */
  //  private PersonList q;
    
    private Person p;
    /** number of customers that leave before completion */
    private int lost;
    /** maximum queue length ever achieved in this run */
    private int maxQLength;
    /** Threshold to trigger serving the next person */
    private int ticksToNextPerson = 0;
    /** total number of people that have passed through the queue */
    private int completed = 0;
    
    private int serviceStartTick;
    
    public Cashier() {
        p = null;
        maxQLength = 0;
        serviceStartTick = 0;
    }
    
    @Override
    public void event(int tick) {
//        if (q.size() > 0) {
//            lost += q.checkLeavers(tick);
//            if (0 >= q.size()) {
//                return;
//            }
            // TODO 1: this logic is not correct, check if person at 
            // head dumped out due to delay 
//            if (tick >= ticksToNextPerson) {
//                Person p = q.deQ();
//                ticksToNextPerson = tick + 
//                                    (int)(p.getCashierTime() + 1);
//                completed++;
//            }
//        }
        if (p == null) {
            return;
        }
        if (tick >= p.getCashierTime() + serviceStartTick) {
            completed++;
            p = null;
            return;
        }
//        if (tick >= p.getLeaveTime()) {
//            lost++;
//            p = null;
//            return;
//        }
    }


    public boolean isEmpty() {
        return p == null ? true : false;
    }
//
//    @Override
//    public Person deQ() {
//        lost++;
//        return q.deQ();
//    }

    //@Override
    public void enQ(Person value, int tick) {
//        q.add(value);
//        if (q.size() > maxQLength) {
//            maxQLength = q.size();
//        }
        if (p != null) {
            throw new RuntimeException("Already a person at cashier");
        }
        p = value;
        maxQLength = 1;
        serviceStartTick = tick;
    }

    @Override
    public int getLeft() {
        return p != null ? 1 : 0;
    }

    @Override
    public int getMaxQlength() {
        return maxQLength;
    }

    @Override
    public int getThroughPut() {
        return completed;
    }

    @Override
    public int getLost() {
        return lost;
    }

//    @Override
//    public Iterator<Person> iterator() {
//        // TODO Auto-generated method stub
//        return new CashierIterator();
//    }
//    
//    private class CashierIterator implements Iterator<Person> {
//        private int index = 0;
//
//        @Override
//        public boolean hasNext() {
//            return index < q.size();
//        }
//
//        @Override
//        public Person next() {
//            return q.getAtIndex(index++);
//        }
//    }
}
