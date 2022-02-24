// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntakeCommand extends CommandBase {
    private final double speedIntake;
    private final double speedBelt;

    private final IntakeSubsystem intake;

    //RunIntakeCommand is created here and asks for the subsystem and two speeds
    public RunIntakeCommand(IntakeSubsystem intake, double speedIntake, double speedBelt) {
        //sets variables to values given to command
        this.intake = intake;
        this.speedIntake = speedIntake;
        this.speedBelt = speedBelt;
        //only one command can use the subsystem at a time
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        //when initialized the command won't do anything
    }

    @Override
    //runs intakeSubsystem and sends it received speeds
    public void execute() {
        intake.run(speedIntake, speedBelt);
    }

    @Override
    //will stop intake when commands ends
    public void end(boolean kInterrupted) {
        intake.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
