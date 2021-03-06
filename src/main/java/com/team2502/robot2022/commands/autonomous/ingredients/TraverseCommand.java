
package com.team2502.robot2022.commands.autonomous.ingredients;
import com.team2502.robot2022.subsystems.TurretSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team2502.robot2022.util.Trapezoidal;
import com.team2502.robot2022.util.Util;

public class TraverseCommand extends CommandBase {


    private final TurretSubsystem turret;
    private double goalPoint;
    private PIDController pid;
    private Trapezoidal trapezoidal;
    private boolean end;

    /**
    * Traverse Command
    * Points turret at the given position
    * @param turret turret subsystem
    * @param goalPoint point to go to in revolutions
     */
    public TraverseCommand(TurretSubsystem turret, double goalPoint) {
        this.turret = turret;

	    this.goalPoint = goalPoint;

	    end = true;

        addRequirements(turret);
    }

    /**
    * Traverse Command
    * Points turret at the given position
    * @param turret turret subsystem
    * @param goalPoint point to go to in revolutions
    * @param end stop when at setpoint
     */
    public TraverseCommand(TurretSubsystem turret, double goalPoint, boolean end) {
        this.turret = turret;

	    this.goalPoint = goalPoint;

	    this.end = end;

        addRequirements(turret);
    }

    @Override
    public void initialize()
    {
        this.pid = new PIDController(0.02,0.0,0.0);
        this.trapezoidal = new Trapezoidal(1);
        pid.setTolerance(.2);

        pid.reset();
        trapezoidal.reset();
    }

    @Override
    public void execute() {
        double error = (goalPoint-turret.getRawAngle());
        //double speed = pid.calculate(error);
        double speed = trapezoidal.calculate(pid.calculate(error));
        speed = Util.constrain(speed,.7);
        turret.runMotor(-speed);
    }

    @Override
    public boolean isFinished() {
	return pid.atSetpoint() && end;
    }
}
