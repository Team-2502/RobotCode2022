package com.team2502.robot2022.commands.vision;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.IntakeSubsystem;
import com.team2502.robot2022.subsystems.PiVisionSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionGoToBallCommand extends CommandBase {
    private final PiVisionSubsystem vision;
    private final DrivetrainSubsystem drivetrain;
    private final IntakeSubsystem intake;

    private final Joystick leftJoystick;
    private final Joystick rightJoystick;
    double leftPower;
    double rightPower;
    double backOfRange;
    double steeringPower;

    float powerDivide = 1.2f;

    private boolean seesTarget;

    private double p;
    private double frictionConstant;

    public VisionGoToBallCommand(PiVisionSubsystem vision_subsystem, DrivetrainSubsystem drivetrain, IntakeSubsystem intake, Joystick leftJoystick, Joystick rightJoystick, double backOfRange){
        vision = vision_subsystem;
        this.drivetrain = drivetrain;
        this.intake = intake;

        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;
        this.backOfRange = backOfRange;
        seesTarget = false;
        addRequirements(drivetrain, vision_subsystem);
    }

    @Override
    public void initialize() {
        drivetrain.setNeutralMode(NeutralMode.Coast);
        frictionConstant = Constants.Subsystem.Vision.FRICTION_LOW;
        p = Constants.Subsystem.Vision.VISION_TURNING_P_LOW;
    }

    @Override
    public void execute() {

        double tx = vision.getTargetX();
        double steering_adjust = 0.0;

        seesTarget = vision.getTargetArea() != 0.0;

        if (seesTarget) {
            double power;
            double userDesiredValue = -(leftJoystick.getY() + rightJoystick.getY()) / 2;

            power = userDesiredValue;

            boolean useFriction = power < frictionConstant;
            double frictionVal = useFriction ? frictionConstant : 0;

            if (tx > 0.01) {
                steering_adjust = p * tx + frictionVal;
            } else if (tx < 0.01) {    //robot needs to turn left
                steering_adjust = p * tx - frictionVal;
            }
            rightPower = -steering_adjust / powerDivide; // Dividing so it turns slower
            leftPower = steering_adjust / powerDivide;
            //drive.getDrive().tankDrive(-leftJoystick.getY() + leftPower / 3.95 + -power / 4 + 0.3, -rightJoystick.getY() + rightPower / 3.95 + -power / 4 + 0.3);
            drivetrain.getDrive().tankDrive(leftPower - power, rightPower - power);
        } else {
            drivetrain.getDrive().tankDrive(-leftJoystick.getY(), -rightJoystick.getY(), true);
        }

    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public void end(boolean interrupted){

    }
}
