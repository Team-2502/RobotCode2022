package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.TurretSubsystem;
import com.team2502.robot2022.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunShooterNTCommand extends CommandBase {
    private final ShooterSubsystem shooter;

    public RunShooterNTCommand(ShooterSubsystem shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setShooterSpeedRPM(0); // stop on init
    }

    @Override
    public void execute() {
        double speedInput = SmartDashboard.getNumber("NT RPM", 0); // get speed from network table
        shooter.setShooterSpeedRPM(speedInput);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopShooter(); // this will coast
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
