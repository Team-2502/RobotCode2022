package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunClimberCommand extends CommandBase {

    private ClimberSubsystem climber;
    private double speed;

    public RunClimberCommand(ClimberSubsystem climber, double speed){
        this.climber = climber;
        this.speed = speed;

        addRequirements(climber);
    }

    @Override
    public void execute(){
        climber.runClimber(speed);
    }

    @Override
    public void end(boolean kInterrupted) { climber.stopClimber(); }
}
