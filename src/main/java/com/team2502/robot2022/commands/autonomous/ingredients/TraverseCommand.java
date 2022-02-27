
package com.team2502.robot2022.commands.autonomous.ingredients;
import com.team2502.robot2022.subsystems.TurretSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team2502.robot2022.util.Trapezoidal;
import com.team2502.robot2022.util.Util;

public class TraverseCommand extends CommandBase {


    private final TurretSubsystem turret;
    private double startPos;
    private double goalPoint;
    private PIDController pid;
    private Trapezoidal trapezoidal;

    /**
    * Distance command
    * moves forward the distance specified
    * @param drivetrain drivetrain subsystem
    * @param goalPoint distance to travel in inches
     */
    public TraverseCommand(TurretSubsystem turret, double goalPoint) {
        this.turret = turret;

	this.goalPoint = goalPoint;

        addRequirements(turret);
    }

    @Override
    public void initialize()
    {
        this.startPos = turret.getRawAngle();
        this.pid = new PIDController(0.02,0.03,0.03);
        this.trapezoidal = new Trapezoidal(1);
        pid.setTolerance(.2);

        pid.reset();
        trapezoidal.reset();
    }

    @Override
    public void execute() {
	double error = (turret.getRawAngle()-startPos)+goalPoint;
	//double speed = pid.calculate(error);
	double speed = trapezoidal.calculate(pid.calculate(error));
        speed = Util.constrain(speed,.7);
	turret.runMotor(-speed);
    }

    @Override
    public boolean isFinished() {
	return pid.atSetpoint();
    }
}
