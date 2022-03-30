package com.team2502.robot2022.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.team2502.robot2022.Constants;
import com.team2502.robot2022.Constants.Subsystem.Climber;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class  ClimberSubsystem extends SubsystemBase {
    //    private final Solenoid piston_right;
//    private final Solenoid piston_left;
    private final WPI_TalonFX rightClimber;
    private final WPI_TalonFX leftClimber;

    private final DigitalInput leftLimit;

    private final Solenoid releaseClimber;

    public ClimberSubsystem(){
        //defines climber motors as Talons and gives correct ID from Constants
//        piston_right = new Solenoid(PneumaticsModuleType.REVPH, RIGHT_PASSIVE_CLIMBER);
//        piston_left = new Solenoid(PneumaticsModuleType.REVPH, LEFT_PASSIVE_CLIMBER);
        rightClimber = new WPI_TalonFX(Constants.RobotMap.Motors.RIGHT_WENCH);
        leftClimber = new WPI_TalonFX(Constants.RobotMap.Motors.LEFT_WENCH);
        releaseClimber = new Solenoid(PneumaticsModuleType.REVPH, Constants.RobotMap.Solenoids.RELEASE_CLIMBER);

	leftLimit = new DigitalInput(Constants.RobotMap.Sensors.CLIMBER_LIMIT_LEFT);

        leftClimber.follow(rightClimber);
    }

    @Override
    public void periodic() {
        //every few microseconds will send motor voltage to driverstation display
        SmartDashboard.putNumber("Right voltage", rightClimber.getMotorOutputVoltage());
        SmartDashboard.putNumber("Left voltage", leftClimber.getMotorOutputVoltage());

        SmartDashboard.putBoolean("Climber Solenoid", releaseClimber.get());

        SmartDashboard.putBoolean("Climber limit", getLimitLeft());

        SmartDashboard.putNumber("Right Distance", rightClimber.getSelectedSensorPosition());
        SmartDashboard.putNumber("Left Distance", leftClimber.getSelectedSensorPosition());
    }

    /**
     * Reset climber encoders
     * */
    public void resetClimber() {
        leftClimber.setSelectedSensorPosition(0);
        rightClimber.setSelectedSensorPosition(0);
    }

    /**
     * go to given distance (0-31)
     * */
    public void setWinchInches(double distanceInches) {
        double distanceCounts = distanceInches * Climber.CLIMBER_TICS_PER_INCH;
        distanceCounts = Math.min(distanceCounts, Climber.CLIMBER_MAX_ENCODER);
        leftClimber.set(ControlMode.Position, distanceCounts);
        rightClimber.set(ControlMode.Position, -distanceCounts);
    }

    /**
     * go to given distance (0-31), with the given feed forward
     * @param distanceInches goal position
     * @param arbFF feed forward value, added after control loop
     */
    public void setWinchInchesWFF(double distanceInches, double arbFF) {
        double distanceCounts = distanceInches * Climber.CLIMBER_TICS_PER_INCH;
        distanceCounts = Math.min(distanceCounts, Climber.CLIMBER_MAX_ENCODER);
        leftClimber.set(ControlMode.Position, distanceCounts, DemandType.ArbitraryFeedForward, arbFF);
        rightClimber.set(ControlMode.Position, -distanceCounts, DemandType.ArbitraryFeedForward, -arbFF);
    }

    /**
     * talon pid status
     * @return is the talon pid within the given constraints
     */
    public boolean atSetpoint() {
        return (
                leftClimber.getClosedLoopError() < Climber.CLIMBER_ERROR
                &&
                rightClimber.getClosedLoopError() < Climber.CLIMBER_ERROR
               );
    }

    public void runClimber(double speed) {
        //when runClimber command is run will ask for value and set motors speed to said value
        rightClimber.set(ControlMode.PercentOutput, speed);
        leftClimber.set(ControlMode.PercentOutput, -speed);
    }

    public void stopClimber() {
        //will stop motors when command is run
        rightClimber.set(ControlMode.PercentOutput, 0);
        leftClimber.set(ControlMode.PercentOutput, 0);
    }

    public void releaseClimber() {
        //releaseClimber.set(!releaseClimber.get());
        releaseClimber.toggle();
    }


    public void runLeftClimber(double speed) {
        leftClimber.set(ControlMode.PercentOutput, speed);
    }

    public void runRightClimber(double speed) {
        rightClimber.set(ControlMode.PercentOutput, speed);
    }

    /**
    * Gets left limit switch status
    * normally false
    * @return left limit switch status
     */
    public boolean getLimitLeft() {

	    return leftLimit.get();
	    
    }

//    public void retractSolenoid() {
//        piston_right.set(true);
//        piston_left.set(true);
//    }
//
//    public void deploySolenoid() {
//        piston_right.set(false);
//        piston_left.set(false);
//    }
//
//    public boolean isRightPistonRetracted() { return piston_right.get(); }
//    public boolean isLeftPistonRetracted() { return piston_left.get(); }
}
