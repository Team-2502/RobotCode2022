package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ToggleClimberSolenoidCommand extends CommandBase {
    ClimberSubsystem climber;

    public ToggleClimberSolenoidCommand(ClimberSubsystem climber) {
        this.climber = climber;
    }

    @Override
    public void initialize() { climber.releaseClimber(); }
}
