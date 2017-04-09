package Simulation;

public interface QueuePerformance {
    /**
     * Get the number of elements currently in the queue
     * @return remaining elements in the queue
     */
    int getLeft();
    
    /**
     * peak length of the queue, not necessarily its current size
     * @return 
     */
    int getMaxQlength();
    
    /**
     * Total number of elements that have passed through the queue
     * @return
     */
    int getThroughPut();
    
    /**
     * Returns the total number of elements removed from the queue
     * due to a failure 
     * @return
     */
    int getLost();
}
