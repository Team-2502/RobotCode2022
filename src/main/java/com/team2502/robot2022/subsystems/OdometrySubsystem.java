package com.team2502.robot2022.subsystems;

import java.util.Deque;

import com.team2502.robot2022.Constants;
import com.team2502.robot2022.util.Ball;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OdometrySubsystem extends SubsystemBase {
    private Pose2d robotPose;
    private Transform2d robotVelocity;
    //private Field2d field;
    
    /**
     * Odometry Subsystem
     * space to store data that needs to be shared between subsystems, primarily odometry data
     * */
    public OdometrySubsystem() {
        robotPose = new Pose2d();
	    //field.setRobotPose(robotPose);
	    //Shuffleboard.getTab("Odometry")
		//    .add("field", field)
		//    .withWidget(BuiltInWidgets.kField);
    }

    @Override
    public void periodic() {
	    //field.setRobotPose(robotPose);
        SmartDashboard.putNumber("xPos", robotPose.getX());
        SmartDashboard.putNumber("yPos", robotPose.getY());
    }

    /**
    * change robot pose by the given transform
    * @param pose transform to adjust pose by
     */
    public void addPose(Transform2d pose) {
	robotPose.plus(pose);
    }

    /**
    * set pose to the given transform
    * @param pose transform to set pose to
     */
    public void setPose(Pose2d pose) {
	robotPose = pose;
    }

    /**
    * set velocity to the given transform
    * @param velocity transform to set velocity to
     */
    public void setVelocity(Transform2d velocity) {
	    this.robotVelocity = velocity;
    }

    /**
    * get robot pose
     */
    public Pose2d getPose() {
	return robotPose;
    }

    /**
    * find distance to basket
    * @return distance to basket (0,0)
     */
    public double getDistance() {
	return robotPose.getTranslation()
		.getDistance(new Translation2d());
    }

    /**
    * find angle to basket
    * @return angle to basket (relative to front of robot)
     */
    public double getAngle() {
	return (robotPose.getRotation().getDegrees() + 180) % 360 ;
    }

    /**
    * Get basket pose based on velocity and distance
    * where to aim
    * @return adjusted pose
     */
    public Pose2d getAdjustedPose() {
	    return robotPose.plus(
            robotVelocity
            .inverse() // invert velocity vector
            .times( // multiply by distance map
                Constants.Subsystem.Drivetrain.DIST_TO_VEL_ADJ_TABLE.get(
                        getDistance()
                )
            )
        );
    }

    /**
    * find distance to aim point
    * @return distance to aim point
     */
    public double getAdjustedDistance() {
	return getAdjustedPose().getTranslation()
		.getDistance(new Translation2d());
    }

    /**
    * angle to aim point
    * @return angle to aim point (relative to front of robot)
     */
    public double getAdjustedAngle() {
	return (robotPose.getRotation().getDegrees() + 180) % 360;
    }
    
    /**
    * correct turret angle to aim at basket with velocity adjustment
    * @return turret angle to aim point
     */
    public double getAdjustedTurretAngle() {
	return  // simply subtract the drivetrain angle to find the turret angle
        Rotation2d.fromDegrees(
                getAdjustedAngle()
                ).minus(robotPose.getRotation())
        .getDegrees();
    }

    /**
     * Hopper contents {@link Deque}
     * front is intake, back is flywheel
     * */
    public Deque<Ball> hopperContents;
}
