package com.team2502.robot2022.commands.autonomous.ingredients;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DoNothingCommand extends CommandBase
{
    public DoNothingCommand() {}
    @Override
    public boolean isFinished()
    {
        return true;
    }
}
