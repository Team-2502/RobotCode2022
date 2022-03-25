package com.team2502.robot2022.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.ControlType;
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

	if (SmartDashboard.getNumber("TURRET_P", -1) == -1 && Constants.Subsystem.Turret.TURRET_NT_TUNE) {
		NTInit();
	}
    }
    @Override
    public void periodic(){ //Does not run anything constantly
        SmartDashboard.putNumber("Turret pos", turnMotorEncoder.getPosition());
        SmartDashboard.putNumber("Turret angle", getAngle());

	if (Constants.Subsystem.Shooter.SHOOTER_NT_TUNE) {
	    NTUpdate();
	}
    }

    /**
     * Set the speed of the motor to the parameter speed, using the WPIlib set() method under the turnMotor object
     * @param speed Speed to run the motor at
     */
    public void runMotor(double speed){
        turnMotor.getPIDController().setReference(speed * turnMotor.getBusVoltage(), ControlType.kVoltage);
    }

    /**
     * set the angle of the turret using the rev pid
     * @param angle angle to put the turret at
     */
    public void setAngle(double angle){
        turnMotor.getPIDController().setReference(
			(angle + Constants.Subsystem.Turret.TURRET_OFFSET) / 
			(360D * Constants.Subsystem.Turret.TURRET_GEAR_RATIO),
			ControlType.kSmartMotion);
    }

    public double getAngle()
    {
        return (turnMotorEncoder.getPosition() * 360D * Constants.Subsystem.Turret.TURRET_GEAR_RATIO) - Constants.Subsystem.Turret.TURRET_OFFSET;
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

    public void NTUpdate() {
        turnMotor.getPIDController().setP(SmartDashboard.getNumber("TURRET_P", 0));
        turnMotor.getPIDController().setI(SmartDashboard.getNumber("TURRET_I",0));
        turnMotor.getPIDController().setD(SmartDashboard.getNumber("TURRET_D",0));
        turnMotor.getPIDController().setIZone(SmartDashboard.getNumber("SHOOTER_IZ",0));
        turnMotor.getPIDController().setSmartMotionMaxVelocity(SmartDashboard.getNumber("SHOOTER_MV",0),0);
        turnMotor.getPIDController().setSmartMotionMaxAccel(SmartDashboard.getNumber("SHOOTER_MA",0),0);
    }

    public void NTInit() {
        SmartDashboard.putNumber("TURRET_P",Constants.Subsystem.Turret.TURRET_P); 
        SmartDashboard.putNumber("TURRET_I",Constants.Subsystem.Turret.TURRET_I);  
        SmartDashboard.putNumber("TURRET_D",Constants.Subsystem.Turret.TURRET_D);  
        SmartDashboard.putNumber("TURRET_IZ",Constants.Subsystem.Turret.TURRET_IZ); 
        SmartDashboard.putNumber("TURRET_FF",Constants.Subsystem.Turret.TURRET_FF); 
        SmartDashboard.putNumber("TURRET_MV",Constants.Subsystem.Turret.TURRET_MV); 
        SmartDashboard.putNumber("TURRET_MA",Constants.Subsystem.Turret.TURRET_MA); 
    }
}
