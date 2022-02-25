package com.team2502.robot2022.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.robot2022.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {

    /**
     * For representing the CANSparkMax(from WPIlib) that represents the motor controller for the baby Neo that turns the turret
     */
    public final CANSparkMax turnMotor;

    public TurretSubsystem(){
        //Create a CANSparkMax(from WPIlib) object.
        //Properties: ID of a variable in Constants, controlling a Baby Neo(kBrushless)
        turnMotor = new CANSparkMax(Constants.RobotMap.Motors.TURRET_TRAVERSE, CANSparkMaxLowLevel.MotorType.kBrushless);
        /*
        * Set a current limit for the motor(so it doesn't spin too fast and break the turret) with the setSmartCurrentLimit
        * method from WPIlib under the turnMotor object.
        */
        turnMotor.setSmartCurrentLimit(20);
    }
    @Override
    public void periodic(){ //Does not run anything constantly
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
        return 0D; //TODO Make this method
    }

    /**
     * Method to stop the motor, using the WPIlib set() method under the turnMotor object
     */
    public void stop(){
        turnMotor.set(0);
    }
}
