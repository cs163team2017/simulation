package Simulation;

public interface IEatery extends CQueue<Person>, 
                                 ClockListener, 
                                 QueuePerformance,
                                 Iterable<Person> {

    void setMainQueue(IMainQ q);
//
//    void event(int tick);
//
//    int getLeft();
//
//    int getMaxQlength();
//
//    int getThroughPut();
//
//    int getLost();
//
//    Person peek();
//
//    Person deQ();
//
//    void enQ(Person value);
//
//    Iterator<Person> iterator();

}