package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.OdometrySubsystem;
import com.team2502.robot2022.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VelocityTurretAimCommand extends CommandBase {
    private final TurretSubsystem turret;
    private final OdometrySubsystem odometry;

    public VelocityTurretAimCommand(TurretSubsystem turret, OdometrySubsystem odometry) {
	    this.turret = turret;
        this.odometry = odometry;
        addRequirements(turret);
    }

    @Override
    public void initialize() {
	    turret.stop();
    }

    @Override
    public void execute() {
        if (Math.abs(odometry.getAdjustedAngle()) > 90) {
            turret.setAngle(odometry.getAdjustedAngle());
        }
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
