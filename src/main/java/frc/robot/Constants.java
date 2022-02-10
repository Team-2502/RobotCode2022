// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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

        public static final int JOYSTICK_DRIVE_RIGHT = 0;
        public static final int JOYSTICK_DRIVE_LEFT = 1;
    }

    public static final class RobotMap {

        public static final class Motors {
            // Talon FX
            public static final int DRIVE_FRONT_RIGHT = 1;
            public static final int DRIVE_FRONT_LEFT = 3;
            public static final int DRIVE_BACK_RIGHT = 2;
            public static final int DRIVE_BACK_LEFT = 4;

	    public static final int SQUEEZE_MOTOR = 24;
	    public static final int INTAKE_MOTOR = 8;

	    public static final int HOPPER_SIDE_BELTS_RIGHT = 1;
	    public static final int HOPPER_SIDE_BELTS_LEFT = 2;
	    public static final int HOPPER_BACK_BELT = 3;
	    public static final int HOPPER_FRONT_BELT = 4;

        }
    }
}
