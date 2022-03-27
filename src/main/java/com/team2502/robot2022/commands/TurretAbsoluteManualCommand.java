package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurretAbsoluteManualCommand extends CommandBase {
    private final TurretSubsystem turret;
    private final double defaultSpeed;
    private final Joystick controlJoystick;

    public TurretAbsoluteManualCommand(TurretSubsystem turret, double defaultSpeed, Joystick controlJoystick) {
	    this.turret = turret;
        this.defaultSpeed = defaultSpeed; // middle of range
	    this.controlJoystick = controlJoystick; // joystick with lever (throttle)
        addRequirements(turret);
    }

    @Override
    public void initialize() {
	    turret.stop();
    }

    @Override
    public void execute() {
        double angleInput = controlJoystick.getThrottle(); // get slider value
        double targetAngle = (1-angleInput)*90; // value decreases as you move the slider up, ranges from -1 to 1 (mapped to 0-2)

        turret.setAngle(targetAngle);
    }

    @Override
    public void end(boolean interrupted) {
	    turret.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
