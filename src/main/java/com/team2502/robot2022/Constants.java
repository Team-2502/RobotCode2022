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
        // None

        // Operator Joystick
        public static final int TOGGLE_INTAKE = 8;

        public static final int SHOOT_BUTTON = 1;
        public static final int SPIN_FLYWHEEL_BUTTON = 2;

        public static final int BUTTON_FLYWHEEL_MANUAL = 999; //Not used

        public static final int RUN_INTAKE_BUTTON = 3;
        public static final int RUN_INTAKE_BACKWARDS_BUTTON = 4;
    }

    public static final class RobotMap {

        public static final class Motors {
            // Talon FX
            public static final int DRIVE_FRONT_RIGHT = 1;
            public static final int DRIVE_FRONT_LEFT = 3;
            public static final int DRIVE_BACK_RIGHT = 2;
            public static final int DRIVE_BACK_LEFT = 4;
            public static final int RIGHT_WINCH = 5;
            public static final int LEFT_WINCH = 6;

            // SparkMax
            public static final int SQUEEZE_MOTOR = 1;
            public static final int INTAKE_MOTOR = 2;

            public static final int HOPPER_TOP_BELT = 3;
            public static final int HOPPER_BOTTOM_BELT = 4;

            public static final int LOAD_MOTOR_1 = 5;
            public static final int LOAD_MOTOR_2 = 6;

            public static final int SHOOTER_LEFT = 7;
            public static final int SHOOTER_RIGHT = 8;

            public static final int TURRET_TRAVERSE = 9;
        }

        public static final class Solenoids
        {
            public static final int DRIVETRAIN = 1;
            public static final int INTAKE = 2;
            public static final int RIGHT_PASSIVE_CLIMBER = 3;
            public static final int LEFT_PASSIVE_CLIMBER = 4;
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
            public static final double SHOOTER_P = 0.0008;
            public static final double SHOOTER_I = 0.0;
            public static final double SHOOTER_D = 0.0;
            public static final double SHOOTER_IZ = 0;
            public static final double SHOOTER_FF = 0.00019;
            public static final double SHOOTER_MAX_OUTPUT = 1;
            public static final double SHOOTER_MIN_OUTPUT = -1;
            public static final double SHOOTER_MANUAL_RPM_MID = 2600; // center of manual range, max is ~4200 with current pid
        }

        public static final class Turret {
            public static final double TRAVERSE_POWER = 0.29; // (traverseInput ^ pow)
            public static final double TRAVERSE_FRICTION = 0.024; // min val for turret to move

        }

        public static final class Vision {
            // drivetrain
            public static final double FRICTION_LOW = 0.29;
            public static final double VISION_TURNING_P_LOW = 0.015;

            // turret
            public static final double FRICTION_TURRET = 0.024;
            public static final double FRICTION_P = 0.006;
            public static final double FRICTION_I = 0.003;

            public static final double LIMELIGHT_HEIGHT = 26; // limelight aperture to ground in inches
            public static final double LIMELIGHT_ELEVATION = 37; // limelight elevation in degrees

            public static final double BASKET_HEIGHT = 96; // basket vision target to ground

            //Everything in this class is referenced in the Vision Subsystem, and explained at least partially there.
            public static final String LIMELIGHT_NETWORK_TABLE = "limelight-turret"; //the name in the network table of the limelight


            //these would be the numbers corresponding to various settings of the limelight
            public static final double limelightOff = 2;
            public static final double limelightOn = 0;
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

        public static final class RaspberryVision {
            // drivetrain
            public static final double FRICTION_LOW = 0.29;
            public static final double VISION_TURNING_P_LOW = 0.015;

            //Everything in this class is referenced in the Vision Subsystem, and explained at least partially there.
            public static final String LIMELIGHT_NETWORK_TABLE = "PhotonVision"; //the name in the network table of the limelight
        }
    }
}
