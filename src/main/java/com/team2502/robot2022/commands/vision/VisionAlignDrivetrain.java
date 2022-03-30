package com.team2502.robot2022.commands.vision;

import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionAlignDrivetrain extends CommandBase {
    private final VisionSubsystem vision;
    private final DrivetrainSubsystem drive;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;
    double leftPower;
    double rightPower;
    double backOfRange;

    private boolean seesTarget;

    private double p;
    private double frictionConstant;

    public VisionAlignDrivetrain(VisionSubsystem vision_subsystem, DrivetrainSubsystem drive_subsystem, Joystick leftJoystick, Joystick rightJoystick){
        vision = vision_subsystem;
        drive = drive_subsystem;
        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;
        seesTarget = false;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        frictionConstant = Constants.Subsystem.Vision.FRICTION_LOW;
        p = Constants.Subsystem.Vision.VISION_TURNING_P_LOW;
        vision.limelightOn();
    }

    @Override
    public void execute() {
	vision.limelightOn();
        double tx = vision.getTargetX();
        double steering_adjust = 0.0f;

        seesTarget = vision.getTargetArea() != 0.0;

        if(seesTarget) {
            if (tx > 1.0) {
                steering_adjust = p * tx;
            } else if (tx < 1.0) {    //robot needs to turn left
                steering_adjust = p * tx;
            }
            leftPower = steering_adjust;
            rightPower = -steering_adjust;
            drive.getDrive().tankDrive(-leftPower, rightPower);
        }
        else {
            drive.getDrive().tankDrive(0, 0, true);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
	    vision.limelightOff();
    }
}
