package Simulation;

/**
 * Basic methods to implement a queue
 * @author Matthew Pische
 *
 * @param <T> Type of the queues values
 */
public interface CQueue<T> {
    /**
     * Fetch the value of the first element in the queue, 
     * without removing it
     * @return first value in the queue
     */
    T peek();
    
    /**
     * Removes and returns the first element from the queue
     * @return first element from the queue
     */
    T deQ();
    
    /**
     * Enqueues a new value to the end of the queue
     * @param value value to enqueue at the end of the queue
     */
    void enQ(T value);
}
