package Simulation;

import java.util.Iterator;

public interface ICashiers extends ClockListener, 
                                   Iterable<Cashier>, 
                                   QueuePerformance {

    /**
     * get a random cashier from the set 
     * @return
     */
    Cashier random();

    void add(Person p, int tick);

    int getLeft();

    int getMaxQlength();

    int getThroughPut();

    int getLost();

    Iterator<Cashier> iterator();

    void event(int tick);

    boolean haveEmpty();
}