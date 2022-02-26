package com.team2502.robot2022.commands.vision;

import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.TurretSubsystem;
import com.team2502.robot2022.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public final class VisionAlignTurret extends CommandBase {
    private final VisionSubsystem vision;
    private final TurretSubsystem turret;
    double backOfRange;

    private boolean seesTarget;

    private double p;
    private double frictionConstant;

    public VisionAlignTurret(VisionSubsystem vision, TurretSubsystem turret){
        this.vision = vision;
        this.turret = turret;
        seesTarget = false;
        addRequirements(turret);
    }

    @Override
    public void initialize() {
        frictionConstant = Constants.Subsystem.Vision.FRICTION_TURRET;
        p = Constants.Subsystem.Vision.FRICTION_P;
    }

    @Override
    public void execute() {

        double tx = vision.getTargetX();
        double steering_adjust = 0.0f;

        seesTarget = vision.getTargetArea() != 0.0;

        if(seesTarget) {
            double power = 0;

            boolean useFriction = power < frictionConstant;
            double frictionVal = useFriction ? frictionConstant : 0;

            if (tx > 1.0) {
                steering_adjust = p * tx + frictionVal;
            } else if (tx < 1.0) {    // robot needs to turn left
                steering_adjust = p * tx - frictionVal;
            }
            turret.runMotor(-steering_adjust);
        }
        else {
            turret.stop();
        }
    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public void end(boolean interrupted) {
	turret.stop();
    }
}
