// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootCommand extends CommandBase {

    private ShooterSubsystem shooter;
    private double speed;

    public ShootCommand(ShooterSubsystem shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        shooter.loadBalls(speed);
    }

    @Override
    public void end(boolean kInterrupted) { shooter.stopLoader(); }

    @Override
    public boolean isFinished() {
        return false;
    }
}
