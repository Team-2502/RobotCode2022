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

    /** control mappings */
    public static final class OI {

        public static final int JOYSTICK_DRIVE_RIGHT = 0;
        public static final int JOYSTICK_DRIVE_LEFT = 1;
        public static final int JOYSTICK_OPERATOR = 2;

        public static final int BUTTON_FLYWHEEL_MANUAL = 2;
    }

    /** hardware ids */
    public static final class RobotMap {

	/** can motor ids */
        public static final class Motors {
            // Talon FX
            public static final int DRIVE_FRONT_RIGHT = 1;
            public static final int DRIVE_FRONT_LEFT = 3;
            public static final int DRIVE_BACK_RIGHT = 2;
            public static final int DRIVE_BACK_LEFT = 4;

	    public static final int SQUEEZE_MOTOR = 24;
	    public static final int INTAKE_MOTOR = 23;

	    public static final int HOPPER_SIDE_BELTS_RIGHT = 11;
	    public static final int HOPPER_SIDE_BELTS_LEFT = 12;
	    public static final int HOPPER_BACK_BELT = 13;
	    public static final int HOPPER_FRONT_BELT = 14;

            // SparkMax
            public static final int SHOOTER_LEFT = 6;
            public static final int SHOOTER_RIGHT = 7;

	    public static final int TURRET_TRAVERSE = 8;
        }
    }

    /** subsystem-specific constants */
    public static final class Subsystem {

        public static final class Shooter {
            public static final double SHOOTER_P = 0.0008;
            public static final double SHOOTER_I = 0.0;
            public static final double SHOOTER_D = 0.0;
            public static final double SHOOTER_IZ = 0;
            public static final double SHOOTER_FF = 0.00019;
            public static final double SHOOTER_MAX_OUTPUT = 1;
            public static final double SHOOTER_MIN_OUTPUT = -0.1;
            public static final double SHOOTER_MANUAL_RPM_MID = 2600; // center of manual range, max is ~4200 with current pid
        }

        public static final class Climber {
            public static final int RIGHT_WENCH = 0; //talon SRX motor
            public static final int LEFT_WENCH = 1; //talon SRX motor
            public static final int RIGHT_PASSIVE_CLIMBER = 0; //solenoid
            public static final int LEFT_PASSIVE_CLIMBER = 1; //solenoid
        }

        public static final class Vision {
            //Everything in this class is referenced in the Vision Subsystem, and explained at least partially there.
            public static final String LIMELIGHT_NETWORK_TABLE = "limelight"; //the name in the network table of the limelight

            //placeholders. Have same values as 2021 bot.
            //these would be the numbers corresponding to various settings of the limelight
            public static final double limelightOff = 1;
            public static final double limelightOn = 3;
            public static final double limelightPipelineDefault = 0;

            public static final LookupTable TARGETY_TO_DISTANCE_TABLE; //lookup table to convert Y values from the limelight to distances
            public static final LookupTable DIST_TO_RPM_STANDSTILL_TABLE; //lookup table to convert distances from the hoop to rpms for the flywheel

            static {
                HashMap<Double, Double> tyToDistMap = new HashMap<>(); //hashmap for the content of the TARGETY_TO_DISTANCE_TABLE
                //add items to lookup table here
                TARGETY_TO_DISTANCE_TABLE = new LookupTable(tyToDistMap); //Initialize the TARGETY_TO_DISTANCE_TABLE, setting it to the values of the above hashmap

                HashMap<Double, Double> distToRPMStandstill = new HashMap<>();//hashmap for the content of the DIST_TO_RPM_STANDSTILL_TABLE
                //add items to lookup table here
                DIST_TO_RPM_STANDSTILL_TABLE = new LookupTable(distToRPMStandstill); //Initialize the DIST_TO_RPM_STANDSTILL_TABLE, setting it to the values of the above hashmap
            }
        }
    }
}
