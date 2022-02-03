package com.team2502.robot2022.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.team2502.robot2022.Constants.ClimberVars.PASSIVE_CLIMBER;
import static com.team2502.robot2022.Constants.ClimberVars.WENCH;

public class ClimberSubsystem extends SubsystemBase {
    private final Solenoid climberSolenoid;
    private final WPI_TalonSRX motor;

    public ClimberSubsystem(){
        climberSolenoid = new Solenoid(PASSIVE_CLIMBER);
        motor = new WPI_TalonSRX(WENCH);
    }

    public void runClimber(double speed) { motor.set(speed); }

    public void stopClimber() { motor.set(0);}

    public void retractSolenoid() { climberSolenoid.set(true); }

    public void deploySolenoid() {
        climberSolenoid.set(false);
    }

    public boolean isRetracted() { return climberSolenoid.get(); }

}
