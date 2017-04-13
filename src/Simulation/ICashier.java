package Simulation;

public interface ICashier extends ClockListener, QueuePerformance {

  //  void event(int tick);

    boolean isEmpty();
    
    void enQ(Person value, int tick);

    int getLeft();

    int getMaxQlength();

    int getThroughPut();

    int getLost();

}