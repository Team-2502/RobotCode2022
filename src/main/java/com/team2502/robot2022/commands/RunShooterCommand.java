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
    private final boolean useTestVal;

    public RunShooterCommand(ShooterSubsystem shooter, VisionSubsystem vision, double defaultSpeed, boolean useTestVal) {
        this.shooter = shooter;
        this.vision = vision;
        this.defaultSpeed = defaultSpeed;
        this.useTestVal = useTestVal;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setShooterSpeedRPM(defaultSpeed);
        vision.limelightOn();
    }

    @Override
    public void execute() {
        vision.limelightOn();
        if(vision.getTargetArea() > 0) {
            if (useTestVal) {
                shooter.setShooterSpeedRPM(vision.getAdjustedShooterSpeed());
            } else {
                shooter.setShooterSpeedRPM(vision.getOptimalShooterSpeed());
            }
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
