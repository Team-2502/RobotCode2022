
package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunSingleWinchCommand extends CommandBase {

    private ClimberSubsystem climber;
    private double speed;
    private Winch winch;

    public enum Winch {
	    LEFT,
	    RIGHT
    }

    /**
    * Runs one of the winches on the climber
    * @param climber subsystem
    * @param speed rate to run winch
    * @param winch which winch to run
     */
    public RunSingleWinchCommand(ClimberSubsystem climber, double speed, Winch winch){
        this.climber = climber;
        this.speed = speed;
        this.winch = winch;

        addRequirements(climber);
    }

    @Override
    public void execute() {
        if (winch == Winch.LEFT) {
            climber.runLeftClimber(speed);
        } else if (winch == Winch.RIGHT) {
            climber.runRightClimber(speed);
        }
    }

    @Override
    public void end(boolean kInterrupted) { climber.stopClimber(); }
}
