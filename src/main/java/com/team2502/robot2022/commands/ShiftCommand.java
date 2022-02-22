package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShiftCommand extends CommandBase {

    DrivetrainSubsystem drivetrain;

    public ShiftCommand(DrivetrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() { drivetrain.toggleDrivetrain();}

    @Override
    public boolean isFinished() {
        return true;
    }
}
