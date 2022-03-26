package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.OdometrySubsystem;
import com.team2502.robot2022.subsystems.TurretSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionOdometryCommand extends CommandBase {

    OdometrySubsystem odometry;
    DrivetrainSubsystem drivetrain;
    VisionSubsystem vision;
    TurretSubsystem turret;

    /**
    * uses the offset and distance from the vision, turret, and gyro to find absolute position relative to the basket
    * @param drivetrain
    * @param odometry
     */
    public VisionOdometryCommand(DrivetrainSubsystem drivetrain, VisionSubsystem vision, TurretSubsystem turret, OdometrySubsystem odometry){
       this.odometry = odometry;
       this.drivetrain = drivetrain;
       this.vision = vision;
       this.turret = turret;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
	if (vision.isTargetVisible()) {
	    double basketOffset = Math.toRadians(drivetrain.getHeading() - turret.getAngle() - vision.getTargetX());
	    double basketDistance = vision.getDistance();

	    double offsetX = -Math.cos(basketOffset)*basketDistance;
	    double offsetY = -Math.sin(basketOffset)*basketDistance;

	    odometry.setPose(new Pose2d(offsetX, offsetY, new Rotation2d(drivetrain.getHeading())));
	}
    }
}
