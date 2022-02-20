// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunFlywheelCommand extends CommandBase {

    private ShooterSubsystem shooter;
    private double speed;

    public RunFlywheelCommand(ShooterSubsystem shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        shooter.setShooterSpeedRPM(speed);
    }

    @Override
    public void end(boolean kInterrupted) { shooter.stopShooter(); }

    @Override
    public boolean isFinished() {
        return false;
    }
}
