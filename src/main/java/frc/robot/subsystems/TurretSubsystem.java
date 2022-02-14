package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.Constants;
import frc.robot.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {
	public final CANSparkMax traverseMotor;

	public TurretSubsystem(){
		traverseMotor = new CANSparkMax(Motors.TURRET_TRAVERSE, CANSparkMaxLowLevel.MotorType.kBrushless);
		traverseMotor.setSmartCurrentLimit(20);
	}
	@Override
	public void periodic(){
	}
	public void runMotor(double speed){
		traverseMotor.set(speed);
	}
	public void stop(){
		traverseMotor.set(0);
	 }
}
