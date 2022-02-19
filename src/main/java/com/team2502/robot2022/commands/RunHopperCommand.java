// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;


public class RunHopperCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final HopperSubsystem hopper;
  private final double speedLeft;
  private final double speedRight;
  private final double speedBack;
  private final double speedFront;

  public RunHopperCommand(HopperSubsystem hopper,
                          double speedLeft,
                          double speedRight,
                          double speedBack,
                          double speedFront) {
    this.speedLeft = speedLeft;
    this.speedRight = speedRight;
    this.speedBack = speedBack;
    this.speedFront = speedFront;
    this.hopper = hopper;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(hopper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hopper.runLeftBelt(speedLeft);
    hopper.runRightBelt(speedRight);
    hopper.runBackBelt(speedBack);
    hopper.runFrontBelt(speedFront);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hopper.runLeftBelt(0);
    hopper.runRightBelt(0);
    hopper.runBackBelt(0);
    hopper.runFrontBelt(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
