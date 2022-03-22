package com.team2502.robot2022.commands;

import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.ShooterSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunShooterTrimmedCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private final VisionSubsystem vision;
    private final double defaultSpeed;
    private final Joystick operator;

    private boolean trimRising = false; // rising edge detector on trim

    public RunShooterTrimmedCommand(ShooterSubsystem shooter, VisionSubsystem vision, double defaultSpeed, Joystick operator) {
        this.shooter = shooter;
        this.vision = vision;
        this.defaultSpeed = defaultSpeed;
	this.operator = operator;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setShooterSpeedRPM(defaultSpeed);
	vision.limelightOn();
    }

    @Override
    public void execute() {
	if (operator.getPOV() == -1) {trimRising = false;}

	if (!trimRising) {
	    if (operator.getPOV() == 0) {
	    	vision.juiceFactor += Constants.Subsystem.Vision.JUICE_ADJ;
		trimRising = true;
	    } else if (operator.getPOV() == 180) {
	    	vision.juiceFactor -= Constants.Subsystem.Vision.JUICE_ADJ;
		trimRising = true;
	    }
	}
	vision.limelightOn();
        if(vision.isTargetVisible()) {
	    shooter.setShooterSpeedRPM(vision.getAdjustedShooterSpeed());
        }
        else {
            shooter.setShooterSpeedRPM(defaultSpeed);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopShooter();
	vision.limelightOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
