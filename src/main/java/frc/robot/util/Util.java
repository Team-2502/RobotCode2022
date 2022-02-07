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
}
