package frc.robot.commands;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.subsystems.DrivetrainSubsystem;

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

        typeEntry.addOption("Split Arcade", Drivetype.Arcade);
        typeEntry.addOption("Reverse", Drivetype.Reverse);
        typeEntry.setDefaultOption("Tank", Drivetype.Tank);
        SmartDashboard.putData("Drive Type", typeEntry);

        addRequirements(drivetrain);
    }

    @Override
    public void initialize()
    {
        drivetrain.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void execute() {
        switch(typeEntry.getSelected()) {
            case Tank:
                drivetrain.getDrive().tankDrive(rightJoystick.getY(), -leftJoystick.getY(), true);
                break;
            case Arcade:
                drivetrain.getDrive().arcadeDrive(rightJoystick.getX(), leftJoystick.getY(), true);
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
        Reverse
    }
}
