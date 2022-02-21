package com.team2502.robot2022.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.robot2022.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {
    public final CANSparkMax turnMotor;

    public TurretSubsystem(){
        turnMotor = new CANSparkMax(Constants.RobotMap.Motors.TURRET_TRAVERSE, CANSparkMaxLowLevel.MotorType.kBrushless);
        turnMotor.setSmartCurrentLimit(20);
    }
    @Override
    public void periodic(){
    }
    public void runMotor(double speed){
        turnMotor.set(speed);
    }
    public void stop(){
        turnMotor.set(0);
    }
}
