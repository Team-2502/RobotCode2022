package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.OdometrySubsystem;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class    DrivetrainOdometryCommand extends CommandBase {

    OdometrySubsystem odometry;
    DrivetrainSubsystem drivetrain;
    DifferentialDriveOdometry differentialDriveOdometry;
    double lastPos;

    /**
    * updates odometry every frame based on drivetrain encoders
    * @param drivetrain
    * @param odometry
     */
    public DrivetrainOdometryCommand(DrivetrainSubsystem drivetrain, OdometrySubsystem odometry){
	    this.odometry = odometry;
	    this.drivetrain = drivetrain;
	    this.lastPos = drivetrain.getInchesTraveled();
    }

    @Override
    public void initialize() {
	    differentialDriveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(-drivetrain.getHeading()));
	    this.lastPos = drivetrain.getInchesTraveled();
    }

    @Override
    public void execute() {
	    differentialDriveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(-drivetrain.getHeading()));
	    Pose2d pose = differentialDriveOdometry.update(Rotation2d.fromDegrees(-drivetrain.getHeading()), drivetrain.getInchesTraveled()-lastPos, drivetrain.getInchesTraveled()-lastPos);
	    lastPos = drivetrain.getInchesTraveled();

	    Transform2d transform = new Transform2d(pose.getTranslation(), new Rotation2d());
	    odometry.addPose(transform);
    }
}
