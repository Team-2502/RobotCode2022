package com.team2502.robot2022.commands.autonomous.ingredients;

import com.team2502.robot2022.subsystems.TurretSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;

import com.team2502.robot2022.Constants.Subsystem.Turret;

public class TurnTurretCommand extends CommandBase {

    private VisionSubsystem vision;
    private TurretSubsystem turret;

    ProfiledPIDController pPIDC;
    private TrapezoidProfile.State setpoint;

    public TurnTurretCommand(TurretSubsystem turret, VisionSubsystem vision) {
        pPIDC = new ProfiledPIDController(
                Turret.TURRET_P,
                Turret.TURRET_I,
                Turret.TURRET_D,
                new TrapezoidProfile.Constraints(Turret.MAX_VEL, Turret.MAX_ACCEL)
        );

        this.vision = vision;
        this.turret = turret;

        pPIDC.enableContinuousInput(Turret.MIN_ANGLE, Turret.MAX_ANGLE); // TODO find real values
        pPIDC.setTolerance(Turret.TURN_TOLERANCE_DEG, Turret.TURN_RATE_TOLERANCE_DEG_PER_S);

        addRequirements(turret, vision);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void initialize()
    {
        pPIDC.reset(turret.getAngle());
    }

    @Override
    public void execute()
    {
        setpoint = new TrapezoidProfile.State(vision.getTargetX() + turret.getAngle(), turret.getAngleVel() + (vision.getLastTX() - vision.getTargetX()) * 50);
        turret.runMotor(pPIDC.calculate(turret.getAngle(), setpoint));
    }

    @Override
    public void end(boolean interrupted)
    {
        turret.stop();
    }
}
