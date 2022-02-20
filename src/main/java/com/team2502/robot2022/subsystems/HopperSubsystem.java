// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.robot2022.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HopperSubsystem extends SubsystemBase {
  private final CANSparkMax hopperTopBelt;
  private final CANSparkMax hopperBottomBelt;

  public HopperSubsystem() {
    hopperTopBelt =  new CANSparkMax(Motors.HOPPER_TOP_BELT, CANSparkMaxLowLevel.MotorType.kBrushless);
    hopperBottomBelt =  new CANSparkMax(Motors.HOPPER_BOTTOM_BELT, CANSparkMaxLowLevel.MotorType.kBrushless);
  }

  public void runTopBelt(double speed) { hopperTopBelt.set(speed); }

  public void runBottomBelt(double speed) { hopperBottomBelt.set(speed); }

  public void runBothBelt(double speed) {
    hopperTopBelt.set(speed);
    hopperBottomBelt.set(speed);
  }

  public void stop() {
    hopperTopBelt.set(0);
    hopperBottomBelt.set(0);
  }
}
