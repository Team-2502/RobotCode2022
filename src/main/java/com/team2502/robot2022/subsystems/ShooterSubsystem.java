package com.team2502.robot2022.subsystems;

import com.revrobotics.*;
import com.team2502.robot2022.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax shooterRight;
    private final CANSparkMax shooterLeft;

    public final SparkMaxPIDController rightPID;
    private final RelativeEncoder rightEncoder;

    private final CANSparkMax loadMotor1;
    private final CANSparkMax loadMotor2;

    private double target;

    public ShooterSubsystem() {
        shooterLeft = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
        shooterRight = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);

        loadMotor1 = new CANSparkMax(Constants.RobotMap.Motors.SUSHI_MOTOR_1, CANSparkMaxLowLevel.MotorType.kBrushless);
        loadMotor2 = new CANSparkMax(Constants.RobotMap.Motors.SUSHI_MOTOR_2, CANSparkMaxLowLevel.MotorType.kBrushless);

        shooterRight.follow(shooterLeft, true); // follow right motor, inverted

        SmartDashboard.putNumber("Shooter Target Velocity", 0);
	    target = 0;
	SmartDashboard.putNumber("NT RPM", 0); // for NT command

        shooterLeft.setSmartCurrentLimit(39);
        shooterRight.setSmartCurrentLimit(39);

        rightPID = shooterLeft.getPIDController();
        rightEncoder = shooterRight.getEncoder();

        setupPID();
    }

    @Override
    public void periodic() { 
	    SmartDashboard.putNumber("Shooter Velocity", rightEncoder.getVelocity()); 
	    SmartDashboard.putBoolean("Within Constraints", atSpeed());

    }

    public void setShooterSpeedRPM(double speed) {
        rightPID.setReference(speed, CANSparkMax.ControlType.kVelocity);
        SmartDashboard.putNumber("Shooter Target Velocity", speed);
	target = speed;
    }

    /**
    * is the shooter rpm within the defined constraints
    * @return is shooter at speed
     */
    public boolean atSpeed() {
	    return (target != 0 && Math.abs(rightEncoder.getVelocity() - target) < Constants.Subsystem.Shooter.RPM_GOOD);
    }

    public void loadBalls(double speed) {
        loadMotor1.set(speed);
        loadMotor2.set(-speed);
    }

    public void stopShooter() {
        rightPID.setReference(0, CANSparkMax.ControlType.kVoltage);
        SmartDashboard.putNumber("Shooter Target Velocity", 0);
	target = 0;
    }

    public double getTargetVelocity() {
	    return target;
    }

    public void stopLoader() {
        loadMotor1.stopMotor();
        loadMotor2.stopMotor();
    }

    public boolean isShooterRunning() { return shooterLeft.get() != 0 || shooterRight.get() != 0; }

    public void setupPID() {
        rightPID.setP(Constants.Subsystem.Shooter.SHOOTER_P);
        rightPID.setI(Constants.Subsystem.Shooter.SHOOTER_I);
        rightPID.setD(Constants.Subsystem.Shooter.SHOOTER_D);
        rightPID.setIZone(Constants.Subsystem.Shooter.SHOOTER_IZ);
        rightPID.setFF(Constants.Subsystem.Shooter.SHOOTER_FF);
        rightPID.setOutputRange(Constants.Subsystem.Shooter.SHOOTER_MIN_OUTPUT, Constants.Subsystem.Shooter.SHOOTER_MAX_OUTPUT);
        shooterRight.burnFlash();
    }

}
