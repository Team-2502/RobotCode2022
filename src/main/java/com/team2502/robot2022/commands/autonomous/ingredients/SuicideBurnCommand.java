package com.team2502.robot2022.commands.autonomous.ingredients;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team2502.robot2022.util.Trapezoidal;
import com.team2502.robot2022.util.Util;
import com.team2502.robot2022.Constants.Subsystem.Drivetrain;;

public class SuicideBurnCommand extends CommandBase {


    private final DrivetrainSubsystem drivetrain;
    private double startPos;
    private double goalPoint;
    private double goalHeading;
    private double maxSpeed;
    private PIDController pid;
    private Trapezoidal trapezoidal;
    private PIDController turnPID;
    private Trapezoidal turnTrapezoidal;
    private double decel;
    private double accel;

    private boolean endFlag;

    /**
    * Suicide Burn Command
    * moves forward the distance specified
    * similar to a SpaceX rocket, it decelerates to the target
    * goes full speed until the setpoint, then decelerates for x seconds
    * @param drivetrain drivetrain subsystem
    * @param goalPoint distance to travel in inches
    * @param speed maximum speed
    * @param decel time to stop
     */
    public SuicideBurnCommand(DrivetrainSubsystem drivetrain, double goalPoint, double speed, double accel, double decel) {
        this.drivetrain = drivetrain;
	this.maxSpeed = speed;
	this.accel = accel;
	this.decel = decel;

	this.goalPoint = goalPoint;

	endFlag = false;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize()
    {
        this.startPos = drivetrain.getInchesTraveled();
        this.trapezoidal = new Trapezoidal(accel, decel, maxSpeed, 0);
	this.turnPID = new PIDController(Drivetrain.CURVE_P,Drivetrain.CURVE_I,Drivetrain.CURVE_D);
	this.turnTrapezoidal = new Trapezoidal(Drivetrain.CURVE_T);
	this.goalHeading = drivetrain.getHeading();
	endFlag = false;
    }

    @Override
    public void execute() {

        double speed = Util.constrain(-maxSpeed,1);
	if (endFlag) {
	    speed = 0;
	}
	speed = trapezoidal.calculate(speed);
	speed = Util.frictionAdjust(speed, Drivetrain.LINE_F);

	double steering_adjust = goalHeading - drivetrain.getHeading();
        steering_adjust = turnPID.calculate(steering_adjust);
        steering_adjust = Util.constrain(steering_adjust, .1);
        steering_adjust = turnTrapezoidal.calculate(steering_adjust);
        steering_adjust = Util.frictionAdjust(steering_adjust, Drivetrain.CURVE_F);

        drivetrain.getDrive().tankDrive(steering_adjust-speed, steering_adjust+speed);

	SmartDashboard.putNumber("Drivetrain Position", -(drivetrain.getInchesTraveled()-startPos));
	SmartDashboard.putNumber("Drivetrain Goal Pos", goalPoint);
	SmartDashboard.putNumber("Drivetrain speed", (speed));

        endFlag = (drivetrain.getInchesTraveled()-startPos) > goalPoint ? true : endFlag;

    }

    @Override
    public void end(boolean interrupted) {
	    drivetrain.stop();
    }

    @Override
    public boolean isFinished() {
	return (endFlag && trapezoidal.calculate() == 0);
    }
}
