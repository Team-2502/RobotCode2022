package frc.robot.subsystems;

import com.revrobotics.*;
import frc.robot.Constants.RobotMap.*;
import frc.robot.Constants.Subsystem.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax shooterRight;
    private final CANSparkMax shooterLeft;

    public final SparkMaxPIDController rightPID;
    private final CANEncoder rightEncoder;

    private final CANSparkMax loadMotor1;
    private final CANSparkMax loadMotor2;

    public ShooterSubsystem() {
        shooterLeft = new CANSparkMax(Motors.SHOOTER_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
        shooterRight = new CANSparkMax(Motors.SHOOTER_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);

        loadMotor1 = new CANSparkMax(Motors.SUSHI_MOTOR_1, CANSparkMaxLowLevel.MotorType.kBrushless);
        loadMotor2 = new CANSparkMax(Motors.SUSHI_MOTOR_2, CANSparkMaxLowLevel.MotorType.kBrushless);

        shooterRight.follow(shooterLeft, true); // follow right motor, inverted

        shooterLeft.setSmartCurrentLimit(39);
        shooterRight.setSmartCurrentLimit(39);

        rightPID = shooterLeft.getPIDController();
        rightEncoder = shooterRight.getEncoder();

        setupPID();
    }

    @Override
    public void periodic() { SmartDashboard.putNumber("Shooter Velocity", rightEncoder.getVelocity()); }

    public void setShooterSpeedRPM(double speed) {
        rightPID.setReference(-speed, CANSparkMax.ControlType.kVelocity);
        SmartDashboard.putNumber("Shooter Target Velocity", speed);
    }

    public void loadBalls(double speed) {
        loadMotor1.set(speed);
        loadMotor2.set(-speed);
    }

    public void stopShooter() {
        shooterRight.set(0);
    }

    public void stopLoader() {
        loadMotor1.stopMotor();
        loadMotor2.stopMotor();
    }

    public boolean isShooterRunning() { return shooterLeft.get() != 0 || shooterRight.get() != 0; }

    public void setupPID() {
        rightPID.setP(Shooter.SHOOTER_P);
        rightPID.setI(Shooter.SHOOTER_I);
        rightPID.setD(Shooter.SHOOTER_D);
        rightPID.setIZone(Shooter.SHOOTER_IZ);
        rightPID.setFF(Shooter.SHOOTER_FF);
        rightPID.setOutputRange(Shooter.SHOOTER_MIN_OUTPUT, Shooter.SHOOTER_MAX_OUTPUT);
        shooterRight.burnFlash();
    }

}
