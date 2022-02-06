package frc.robot.commands;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnAngleCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrain;
    private double angle;
    private double turnAngle;
    AHRS navx;
    boolean isDone = false;

    private PIDController pid = new PIDController(0.001, 0.001, 0.001);

    public TurnAngleCommand(DrivetrainSubsystem drivetrain, double turnAngle, AHRS navx) {
        this.drivetrain = drivetrain;
        this.turnAngle = turnAngle;
        this.navx = navx;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        angle = navx.getAngle();
        turnAngle = angle + turnAngle;
    }

    @Override
    public void execute() {

    }

    public boolean isFinished(){ return isDone; }
}
