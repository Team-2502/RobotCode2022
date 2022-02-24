package com.team2502.robot2022.commands.autonomous.ingredients;

import com.team2502.robot2022.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class VoltageDriveCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrain;
    private final double leftVolts;
    private final double rightVolts;

    public VoltageDriveCommand(DrivetrainSubsystem drivetrain, double leftVolts, double rightVolts) {
        this.drivetrain = drivetrain;
        this.leftVolts = leftVolts;
        this.rightVolts = rightVolts;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.getDrive().tankDrive(leftVolts, rightVolts);
    }

    @Override
    public void execute() {
        drivetrain.getDrive().tankDrive(leftVolts, rightVolts);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.getDrive().tankDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
