// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package com.team2502.robot2022.commands.autonomous.ingredients;

import com.team2502.robot2022.subsystems.IntakeSubsystem;
import com.team2502.robot2022.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootCommand extends CommandBase {

    private ShooterSubsystem shooter;
    private IntakeSubsystem intake;
    private double intakeSpeed;
    private double rollerSpeed;
    private double beltSpeed;
    private double speed;

    public ShootCommand(ShooterSubsystem shooter, IntakeSubsystem intake, double rollerSpeed, double intakeSpeed, double beltSpeed) {
        this.shooter = shooter;
        this.intake = intake;
        this.rollerSpeed = rollerSpeed;
        this.intakeSpeed = intakeSpeed;
        this.beltSpeed = beltSpeed;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        shooter.loadBalls(rollerSpeed);
        intake.run(intakeSpeed, beltSpeed);
    }

    @Override
    public void end(boolean kInterrupted) {
        shooter.stopLoader();
        intake.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
