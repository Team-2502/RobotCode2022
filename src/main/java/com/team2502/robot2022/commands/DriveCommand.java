package com.team2502.robot2022.commands;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveCommand extends CommandBase {

    private final SendableChooser<Drivetype> typeEntry = new SendableChooser<>();

    private final DrivetrainSubsystem drivetrain;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;

    public DriveCommand(DrivetrainSubsystem drivetrain, Joystick joystickDriveLeft, Joystick joystickDriveRight) {
        this.drivetrain = drivetrain;
        leftJoystick = joystickDriveLeft;
        rightJoystick = joystickDriveRight;

        // typeEntry.addOption("Reverse", Drivetype.Reverse);
        typeEntry.addOption("Crowd-safe Arcade", Drivetype.KidzArcade);
        typeEntry.setOption("Split Arcade", Drivetype.Arcade);
        typeEntry.addDefaultOption("Tank", Drivetype.Tank);
        SmartDashboard.putData("Drive Type", typeEntry);

        addRequirements(drivetrain);
    }

    @Override
    public void initialize()
    {
        drivetrain.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    public void execute() {
        switch(typeEntry.getSelected()) {
            case Tank:
                drivetrain.getDrive().tankDrive(-leftJoystick.getY(), rightJoystick.getY(), true);
                break;
            case Arcade:
                drivetrain.getDrive().arcadeDrive(rightJoystick.getX() * Constants.Subsystem.Drivetrain.TELEOP_TURN_GAIN, -leftJoystick.getY(), true);
                break;
            case KidzArcade:
                drivetrain.getDrive().arcadeDrive(.4* rightJoystick.getX() * Constants.Subsystem.Drivetrain.TELEOP_TURN_GAIN,.4 * -leftJoystick.getY(), true);
                break;
            case Reverse:
                drivetrain.getDrive().tankDrive(leftJoystick.getY(), rightJoystick.getY(), true);
        }
        SmartDashboard.putNumber("distance traveled", drivetrain.getInchesTraveled());
        SmartDashboard.putNumber("tics traveled", drivetrain.getRevsAvg());
    }

    private enum Drivetype {
        Tank,
        Arcade,
        KidzArcade,
        Reverse
    }
}
