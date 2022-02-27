package com.team2502.robot2022;
import com.team2502.robot2022.commands.TurnAngleCommand;
import com.team2502.robot2022.commands.autonomous.CommandFactory;
import com.team2502.robot2022.commands.autonomous.groups.AutonomousCommandGroupFactory;
import com.team2502.robot2022.commands.autonomous.ingredients.*;
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
        TWO_BALL_SEXY((d,i,v,s,t) -> new SequentialCommandGroup(
				new ParallelRaceGroup( // intake while moving forward
				new RunIntakeCommand(i, 0.5, 0.85, true),
				new DistanceDriveCommand(d, 80.0),
				new WaitCommand(8)
					),
				new ParallelRaceGroup( // align while reversing, then shoot
				new VisionAlignTurret(v, t),
				new RunShooterCommand(s, v, 2502),
				new SequentialCommandGroup (
					new ParallelRaceGroup ( // reverse intake for 1s
						new WaitCommand(1),
						new ShootCommand(s, i, -0.6, -0.5, -0.85, false)
						),
					new ShootCommand(s, i, 0.5, 0, 0.85, false) // shoot
					),
				new DistanceDriveCommand(d, 80.0),
				new WaitCommand(6)
					),
				new WaitCommand(3)
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
