package com.team2502.robot2022.subsystems;

import com.revrobotics.*;
import com.team2502.robot2022.Constants;

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
        shooterLeft = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
        shooterLeft.setSmartCurrentLimit(35);
        shooterRight = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);
        shooterRight.setSmartCurrentLimit(35);
        shooterLeft.follow(shooterRight, true); // follow right motor, inverted

        loadMotor1 = new CANSparkMax(Constants.RobotMap.Motors.LOAD_MOTOR_1, CANSparkMaxLowLevel.MotorType.kBrushless);
        loadMotor2 = new CANSparkMax(Constants.RobotMap.Motors.LOAD_MOTOR_2, CANSparkMaxLowLevel.MotorType.kBrushless);

        rightPID = shooterRight.getPIDController();
        rightEncoder = shooterRight.getEncoder();

        setupPID();
    }

    @Override
    public void periodic() { SmartDashboard.putNumber("Shooter Velocity", rightEncoder.getVelocity()); }

    public void setShooterSpeedRPM(double speed) {
        rightPID.setReference(speed, CANSparkMax.ControlType.kVelocity);
        SmartDashboard.putNumber("Shooter Target Velocity", speed);
    }

    public void loadBalls(double speed) {
        loadMotor1.set(speed);
        loadMotor2.set(speed);
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
        rightPID.setP(Constants.Subsystem.Shooter.SHOOTER_P);
        rightPID.setI(Constants.Subsystem.Shooter.SHOOTER_I);
        rightPID.setD(Constants.Subsystem.Shooter.SHOOTER_D);
        rightPID.setIZone(Constants.Subsystem.Shooter.SHOOTER_IZ);
        rightPID.setFF(Constants.Subsystem.Shooter.SHOOTER_FF);
        rightPID.setOutputRange(Constants.Subsystem.Shooter.SHOOTER_MIN_OUTPUT, Constants.Subsystem.Shooter.SHOOTER_MAX_OUTPUT);
        shooterRight.burnFlash();
    }

}
