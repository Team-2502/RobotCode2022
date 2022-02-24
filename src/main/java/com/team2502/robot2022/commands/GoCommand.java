package com.team2502.robot2022.commands;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team2502.robot2022.util.Trapezoidal;

public class GoCommand extends CommandBase {


    private final DrivetrainSubsystem drivetrain;
    private double startPos;
    private double goalPoint;
    private PIDController pid;
    private Trapezoidal trapezoidal;

    public GoCommand(DrivetrainSubsystem drivetrain, double goalPoint) {
        this.drivetrain = drivetrain;

	this.goalPoint = goalPoint;

        addRequirements(drivetrain);
    }

    double constrain(double val, double max) {
        return Math.max(-max,Math.min(max,val));
    }

    @Override
    public void initialize()
    {
        this.startPos = drivetrain.getInchesTraveled();
        this.pid = new PIDController(0.02,0.03,0.03);
        this.trapezoidal = new Trapezoidal(.6);
        pid.setTolerance(.2);

        pid.reset();
        trapezoidal.reset();
    }

    @Override
    public void execute() {
	    double error = (drivetrain.getInchesTraveled()-startPos)+goalPoint;
	    //double speed = pid.calculate(error);
	    double speed = trapezoidal.calculate(pid.calculate(error));
        speed = constrain(speed,.3);
	    drivetrain.setSpeed(-speed,speed);
    }

    @Override
    public boolean isFinished() {
	    return pid.atSetpoint();
    }
}
