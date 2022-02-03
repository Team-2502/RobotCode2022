// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.robot2022.Constants.Motors;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HopperSubsystem extends SubsystemBase {
  //Assuming same hopper setup as 2020/2021 robot, can change motors
  private final CANSparkMax hopperSideBeltsRight; //TODO figure out wpilib reps of hardware (baby neo)
  private final CANSparkMax hopperSideBeltsLeft;
  private final CANSparkMax hopperFrontBelt;
  private final CANSparkMax hopperBackBelt;

  public HopperSubsystem() {
    hopperSideBeltsRight =  new CANSparkMax(Motors.HOPPER_SIDE_BELTS_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);
    hopperSideBeltsLeft =  new CANSparkMax(Motors.HOPPER_SIDE_BELTS_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
    hopperBackBelt =  new CANSparkMax(Motors.HOPPER_BACK_BELT, CANSparkMaxLowLevel.MotorType.kBrushless);
    hopperFrontBelt =  new CANSparkMax(Motors.HOPPER_FRONT_BELT, CANSparkMaxLowLevel.MotorType.kBrushless);
  }

  public void runLeftBelt(double speed) { hopperSideBeltsLeft.set(speed); }

  public void runRightBelt(double speed) { hopperSideBeltsRight.set(speed); }

  public void runBackBelt(double speed) { hopperBackBelt.set(speed); }

  public void runFrontBelt(double speed) { hopperFrontBelt.set(speed); }
}
