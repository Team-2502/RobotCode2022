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
    AHRS navx;
    Trapezoidal trapezoidal;
    double error;

    private PIDController pid;

    public TurnAngleCommand(DrivetrainSubsystem drivetrain, double turnAngle, AHRS navx) {
        this.drivetrain = drivetrain;
        this.turnAngle = turnAngle;
        this.navx = navx;
	this.trapezoidal = new Trapezoidal(0.4);
	this.pid = new PIDController(0.12, 0.03, 0.002);
	this.pid.setTolerance(0.25); // quit when within 0.25 degrees

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        navx.reset();
        startAngle = navx.getAngle();
        turnAngle = startAngle + turnAngle;
	pid.reset();
	trapezoidal.reset();
    error = 0;
    }

    @Override
    public void execute() {
        if(navx.getAngle() > 0) {
            error = navx.getAngle() - turnAngle;
        } else{
            error = navx.getAngle() + turnAngle;
        }
	SmartDashboard.putNumber("error", error);

	double speed = trapezoidal.calculate(pid.calculate(error + 4));
	
	speed = Util.constrain(speed,.3);

	SmartDashboard.putNumber("speed", speed);

	drivetrain.setSpeed(speed, speed);

    }

    public boolean isFinished(){ return pid.atSetpoint(); }
}
