package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunShooterCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private final VisionSubsystem vision;
    private final double defaultSpeed;

    public RunShooterCommand(ShooterSubsystem shooter, VisionSubsystem vision, double defaultSpeed) {
        this.shooter = shooter;
        this.vision = vision;
        this.defaultSpeed = defaultSpeed;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setShooterSpeedRPM(defaultSpeed);
    }

    @Override
    public void execute() {
        if(vision.getTargetArea() > 0) {
            shooter.setShooterSpeedRPM(vision.getOptimalShooterSpeed());
        }
        else {
            shooter.setShooterSpeedRPM(defaultSpeed);
        }
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
