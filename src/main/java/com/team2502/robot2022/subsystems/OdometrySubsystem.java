package com.team2502.robot2022.subsystems;

import java.util.Deque;

import com.team2502.robot2022.Constants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OdometrySubsystem extends SubsystemBase {
    private Pose2d robotPose;
    private Transform2d robotVelocity;
    
    /**
     * Odometry Subsystem
     * space to store data that needs to be shared between subsystems, primarily odometry data
     * */
    public OdometrySubsystem() {
	robotPose = new Pose2d();
    }

    @Override
    public void periodic() {
	    Field2d field = new Field2d();
	    field.setRobotPose(robotPose);
	    Shuffleboard.getTab("Odometry")
		    .add("field", field)
		    .withWidget(BuiltInWidgets.kField);
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
}
