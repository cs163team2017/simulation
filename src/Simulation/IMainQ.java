package Simulation;

public interface IMainQ {

    /* (non-Javadoc)
     * @see Simulation.IMainQ#event(int)
     */
    void event(int tick);

    /* (non-Javadoc)
     * @see Simulation.IMainQ#setCashiers(Simulation.Cashiers)
     */
    void setCashiers(Cashiers c);

    /* (non-Javadoc)
     * @see Simulation.IMainQ#getLeft()
     */
    int getLeft();

    /* (non-Javadoc)
     * @see Simulation.IMainQ#getMaxQlength()
     */
    int getMaxQlength();

    /* (non-Javadoc)
     * @see Simulation.IMainQ#getThroughPut()
     */
    int getThroughPut();

    /* (non-Javadoc)
     * @see Simulation.IMainQ#getLost()
     */
    int getLost();

    /* (non-Javadoc)
     * @see Simulation.IMainQ#peek()
     */
    Person peek();

    /* (non-Javadoc)
     * @see Simulation.IMainQ#deQ()
     */
    Person deQ();

    /* (non-Javadoc)
     * @see Simulation.IMainQ#enQ(Simulation.Person)
     */
    void enQ(Person value);

}