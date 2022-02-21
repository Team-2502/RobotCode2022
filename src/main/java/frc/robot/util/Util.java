package frc.robot.util;

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
    *
    */
    public static double frictionAdjust(double val, double friction) {
        double frictionVal = val < friction ? friction : 0;
    
	// this saves like 2 cycles
	val -= frictionVal;
        if (val > 0) {
		val += 2* frictionVal;
        }

        return val;
    }
}
