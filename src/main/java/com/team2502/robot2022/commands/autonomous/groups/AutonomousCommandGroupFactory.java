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
				new SuicideBurnCommand(d, 12*10, 1, .8, 1.4),
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
				sequence(
					deadline(
						new WaitCommand(1.75),
						new TraverseCommand(t, Constants.Subsystem.Turret.CENTER) // center turret
					),
					new VisionAlignTurret(v, t)
				),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.04))
				),
				deadline(
				new WaitCommand(4.5),
				new VisionAlignTurret(v, t),
                sequence(
                    deadline( // reverse for quarter second to clear flywheel
                        new WaitCommand(0.25),
						new ShootCommand(s, i, -0.6, 0, -0.35, false)
                        ),
                    new SmartShootCommand(s, i, 0.35, 0, 0.35, false)
                ),
                new RunShooterCommand(s, v, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.04D),false) // Shoot at known distance if not found
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
						new TimeLeftCommand(5), // wait until near end of auto
						new RunIntakeCommand(i, 0.5, 0.85, true) // intake
					),
                    deadline( // reverse for eighth second to clear flywheel
                        new WaitCommand(0.125),
						new ShootCommand(s, i, -0.6, 0, -0.35, false)
                    ),
					new SmartShootCommand(s, i, 0.35, 0, 0.35, false)
				),
                new RunShooterCommand(s, v, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(20.74D),false) // Shoot at known distance if not found
				)
			)),

	FOUR_BALL_LIMELESS((d,i,v,s,t,p) -> sequence(
				deadline(
				new SuicideBurnCommand(d, 12*10, 1, .8, 1.4),
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
                new TraverseCommand(t, 31.21), // center turret
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(11.04))
				),
				deadline(
				new WaitCommand(4.5),
                sequence(
                    deadline( // reverse for quarter second to clear flywheel
                        new WaitCommand(0.25),
						new ShootCommand(s, i, -0.6, 0, -0.35, false)
                        ),
                    new SmartShootCommand(s, i, 0.35, 0, 0.35, false)
                ),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(11.04))
				),
				deadline(
				new SuicideBurnCommand(d, 12*5, 1, .8, 1.4),
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
                new TraverseCommand(t, 29.57), // align turret
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(17.65))
				),
				deadline(
				new WaitCommand(10),
				sequence(
					deadline(
						new TimeLeftCommand(4), // wait until near end of auto
						new RunIntakeCommand(i, 0.5, 0.85, true) // intake
					),
					new SmartShootCommand(s, i, 0.35, 0, 0.35, false)
				),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(17.65))
				)
			)),


	ONE_BALL_LATE((d,i,v,s,t,p) -> sequence(
                new TimeLeftCommand(8),
				deadline(
				new SuicideBurnCommand(d, 12*10, 1, .8, 1.4),
				sequence(
					deadline(
						new WaitCommand(1.75),
						new TraverseCommand(t, Constants.Subsystem.Turret.CENTER) // center turret
					),
					new VisionAlignTurret(v, t)
				),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.04))
				),
				deadline(
				new WaitCommand(4.5),
				new VisionAlignTurret(v, t),
                sequence(
                    deadline( // reverse for quarter second to clear flywheel
                        new WaitCommand(0.25),
						new ShootCommand(s, i, -0.6, 0, -0.35, false)
                        ),
                    new SmartShootCommand(s, i, 0.35, 0, 0.35, false)
                ),
                new RunShooterCommand(s, v, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.04D),false) // Shoot at known distance if not found
				)
			)),

	ONE_BALL_EARLY((d,i,v,s,t,p) -> sequence(
				deadline(
				new SuicideBurnCommand(d, 12*10, 1, .8, 1.4),
				sequence(
					deadline(
						new WaitCommand(1.75),
						new TraverseCommand(t, Constants.Subsystem.Turret.CENTER) // center turret
					),
					new VisionAlignTurret(v, t)
				),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.04))
				),
				deadline(
				new WaitCommand(4.5),
				new VisionAlignTurret(v, t),
                sequence(
                    deadline( // reverse for quarter second to clear flywheel
                        new WaitCommand(0.25),
						new ShootCommand(s, i, -0.6, 0, -0.35, false)
                        ),
                    new SmartShootCommand(s, i, 0.35, 0, 0.35, false)
                ),
                new RunShooterCommand(s, v, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.04D),false) // Shoot at known distance if not found
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
                new RunShooterCommand(s, v, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(12.74D),false) // Shoot at known distance if not found
				)
                )
            ),

	TWO_BALL_WALL_HIGH((d,i,v,s,t,p) -> sequence(
				deadline(
				new SuicideBurnCommand(d, 12*1.3, 1, .8, 1.4),
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
				sequence(
					deadline(
						new WaitCommand(1.75),
						new TraverseCommand(t, 23) // center turret
					),
					new VisionAlignTurret(v, t)
				),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(7.74))
				),
                parallel( // backup second stage while clearing intake
				new SuicideBurnCommand(d, 12*0.43, 1, .8, 1.4),
                sequence(
                    deadline(
                        new WaitCommand(1.5),
                        new RunIntakeCommand(i, 0.5, 0.85, false) // intake
                    ),
                    deadline( // reverse for quarter second to clear flywheel
                        new WaitCommand(0.125),
                        new ShootCommand(s, i, -0.6, 0, -0.35, false)
                    )
                )
                ),
				deadline(
				new WaitCommand(6),
				new VisionAlignTurret(v, t),
                new SmartShootCommand(s, i, 0.35, 0, 0.225, false),
				new RunShooterAtSpeedCommand(s, 2250)
				)
                )
            ),

	TWO_BALL_WALL((d,i,v,s,t,p) -> sequence(
				deadline(
				new SuicideBurnCommand(d, 12*1.3, 1, .8, 1.4),
				new RunIntakeCommand(i, 0.5, 0.85, true), // intake
				sequence(
					deadline(
						new WaitCommand(1.75),
						new TraverseCommand(t, 23) // center turret
					),
					new VisionAlignTurret(v, t)
				),
				new RunShooterAtSpeedCommand(s, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(7.74))
				),
                deadline(
                    new WaitCommand(1.5),
                    new RunIntakeCommand(i, 0.5, 0.85, false) // intake
                ),
                deadline( // reverse for quarter second to clear flywheel
                    new WaitCommand(0.125),
                    new ShootCommand(s, i, -0.6, 0, -0.35, false)
                ),
				deadline(
				new WaitCommand(4),
				new VisionAlignTurret(v, t),
                new SmartShootCommand(s, i, 0.35, 0, 0.225, false),
                new RunShooterCommand(s, v, Constants.Subsystem.Vision.DIST_TO_RPM_STANDSTILL_TABLE.get(7.74D),false) // Shoot at known distance if not found
				),
				new SuicideBurnCommand(d, 12*0.6, 1, .8, 1.4)
                )
            ),

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
