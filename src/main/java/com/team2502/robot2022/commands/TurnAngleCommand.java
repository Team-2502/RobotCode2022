package com.team2502.robot2022.commands;
import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2022.util.Trapezoidal;
import com.team2502.robot2022.util.Util;
import edu.wpi.first.math.controller.PIDController;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnAngleCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrain;
    private double turnAngle;
    private double startAngle;
    Trapezoidal trapezoidal;
    double error;

    private PIDController pid;

    public TurnAngleCommand(DrivetrainSubsystem drivetrain, double turnAngle) {
        this.drivetrain = drivetrain;
        this.turnAngle = turnAngle;
        this.trapezoidal = new Trapezoidal(0.4);
        this.pid = new PIDController(0.12, 0.03, 0.002);
        this.pid.setTolerance(0.25); // quit when within 0.25 degrees

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetHeading();
        startAngle = drivetrain.getHeading();
        turnAngle = startAngle + turnAngle;
	pid.reset();
	trapezoidal.reset();
    error = 0;
    }

    @Override
    public void execute() {
        if(drivetrain.getHeading() > 0) {
            error = drivetrain.getHeading() - turnAngle;
        } else{
            error = drivetrain.getHeading() + turnAngle;
        }
	SmartDashboard.putNumber("error", error);

	double speed = trapezoidal.calculate(pid.calculate(error + 4));
	
	speed = Util.constrain(speed,.3);

	SmartDashboard.putNumber("speed", speed);

	drivetrain.setSpeed(speed, speed);

    }

    public boolean isFinished(){ return pid.atSetpoint(); }
}
