package com.team2502.robot2022.commands;

import com.team2502.robot2022.Constants.Subsystem.Climber;
import com.team2502.robot2022.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunClimberDistanceCommand extends CommandBase {

    private ClimberSubsystem climber;
    private double endPoint;
    private int pidID;

    /**
     * Moves the climber to the given position, input is distance from bottom between 0 and 31 inches
     * @param climber
     * @param endPoint
     */
    public RunClimberDistanceCommand(ClimberSubsystem climber, double endPoint){
        this.climber = climber;
        this.endPoint = endPoint;
        this.pidID = Climber.CLIMBER_UP_PID;

        addRequirements(climber);
    }

    /**
     * Moves the climber to the given position, input is distance from bottom between 0 and 31 inches
     *
     * uses the given pid instead of the default
     * @param climber
     * @param endPoint
     * @param pidID pid on talon to use (0-3)
     */
    public RunClimberDistanceCommand(ClimberSubsystem climber, double endPoint, int pidID){
        this.climber = climber;
        this.endPoint = endPoint;
        this.pidID = pidID;

        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.setWinchPID(pidID);
    }

    @Override
    public void execute(){
        climber.setWinchInches(endPoint);
    }

    @Override
    public boolean isFinished() {
        return climber.atSetpoint() && false;

    }
    @Override
    public void end(boolean kInterrupted) { climber.stopClimber(); }
}
