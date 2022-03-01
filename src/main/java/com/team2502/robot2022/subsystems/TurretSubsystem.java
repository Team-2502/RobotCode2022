package com.team2502.robot2022.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.team2502.robot2022.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretSubsystem extends SubsystemBase {

    /**
     * For representing the CANSparkMax(from WPIlib) that represents the motor controller for the baby Neo that turns the turret
     */
    public final CANSparkMax turnMotor;

    public final RelativeEncoder turnMotorEncoder;


    public TurretSubsystem(){
        //Create a CANSparkMax(from WPIlib) object.
        //Properties: ID of a variable in Constants, controlling a Baby Neo(kBrushless)
        turnMotor = new CANSparkMax(Constants.RobotMap.Motors.TURRET_TRAVERSE, CANSparkMaxLowLevel.MotorType.kBrushless);
        /*
        * Set a current limit for the motor(so it doesn't spin too fast and break the turret) with the setSmartCurrentLimit
        * method from WPIlib under the turnMotor object.
        */
        turnMotor.setSmartCurrentLimit(20);

	turnMotor.setSoftLimit(SoftLimitDirection.kForward,Constants.Subsystem.Turret.LIMIT_MAX);
	turnMotor.setSoftLimit(SoftLimitDirection.kReverse,Constants.Subsystem.Turret.LIMIT_MIN);
	turnMotor.enableSoftLimit(SoftLimitDirection.kForward,true);
	turnMotor.enableSoftLimit(SoftLimitDirection.kReverse,true);

        turnMotorEncoder = turnMotor.getEncoder();

    }
    @Override
    public void periodic(){ //Does not run anything constantly
        SmartDashboard.putNumber("Turret pos", turnMotorEncoder.getPosition());
    }

    /**
     * Set the speed of the motor to the parameter speed, using the WPIlib set() method under the turnMotor object
     * @param speed Speed to run the motor at
     */
    public void runMotor(double speed){
        turnMotor.set(speed);
    }

    public double getAngle()
    {
        return turnMotorEncoder.getPosition() * 360D * Constants.Subsystem.Turret.TURRET_GEAR_RATIO;
    }

    public double getRawAngle() {
	    return turnMotorEncoder.getPosition();
    }

    public double getAngleVel()
    {
        return turnMotorEncoder.getVelocity() * 360D * Constants.Subsystem.Turret.TURRET_GEAR_RATIO;
    }

    /**
     * Method to stop the motor, using the WPIlib set() method under the turnMotor object
     */
    public void stop(){
        turnMotor.set(0);
    }
}
