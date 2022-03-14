package com.team2502.robot2022.commands.autonomous.ingredients;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.IntakeSubsystem;
import com.team2502.robot2022.subsystems.PiVisionSubsystem;
import com.team2502.robot2022.util.Trapezoidal;
import com.team2502.robot2022.util.Util;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveToBallCommand extends CommandBase {
    private final PiVisionSubsystem vision;
    private final DrivetrainSubsystem drivetrain;
    private final IntakeSubsystem intake;
    double leftPower;
    double rightPower;
    double backOfRange;
    double steeringPower;

    float powerDivide = 1.2f;

    private boolean seesTarget;

    private double p;
    private double frictionConstant;

    /**
    * Distance command
     */
    public DriveToBallCommand(DrivetrainSubsystem drivetrain, PiVisionSubsystem vision, IntakeSubsystem intake, double backOfRange) {
        this.drivetrain = drivetrain;
        this.vision = vision;
        this.intake = intake;
        this.backOfRange = backOfRange;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize()
    {
        drivetrain.setNeutralMode(NeutralMode.Coast);
        frictionConstant = Constants.Subsystem.Vision.FRICTION_LOW;
        p = Constants.Subsystem.Vision.VISION_TURNING_P_LOW;
    }

    @Override
    public void execute() {

        double tx = vision.getTargetX();
        double steering_adjust = 0.0;

        seesTarget = vision.getTargetArea() != 0.0;
        drivetrain.setHighGear();
        drivetrain.getDrive().tankDrive(0.1,0.1);

        if (seesTarget) {
            drivetrain.getDrive().tankDrive(1,1);
            double power = 0.25;
            if (tx > 0.01) {
                steering_adjust = p * tx + frictionConstant;
            } else if (tx < 0.01) {    //robot needs to turn left
                steering_adjust = p * tx - frictionConstant;
            } else {
                steering_adjust = 0;
            }
            rightPower = -steering_adjust / powerDivide; // Dividing so it turns slower
            leftPower = steering_adjust / powerDivide;
            //drive.getDrive().tankDrive(-leftJoystick.getY() + leftPower / 3.95 + -power / 4 + 0.3, -rightJoystick.getY() + rightPower / 3.95 + -power / 4 + 0.3);
            drivetrain.getDrive().tankDrive(leftPower - power, rightPower - power);
        } else {
            drivetrain.getDrive().tankDrive(0,0);
        }

    }

    @Override
    public boolean isFinished() {
	return false;
    }
}
