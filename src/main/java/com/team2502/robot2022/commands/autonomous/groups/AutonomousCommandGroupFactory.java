package com.team2502.robot2022.commands.autonomous.groups;

import com.team2502.robot2022.Constants;
import com.team2502.robot2022.commands.*;
import com.team2502.robot2022.commands.vision.*;
import com.team2502.robot2022.commands.autonomous.CommandFactory;
import com.team2502.robot2022.commands.autonomous.ingredients.*;
import com.team2502.robot2022.subsystems.PiVisionSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import static edu.wpi.first.wpilibj2.command.CommandGroupBase.*;

/**
 * class for autonomous command groups
 * put new groups before the do nothing group
 * */
public enum AutonomousCommandGroupFactory { // first auto is default
	FOUR_BALL((d,i,v,s,t,p) -> sequence(
				deadline(
				new SuicideBurnCommand(d, 12*8, 1, .8, 1.4),
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
				sequence(
					deadline(
						new WaitCommand(1.75),
						new TraverseCommand(t, Constants.Subsystem.Turret.CENTER) // center turret
					),
					new VisionAlignTurret(v, t)
				),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.74))
				),
				deadline(
				new WaitCommand(4),
				new VisionAlignTurret(v, t),
                sequence(
                    deadline( // reverse for eighth second to clear flywheel
                        new WaitCommand(0.125),
						new ShootCommand(s, i, -0.6, 0, -0.35, false)
                        ),
                    new SmartShootCommand(s, i, 0.35, 0, 0.35, false)
                ),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.74))
				),
				deadline(
				new SuicideBurnCommand(d, 12*5, 1, .8, 1.4),
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
				new VisionAlignTurret(v, t),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(19.76))
				),
				deadline(
				new WaitCommand(10),
				new VisionAlignTurret(v, t),
				sequence(
					deadline(
						new TimeLeftCommand(3.5), // wait until near end of auto
						new RunIntakeCommand(i, 0.5, 0.85, true) // intake
					),
					new SmartShootCommand(s, i, 0.35, 0, 0.35, false)
				),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(20.76))
				)
			)),

	TWO_BALL_HANGAR((d,i,v,s,t,p) -> sequence(
				deadline(
				new SuicideBurnCommand(d, 12*8, 1, .8, 1.4),
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
				sequence(
					deadline(
						new WaitCommand(1.75),
						new TraverseCommand(t, Constants.Subsystem.Turret.CENTER) // center turret
					),
					new VisionAlignTurret(v, t)
				),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.74))
				),
				deadline(
				new WaitCommand(4),
				new VisionAlignTurret(v, t),
				new SmartShootCommand(s, i, 0.35, 0, 0.225, false),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.74))
				)
                )
            ),

        THREE_BALL((d,i,v,s,t,p) -> new SequentialCommandGroup(
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
				new WaitCommand(11) // shoot for 6s before stopping
					),
				new FreezeCommand(v,i,d,t,s),
				new SpinFlywheelCommand(s, 0) // stop flywheel
			)),

        TWO_BALL((d,i,v,s,t,p) -> new SequentialCommandGroup(
				new ParallelRaceGroup( // intake while moving forward
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
				new DistanceDriveCommand(d, 37.0), // move to ball
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

        TAXI((d,i,v,s,t,p) -> new SequentialCommandGroup( // leaves the hangar
				new WaitCommand(10), // wait for alliance members
				new ParallelRaceGroup( //
				new DistanceDriveCommand(d, 45.0), // move off line
				new TraverseCommand(t, Constants.Subsystem.Turret.CENTER,false), // center turret
				new WaitCommand(4) // sanity check
					)
			)),

		GOTOBALL((d,i,v,s,t,p) -> new SequentialCommandGroup( // leaves the hangar
				new WaitCommand(1), // wait for alliance members
                new DriveToBallCommand(d,p,i,1) // DRIVES TO BALL
		)),

        DO_NOTHING("Do Nothing", ((d,i,v,s,t,p) -> new DoNothingCommand())); // always put last

        AutonomousCommandGroupFactory(String name, CommandFactory commandFactory)
        {
            this.commandFactory = commandFactory;
            this.name = name;
        }

        AutonomousCommandGroupFactory(CommandFactory commandFactory) {
            this.commandFactory = commandFactory;
            this.name = name().replace('_', ' ').toLowerCase();
        }
        public final CommandFactory commandFactory;
        public final String name;
}
