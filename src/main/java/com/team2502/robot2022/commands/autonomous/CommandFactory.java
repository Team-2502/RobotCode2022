package com.team2502.robot2022.commands.autonomous;

import com.team2502.robot2022.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

@FunctionalInterface
public interface CommandFactory
{
    CommandBase getInstance(
            DrivetrainSubsystem drivetrainSubsystem,
            IntakeSubsystem intakeSubsystem,
            VisionSubsystem visionSubsystem,
            ShooterSubsystem shooterSubsystem,
            TurretSubsystem turretSubsystem,
            PiVisionSubsystem piVisionSubsystem
    );
}
