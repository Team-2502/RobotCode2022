package com.team2502.robot2022.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.team2502.robot2022.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class  ClimberSubsystem extends SubsystemBase {
    //    private final Solenoid piston_right;
//    private final Solenoid piston_left;
    private final WPI_TalonFX motor_right;
    private final WPI_TalonFX motor_left;

    public ClimberSubsystem(){
//        piston_right = new Solenoid(PneumaticsModuleType.REVPH, RIGHT_PASSIVE_CLIMBER);
//        piston_left = new Solenoid(PneumaticsModuleType.REVPH, LEFT_PASSIVE_CLIMBER);
        motor_right = new WPI_TalonFX(Constants.RobotMap.Motors.RIGHT_WENCH);
        motor_left = new WPI_TalonFX(Constants.RobotMap.Motors.LEFT_WENCH);
    }

    public void runClimber(double speed) {
        motor_right.set(speed);
        motor_left.set(speed);
    }

    public void stopClimber() {
        motor_right.set(0);
        motor_left.set(0);
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
