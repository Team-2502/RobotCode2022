package com.team2502.robot2022.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2502.robot2022.Constants;

import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionGoToBallCommand extends CommandBase {
    private final VisionSubsystem vision;
    private final DrivetrainSubsystem drivetrain;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;
    double leftPower;
    double rightPower;
    double backOfRange;

    private boolean seesTarget;

    private double p;
    private double frictionConstant;

    public VisionGoToBallCommand(VisionSubsystem vision_subsystem, DrivetrainSubsystem drivetrain, Joystick leftJoystick, Joystick rightJoystick, double backOfRange){
        vision = vision_subsystem;
        this.drivetrain = drivetrain;
        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;
        this.backOfRange = backOfRange;
        seesTarget = false;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
            frictionConstant = Constants.Subsystem.Vision.FRICTION_LOW;
            p = Constants.Subsystem.Vision.VISION_TURNING_P_LOW;

        drivetrain.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void execute() {

        double tx = vision.getTargetX();
        double steering_adjust = 0.0f;

        seesTarget = vision.getTargetArea() != 0.0;

        if(seesTarget) {
            double power = 0.2;
            double userDesiredValue = -(leftJoystick.getY() + rightJoystick.getY()) / 2;

            boolean useFriction = true;
            double frictionVal = useFriction ? frictionConstant : 0;

            if (tx > 1.0) {
                steering_adjust = p * tx + frictionVal;
            } else if (tx < 1.0) {    //robot needs to turn left
                steering_adjust = p * tx - frictionVal;
            }
            leftPower = steering_adjust;
            rightPower = -steering_adjust;
            drivetrain.getDrive().tankDrive(leftPower + power, rightPower + power);
        }
        else {
            drivetrain.getDrive().tankDrive(-leftJoystick.getY(), -rightJoystick.getY(), true);
        }
    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public void end(boolean interrupted) {  }
}
