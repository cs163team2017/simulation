package Simulation;

/**********************************************************************
 * Interface providing common methods to access performance metrics
 * @author Matthew Pische
 *
 **********************************************************************/
public interface QueuePerformance {
    /******************************************************************
     * Get the number of elements currently in the queue
     * @return remaining elements in the queue
     ******************************************************************/
    int getLeft();
    
    /******************************************************************
     * peak length of the queue, not necessarily its current size
     * @return greatest number of people who had been waiting at once
     ******************************************************************/
    int getMaxQueueLength();
    
    /******************************************************************
     * Total number of elements that have passed through the queue
     * @return total number of people who successfully completed this 
     * stage of the simulation 
     ******************************************************************/
    int getThroughput();
    
    /******************************************************************
     * Returns the total number of elements removed from the queue
     * due to a failure 
     * @return total number of people who exited the system
     ******************************************************************/
    int getLost();
}
