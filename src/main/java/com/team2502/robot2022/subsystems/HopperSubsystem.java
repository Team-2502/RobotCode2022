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
  private final CANSparkMax hopperSideBeltsRight;
  private final CANSparkMax hopperSideBeltsLeft;
  private final CANSparkMax hopperBottomBelt;
  private final CANSparkMax hopperExitWheel;

  public HopperSubsystem() {
    hopperSideBeltsRight = new CANSparkMax(Motors.HOPPER_SIDE_BELTS_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);
    hopperSideBeltsRight.setSmartCurrentLimit(25);
    hopperSideBeltsLeft = new CANSparkMax(Motors.HOPPER_SIDE_BELTS_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
    hopperSideBeltsLeft.setSmartCurrentLimit(25);
    hopperBottomBelt = new CANSparkMax(Motors.HOPPER_BOTTOM_BELT, CANSparkMaxLowLevel.MotorType.kBrushless);
    hopperBottomBelt.setSmartCurrentLimit(25);
    hopperExitWheel = new CANSparkMax(Motors.HOPPER_EXIT_WHEEL, CANSparkMaxLowLevel.MotorType.kBrushless);
    hopperExitWheel.setSmartCurrentLimit(25);

    hopperSideBeltsRight.setInverted(true);
    hopperExitWheel.setInverted(false);
  }

  //Will change methods to match 2022 hopper construction
  public void runLeftBelt(double speed) {
    hopperSideBeltsLeft.set(speed);
  }

  public void runRightBelt(double speed) {
    hopperSideBeltsRight.set(speed);
  }

  public void runExitWheel(double speed) {
    hopperExitWheel.set(speed);
  }

  public void runBottomBelt(double speed) {
    hopperBottomBelt.set(speed);
  }



  //Nothing periodic needed; nothing on shuffleboard
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
