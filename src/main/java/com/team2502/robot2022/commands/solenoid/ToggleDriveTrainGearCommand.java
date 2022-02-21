package com.team2502.robot2022.commands.solenoid;

import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleDriveTrainGearCommand extends InstantCommand
{
    private final DrivetrainSubsystem drivetrain;

    public ToggleDriveTrainGearCommand(DrivetrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize()
    {
        drivetrain.toggleGear();
    }
}
