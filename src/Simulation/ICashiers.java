package Simulation;

import java.util.Iterator;

public interface ICashiers extends ClockListener, 
                                   Iterable<ICashier>, 
                                   QueuePerformance {

    void add(Person p, int tick);
    
    void add(ICashier c);

    int getLeft();

    int getMaxQlength();

    int getThroughPut();

    int getLost();

    Iterator<ICashier> iterator();

    void event(int tick);

    boolean haveEmpty();
}