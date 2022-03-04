
package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunShooterAtSpeedCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private final double speed;

    /**
    * Run shooter at a constant speed
    * @param shooter subsystem
    * @param speed target RPM
     */
    public RunShooterAtSpeedCommand(ShooterSubsystem shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setShooterSpeedRPM(speed);
    }

    @Override
    public void execute() {
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
