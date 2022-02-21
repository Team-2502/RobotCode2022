package frc.robote.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import frc.robote.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase{
    private DifferentialDrive drive;

    private WPI_TalonFX drivetrainBackLeft;
    private WPI_TalonFX drivetrainFrontLeft;
    private WPI_TalonFX drivetrainBackRight;
    private WPI_TalonFX drivetrainFrontRight;

    private AHRS navX = new AHRS();

    public DrivetrainSubsystem(){
        drivetrainBackLeft = new WPI_TalonFX(Motors.DRIVE_BACK_LEFT);
        drivetrainFrontLeft = new WPI_TalonFX(Motors.DRIVE_FRONT_LEFT);
        drivetrainFrontRight = new WPI_TalonFX(Motors.DRIVE_FRONT_RIGHT);
        drivetrainBackRight = new WPI_TalonFX(Motors.DRIVE_BACK_RIGHT);

        drivetrainBackLeft.follow(drivetrainFrontLeft);//backleft follows front left motor
        drivetrainBackRight.follow(drivetrainFrontRight);

        drivetrainBackRight.setInverted(TalonFXInvertType.CounterClockwise);
        drivetrainBackLeft.setInverted(TalonFXInvertType.CounterClockwise);
        drivetrainFrontRight.setNeutralMode(NeutralMode.Brake);
        drivetrainFrontLeft.setNeutralMode(NeutralMode.Brake);
        drive = new DifferentialDrive(drivetrainFrontLeft, drivetrainFrontRight);
    }

    public DifferentialDrive getDrive(){
        return drive;
    }

    public void setSpeed(double leftSpeed, double rightSpeed){
        drive.tankDrive(leftSpeed, rightSpeed);
    }
    public void brake(){
        drive.stopMotor();
    }

    /**
    * Average drivetrain motor revs
    * @return double revs since restart
     */
    public double getRevsAvg() {
	    return (
			    -drivetrainFrontRight.getSelectedSensorPosition()+
			    drivetrainFrontLeft.getSelectedSensorPosition()
		   )/2;
    }

    /**
    * inches since init
    * @return inches since initialization
     */
    public double getInchesTraveled() {
	    /*return (getRevsAvg()/2048) // encoder
		    * (24 / 50) // gearbox
		    * (6 * Math.PI); // wheel radius */
	    return drivetrainFrontRight.getSelectedSensorPosition()/1033.29; // magic number :)
    }

    public void setNeutralMode(NeutralMode nm)
    {
        drivetrainFrontRight.setNeutralMode(nm);
        drivetrainFrontLeft.setNeutralMode(nm);
    }

    public double getRpm(){ return (drivetrainFrontLeft.getSelectedSensorVelocity() * 600) / 2048; }

    @Override
    public void periodic(){

        SmartDashboard.putNumber("Angle", navX.getAngle());
        SmartDashboard.putNumber("RPM", getRpm());
    }
}
