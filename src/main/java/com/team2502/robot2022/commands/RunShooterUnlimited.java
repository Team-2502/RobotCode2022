

package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.ShooterSubsystem;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunShooterUnlimited extends CommandBase {
    private final ShooterSubsystem shooter;

    /**
    * Run shooter at a 13.6 volts, and unattainable goal
    * @param shooter subsystem
     */
    public RunShooterUnlimited(ShooterSubsystem shooter) {
        this.shooter = shooter;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setShooterVoltage(13.6);
    }

    @Override
    public void execute() {
        shooter.setShooterVoltage(13.6);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopShooter();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
