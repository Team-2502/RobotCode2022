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
import com.team2502.robot2022.Constants.Subsystem.RaspberryVision;

/**
 * Subsystem that allows you to
 * access and retrieve data from
 * the Logitech camera, also
 * send data to Smartdashboard
 */

public class PiVisionSubsystem extends SubsystemBase {
    //private final NetworkTable camera; //Creates a NetworkTable object. This one is for getting data from the limelight.

    //Create a doubles for where the target(what the limelight recognizes to fit the specified parameters) is on the limelight screen.
    private double targetX;
    private double targetY;
    //And the area. Not sure what this is even for, but we might use it.
    private double targetArea;

    private final NetworkTable camera;


    public PiVisionSubsystem() {
        camera = NetworkTableInstance.getDefault().getTable(RaspberryVision.PHOTONVISION_NETWORK_TABLE);
    }


    @Override
    public void periodic() {

        NetworkTableEntry TX_ENTRY = camera.getEntry("targetYaw");
        NetworkTableEntry TY_ENTRY = camera.getEntry("targetPitch");
        NetworkTableEntry AREA_ENTRY = camera.getEntry("targetArea");

        targetX = TX_ENTRY.getDouble(0.0);
        targetY = TY_ENTRY.getDouble(0.0);
        targetArea = AREA_ENTRY.getDouble(0.0);

        SmartDashboard.putNumber("PhotonVision X", targetX);
        SmartDashboard.putNumber("PhotonVision Y", targetY);
        SmartDashboard.putNumber("PhotonVision Area", targetArea);
	    SmartDashboard.putNumber("Ball Distance", getDistance());

    }

    public double getTargetX() {
        return targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public double getDistance() {
        return Util.findDist(RaspberryVision.CAMERA_HEIGHT/12,  RaspberryVision.CAMERA_ELEVATION, RaspberryVision.BALL_HEIGHT/12, targetY);
    }

    public double getTargetArea() {
        return targetArea;
    } //method to get the area(can somebody figure out what this is?)

    /**
     * Is the PhotonVision locked onto anything?
     * returns true if the target area is nonzero
     * */
    public boolean isTargetVisible() {
	    return (getTargetArea() != 0);
    }

}
