package com.team2502.robot2022.commands.solenoid;

import com.team2502.robot2022.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleIntakeCommand extends InstantCommand {
    private final IntakeSubsystem intake;

    public ToggleIntakeCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize()
    {
        intake.toggleIntake();
    }
}
