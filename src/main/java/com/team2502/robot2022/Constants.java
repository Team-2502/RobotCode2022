// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022;
import com.team2502.robot2022.util.LookupTable;

import java.util.HashMap;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class OI {

        // Joysticks
        public static final int JOYSTICK_DRIVE_RIGHT = 0;
        public static final int JOYSTICK_DRIVE_LEFT = 1;
        public static final int JOYSTICK_OPERATOR = 2;

        // Buttons

        // Right Drive Joystick
        public static final int SHIFT = 1;

        // Left Drive Joystick
        public static final int RUN_INTAKE_DRIVER_BUTTON = 1;
        public static final int RUN_INTAKE_BACKWARDS_DRIVER_BUTTON = 2;


        // Operator Joystick

	// right side
	// 13 12 11
	// 14 15 16
        public static final int VISION_DRIVETRAIN_ALIGN = 99;

        public static final int SHOOT_BUTTON = 1;

        public static final int BUTTON_FLYWHEEL_MANUAL = 2; //used

        public static final int SPIN_FLYWHEEL_BUTTON = 3;

        public static final int TOGGLE_INTAKE = 4;

        public static final int RUN_CLIMBER_WENCH_BACKWARDS_BUTTON = 5;
        public static final int RUN_CLIMBER_WENCH_BUTTON = 6;

	public static final int RUN_SHOOTER_NT_BUTTON = 7;

        public static final int VISION_TURRET_ALIGN = 8;

        public static final int RUN_INTAKE_BACKWARDS_BUTTON = 9;
       // public static final int VISION_TURRET_UNALIGN = 99;
        public static final int CENTER_TURRET = 11;

       //public static final int RELEASE_CLIMBER_BUTTON = 99;

        public static final int RUN_WINCH_RIGHT_FORWARD = 12;
        public static final int RUN_WINCH_LEFT_FORWARD = 13;
        public static final int RUN_WINCH_LEFT_BACKWARD = 14;
        public static final int RUN_WINCH_RIGHT_BACKWARD = 15;
	
        public static final int SPIN_FLYWHEEL_JUICED_BUTTON = 10;
    }

    public static final class RobotMap {

        public static final class Motors {
            // Talon FX
            public static final int DRIVE_FRONT_RIGHT = 1;
            public static final int DRIVE_FRONT_LEFT = 3;
            public static final int DRIVE_BACK_RIGHT = 2;
            public static final int DRIVE_BACK_LEFT = 4;
            public static final int RIGHT_WENCH = 5;
            public static final int LEFT_WENCH = 6;

            // SparkMax
            public static final int SQUEEZE_MOTOR = 8;
            public static final int INTAKE_MOTOR = 7;

            public static final int HOPPER_BOTTOM_BELT = 9;

            public static final int SUSHI_MOTOR_1 = 10;
            public static final int SUSHI_MOTOR_2 = 11;

            public static final int SHOOTER_LEFT = 13;
            public static final int SHOOTER_RIGHT = 12;

            public static final int TURRET_TRAVERSE = 14;
        }

        public static final class Solenoids
        {
            public static final int DRIVETRAIN = 0;
            public static final int INTAKE = 15;
            public static final int RIGHT_PASSIVE_CLIMBER = 3;
            public static final int LEFT_PASSIVE_CLIMBER = 4;
            public static final int RELEASE_CLIMBER = 7;
        }

	public static final class Sensors
	{
            public static final int CLIMBER_LIMIT_LEFT = 0;
	}
    }

    public static final class Auto
    {
        public static final double TURN_TO_ANGLE_KP = 0.015;

        public static final int TURN_TOLERANCE_DEG = 1;
        public static final int TURN_RATE_TOLERANCE_DEG_PER_SEC = 1;
    }

    /** subsystem-specific constants */
    public static final class Subsystem {

        public static final class Shooter {
            public static final double SHOOTER_P = 0.00019;
            public static final double SHOOTER_I = 0.00000027;
            public static final double SHOOTER_D = 0.0037;
            public static final double SHOOTER_IZ = 100;
            public static final double SHOOTER_FF = 0.000209;
            public static final double SHOOTER_NV = 11.4; // nominal voltage
            public static final double SHOOTER_MAX_OUTPUT = 1;
            public static final double SHOOTER_MIN_OUTPUT = -1;
            public static final double SHOOTER_MANUAL_RPM_MID = 2600; // center of manual range, max is ~4200 with current pid

	    public static final boolean SHOOTER_NT_TUNE = true; // use with "sPID" shuffleboard tab

            public static final double RPM_GOOD = 40;
        }

        public static final class Turret {
            public static final double TURRET_P = 0.0004;
            public static final double TURRET_I = 0.0;
            public static final double TURRET_D = 0.01;
            public static final double MAX_VEL = 1;
            public static final double MAX_ACCEL = 1;

            public static final double MIN_ANGLE = 0;
            public static final double MAX_ANGLE = 180;

            public static final int LIMIT_MIN = 0;
            public static final int LIMIT_MAX = 51;
            public static final double CENTER = 27;

            public static final double TURN_TOLERANCE_DEG = 1;
            public static final double TURN_RATE_TOLERANCE_DEG_PER_S = 1;

            public static final double TURRET_GEAR_RATIO = 0.08387; // 26/310

            public static final double TRAVERSE_POWER = 1; // (traverseInput ^ pow)
            public static final double TRAVERSE_FRICTION = 0.024; // min val for turret to move

        }

        public static final class Vision {
            // drivetrain
            public static final double FRICTION_LOW = 0.29;
            public static final double VISION_TURNING_P_LOW = 0.015;

            // turret
            public static final double FRICTION_TURRET = 0.018;
            public static final double FRICTION_P = 0.0085;
            public static final double FRICTION_I = 0.003;

            public static final double LIMELIGHT_HEIGHT = 25.5; // limelight aperture to ground in inches
            public static final double LIMELIGHT_ELEVATION = 38.45; // limelight elevation in degrees

            public static final double BASKET_HEIGHT = 100.5; // basket vision target to ground

	    public static final double JUICE_FACTOR = -.75; // amount to inflate distance
	    public static final double JUICE_ADJ = 1D/12D; // amount to trim by

            //Everything in this class is referenced in the Vision Subsystem, and explained at least partially there.
            public static final String LIMELIGHT_NETWORK_TABLE = "limelight-turret"; //the name in the network table of the limelight


            //these would be the numbers corresponding to various settings of the limelight
            public static final double limelightOff = 2;
            public static final double limelightOn = 0;
            public static final double limelightPipelineDefault = 2;

            //public static final LookupTable TARGETY_TO_DISTANCE_TABLE; //lookup table to convert Y values from the limelight to distances
            public static final LookupTable DIST_TO_RPM_STANDSTILL_TABLE; //lookup table to convert distances from the hoop to rpms for the flywheel

            static {
                HashMap<Double, Double> distToRPMStandstill = new HashMap<>(); //hashmap for the content of the TARGETY_TO_DISTANCE_TABLE
                //add items to lookup table here
                //TARGETY_TO_DISTANCE_TABLE = new LookupTable(distToRPMStandstill); //Initialize the TARGETY_TO_DISTANCE_TABLE, setting it to the values of the above hashmap

                //HashMap<Double, Double> distToRPMStandstill = new HashMap<>();//hashmap for the content of the DIST_TO_RPM_STANDSTILL_TABLE
		//low 
                distToRPMStandstill.put(0D, 1299D);
                distToRPMStandstill.put(4.1D, 1420D);
                distToRPMStandstill.put(5.8D, 1620D);
                distToRPMStandstill.put(8D, 1650D);
                distToRPMStandstill.put(8.19D, 1680D);

		//high
                distToRPMStandstill.put(8.2D, 2150D);
                distToRPMStandstill.put(9.9D, 2200D);
                distToRPMStandstill.put(10.23D,2250D);
                distToRPMStandstill.put(11.02D,2300D);
                distToRPMStandstill.put(13.4D, 2458.373D);
                distToRPMStandstill.put(15.55D, 2600D);
                distToRPMStandstill.put(20.01D, 2900D);

                    //add items to lookup table here
                    DIST_TO_RPM_STANDSTILL_TABLE = new LookupTable(distToRPMStandstill); //Initialize the DIST_TO_RPM_STANDSTILL_TABLE, setting it to the values of the above hashmap
            }
        }

        public static final class RaspberryVision {
            public static final String PHOTONVISION_NETWORK_TABLE = "photonvision/runcam";

	    public static final double CAMERA_HEIGHT = 25;
	    public static final double CAMERA_ELEVATION = -18.47; // angle
	    public static final double BALL_HEIGHT = 4;
        }

        public static final class Drivetrain {
            public static final double TICKS_PER_INCH = 839.548; // low gear

	    // constants when turning from a standstill
	    public static final double TURN_P = 0.039;
	    public static final double TURN_I = 0.0;
	    public static final double TURN_D = 0;
	    public static final double TURN_F = 0.03; // friction    
	    public static final double TURN_T = 0.8; // trapezoidal 

	    // constants when turning during a move
	    public static final double CURVE_P = 0.022;
	    public static final double CURVE_I = 0.0;
	    public static final double CURVE_D = 0.1;
	    public static final double CURVE_F = 0.055; // friction    
	    public static final double CURVE_T = 0.5; // trapezoidal 

	    // constants for straight line movement
	    public static final double LINE_P = 0.009;
	    public static final double LINE_I = 0.0;
	    public static final double LINE_D = 0.0;
	    public static final double LINE_F = 0.055; // friction
	    public static final double LINE_T = 0.6; // trapezoidal
        }
    }
}
