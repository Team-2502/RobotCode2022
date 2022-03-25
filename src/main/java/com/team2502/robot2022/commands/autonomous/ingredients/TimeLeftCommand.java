package com.team2502.robot2022.commands.autonomous.ingredients;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TimeLeftCommand extends CommandBase {
	private double time;

	/**
	* Wait until the specified time is remaining in the current match period, teleop or auto
	* @param time time (seconds) to wait until
	 */
	public TimeLeftCommand(double time) {
		this.time = time;
	}

	@Override
	public boolean isFinished() {
		return Timer.getMatchTime() < time;
	}
}
