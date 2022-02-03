package com.team2502.robot2022.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.WPI_MotorSafetyImplem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.team2502.robot2022.Constants;
import com.team2502.robot2022.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase{
    private DifferentialDrive drive;

    public DrivetrainSubsystem(){
        WPI_TalonFX drivetrainBackLeft = new WPI_TalonFX(Motors.DRIVE_BACK_LEFT);
        WPI_TalonFX drivetrainFrontLeft = new WPI_TalonFX(Motors.DRIVE_FRONT_LEFT);
        WPI_TalonFX drivetrainFrontRight = new WPI_TalonFX(Motors.DRIVE_FRONT_RIGHT);
        WPI_TalonFX drivetrainBackRight = new WPI_TalonFX(Motors.DRIVE_BACK_RIGHT);

        drivetrainBackLeft.follow(drivetrainFrontLeft);//backleft follows front left motor
        drivetrainBackRight.follow(drivetrainFrontRight);

        drivetrainFrontRight.setInverted(TalonFXInvertType.CounterClockwise);
        drivetrainFrontLeft.setInverted(TalonFXInvertType.CounterClockwise);
        drivetrainFrontRight.setNeutralMode(NeutralMode.Brake);
        drivetrainFrontLeft.setNeutralMode(NeutralMode.Brake);
        drive = new DifferentialDrive(drivetrainFrontLeft, drivetrainFrontRight);
    }
    public DifferentialDrive getDrive(){
        return drive;
    }

    public void setSpeed(double leftSpeed, double rightSpeed){
        drive.tankDrive(leftSpeed, rightSpeed);
    }
    public void brake(){
        drive.stopMotor();
    }

}
