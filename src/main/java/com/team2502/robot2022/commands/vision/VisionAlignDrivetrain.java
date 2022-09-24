package com.team2502.robot2022.commands.vision;

import com.team2502.robot2022.Constants.Subsystem.Vision;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import com.team2502.robot2022.util.Util;

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
        vision.limelightOn();
    }

    @Override
    public void execute() {
        double power = Util.constrain(leftJoystick.getY() + rightJoystick.getY(),1);

        vision.limelightOn();
        double error = vision.getTargetX();
        seesTarget = vision.getTargetArea() != 0.0;

        if(seesTarget) {
            double turnPower = Vision.VISION_TURNING_P_LOW * error;
            turnPower = Util.frictionAdjust(turnPower, Vision.FRICTION_LOW);

            drive.getDrive().tankDrive(turnPower-power, turnPower+power); // left, -right
        }
        else {
            drive.getDrive().tankDrive(-power, power, true);
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
