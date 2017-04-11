package Simulation;

import java.util.ArrayList;
import java.util.Iterator;

public interface IEateries extends ClockListener, 
                                   Iterable<IEatery>, 
                                   QueuePerformance  {

    void setMainQueue(IMainQ q);

    /** 
     * add one eatery
     */
    void add();

    /**
     * Add multple eateries 
     * @param n number of eateries to add
     */
    void add(int n);

    /** 
     * Add a person to a random eatery
     */
    void add(Person p);

    /**
     * removes the last eatery 
     * @return returns the number of customers lost from its queue
     */
    int remove();

    /** removes the eatery at the given index
     * 
     * @param i index of the eatery to remove
     * @return the removed Eatery
     */
    IEatery remove(int i);

    /**
     * get a random eatery from the set of eateries 
     * @return
     */
    IEatery random();

    void event(int tick);

    Iterator<IEatery> iterator();

    /**
     * create an ArrayList of the held Eateries
     * @return
     */
    ArrayList<IEatery> toArrayList();

    int getLeft();

    int getMaxQlength();

    int getThroughPut();

    int getLost();

    void add(IEatery e);

}