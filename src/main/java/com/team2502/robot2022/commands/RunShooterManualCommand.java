package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import com.team2502.robot2022.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunShooterManualCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private final TurretSubsystem turret;
    private final double defaultSpeed;
    private final Joystick controlJoystick;

    public RunShooterManualCommand(ShooterSubsystem shooter, TurretSubsystem turret, double defaultSpeed, Joystick controlJoystick) {
        this.shooter = shooter;
	    this.turret = turret;
        this.defaultSpeed = defaultSpeed; // middle of range
	    this.controlJoystick = controlJoystick; // joystick with lever (throttle)
        addRequirements(shooter, turret);
    }

    @Override
    public void initialize() {
        shooter.setShooterSpeedRPM(0); // stop on init
	    turret.stop();
    }

    @Override
    public void execute() {
	    double speedInput = controlJoystick.getThrottle(); // get slider value
	    double targetRpm = (1-speedInput)*defaultSpeed; // value decreases as you move the slider up, ranges from -1 to 1 (mapped to 0-2)
    	shooter.setShooterSpeedRPM(targetRpm);

	    turret.runMotor(-controlJoystick.getTwist());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopShooter(); // this will coast
	    turret.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
