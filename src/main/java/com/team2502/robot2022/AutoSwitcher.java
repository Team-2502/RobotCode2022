package com.team2502.robot2022;
import com.team2502.robot2022.commands.TurnAngleCommand;
import com.team2502.robot2022.commands.autonomous.CommandFactory;
import com.team2502.robot2022.commands.autonomous.groups.AutonomousCommandGroupFactory;
import com.team2502.robot2022.commands.autonomous.ingredients.*;
import com.team2502.robot2022.commands.autonomous.ingredients.FreezeCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import com.team2502.robot2022.commands.*;
import com.team2502.robot2022.commands.vision.*;

public class AutoSwitcher {
    /**
     * The actual sendable containing the autonomi
     */
    private static SendableChooser<AutoMode> autoChooser;

    /**
     * Initialize AutoStartLocationSwitcher#autoChooser, put the enum values in it, and put it on the dashboard
     */
    static void putToSmartDashboard()
    {
        autoChooser = new SendableChooser<>();

        for (int i = 0; i < AutoMode.values().length; i++) {
            AutoMode mode = AutoMode.values()[i];
            if(i == 0) {
                autoChooser.setDefaultOption(mode.name, mode);
            }
            else {
                autoChooser.addOption(mode.name, mode);
            }
        }

        SmartDashboard.putData("Autonomous Chooser", autoChooser);
    }

    /**
     * Get an instance of the autonomous selected
     *
     * @return A new instance of the selected autonomous
     */
    static CommandFactory getAutoInstance() { return autoChooser.getSelected().getCommandFactory(); }

    /**
     * An enum containing all the autonomi the drivers can select from
     */
    public enum AutoMode
    {
        //TEST_TURN((d,i,v,s,t) -> new TurnToAngleCommand(d, 179D)), /// divst subby
        TEST_FRICTION((d,i,v,s,t) -> new SequentialCommandGroup(new VoltageDriveCommand(d, -0.29, 0.29))),
        TAXI((d,i,v,s,t) -> new SequentialCommandGroup( // leaves the hangar
				new WaitCommand(10), // wait for alliance members
				new ParallelRaceGroup( //
				new DistanceDriveCommand(d, 45.0), // move off line
				new TraverseCommand(t, Constants.Subsystem.Turret.CENTER), // center turret
				new WaitCommand(4) // sanity check
					)
			)),
        TWO_BALL((d,i,v,s,t) -> new SequentialCommandGroup(
				new ParallelRaceGroup( // intake while moving forward
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
				new DistanceDriveCommand(d, 77.0), // move to ball
				new TraverseCommand(t, Constants.Subsystem.Turret.CENTER), // center turret

				new WaitCommand(4)
					),
				new ParallelRaceGroup( // align, then shoot
				new VisionAlignTurret(v, t),
				new SpinFlywheelCommand(s, 3030), // ~11ft on lookup table
				new SequentialCommandGroup (
					new ParallelRaceGroup ( // reverse intake for 1s to unjam flywheel
						new WaitCommand(.2),
						new ShootCommand(s, i, -0.6, -0.5, -0.85, false)
						),
					new WaitCommand(1.8), // spool up
					new ShootCommand(s, i, 0.2, 0, 0.45, false) // shoot
					),
				new WaitCommand(8) // shoot for 6s before stopping
					),
				new FreezeCommand(v,i,d,t,s),
				new SpinFlywheelCommand(s, 0) // stop flywheel
			)),
        THREE_BALL((d,i,v,s,t) -> new SequentialCommandGroup(
				new ParallelRaceGroup( // intake while moving forward
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
				new DistanceDriveCommand(d, 77.0), // move to ball
				new TraverseCommand(t, Constants.Subsystem.Turret.CENTER), // center turret
				new WaitCommand(4)
					),
				new ParallelRaceGroup( // align, then shoot
				new VisionAlignTurret(v, t),
				new SpinFlywheelCommand(s, 3030), // ~11ft on lookup table
				new SequentialCommandGroup (
					new ParallelRaceGroup ( // reverse intake for 1s to unjam flywheel
						new WaitCommand(.2),
						new ShootCommand(s, i, -0.6, -0.5, -0.85, true)
						),
					new WaitCommand(1.8), // spool up
					new ShootCommand(s, i, 0.2, 0.85, 0.45, true) // shoot
					),
				new WaitCommand(14) // shoot for 6s before stopping
					),
				new FreezeCommand(v,i,d,t,s),
				new SpinFlywheelCommand(s, 0) // stop flywheel
			)),
        DO_NOTHING("Do Nothing", DoNothingCommand::new); // always put last

        /**
         * A lambda that creates a new instance of the command
         */
        private final CommandFactory commandFactory;

        /**
         * The name of the command to display on the driver station
         */
        private final String name;

        /**
         * Make a new auto mode that can be selected from
         *
         * @param name           The name of the command
         * @param commandFactory A lambda that can create a new command (usually method reference to constructor)
         */
        AutoMode(String name, CommandFactory commandFactory)
        {
            this.commandFactory = commandFactory;
            this.name = name;
        }

        AutoMode(CommandFactory commandFactory) {
            this.commandFactory = commandFactory;
            this.name = name().replace('_', ' ').toLowerCase();
        }

        AutoMode(String name, SimpleCommandFactory sCf) {
            this(name, simpleToFull(sCf));
        }

        /**
         * @return A new instance of the command (generally runs constructor)
         */
        public CommandFactory getCommandFactory()
        {
            return commandFactory;
        }
    }

    @FunctionalInterface
    private interface SimpleCommandFactory
    {
        CommandBase getInstance();
    }

    private static CommandFactory simpleToFull(SimpleCommandFactory sCF) {
        return (d,i,v,s,t) -> sCF.getInstance();
    }
}
