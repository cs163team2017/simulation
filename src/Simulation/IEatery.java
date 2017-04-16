package Simulation;

public interface IEatery extends CQueue<Person>, 
                                 ClockListener, 
                                 QueuePerformance,
                                 Iterable<Person> {
    /******************************************************************
     * provide the eatery with a mainQ component where the eatery 
     * will pass each person after they have completed their service
     * @param q the main queue to add 
     ******************************************************************/
    void setMainQueue(IMainQ q);

}