
package com.team2502.robot2022.commands.autonomous.ingredients;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2502.robot2022.Constants;
import com.team2502.robot2022.Constants.Subsystem.Drivetrain;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.IntakeSubsystem;
import com.team2502.robot2022.subsystems.PiVisionSubsystem;
import com.team2502.robot2022.util.Trapezoidal;
import com.team2502.robot2022.util.Util;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionTrackBallCommand extends CommandBase {
    private final PiVisionSubsystem vision;
    private final DrivetrainSubsystem drivetrain;
    private final IntakeSubsystem intake;

    private PIDController turnPID;
    private PIDController straightPID;
    private Trapezoidal turnTrapezoidal;
    private Trapezoidal straightTrapezoidal;

    private double startPOS;
    private double goalPoint;
    private double startHeading;
    private double goalHeading;
    
    private double lastTX;

    public VisionTrackBallCommand(PiVisionSubsystem vision, DrivetrainSubsystem drivetrain, IntakeSubsystem intake){
        this.vision = vision;
        this.drivetrain = drivetrain;
        this.intake = intake;

	this.turnPID = new PIDController(Drivetrain.CURVE_P,Drivetrain.CURVE_I,Drivetrain.CURVE_D);
	this.turnTrapezoidal = new Trapezoidal(Drivetrain.TURN_T);

	this.straightPID = new PIDController(Drivetrain.LINE_P,Drivetrain.LINE_I,Drivetrain.LINE_D);
	this.straightTrapezoidal = new Trapezoidal(Drivetrain.LINE_T);


        addRequirements(drivetrain, vision, intake);
    }

    @Override
    public void initialize() {
        drivetrain.setNeutralMode(NeutralMode.Coast);
	this.startPOS = drivetrain.getInchesTraveled();
	//goalPoint = 12 * Constants.Subsystem.Drivetrain.TICKS_PER_INCH;
	goalPoint = 0;
	turnPID.reset();
	turnTrapezoidal.reset();
	straightPID.reset();
	straightTrapezoidal.reset();

	startHeading = drivetrain.getHeading();
	goalHeading =  drivetrain.getHeading();

	double lastTX = vision.getTargetX();
    }

    @Override
    public void execute() {

	double steering_adjust = 0;
	double error = (drivetrain.getInchesTraveled()-startPOS)+goalPoint;

        if (vision.isTargetVisible() && vision.getTargetX() != lastTX) {
	    lastTX = vision.getTargetX(); // don't update goals if there isn't new data

	    startHeading = drivetrain.getHeading();
            goalHeading = drivetrain.getHeading()-vision.getTargetX();

	    startPOS = drivetrain.getInchesTraveled(); // reset to match new goal
	    goalPoint = (vision.getDistance() + .5) * 12;
        }

	steering_adjust = goalHeading - drivetrain.getHeading();
        steering_adjust = turnPID.calculate(steering_adjust);
        steering_adjust = steering_adjust * error * 0.025;
        steering_adjust = Util.constrain(steering_adjust, .4);
        steering_adjust = turnTrapezoidal.calculate(steering_adjust);
        steering_adjust = Util.frictionAdjust(steering_adjust, Drivetrain.CURVE_F);

	error = (drivetrain.getInchesTraveled()-startPOS)+goalPoint;
	SmartDashboard.putNumber("flerror", error);
	error = Util.constrain(error, 50);
	double power = straightPID.calculate(error);
	power = straightTrapezoidal.calculate(power);
	power = Util.frictionAdjust(power, Drivetrain.LINE_F);

        drivetrain.getDrive().tankDrive(steering_adjust-power, steering_adjust+power);

	if (error < 3*12) {
		intake.run(.5, .85);
		intake.deployIntake();
	} else {
		intake.stop();
	}
    }

    @Override
    public boolean isFinished() { return false;}
    //public boolean isFinished() { return straightPID.atSetpoint();}

    @Override
    public void end(boolean interrupted){
	    intake.retractIntake();
	    intake.stop();
    }
}
