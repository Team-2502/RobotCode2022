package com.team2502.robot2022.commands.autonomous.ingredients;

import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class TurnToAngleCommand extends PIDCommand {


    public TurnToAngleCommand(DrivetrainSubsystem drivetrain, double setpoint) {
        super(
                new PIDController(Constants.Auto.TURN_TO_ANGLE_KP, 0D, 0D), // Define PIDController
                drivetrain::getHeading, // Input Source
                setpoint, // Target angle
                output -> // Sound PIDController output to drivetrain
                {
                    double kFriction = Constants.Subsystem.Vision.FRICTION_LOW;
                    double power = 0D;
                    power = output + ((output > 0)? kFriction:-kFriction);
                    drivetrain.setSpeed(-power, power);
                },
                drivetrain
        );

        getController().enableContinuousInput(-180.0f, 180.0f);
    }
}
