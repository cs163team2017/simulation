package Simulation;

/**********************************************************************
 * class to create a new cashier 
 * @author Matthew Pische
 *
 **********************************************************************/
public class Cashier implements ClockListener,
                                QueuePerformance,
                                ICashier
                                {    
    /** person currently at the counter */
    private Person p;
    /** number of customers that leave before completion, 
     * always zero here */
    private int lost = 0;
    /** maximum queue length ever achieved in this run */
    private int maxQLength;
    /** total number of people that have passed through the queue */
    private int completed = 0;
    /** tick that the customer currently at the cashier began checkout
     * checkout completes when this number + the person's checkout
     * duration are exceeded by the clock time
     */
    private int serviceStartTick;
    
    /******************************************************************
     * instantiate a cashier
     ******************************************************************/
    public Cashier() {
        p = null;
        maxQLength = 0;
        serviceStartTick = 0;
    }
    
    /* (non-Javadoc)
     * @see Simulation.ICashier#event(int)
     */
    @Override
    public void event(int tick) {
        if (p == null) {
            return;
        }
        if (tick >= p.getCashierTime() + serviceStartTick) {
            completed++;
            p = null;
            return;
        }
    }


    /* (non-Javadoc)
     * @see Simulation.ICashier#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return p == null ? true : false;
    }

    //@Override
    /* (non-Javadoc)
     * @see Simulation.ICashier#enQ(Simulation.Person, int)
     */
    @Override
    public void enQ(Person value, int tick) {
        if (p != null) {
            throw new RuntimeException("Already a person at cashier");
        }
        p = value;
        maxQLength = 1;
        serviceStartTick = tick;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashier#getLeft()
     */
    @Override
    public int getLeft() {
        return p != null ? 1 : 0;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashier#getMaxQlength()
     */
    @Override
    public int getMaxQueueLength() {
        return maxQLength;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashier#getThroughPut()
     */
    @Override
    public int getThroughput() {
        return completed;
    }

    /* (non-Javadoc)
     * @see Simulation.ICashier#getLost()
     */
    @Override
    public int getLost() {
        return lost;
    }
}
