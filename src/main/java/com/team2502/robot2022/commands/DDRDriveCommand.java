package com.team2502.robot2022.commands;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;
import com.team2502.robot2022.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.team2502.robot2022.util.KonamiHandler;
import com.team2502.robot2022.util.Trapezoidal;
import com.team2502.robot2022.util.KonamiHandler.BUTTONS;

public class DDRDriveCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrain;
    private final Joystick groovyJoystick;
    private final TurretSubsystem turret;
    private Trapezoidal speedTrapezoidal;
    private Trapezoidal rotationTrapezoidal;
    private Trapezoidal turretRotationTrapezoidal;
    private KonamiHandler konamiHandler;
    private double lastInputChasm;

    public DDRDriveCommand(DrivetrainSubsystem drivetrain, TurretSubsystem turret, Joystick groovyJoystick) {
        this.drivetrain = drivetrain;
        this.turret = turret;
        this.groovyJoystick = groovyJoystick;
        this.speedTrapezoidal = new Trapezoidal(2);
        this.rotationTrapezoidal = new Trapezoidal(2);
        this.turretRotationTrapezoidal = new Trapezoidal(0.7);
        this.konamiHandler = new KonamiHandler();
        this.lastInputChasm = Timer.getFPGATimestamp();

        addRequirements(drivetrain);
    }

    @Override
    public void initialize()
    {
        drivetrain.setNeutralMode(NeutralMode.Coast);
        konamiHandler.reset();
    }

    @Override
    public void execute() {
        double speed = 0;
        double rotation = 0;
        double turretRotation = 0;

        double topSpeed = konamiHandler.isFinished() ? 1 : .4;
        double topSpeedTurret = konamiHandler.isFinished() ? 1 : .2;




        if (groovyJoystick.getRawButtonPressed(Constants.OI.DDR_UP)) {
            konamiHandler.handle(BUTTONS.UP);
        }
        if (groovyJoystick.getRawButtonPressed(Constants.OI.DDR_DOWN)) {
            konamiHandler.handle(BUTTONS.DOWN);
        }
        if (groovyJoystick.getRawButtonPressed(Constants.OI.DDR_LEFT)) {
            konamiHandler.handle(BUTTONS.LEFT);
        }
        if (groovyJoystick.getRawButtonPressed(Constants.OI.DDR_RIGHT)) {
            konamiHandler.handle(BUTTONS.RIGHT);
        }
        if (groovyJoystick.getRawButtonPressed(Constants.OI.DDR_TURRET_LEFT)) {
            konamiHandler.handle(BUTTONS.A);
        }
        if (groovyJoystick.getRawButtonPressed(Constants.OI.DDR_TURRET_RIGHT)) {
            konamiHandler.handle(BUTTONS.B);
        }

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

        if (groovyJoystick.getRawButton(Constants.OI.DDR_TURRET_LEFT)) {
            turretRotation = -topSpeedTurret;
        }
        if (groovyJoystick.getRawButton(Constants.OI.DDR_TURRET_RIGHT)) {
            turretRotation = topSpeedTurret;
        }

        if (speed == 0 && rotation == 0) {
            lastInputChasm = Timer.getFPGATimestamp();
        }

        if (Timer.getFPGATimestamp() - lastInputChasm > 1.5) {
            speed = 0;
            rotation = 0;
            turretRotation = 0;
        }
        
        turret.runMotor(-turretRotationTrapezoidal.calculate(turretRotation));
        drivetrain.getDrive().arcadeDrive(rotationTrapezoidal.calculate(rotation), speedTrapezoidal.calculate(speed));
    }
}
