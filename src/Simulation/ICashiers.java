package Simulation;

import java.util.Iterator;

public interface ICashiers extends ClockListener, 
                                   Iterable<ICashier>, 
                                   QueuePerformance {
    /**
     * add a person to the first available cashier in the set
     * @param p person to send to a cashier
     * @param tick current tick of the clock
     */
    void enQ(Person p, int tick);
    
    /**
     * add the passed in cashier to the set of cashiers
     * @param c the iCashier to add
     */
    void add(ICashier c);

    int getLeft();

    int getMaxQueueLength();

    int getThroughput();

    int getLost();

    Iterator<ICashier> iterator();

    void event(int tick);

    /**
     * Determine if there is at least one available cashier to receive
     * a customer
     * @return if a empty cashier exists
     */
    boolean haveEmpty();
}