// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.subsystems;

import com.team2502.robot2022.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax intakeMotor;
    private final CANSparkMax topBelt;
    private final CANSparkMax bottomBelt;

    private final ColorSensorV3 color;

    private final Solenoid intakeDeploySolenoid;

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(Constants.RobotMap.Motors.INTAKE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
        topBelt = new CANSparkMax(Constants.RobotMap.Motors.SQUEEZE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
        bottomBelt = new CANSparkMax(Constants.RobotMap.Motors.HOPPER_BOTTOM_BELT, CANSparkMaxLowLevel.MotorType.kBrushless);

        intakeDeploySolenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.RobotMap.Solenoids.INTAKE);

	    color = new ColorSensorV3(edu.wpi.first.wpilibj.I2C.Port.kOnboard);

        intakeMotor.setSmartCurrentLimit(40);
        topBelt.setSmartCurrentLimit(25);
        bottomBelt.setSmartCurrentLimit(25);
    }

    @Override
    public void periodic() {
	    /*
	    SmartDashboard.putNumber("Color red: ", color.getRed());
	    SmartDashboard.putNumber("Color uberred: ", color.getIR());
	    SmartDashboard.putNumber("Color green: ", color.getGreen());
	    SmartDashboard.putNumber("Color blue: ", color.getBlue());
	    SmartDashboard.putString("Color: ", color.getColor().toString());
	    SmartDashboard.putBoolean("Color is red: ", hasRedBall());
	    SmartDashboard.putBoolean("Color is blue: ", hasBlueBall());
	    SmartDashboard.putNumber("Color distance: ", color.getProximity());
	    */
    }

    /**
    * returns if there is a ball in the intake
    * @return whether or not a ball is in the intake
     */
    public boolean ballBySensor() {
	    return color.getProximity() > 700;
    }

    /**
    * Is there a red ball in the hopper?
    * @return is there a red ball in the hopper
     */
    public boolean hasRedBall() {
	    return ballBySensor() && color.getRed() > 1000 && color.getBlue() < 4000;
    }

    /**
    * Is there a blue ball in the hopper?
    * @return is there a blue ball in the hopper
     */
    public boolean hasBlueBall() {
	    return ballBySensor() && color.getRed() < 1000 && color.getBlue() > 4000;
    }

    public void run(double intakeSpeed, double beltSpeed) {
        intakeMotor.set(intakeSpeed);
        topBelt.set(-beltSpeed);
        bottomBelt.set(beltSpeed);
    }

    public void runSqueeze(double speed) {
        topBelt.set(speed);
    }

    public void stop() {
        intakeMotor.stopMotor();
        bottomBelt.stopMotor();
        topBelt.stopMotor();
    }

    public void stopSqueeze() {
        topBelt.set(0);
    }

    public void deployIntake()
    {
        intakeDeploySolenoid.set(true);
    }

    public void retractIntake()
    {
        intakeDeploySolenoid.set(false);
    }

    public void toggleIntake()
    {
        intakeDeploySolenoid.set(!intakeDeploySolenoid.get());
    }
}
