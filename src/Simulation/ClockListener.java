package Simulation;

public interface ClockListener {
    /****************************************************************** 
     * Receives the current tick of the clock to execute whatever needs 
     * to be done
     * @param tick the current clock time
     ******************************************************************/
    public void event(int tick);
}
