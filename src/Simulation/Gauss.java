package Simulation;

import java.util.Random;

public class Gauss {
    /**
     * returns a number for randomized gaussian distribution
     * @param r the simulation's random object
     * @param ave the desired average value of the distribution
     * @return
     */
    public static double get(Random r, int ave) {
        return ave * 0.5 * r.nextGaussian() + ave + 0.5;
    }
}
