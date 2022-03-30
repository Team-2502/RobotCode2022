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

public class    SidewinderCommand extends CommandBase {
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
    private double error;

    private boolean finished;

    public SidewinderCommand(PiVisionSubsystem vision, DrivetrainSubsystem drivetrain, IntakeSubsystem intake, double goalDist){
        this.vision = vision;
        this.drivetrain = drivetrain;
        this.intake = intake;
        this.goalPoint = goalDist;

        this.turnPID = new PIDController(Drivetrain.CURVE_P,Drivetrain.CURVE_I,Drivetrain.CURVE_D);
        this.turnTrapezoidal = new Trapezoidal(Drivetrain.TURN_T);

        this.straightPID = new PIDController(Drivetrain.LINE_P,Drivetrain.LINE_I,Drivetrain.LINE_D);
        this.straightTrapezoidal = new Trapezoidal(Drivetrain.LINE_T);

        this.finished = false;


        addRequirements(drivetrain, vision, intake);
    }

    @Override
    public void initialize() {
        drivetrain.setNeutralMode(NeutralMode.Coast);
        this.startPOS = drivetrain.getInchesTraveled();
        turnPID.reset();
        turnTrapezoidal.reset();
        straightPID.reset();
        straightTrapezoidal.reset();

        startHeading = drivetrain.getHeading();
        goalHeading =  drivetrain.getHeading();

        double lastTX = 0;
    }

    @Override
    public void execute() {

        double steering_adjust = 0;
        double error = (drivetrain.getInchesTraveled()-startPOS)+goalPoint;

        if (vision.isTargetVisible() && vision.getTargetX() != lastTX && vision.getDistance() > 5) {
            lastTX = vision.getTargetX(); // don't update goals if there isn't new data

            startHeading = drivetrain.getHeading();
            goalHeading = drivetrain.getHeading()-vision.getTargetX();
        }

        error = (drivetrain.getInchesTraveled()-startPOS)+goalPoint;
        error = Util.constrain(error, 150);
        SmartDashboard.putNumber("flerror", error);
        double power = straightPID.calculate(error) - .23;
        power = straightTrapezoidal.calculate(power);
        power = Util.frictionAdjust(power, Drivetrain.LINE_F);

        steering_adjust = goalHeading - drivetrain.getHeading();
        steering_adjust = turnPID.calculate(steering_adjust);
        steering_adjust = steering_adjust * error * 0.025;
        steering_adjust = Util.constrain(steering_adjust, .4);
        steering_adjust = turnTrapezoidal.calculate(steering_adjust);
        steering_adjust = Util.frictionAdjust(steering_adjust, Drivetrain.CURVE_F);

        drivetrain.getDrive().tankDrive(steering_adjust-power, steering_adjust+power);

	if (error < 3*12) {
		intake.run(.5, .85);
		intake.deployIntake();
		
		if (error < 12) {
			finished = true;
		}
	} else {
		intake.stop();
	}
    }

    @Override
    public boolean isFinished() { return finished;} // hack to not exit first execution

    @Override
    public void end(boolean interrupted){
	    intake.retractIntake();
	    intake.stop();
    }
}
