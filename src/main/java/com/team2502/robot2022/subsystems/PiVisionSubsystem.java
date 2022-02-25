// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.subsystems;

import com.team2502.robot2022.Constants;
import com.team2502.robot2022.Constants.Subsystem.Vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.team2502.robot2022.util.Util;

public class PiVisionSubsystem extends SubsystemBase {
    //private final NetworkTable camera; //Creates a NetworkTable object. This one is for getting data from the limelight.
    private final NetworkTable smartDashboard; //Creates a NetworkTable object. This one is for getting/sending data from/to Shuffleboard(on the driverstation)

    //Create a doubles for where the target(what the limelight recognizes to fit the specified parameters) is on the limelight screen.
    private double targetX;
    private double targetY;
    //And the area. Not sure what this is even for, but we might use it.
    private double targetArea;


    public PiVisionSubsystem() {
        //PhotonCamera camera = new PhotonCamera("Microsoft_LifeCam_HD-3000-input");
        smartDashboard = NetworkTableInstance.getDefault().getTable("SmartDashboard"); //Tells the smartDashboard object to correspond with the shuffleboard one.
    }


    @Override
    public void periodic() {

        SmartDashboard.putNumber("Camera Target X", targetX);
        SmartDashboard.putNumber("Camera Target Y", targetY);
        SmartDashboard.putNumber("Camera Target Area", targetArea);
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
}
