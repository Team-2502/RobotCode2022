package frc.robot.commands;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.Trapezoidal;

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

    @Override
    public void initialize()
    {
	this.startPos = drivetrain.getInchesTraveled();
	this.pid = new PIDController(0.06,0.05,0.03);
	this.trapezoidal = new Trapezoidal(.008);
	pid.setTolerance(.2);

        //drivetrain.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void execute() {
	    double error = (drivetrain.getInchesTraveled()-startPos)+goalPoint;
	    //double speed = pid.calculate(error);
	    double speed = trapezoidal.calculate(pid.calculate(error));
	    drivetrain.setSpeed(-speed,speed);
    }

    @Override
    public boolean isFinished() {
	    return pid.atSetpoint();
    }
}
