// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022;

import com.team2502.robot2022.commands.autonomous.ingredients.DistanceDriveCommand;
import com.team2502.robot2022.commands.autonomous.ingredients.FlakCommand;
import com.team2502.robot2022.commands.autonomous.ingredients.ShootCommand;
import com.team2502.robot2022.commands.autonomous.ingredients.SidewinderCommand;
import com.team2502.robot2022.commands.autonomous.ingredients.SuicideBurnCommand;
import com.team2502.robot2022.commands.autonomous.ingredients.TraverseCommand;
import com.team2502.robot2022.commands.autonomous.ingredients.VisionTrackBallCommand;
import com.team2502.robot2022.Constants.Subsystem.Vision;
import com.team2502.robot2022.commands.*;
import com.team2502.robot2022.commands.solenoid.ShiftCommand;
import com.team2502.robot2022.commands.solenoid.ToggleIntakeCommand;
import com.team2502.robot2022.commands.vision.VisionAlignDrivetrain;
import com.team2502.robot2022.commands.vision.VisionAlignTurret;
import com.team2502.robot2022.subsystems.*;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

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
    protected final PiVisionSubsystem PI_VISION = new PiVisionSubsystem();
    protected final ClimberSubsystem CLIMBER = new ClimberSubsystem();

    //Joysticks are defined here
    private static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(Constants.OI.JOYSTICK_DRIVE_RIGHT);
    private static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(Constants.OI.JOYSTICK_DRIVE_LEFT);
    private static final Joystick JOYSTICK_OPERATOR = new Joystick(Constants.OI.JOYSTICK_OPERATOR);


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        //command here are always running when initialized
        DRIVETRAIN.setDefaultCommand(new DriveCommand(DRIVETRAIN, JOYSTICK_DRIVE_LEFT, JOYSTICK_DRIVE_RIGHT));

        TURRET.setDefaultCommand(new TurnTurretCommand(TURRET, JOYSTICK_OPERATOR));
        // Configure the button bindings
        configureButtonBindings();

        AutoSwitcher.putToSmartDashboard();
        //CameraServer.getInstance().startAutomaticCapture("runcam-output", 0); // rio camera
    }


    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    //commands controlled by buttons are here
    //Buttons are defined here
    private void configureButtonBindings()
    {
        JoystickButton RunIntakeButton = new JoystickButton(JOYSTICK_DRIVE_LEFT, Constants.OI.RUN_INTAKE_DRIVER_BUTTON);
        RunIntakeButton.toggleWhenPressed(new RunIntakeCommand(INTAKE, 0.5, 0.85, true));

        JoystickButton RunIntakeBackwardsButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_INTAKE_BACKWARDS_BUTTON);
        RunIntakeBackwardsButton.whenHeld(new ShootCommand(SHOOTER, INTAKE, -0.6, -0.5, -0.85, false));

        JoystickButton RunIntakeBackwardsDriverButton = new JoystickButton(JOYSTICK_DRIVE_LEFT, Constants.OI.RUN_INTAKE_BACKWARDS_DRIVER_BUTTON);
        RunIntakeBackwardsDriverButton.whenHeld(new RunIntakeCommand(INTAKE, -0.5, -0.85, true));

        JoystickButton RunShooterManualButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_FLYWHEEL_MANUAL);
        RunShooterManualButton.whenHeld(new RunShooterManualCommand(SHOOTER, TURRET, Constants.Subsystem.Shooter.SHOOTER_MANUAL_RPM_MID, JOYSTICK_OPERATOR)); // use lever from operator joystick

        JoystickButton ShootButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.SHOOT_BUTTON);
        ShootButton.whenHeld(new SmartShootCommand(SHOOTER, INTAKE, 0.35, 0, 0.225, false));

        //JoystickButton VisionAlignDrivetrainButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.VISION_DRIVETRAIN_ALIGN);
        //VisionAlignDrivetrainButton.whenHeld(new VisionAlignDrivetrain(VISION, DRIVETRAIN, JOYSTICK_DRIVE_LEFT, JOYSTICK_DRIVE_RIGHT));

        JoystickButton VisionAlignTurretButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.VISION_TURRET_ALIGN);
        VisionAlignTurretButton.whenHeld(new VisionAlignTurret(VISION, TURRET));
        //VisionAlignTurretButton.whenReleased(new VisionUnAlignTurret(VISION, TURRET));

        JoystickButton ShiftButton = new JoystickButton(JOYSTICK_DRIVE_RIGHT, Constants.OI.SHIFT);
        ShiftButton.whenPressed(new ShiftCommand(DRIVETRAIN));

        JoystickButton ToggleIntakeButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.TOGGLE_INTAKE);
        ToggleIntakeButton.whenPressed(new ToggleIntakeCommand(INTAKE));

        JoystickButton RunClimberButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_CLIMBER_WENCH_BUTTON);
        RunClimberButton.whenHeld(new RunClimberCommand(CLIMBER, Constants.Subsystem.Climber.CLIMBER_SPEED));

        JoystickButton RunClimberBackwardsButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_CLIMBER_WENCH_BACKWARDS_BUTTON);
        RunClimberBackwardsButton.whenHeld(new RunClimberCommand(CLIMBER, -Constants.Subsystem.Climber.CLIMBER_SPEED));

        JoystickButton RunShooterButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.SPIN_FLYWHEEL_BUTTON);
        RunShooterButton.whenHeld(new RunShooterCommand(SHOOTER, VISION, Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(0D),false)); // Shoot for 0 distance if not found

        JoystickButton RunShooterAdjButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.SPIN_FLYWHEEL_JUICED_BUTTON);
        RunShooterAdjButton.whenHeld(new RunShooterTrimmedCommand(SHOOTER, VISION, Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(0D), JOYSTICK_OPERATOR)); // Shoot for 0 distance if not found

       //JoystickButton ReleaseClimberButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RELEASE_CLIMBER_BUTTON);
      // ReleaseClimberButton.whenPressed(new ReleaseClimberSolenoidCommand(CLIMBER));

        JoystickButton CenterTurretButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.CENTER_TURRET);
        CenterTurretButton
		.whenHeld(new TraverseCommand(TURRET, Constants.Subsystem.Turret.CENTER))
		.whileHeld(new RunShooterAtSpeedCommand(SHOOTER, Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(0D)));

//        JoystickButton runWinch1 = new JoyStickButton(JOYSTICK_OPERATOR, 18);
//        runWinch1.whenHeld(new RunSingleWenchCommand(CLIMBER, 0, -0.3));
//
//        JoystickButton runWinch2 = new JoyStickButton(JOYSTICK_OPERATOR, 17);
//        runWinch1.whenHeld(new RunSingleWenchCommand(CLIMBER, 1, 0.3));
//
//        JoystickButton runWench1Back = new JoyStickButton(JOYSTICK_OPERATOR, 16);
//        runWench1.whenHeld(new RunSingleWenchCommand(CLIMBER, 0, 0.3));
//
//        JoystickButton runWench2Back = new JoyStickButton(JOYSTICK_OPERATOR, 15);
//        runWench1.whenHeld(new RunSingleWenchCommand(CLIMBER, 1, -0.3));


        // Add button to command mappings here.
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
	JoystickButton runWinchLeftForward = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_WINCH_LEFT_FORWARD);
	runWinchLeftForward.whileHeld(new RunSingleWinchCommand(CLIMBER,Constants.Subsystem.Climber.CLIMBER_SPEED, RunSingleWinchCommand.Winch.LEFT));

	JoystickButton runWinchLeftBackward = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_WINCH_LEFT_BACKWARD);
	runWinchLeftBackward.whileHeld(new RunSingleWinchCommand(CLIMBER,-Constants.Subsystem.Climber.CLIMBER_SPEED, RunSingleWinchCommand.Winch.LEFT));

	JoystickButton runWinchRightForward = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_WINCH_RIGHT_FORWARD);
	runWinchRightForward.whileHeld(new RunSingleWinchCommand(CLIMBER,-Constants.Subsystem.Climber.CLIMBER_SPEED, RunSingleWinchCommand.Winch.RIGHT));

	JoystickButton runWinchRightBackward = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_WINCH_RIGHT_BACKWARD);
	runWinchRightBackward.whileHeld(new RunSingleWinchCommand(CLIMBER,Constants.Subsystem.Climber.CLIMBER_SPEED, RunSingleWinchCommand.Winch.RIGHT));
	JoystickButton runShooterNTButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.RUN_SHOOTER_NT_BUTTON);
	runShooterNTButton.whenHeld(new RunShooterNTCommand(SHOOTER));

	//JoystickButton goTwoFeet = new JoystickButton(JOYSTICK_DRIVE_RIGHT, 4);
	//goTwoFeet.whenPressed(new DistanceDriveCommand(DRIVETRAIN, 12*8));

	JoystickButton falcon9 = new JoystickButton(JOYSTICK_DRIVE_RIGHT, 3);
	falcon9.whenPressed(new SuicideBurnCommand(DRIVETRAIN, 12*2, 1, .8, 1.4));

	JoystickButton missile = new JoystickButton(JOYSTICK_DRIVE_RIGHT, 2);
	missile.whileHeld(new SidewinderCommand(PI_VISION, DRIVETRAIN, INTAKE, 10*12));

	JoystickButton toggleClimber = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.TOGGLE_CLIMBER);
	toggleClimber.whenPressed(new ReleaseClimberSolenoidCommand(CLIMBER))
        .whileHeld(new TraverseCommand(TURRET, Constants.Subsystem.Turret.CENTER)); // center turret while climber up

    JoystickButton resetClimber = new JoystickButton(JOYSTICK_DRIVE_RIGHT, Constants.OI.CLIMBER_RESET_ENCODER);
    resetClimber.whenPressed(new InstantCommand(CLIMBER::resetClimber, CLIMBER));

    JoystickButton centerClimber = new JoystickButton(JOYSTICK_DRIVE_RIGHT, Constants.OI.CLIMBER_CENTER);
    centerClimber.whenPressed(new RunClimberDistanceCommand(CLIMBER, 15D));

    JoystickButton retractClimber = new JoystickButton(JOYSTICK_DRIVE_RIGHT, Constants.OI.CLIMBER_RETRACT);
    retractClimber.whenPressed(new RunClimberDistanceCommand(CLIMBER, 0D));

    JoystickButton extendClimber = new JoystickButton(JOYSTICK_DRIVE_RIGHT, Constants.OI.CLIMBER_EXTEND);
    extendClimber.whenPressed(new RunClimberDistanceCommand(CLIMBER, Constants.Subsystem.Climber.CLIMBER_TRAVEL));
    }

    // 13.47

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // An ExampleCommand will run in autonomous
        return AutoSwitcher.getAutoInstance().getInstance(
                DRIVETRAIN,
                INTAKE,
                VISION,
                SHOOTER,
                TURRET,
                PI_VISION
        );
    }
}
