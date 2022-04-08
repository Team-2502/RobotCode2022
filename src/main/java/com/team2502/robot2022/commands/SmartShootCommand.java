// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.IntakeSubsystem;
import com.team2502.robot2022.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SmartShootCommand extends CommandBase {

    private ShooterSubsystem shooter;
    private IntakeSubsystem intake;
    private double intakeSpeed;
    private double rollerSpeed;
    private double beltSpeed;
    private double speed;
    private boolean toggleIntake;

    public SmartShootCommand(ShooterSubsystem shooter, IntakeSubsystem intake, double rollerSpeed, double intakeSpeed, double beltSpeed, boolean toggleIntake) {
        this.shooter = shooter;
        this.intake = intake;
        this.rollerSpeed = rollerSpeed;
        this.intakeSpeed = intakeSpeed;
        this.beltSpeed = beltSpeed;
        this.toggleIntake = toggleIntake;

        addRequirements(intake);
    }

    @Override
    public void initialize() {
        if(toggleIntake == true){ intake.deployIntake(); }
        intake.setBelt();
    }

    @Override
    public void execute() {
        if (shooter.atSpeed()) {
            shooter.loadBalls(rollerSpeed);
            intake.run(intakeSpeed, beltSpeed);
        } else {
            shooter.loadBalls(-rollerSpeed/2);
            intake.run(intakeSpeed/2, beltSpeed/2);
        }

    }

    @Override
    public void end(boolean kInterrupted) {
        shooter.stopLoader();
        intake.stop();
        intake.retractIntake();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
