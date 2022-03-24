package com.team2502.robot2022.subsystems;

import java.util.Deque;

import com.team2502.robot2022.Constants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OdometrySubsystem extends SubsystemBase {
    private Pose2d robotPose;
    
    /**
     * Odometry Subsystem
     * space to store data that needs to be shared between subsystems, primarily odometry data
     * */
    public OdometrySubsystem() {
	robotPose = new Pose2d();
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
    * get robot pose
     */
    public Pose2d getPose() {
	return robotPose;
    }
}
