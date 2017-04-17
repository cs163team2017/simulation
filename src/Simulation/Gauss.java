package Simulation;

import java.util.Random;

public class Gauss {
    /******************************************************************
     * returns a number for randomized gaussian distribution
     * @param r the simulation's random object
     * @param ave the desired average value of the distribution
     * @return random value
     ******************************************************************/
    public static double get(Random r, int ave) {
        return ave * 0.5 * r.nextGaussian() + ave + 0.5;
    }
    
    /******************************************************************
     * artifical version of the gaussian distribution for testing
     * @param d gaussian value
     * @param ave target average
     * @return fixed value
     ******************************************************************/
    public static double get(Double d, int ave) {
        return ave * 0.5 * d + ave + 0.5;
    }
}
