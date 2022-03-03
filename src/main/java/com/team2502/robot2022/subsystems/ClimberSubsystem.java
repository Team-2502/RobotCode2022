package com.team2502.robot2022.subsystems;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.team2502.robot2022.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class  ClimberSubsystem extends SubsystemBase {
    //    private final Solenoid piston_right;
//    private final Solenoid piston_left;
    private final WPI_TalonFX rightClimber;
    private final WPI_TalonFX leftClimber;

    private final Solenoid releaseClimber;

    public ClimberSubsystem(){
        //defines climber motors as Talons and gives correct ID from Constants
//        piston_right = new Solenoid(PneumaticsModuleType.REVPH, RIGHT_PASSIVE_CLIMBER);
//        piston_left = new Solenoid(PneumaticsModuleType.REVPH, LEFT_PASSIVE_CLIMBER);
        rightClimber = new WPI_TalonFX(Constants.RobotMap.Motors.RIGHT_WENCH);
        leftClimber = new WPI_TalonFX(Constants.RobotMap.Motors.LEFT_WENCH);
        releaseClimber = new Solenoid(PneumaticsModuleType.REVPH, Constants.RobotMap.Solenoids.RELEASE_CLIMBER);

        leftClimber.follow(rightClimber);
    }

    @Override
    public void periodic() {
        //every few microseconds will send motor voltage to driverstation display
        SmartDashboard.putNumber("Right voltage", rightClimber.getMotorOutputVoltage());
        SmartDashboard.putNumber("Left voltage", leftClimber.getMotorOutputVoltage());

        SmartDashboard.putBoolean("Climber Solenoid", releaseClimber.get());
    }

    public void runClimber(double speed) {
        //when runClimber command is run will ask for value and set motors speed to said value
        rightClimber.set(speed);
        leftClimber.set(-speed);
    }

    public void stopClimber() {
        //will stop motors when command is run
        rightClimber.set(0);
        leftClimber.set(0);
    }

    public void releaseClimber() {
        //releaseClimber.set(!releaseClimber.get());
        releaseClimber.toggle();
    }


    public void runLeftClimber(double speed) {
        leftClimber.set(speed);
    }

    public void runRightClimber(double speed) {
        rightClimber.set(speed);
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
