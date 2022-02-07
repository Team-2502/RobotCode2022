package frc.robot.commands;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.Trapezoidal;
import frc.robot.util.Util;

public class TurnAngleCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrain;
    private double turnAngle;
    private double startAngle;
    AHRS navx;
    Trapezoidal trapezoidal;

    private PIDController pid;

    public TurnAngleCommand(DrivetrainSubsystem drivetrain, double turnAngle, AHRS navx) {
        this.drivetrain = drivetrain;
        this.turnAngle = turnAngle;
        this.navx = navx;
	this.trapezoidal = new Trapezoidal(0.3);
	this.pid = new PIDController(0.12, 0.03, 0.002);
	this.pid.setTolerance(3.5); // quit when within 3.5 degrees

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        startAngle = navx.getAngle();
        turnAngle = startAngle + turnAngle;
	pid.reset();
	trapezoidal.reset();
    }

    @Override
    public void execute() {
	double error = navx.getAngle() - turnAngle;
	SmartDashboard.putNumber("error", error);

	double speed = trapezoidal.calculate(pid.calculate(error));
	
	speed = Util.constrain(speed,.3); // keep speed from exceeding 30%

	SmartDashboard.putNumber("speed", speed);

	drivetrain.setSpeed(speed, speed);

    }

    public boolean isFinished(){ return pid.atSetpoint(); }
}
