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
    private boolean toggleIntake;

    public ShootCommand(ShooterSubsystem shooter, IntakeSubsystem intake, double rollerSpeed, double intakeSpeed, double beltSpeed, boolean toggleIntake) {
        this.shooter = shooter;
        this.intake = intake;
        this.rollerSpeed = rollerSpeed;
        this.intakeSpeed = intakeSpeed;
        this.beltSpeed = beltSpeed;
        this.toggleIntake = toggleIntake;

        addRequirements(intake, shooter);
    }

    @Override
    public void initialize() {
        if(toggleIntake == true){ intake.deployIntake(); }
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
        intake.retractIntake();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
