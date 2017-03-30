package Simulation;

public interface CisQueue<T> {
    T peek();
    T deQ();
    void enQ(T value);
}
