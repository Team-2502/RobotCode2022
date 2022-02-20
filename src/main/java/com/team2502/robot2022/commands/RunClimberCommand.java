package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunClimberCommand extends CommandBase {

    ClimberSubsystem climberSubsystem;

    public RunClimberCommand(ClimberSubsystem climberSubsystem) {
        this.climberSubsystem = climberSubsystem;

        addRequirements(climberSubsystem);
    }


    @Override
    public void execute() {
        climberSubsystem.runClimber(0.3);
    }

    @Override
    public void end(boolean kInterrupted) { climberSubsystem.stopClimber(); }

    @Override
    public boolean isFinished() { return false; }
}