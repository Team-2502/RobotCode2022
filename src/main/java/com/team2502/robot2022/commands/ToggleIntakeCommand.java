package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ToggleIntakeCommand extends CommandBase {

    private IntakeSubsystem intake;

    public ToggleIntakeCommand(IntakeSubsystem intake){
        this.intake = intake;
    }

    @Override
    public void initialize() { intake.toggleIntake(); }

    @Override
    public boolean isFinished(){ return true; }
}
