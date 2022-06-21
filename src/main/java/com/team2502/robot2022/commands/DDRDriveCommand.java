package com.team2502.robot2022.commands;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.team2502.robot2022.util.Trapezoidal;

public class DDRDriveCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrain;
    private final Joystick groovyJoystick;
    private Trapezoidal speedTrapezoidal;
    private Trapezoidal rotationTrapezoidal;

    public DDRDriveCommand(DrivetrainSubsystem drivetrain, Joystick groovyJoystick) {
        this.drivetrain = drivetrain;
        this.groovyJoystick = groovyJoystick;
        this.speedTrapezoidal = new Trapezoidal(2);
        this.rotationTrapezoidal = new Trapezoidal(2);

        addRequirements(drivetrain);
    }

    @Override
    public void initialize()
    {
        drivetrain.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    public void execute() {
        double speed = 0;
        double rotation = 0;
        final double topSpeed = .4;

        if (groovyJoystick.getRawButton(Constants.OI.DDR_UP)) {
            speed = topSpeed;
        }
        if (groovyJoystick.getRawButton(Constants.OI.DDR_DOWN)) {
            speed = -topSpeed;
        }
        if (groovyJoystick.getRawButton(Constants.OI.DDR_LEFT)) {
            rotation = -topSpeed;
        }
        if (groovyJoystick.getRawButton(Constants.OI.DDR_RIGHT)) {
            rotation = topSpeed;
        }



        
        drivetrain.getDrive().arcadeDrive(rotationTrapezoidal.calculate(rotation), speedTrapezoidal.calculate(speed));
    }
}
