// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022;

import com.team2502.robot2022.commands.*;
import com.team2502.robot2022.subsystems.*;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
    protected final DrivetrainSubsystem DRIVETRAIN = new DrivetrainSubsystem();
    protected final IntakeSubsystem INTAKE = new IntakeSubsystem();
    protected final TurretSubsystem TURRET = new TurretSubsystem();
    protected final ShooterSubsystem SHOOTER = new ShooterSubsystem();
    protected final VisionSubsystem VISION = new VisionSubsystem();

    private final AHRS navx = new AHRS();

    private static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(Constants.OI.JOYSTICK_DRIVE_RIGHT);
    private static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(Constants.OI.JOYSTICK_DRIVE_LEFT);
    private static final Joystick JOYSTICK_OPERATOR = new Joystick(Constants.OI.JOYSTICK_OPERATOR);


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        DRIVETRAIN.setDefaultCommand(new DriveCommand(DRIVETRAIN, JOYSTICK_DRIVE_LEFT, JOYSTICK_DRIVE_RIGHT));
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
        JoystickButton RunIntakeButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_INTAKE_BUTTON);
        RunIntakeButton.whenHeld(new RunIntakeCommand(INTAKE, 0.5, 0.85));

        JoystickButton RunIntakeBackwardsButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_INTAKE_BACKWARDS_BUTTON);
        //RunIntakeBackwardsButton.whenHeld(new RunIntakeCommand(INTAKE, -0.5, -0.85));
        RunIntakeBackwardsButton.whenHeld(new ShootCommand(SHOOTER, INTAKE, -0.6, -0.5, -0.85));

        JoystickButton RunShooterManualButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_FLYWHEEL_MANUAL);
        RunShooterManualButton.whenHeld(new RunShooterManualCommand(SHOOTER, TURRET, Constants.Subsystem.Shooter.SHOOTER_MANUAL_RPM_MID, JOYSTICK_OPERATOR)); // use lever from operator joystick

        JoystickButton ShootButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.SHOOT_BUTTON);
        ShootButton.whenHeld(new ShootCommand(SHOOTER, INTAKE, 0.5, 0, 0.85));

        JoystickButton VisionAlignDrivetrainButton = new JoystickButton(JOYSTICK_DRIVE_LEFT, Constants.OI.VISION_DRIVETRAIN_ALIGN);
        VisionAlignDrivetrainButton.whenHeld(new VisionAlignDrivetrain(VISION, DRIVETRAIN, JOYSTICK_DRIVE_LEFT, JOYSTICK_DRIVE_RIGHT));

        JoystickButton VisionAlignTurretButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.VISION_TURRET_ALIGN);
        VisionAlignTurretButton.whenHeld(new VisionAlignTurret(VISION, TURRET));

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
        return new CommandBase(){};
    }
}
