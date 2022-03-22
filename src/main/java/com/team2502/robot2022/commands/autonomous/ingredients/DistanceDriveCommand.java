package com.team2502.robot2022.commands.autonomous.ingredients;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team2502.robot2022.util.Trapezoidal;
import com.team2502.robot2022.util.Util;
import com.team2502.robot2022.Constants.Subsystem.Drivetrain;;

public class DistanceDriveCommand extends CommandBase {


    private final DrivetrainSubsystem drivetrain;
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
    public DistanceDriveCommand(DrivetrainSubsystem drivetrain, double goalPoint) {
        this.drivetrain = drivetrain;

	this.goalPoint = goalPoint;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize()
    {
        this.startPos = drivetrain.getInchesTraveled();
        this.pid = new PIDController(Drivetrain.LINE_P,Drivetrain.LINE_I,Drivetrain.LINE_D);
        this.trapezoidal = new Trapezoidal(.6);
        pid.setTolerance(.2);

        pid.reset();
        trapezoidal.reset();
    }

    @Override
    public void execute() {
	double error = (drivetrain.getInchesTraveled()-startPos)+goalPoint;

	double speed = pid.calculate(error);
        speed = Util.constrain(speed,1); // constrain before ramp to reduce overshoot
	speed = trapezoidal.calculate(speed);
	speed = Util.frictionAdjust(speed, Drivetrain.LINE_F);
	drivetrain.setSpeed(-speed,speed);
    }

    @Override
    public boolean isFinished() {
	return pid.atSetpoint();
    }
}
