// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import com.team2502.robot2022.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import com.team2502.robot2022.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import com.kauailabs.navx.frc.AHRS;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    
    private final ExampleCommand autoCommand = new ExampleCommand(exampleSubsystem);

    protected final DrivetrainSubsystem DRIVE_TRAIN = new DrivetrainSubsystem();
    private final HopperSubsystem HOPPER = new HopperSubsystem();
    private final IntakeSubsystem INTAKE = new IntakeSubsystem();

    protected final ShooterSubsystem SHOOTER = new ShooterSubsystem();

    protected final TurretSubsystem TURRET = new TurretSubsystem();

    private final AHRS navx = new AHRS();

    private static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(Constants.OI.JOYSTICK_DRIVE_RIGHT);
    private static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(Constants.OI.JOYSTICK_DRIVE_LEFT);
  private static final Joystick JOYSTICK_OPERATOR = new Joystick(Constants.OI.JOYSTICK_OPERATOR);
    
    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {

      DRIVE_TRAIN.setDefaultCommand(new DriveCommand(DRIVE_TRAIN, JOYSTICK_DRIVE_LEFT, JOYSTICK_DRIVE_RIGHT));
        // Configure the button bindings
        configureButtonBindings();
    }
    
    
    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings()
    {
	    JoystickButton GoAFootButton = new JoystickButton(JOYSTICK_DRIVE_RIGHT, 1);
	    GoAFootButton.whenPressed(new GoCommand(DRIVE_TRAIN,12));

	    JoystickButton TurnAngleButton = new JoystickButton(JOYSTICK_DRIVE_LEFT, 1);
	    TurnAngleButton.whenPressed(new TurnAngleCommand(DRIVE_TRAIN, 90, navx));

            JoystickButton RunShooterManualButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_FLYWHEEL_MANUAL);
	    RunShooterManualButton.whenHeld(new RunShooterManualCommand(SHOOTER, TURRET, Constants.Subsystem.Shooter.SHOOTER_MANUAL_RPM_MID, JOYSTICK_OPERATOR)); // use lever from operator joystick
        // Add button to command mappings here.
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
    }
    
    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // An ExampleCommand will run in autonomous
        return autoCommand;
    }
}
