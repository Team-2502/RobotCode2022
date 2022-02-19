// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import static com.team2502.robot2022.Constants.RobotMap.Motors.INTAKE_MOTOR;
import static com.team2502.robot2022.Constants.RobotMap.Motors.SQUEEZE_MOTOR;

public class IntakeSubsystem extends SubsystemBase {
  private final CANSparkMax intakeMotor;
  private final CANSparkMax squeezeMotor;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(INTAKE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    intakeMotor.setSmartCurrentLimit(25);
    squeezeMotor = new CANSparkMax(SQUEEZE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    squeezeMotor.setSmartCurrentLimit(25);
  }

  public void runIntake(double speed) {
    intakeMotor.set(speed);
  }

  public void runSqueeze(double speed) {
    squeezeMotor.set(speed);
  }

  public void stopIntake() {
    intakeMotor.set(0);
  }

  public void stopSqueeze() {
    squeezeMotor.set(0);
  }
}
