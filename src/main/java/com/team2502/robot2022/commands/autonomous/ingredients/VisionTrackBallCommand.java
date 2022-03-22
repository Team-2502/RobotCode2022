
package com.team2502.robot2022.commands.autonomous.ingredients;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2502.robot2022.Constants.Subsystem.Drivetrain;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.IntakeSubsystem;
import com.team2502.robot2022.subsystems.PiVisionSubsystem;
import com.team2502.robot2022.util.Trapezoidal;
import com.team2502.robot2022.util.Util;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionTrackBallCommand extends CommandBase {
    private final PiVisionSubsystem vision;
    private final DrivetrainSubsystem drivetrain;
    private final IntakeSubsystem intake;

    private PIDController pid;
    private Trapezoidal trapezoidal;

    public VisionTrackBallCommand(PiVisionSubsystem vision, DrivetrainSubsystem drivetrain, IntakeSubsystem intake){
        this.vision = vision;
        this.drivetrain = drivetrain;
        this.intake = intake;

	this.pid = new PIDController(Drivetrain.TURN_P,Drivetrain.TURN_I,Drivetrain.TURN_D);
	this.trapezoidal = new Trapezoidal(Drivetrain.TURN_T);

        addRequirements(drivetrain, vision, intake);
    }

    @Override
    public void initialize() {
        drivetrain.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    public void execute() {

        if (vision.isTargetVisible()) {
	    double steering_adjust = vision.getTargetX();

	    steering_adjust = pid.calculate(steering_adjust);
	    steering_adjust = trapezoidal.calculate(steering_adjust);
	    steering_adjust = Util.frictionAdjust(steering_adjust, Drivetrain.TURN_F);

            drivetrain.getDrive().tankDrive(steering_adjust, steering_adjust);
        } else {
            drivetrain.stop();
        }

    }

    @Override
    public boolean isFinished() { return pid.atSetpoint();}

    @Override
    public void end(boolean interrupted){

    }
}
