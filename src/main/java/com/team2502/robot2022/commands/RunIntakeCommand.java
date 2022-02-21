// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robote.commands;

import frc.robote.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntakeCommand extends CommandBase {
    private final double speedIntake;
    private final double speedBelt;

    private final IntakeSubsystem intake;

    public RunIntakeCommand(IntakeSubsystem intake, double speedIntake, double speedBelt) {
        this.intake = intake;
        this.speedIntake = speedIntake;
        this.speedBelt = speedBelt;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        intake.run(speedIntake, speedBelt);
    }

    @Override
    public void end(boolean kInterrupted) {
        intake.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
