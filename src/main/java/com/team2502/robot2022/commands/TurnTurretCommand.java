package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class TurnTurretCommand extends CommandBase {

    TurretSubsystem turret;
    Joystick operator;

    public TurnTurretCommand(TurretSubsystem turret, Joystick operator){
        this.turret = turret;
        this.operator = operator;

        addRequirements(turret);
    }

    @Override
    public void execute() {
        turret.runMotor(operator.getTwist());
    }
}
