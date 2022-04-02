
package com.team2502.robot2022.commands.autonomous.ingredients;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinFlywheelCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private final double speed;

    public SpinFlywheelCommand(ShooterSubsystem shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setShooterSpeedRPM(speed);
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
