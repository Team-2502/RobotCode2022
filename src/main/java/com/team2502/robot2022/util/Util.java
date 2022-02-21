package com.team2502.robot2022.util;

/** class for static utility routines */
public class Util {
    /**
    * Constrain val between -max and max
    * @param val value to constrain
    * @param max maximum and minimum value
    * @return constrained value
     */
    public static double constrain(double val, double max) {
	    return Math.max(-max,Math.min(max,val));
    }

    /**
    * Adjust value so (|val| >= friction)
    * @param val value to adjust
    * @param friction minimum
    * @return adjusted value
    */
    public static double frictionAdjust(double val, double friction) {
        double frictionVal = val < friction ? friction : 0;
    
        if (val > 0) {
		return val +  frictionVal;
        } else {
		return val - frictionVal;
        }
    }
}
