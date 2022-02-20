// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import static com.team2502.robot2022.Constants.RobotMap.Motors.*;

public class IntakeSubsystem extends SubsystemBase {
  private final CANSparkMax intakeMotor;
  private final CANSparkMax squeezeMotor;
  private final CANSparkMax bottomBelt;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(INTAKE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    intakeMotor.setSmartCurrentLimit(25);
    squeezeMotor = new CANSparkMax(SQUEEZE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    squeezeMotor.setSmartCurrentLimit(25);
    bottomBelt = new CANSparkMax(HOPPER_BOTTOM_BELT, CANSparkMaxLowLevel.MotorType.kBrushless);
    bottomBelt.setSmartCurrentLimit(25);
  }

  public void run(double intakeSpeed, double beltSpeed) {
    intakeMotor.set(intakeSpeed);
    squeezeMotor.set(beltSpeed);
    bottomBelt.set(beltSpeed);
  }

  public void runSqueeze(double speed) {
    squeezeMotor.set(speed);
  }

  public void stop() {
    intakeMotor.stopMotor();
    bottomBelt.stopMotor();
    squeezeMotor.stopMotor();
  }

  public void stopSqueeze() {
    squeezeMotor.set(0);
  }
}
