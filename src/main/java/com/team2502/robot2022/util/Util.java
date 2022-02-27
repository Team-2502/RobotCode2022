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

    /**
    * Adjust value so (|val| >= friction), normalized (between -x and x)
    * @param val value to adjust
    * @param friction minimum
    * @param normal value to normalize output to (assuming val is constrained)
    * @return adjusted value
    */
    public static double frictionAdjustNormal(double val, double friction, double normal) {
	    double constrained = (val/normal)-friction;
	    return (frictionAdjust(constrained, friction));
    }

    /**
    * normalize val from in range A to in range B (linear)
    * @param val value to normalize
    * @param minA low end of start range
    * @param maxA high end of start range
    * @param minB low end of end range
    * @param maxB high end of end range
    * @return val shifted from A to B
     */
    public static double normalize(double val, double minA, double maxA, double minB, double maxB) {
	    return (((val-minA) * ((maxB-minB)/(maxA-minA))) + minB);
    }

    /**
    * normalize val from in range A to in range B, positive or negative 
    * @param val value to normalize
    * @param minA low end of start range
    * @param maxA high end of start range
    * @param minB low end of end range
    * @param maxB high end of end range
    * @return val shifted from A to B
     */
    public static double normalizeSigned(double val, double minA, double maxA, double minB, double maxB) {
	    return (val > 0 ? // if val positive
			    normalize(val, minA, maxA, minB, maxB)
			    : // if negative
			    -normalize(Math.abs(val), minA, maxA, minB, maxB)
			    );
    }

    /**
    * Converts a pixel value to an angle off the center of the camera
    * @param fov camera field of view in the wanted axis
    * @param resolution camera resolution in the wanted axis
    * @param pixel pixel on wanted axis to convert to degrees
    * @return offset of pixel from center of camera
     */
    public static double angleFromPixel(double fov, double resolution, double pixel) {
	    return Math.toDegrees(Math.atan(
		    (2 * pixel *
			Math.tan(
			  Math.toRadians(fov/4))
			)
		    /
		    resolution
		    )
	    );
    }

    /**
    * Get distance to target from elevation using trigonometry
    * @param camHeight distance from ground to camera aperture
    * @param camElevation angle of camera
    * @param basketHeight distance from ground to basket vision targets
    * @param targetElevation angle of target relative to camera
    * @return distance to target
     */
    public static double findDist(double camHeight, double camElevation, double basketHeight, double targetElevation) {
	    return (
			    (basketHeight - camHeight)
			    /
			    Math.tan(Math.toRadians(targetElevation+ camElevation))
		   );
    }
}
