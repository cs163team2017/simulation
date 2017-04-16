package Simulation;

import java.util.Iterator;

public interface IEateries extends ClockListener, 
                                   Iterable<IEatery>, 
                                   QueuePerformance  {
    /******************************************************************
     * set the main queue that people will be sent to once service is 
     * complete at their respective eateries
     * @param q the queue to receive people after service at the eatery
     ******************************************************************/
    void setMainQueue(IMainQ q);

    /****************************************************************** 
     * add one eatery
     ******************************************************************/
    void add();

    /******************************************************************
     * Add multple eateries 
     * @param n number of eateries to add
     ******************************************************************/
    void add(int n);

    /******************************************************************
     * Add a person to a random eatery
     ******************************************************************/
    void enQ(Person p);

    /******************************************************************
     * removes the last eatery 
     * @return returns the number of customers lost from its queue
     ******************************************************************/
    int remove();

    /****************************************************************** 
     * removes the eatery at the given index
     * @param i index of the eatery to remove
     * @return the removed Eatery
     ******************************************************************/
    IEatery remove(int i);

    /******************************************************************
     * get a random eatery from the set of eateries 
     * @return the selected eatery
     ******************************************************************/
    IEatery random();

    void event(int tick);

    Iterator<IEatery> iterator();

    int getLeft();

    int getMaxQueueLength();

    int getThroughput();

    int getLost();

    /******************************************************************
     * adds the passed in eatery to the current set of eateries
     * @param e the eatery to add
     ******************************************************************/
    void add(IEatery e);

}