package com.team2502.robot2022.commands.autonomous.ingredients;

import com.team2502.robot2022.subsystems.TurretSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;

import com.team2502.robot2022.Constants.Subsystem.Turret;

public class TurnTurretCommand extends ProfiledPIDCommand {

    public TurnTurretCommand(TurretSubsystem turret, VisionSubsystem vision) {
        super(new ProfiledPIDController(
                        Turret.TURRET_P,
                        Turret.TURRET_I,
                        Turret.TURRET_D,
                        new TrapezoidProfile.Constraints(Turret.MAX_VEL, Turret.MAX_ACCEL)
                        ),
                turret::getAngle,
                vision::getTurretSetpoint,
                (t, u) -> turret.runMotor(t),
                turret,
                vision
        );

        getController().enableContinuousInput(Turret.MIN_ANGLE, Turret.MAX_ANGLE);
        getController().setTolerance(Turret.TURN_TOLERANCE_DEG, Turret.TURN_RATE_TOLERANCE_DEG_PER_S);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
