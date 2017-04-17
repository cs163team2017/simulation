package Simulation;

public interface ICashier extends ClockListener, QueuePerformance {

    /******************************************************************
     * determines if this cashier is not currently serving a customer
     * @return if the cashier can take a new person
     ******************************************************************/
    boolean isEmpty();
    
    /******************************************************************
     * makes the cashier recieve the passed in customer
     * @param value the person to begin checking out
     * @param tick the current tick at which the person's checkout 
     * process begins (used to calculate when the checkout process 
     * completes)
     ******************************************************************/
    void enQ(Person value, int tick);
    
    /**
     * View the person currently at the register, or null if empty
     * @return person being served
     */
    Person peek();

    int getLeft();

    int getMaxQueueLength();

    int getThroughput();

    int getLost();

}