// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Subsystem.Vision.*;

public class VisionSubsystem extends SubsystemBase {
    private final NetworkTable limelight; //Creates a NetworkTable object. This one is for getting data from the limelight.
    private final NetworkTable smartDashboard; //Creates a NetworkTable object. This one is for getting/sending data from/to Shuffleboard(on the driverstation)

    //Create a doubles for where the target(what the limelight recognizes to fit the specified parameters) is on the limelight screen.
    private double targetX;
    private double targetY;
    //And the area. Not sure what this is even for, but we might use it.
    private double targetArea;


    public VisionSubsystem() {
        limelight = NetworkTableInstance.getDefault().getTable(LIMELIGHT_NETWORK_TABLE); //Tells the limelight object to correspond with the network table of a key specified in constants, which in turn corresponds with the limelight.
        smartDashboard = NetworkTableInstance.getDefault().getTable("SmartDashboard"); //Tells the smartDashboard object to correspond with the shuffleboard one.
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run(fast)

        //constantly get the values from the limelight
        NetworkTableEntry TX_ENTRY = limelight.getEntry("tx");
        NetworkTableEntry TY_ENTRY = limelight.getEntry("ty");
        NetworkTableEntry AREA_ENTRY = limelight.getEntry("ta");
        //and set them to the variables we made earlier
        targetX = TX_ENTRY.getDouble(0.0);
        targetY = TY_ENTRY.getDouble(0.0);
        targetArea = AREA_ENTRY.getDouble(0.0);

        SmartDashboard.putNumber("Limelight Target X", targetX);
        SmartDashboard.putNumber("Limelight Target Y", targetY);
        SmartDashboard.putNumber("Limelight Target Area", targetArea);

        //SmartDashboard.putNumber("Limelight Distance To Target", getDistance());


    }

    /**
     * Gets the X of the target(anything that the limelight sees to meet it's set parameters) on the limelight screen
     * from the limelight's NetworkTable(communication of arbitrary values between the limelight, driver station, and robot).
     * Used in the to turn the turret to where the hoop is.
     *
     * @return That X value
     */
    public double getTargetX() {
        return targetX;
    }

    public double getTargetArea() {
        return targetArea;
    } //method to get the area(can somebody figure out what this is?)

    /**
     * Turns off the limelights LED's.
     * Gets the number that corresponds to the 'off' setting from the Constants class(specifically the 'limelightOff' variable),
     * then sets the mode of the limelight's LEDS to that number.
     * Used to not blind[hyperbole] any unfortunate soul that happens to look at the limelight when we are not using it
     */
    public void limelightOff() {
        limelight.getEntry("ledMode").setNumber(limelightOff);
    }

    /**
     * Turns on the limelights LED's.
     * Gets the number that corresponds to the 'on' setting from the Constants class(specifically the 'limelightOff' variable),
     * then sets the mode of the limelight's LEDS to that number.
     * Used to turn the limelight back on so that we can aim[after turning it off for not-blinding-the-ref purposes]
     */
    public void limelightOn() {
        limelight.getEntry("ledMode").setNumber(limelightOn);
    }

    /**
     * Sets the limelight's LEDs to the default setting.
     * Default setting is gotten from the limelight's pipeline[configuration for detecting specific things]
     * , by setting the limelight's LEDs to a number specified as 'pipeline default' by the Constants class.
     * Used to turn the limelight off when we initialize[for not-blinding-the-ref purposes]
     */
    public void limelightPipeline() {
        limelight.getEntry("ledMode").setNumber(limelightPipelineDefault);
    }

    /**
     * Calculates the distance to the target[hoop] based on the Y of the target given by the limelight.
     * Does so by taking the value the limelight gives from it's 'ty' network table entry
     * (table for sending arbitrary values between the limelight, robot, and driver station)
     * and putting it through a lookup table in the Constants class(specifically the TARGETY_TO_DISTANCE_TABLE).
     *
     * @return The distance to the target(hoop)
     */
    public double getDistance() {
        return TARGETY_TO_DISTANCE_TABLE.get(targetY);
    }

    /**
     * Calculates the optimal speed for the shooter to spin, given the y of the target given by the limelight.
     * Does so by taking the y given by the limelight, putting it through a lookup table to get the distance,
     * then putting it through another lookup table to get the optimal speed.
     * Used to accurately shoot from arbitrary distances from the target.
     *
     * @return An RPM for the shooter to spin at.
     */
    public double getShooterSpeedStandstill() {
        return DIST_TO_RPM_STANDSTILL_TABLE.get(TARGETY_TO_DISTANCE_TABLE.get(targetY));
    }
}
