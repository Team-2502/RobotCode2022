package com.team2502.robot2022.commands.autonomous.ingredients;

import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FreezeCommand extends CommandBase {
    VisionSubsystem vision;
    IntakeSubsystem intake;
    DrivetrainSubsystem drivetrain;
    TurretSubsystem turret;
    ShooterSubsystem shooter;

    public FreezeCommand(VisionSubsystem vision, IntakeSubsystem intake, DrivetrainSubsystem drivetrain,
                         TurretSubsystem turret, ShooterSubsystem shooter) {

        this.vision = vision;
        this.intake = intake;
        this.drivetrain = drivetrain;
        this.turret = turret;
        this.shooter = shooter;

        addRequirements(vision, intake, drivetrain, turret, shooter);
    }

    @Override
    public void execute() {
        vision.limelightOff();
        intake.stop();
        drivetrain.stop();
        turret.stop();
        shooter.stopShooter();
        shooter.stopLoader();
    }
}
